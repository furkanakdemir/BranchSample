package net.furkanakdemir.branchsample.network

import net.furkanakdemir.branchsample.data.PlacesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BranchService {

    @GET("/v2/venues/search")
    suspend fun getBranches(
        @Query("query") query: String,
        @Query("ll") latlng: String = "41.058648,28.994625"
    ): PlacesResponse?
}
