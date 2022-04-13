package com.binar.task_chapter4_badhrulsalman.network

import com.binar.task_chapter4_badhrulsalman.data.models.GetAllCarResponseItem
import com.binar.task_chapter4_badhrulsalman.request.RegisterRequest
import com.binar.task_chapter4_badhrulsalman.response.RegisterResponseItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "https://rent-cars-api.herokuapp.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface CarsApiService {

    @GET("admin/car")
    fun allCar(): Call<List<GetAllCarResponseItem>>

    @POST("admin/auth/register")
    fun registerBody(@Body registerRequest: RegisterRequest): Call<RegisterResponseItem>
}

object CarsApi{

    private val logging: HttpLoggingInterceptor
    get() {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val instance: CarsApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(CarsApiService::class.java)
    }
}