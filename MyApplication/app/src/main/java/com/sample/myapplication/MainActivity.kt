package com.sample.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = retrofit.create(SampleService::class.java)

        val x = service.checkOperation()

        thread {
            val y = x.execute()
            if (y.isSuccessful) {
                println("successful!!")
                println(y.body()?.greeting)
            } else
                println("NOT successful!!")

        }
    }
}

interface SampleService {
    @GET("user")
    fun checkOperation(): Call<Receiver>
}

val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:80/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

data class Receiver(val greeting: String)