package com.example.amazonclone.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonclone.model.login.LoginRequest
import com.example.amazonclone.model.login.RegisterRequest
import com.example.amazonclone.model.login.RegisterResponse
import com.example.amazonclone.repository.AmazonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository: AmazonRepository
): ViewModel(){
    val registerResponseLiveData :  LiveData<RegisterResponse> = repository.registerResponse
    fun register(registerRequest: RegisterRequest){
        registerUser(registerRequest)
    }

    private fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            repository.registerUser(registerRequest)
        }
    }

    val loginResponseLiveData :  LiveData<RegisterResponse> = repository.loginResponse

    fun login(loginRequest: LoginRequest){
        loginUser(loginRequest)
    }

    private fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            repository.loginUser(loginRequest)
        }

    }
}