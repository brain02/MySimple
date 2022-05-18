package qqq.qqq.api

import io.reactivex.Observable
import qqq.qqq.models.PassengersResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("passenger")
    suspend fun getPassengersData(
        @Query("page") page: Int,
        @Query("size") size: Int = 10
    ): PassengersResponse

    @GET("passenger")
    fun getPassengersRx(
        @Query("page") page: Int,
        @Query("size") size: Int = 10
    ): Observable<PassengersResponse>

    fun getPassengers(
        @Query("page") page: Int,
        @Query("size") size: Int = 10
    ): PassengersResponse

    companion object {

        private const val BASE_URL = "https://api.instantwebtools.net/v1/"

        operator fun invoke(): MyApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MyApi::class.java)
    }
}