package com.edtech.siddhi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edtech.siddhi.api.LeetcodeApiService
import com.edtech.siddhi.api.LeetcodeProfile
import com.edtech.siddhi.api.UserDetail
import com.edtech.siddhi.repository.LeetcodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeetcodeViewModel @Inject constructor(
    private val repository: LeetcodeRepository
) : ViewModel() {

    private val _user = MutableStateFlow<UserDetail?>(null)
    val user : StateFlow<UserDetail?> = _user

    private val _profile = MutableStateFlow<LeetcodeProfile?>(null)
    val profile: StateFlow<LeetcodeProfile?> = _profile

    fun getProfile(username: String) {
        viewModelScope.launch {
            val data = repository.getProfileDetails(username)
            if (data != null) {
                _profile.value = data // ✅ Ensure state is updated
            } else {
                println("LeetcodeViewModel: No Data Found") // ❌ Debugging
            }
        }
    }

    fun getUser(username: String){
        viewModelScope.launch {
            _user.value = repository.getUserDetails(username)
        }
    }
}
