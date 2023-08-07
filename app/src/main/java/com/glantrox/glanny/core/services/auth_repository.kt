package com.glantrox.glanny.core.services

import android.util.Log
import com.glantrox.glanny.core.models.LoginResponse
import com.glantrox.glanny.core.models.RegistrationResponse
import com.glantrox.glanny.core.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.coroutines.tasks.await


class AuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDb = FirebaseFirestore.getInstance().collection("users")

    // O=========================================================================>
    // ? Authenticate Initialization
    // <=========================================================================O

      fun isLoggedIn(): Boolean {
        val response = firebaseAuth.currentUser?.email
        return response != null
    }

    // O=========================================================================>
    // ? Register ( Email and Password )
    // <=========================================================================O

     suspend fun registerUserWithEmailAndPassword(user: User){
         val registerTask = firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
         val registerResult: AuthResult = registerTask.await()
         if(registerResult.user != null) {
             val userCredential = hashMapOf(
                 "userId" to registerResult.user!!.uid,
                 "name" to user.username,
                 "email" to registerResult.user!!.email,
                 "profilePicture" to "",
                 "aboutMe" to "",
                 "createdAt" to FieldValue.serverTimestamp(),
             )
             firebaseDb.document(registerResult.user!!.uid).set(userCredential).await()
         }

     }

    // O=========================================================================>
    // ? Sign In ( Email and Password )
    // <=========================================================================O

    suspend fun signInUserWithEmailAndPassword(user: User) {
        Log.d("errorLogin1", "Loading...")
        val loginTask = firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
        val loginResult: AuthResult = loginTask.await()
        if(loginResult.user != null) {
            Log.d("errorLogin1", "Success")
            LoginResponse.Success
        } else {
            Log.d("errorLogin1", "Error 1")
            LoginResponse.Error("Check your Connection")
        }
    }

}

