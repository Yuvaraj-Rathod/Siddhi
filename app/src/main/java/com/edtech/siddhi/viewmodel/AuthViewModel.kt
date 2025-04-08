package com.edtech.siddhi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edtech.siddhi.model.UserDetail
import com.edtech.siddhi.utils.Validations
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.rpc.context.AttributeContext.Auth
import okhttp3.internal.checkOffsetAndCount

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    // ✅ Temporarily store user data until email is verified
    private var pendingUsername: String? = null
    private var pendingEmail: String? = null
    private var pendingLeetcodeId: String? = null

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        _authState.value = if (auth.currentUser == null) {
            AuthState.UnAuthenticated
        } else {
            AuthState.Authenticated
        }
    }

    fun logIn(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or Password cannot be empty")
            return
        }

        if (!Validations.isValidPassword(password)) {
            _authState.value = AuthState.Error("Follow the instructions for password")
            return
        }

        _authState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.reload()?.addOnSuccessListener {
                        if (user.isEmailVerified) {
                            _authState.value = AuthState.Authenticated
                        } else {
                            _authState.value = AuthState.Error("Please verify your email before logging in.")
                        }
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun SignUp(email: String, password: String, username: String, leetcodeId: String) {
        if (email.isEmpty() || password.isEmpty() || username.isEmpty() || leetcodeId.isEmpty()) {
            _authState.value = AuthState.Error("Enter credentials")
            return
        }

        if (!Validations.isValidPassword(password)) {
            _authState.value = AuthState.Error("Enter a valid password")
            return
        }

        _authState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()?.addOnCompleteListener { verifyTask ->
                        if (verifyTask.isSuccessful) {
                            // ✅ Store the user data temporarily
                            pendingUsername = username
                            pendingEmail = email
                            pendingLeetcodeId = leetcodeId
                            _authState.value = AuthState.EmailVerificationSent
                        } else {
                            _authState.value = AuthState.Error("Please provide a valid email")
                        }
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun reloadAndCheckEmailVerification() {
        val user = auth.currentUser
        user?.reload()?.addOnSuccessListener {
            if (user.isEmailVerified) {
                // ✅ Now write to Firestore using stored values
                val userMap = mapOf(
                    "username" to pendingUsername,
                    "email" to pendingEmail,
                    "leetcodeId" to pendingLeetcodeId,
                    "likedVideos" to listOf<String>(),
                    "watchedVideos" to listOf<String>()
                )

                firestore.collection("users")
                    .document(user.uid)
                    .set(userMap)
                    .addOnSuccessListener {
                        // Clear stored values
                        pendingUsername = null
                        pendingEmail = null
                        pendingLeetcodeId = null

                        _authState.value = AuthState.Authenticated
                    }
                    .addOnFailureListener {
                        _authState.value = AuthState.Error("Failed to save user data")
                    }
            } else {
                _authState.value = AuthState.Error("Email not verified yet.")
            }
        }?.addOnFailureListener {
            _authState.value = AuthState.Error("Failed to reload user.")
        }
    }

    fun resendVerificationEmail() {
        val user = auth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _authState.value = AuthState.EmailVerificationSent
            } else {
                _authState.value = AuthState.Error("Could not resend verification email.")
            }
        }
    }

    fun signOut() {
        auth.signOut()
        _authState.value = AuthState.UnAuthenticated
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object UnAuthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val msg: String) : AuthState()
    object EmailVerificationSent : AuthState()
}
