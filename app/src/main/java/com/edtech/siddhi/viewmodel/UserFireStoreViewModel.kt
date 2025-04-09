package com.edtech.siddhi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edtech.siddhi.model.UserFireStore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserFireStoreViewModel : ViewModel() {
    private var _userDetails = MutableLiveData<UserFireStore>()
    var userDetails : LiveData<UserFireStore> = _userDetails

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val user = auth.currentUser


    init{
        user?.let {
            db.collection("users").document(it.uid)
                .get()
                .addOnSuccessListener { document ->
                     _userDetails.value = document.toObject(UserFireStore::class.java)
                }
//                .addOnFailureListener {
//
//                }
        }
    }
}