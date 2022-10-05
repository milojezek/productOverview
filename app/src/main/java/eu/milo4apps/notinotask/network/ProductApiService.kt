package eu.milo4apps.notinotask.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://my-json-server.typicode.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ProductApiService {
    @GET("cernfr1993/notino-assignment/db")
    suspend fun getResponse(): Response<NestedJsonModel>
}

object ProductApi {
    val retrofitService : ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java) }
}