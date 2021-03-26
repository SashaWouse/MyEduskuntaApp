package my.firstaapp.myeduskuntaapp.internet

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import my.firstaapp.myeduskuntaapp.database.VotingInfo
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://users.metropolia.fi/~alexaded/"

// Moshi
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

// Moshi Converter
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface VotingApiService {
    // All API data
    @GET("eduskunta/?action=getall")
    suspend fun getAllVotingRecords(): List<VotingInfo>

    @GET("eduskunta")
    suspend fun voteLike(@Query("id") id:String, @Query("action") a:String="plus")

    @GET("eduskunta")
    suspend fun voteDislike(@Query("id") id: String, @Query("action") a:String="minus")
}

object VotingApi {
    val retrofitService : VotingApiService by lazy {
        retrofit.create(VotingApiService::class.java) }
}