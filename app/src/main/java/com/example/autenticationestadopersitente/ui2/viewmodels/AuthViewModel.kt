package com.example.autenticationestadopersitente.ui2.viewmodels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.autenticationestadopersitente.ui2.datastore.SessionManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val sessionManager = SessionManager(application)

    val isSessionActive = sessionManager.sessionFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    val userEmail = sessionManager.userEmailFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ""
    )

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                viewModelScope.launch {
                    sessionManager.saveSession(email)
                    onResult(true)
                }
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

    fun register(email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                viewModelScope.launch {
                    sessionManager.saveSession(email)
                    onResult(true)
                }
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

    fun logout() {
        viewModelScope.launch {
            auth.signOut()
            sessionManager.clearSession()
        }
    }
}
