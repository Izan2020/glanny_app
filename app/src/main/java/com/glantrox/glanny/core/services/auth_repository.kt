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
    // ? Register ( Email and Password )
    // <=========================================================================O

     suspend fun registerUserWithEmailAndPassword(user: User): RegistrationResponse {
      try {
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
              val dbTask = firebaseDb.document(registerResult.user!!.uid).set(userCredential).await()

              return  RegistrationResponse.Success
          } else {
              return  RegistrationResponse.Error("Terjadi Kesalahan Server")
          }

     } catch (e: FirebaseAuthException) {
          Log.d("firebaseAuthReg", "Exception ${e.localizedMessage}")
         throw Exception(e.message)
     }
    }

    // O=========================================================================>
    // ? Sign In ( Email and Password )
    // <=========================================================================O

    suspend fun signInUserWithEmailAndPassword(user: User): LoginResponse {
        try {
            val loginTask = firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
            val loginResult: AuthResult = loginTask.await()
            if(loginResult.user != null) {
                return  LoginResponse.Success
            } else {
                return  LoginResponse.Error("Check your Connection")
            }
        } catch (e: FirebaseAuthException) {
            throw Exception(e.message)
        }
    }

}

