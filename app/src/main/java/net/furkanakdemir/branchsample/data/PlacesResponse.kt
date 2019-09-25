package net.furkanakdemir.branchsample.data


import com.google.gson.annotations.SerializedName

data class PlacesResponse(
    @SerializedName("response") val response: Response?
) {
    data class Response(
        @SerializedName("venues") val venues: List<VenueRaw?>?
    ) {
        data class VenueRaw(
            @SerializedName("id") val id: String?,
            @SerializedName("name") val name: String?,
            @SerializedName("categories") val categories: List<Category?>?,
            @SerializedName("location") val location: Location?
        ) {
            data class Category(
                @SerializedName("id") val id: String?,
                @SerializedName("name") val name: String?
            )

            data class Location(
                @SerializedName("distance") val distance: Int?,
                @SerializedName("formattedAddress") val formattedAddress: List<String?>?,
                @SerializedName("lat") val lat: Double?,
                @SerializedName("lng") val lng: Double?
            )
        }
    }
}
