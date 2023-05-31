package com.dashkovskiy.world_skills_medic_app.api

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

data class CodeSendSuccess(
    val message: String
)

data class Token(
    val token: String
)

enum class Gender {
    @SerializedName("Мужской")
    MALE,
    @SerializedName("Женский")
    FEMALE,
    NOT_SPECIFIED
}

//interface for network call to medic api
interface ApiService {

    @POST("api/sendCode")
    suspend fun sendCode(@Header("email") email: String): Response<CodeSendSuccess>

    @POST("api/signin")
    suspend fun signIn(
        @Header("email") email: String,
        @Header("code") code: String
    ): Response<Token>
}