package my.firstaapp.myeduskuntaapp.internet

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import my.firstaapp.myeduskuntaapp.database.ParlamentData
import my.firstaapp.myeduskuntaapp.database.ParlamentRecords
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// val can be initialized runtime, const not.
// compiler replaces references to const types when compiling
// val are valuated at run time.

// base URL
private const val BASE_URL = "https://avoindata.eduskunta.fi/"

// Moshi
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface ParlamentApiService {
    @GET("api/v1/seating/")
    suspend fun getParlamentRecords(): List<ParlamentData> //ParlamentRecords
}

object ParlamentApi {
    val retrofitService : ParlamentApiService by lazy {
        retrofit.create(ParlamentApiService::class.java) }
}