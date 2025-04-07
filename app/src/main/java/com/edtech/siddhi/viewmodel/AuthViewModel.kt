package com.edtech.siddhi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edtech.siddhi.utils.Validations
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.google.rpc.context.AttributeContext.Auth
import okhttp3.internal.checkOffsetAndCount

class AuthViewModel : ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        if (auth.currentUser == null)
            _authState.value = AuthState.UnAuthenticated
        else
            _authState.value = AuthState.Authenticated
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
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value =
                        AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun SignUp(email : String, password: String,username : String, leetcodeId : String){

        if(email.isEmpty() || password.isEmpty() ||username.isEmpty() || leetcodeId.isEmpty()){
            _authState.value = AuthState.Error("enter credentials")
            return
        }
        if(!Validations.isValidPassword(password)){
            _authState.value = AuthState.Error("Enter a Valid Password")
            return
        }
        _authState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    _authState.value =  AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something Went Wrong")
                }
            }
    }

    fun signOut(){
        auth.signOut()
        _authState.value = AuthState.UnAuthenticated
    }

    fun getCurrentUser() : FirebaseUser? {
        return auth.currentUser
    }
}

sealed class AuthState{
    object Authenticated : AuthState()
    object UnAuthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val msg : String) : AuthState()
    object EmailVerificationSent : AuthState()
}