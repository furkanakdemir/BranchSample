package net.furkanakdemir.branchsample.data


import com.google.gson.annotations.SerializedName

data class PlacesResponse(
    @SerializedName("response") val response: Response?
) {
    data class Response(
        @SerializedName("venues") val venues: List<VenueRaw?>?
    ) {
        data class VenueRaw(
            @SerializedName("beenHere") val beenHere: BeenHere?,
            @SerializedName("categories") val categories: List<Category?>?,
            @SerializedName("hasPerk") val hasPerk: Boolean?,
            @SerializedName("hereNow") val hereNow: HereNow?,
            @SerializedName("id") val id: String?,
            @SerializedName("location") val location: Location?,
            @SerializedName("name") val name: String?,
            @SerializedName("stats") val stats: Stats?,
            @SerializedName("verified") val verified: Boolean?
        ) {
            data class BeenHere(
                @SerializedName("count") val count: Int?,
                @SerializedName("lastCheckinExpiredAt") val lastCheckinExpiredAt: Int?,
                @SerializedName("marked") val marked: Boolean?,
                @SerializedName("unconfirmedCount") val unconfirmedCount: Int?
            )

            data class Category(
                @SerializedName("icon") val icon: Icon?,
                @SerializedName("id") val id: String?,
                @SerializedName("name") val name: String?,
                @SerializedName("pluralName") val pluralName: String?,
                @SerializedName("primary") val primary: Boolean?,
                @SerializedName("shortName") val shortName: String?
            ) {
                data class Icon(
                    @SerializedName("prefix") val prefix: String?,
                    @SerializedName("suffix") val suffix: String?
                )
            }

            data class HereNow(
                @SerializedName("count") val count: Int?,
                @SerializedName("groups") val groups: List<Any?>?,
                @SerializedName("summary") val summary: String?
            )

            data class Location(
                @SerializedName("cc") val cc: String?,
                @SerializedName("country") val country: String?,
                @SerializedName("distance") val distance: Int?,
                @SerializedName("formattedAddress") val formattedAddress: List<String?>?,
                @SerializedName("labeledLatLngs") val labeledLatLngs: List<LabeledLatLng?>?,
                @SerializedName("lat") val lat: Double?,
                @SerializedName("lng") val lng: Double?
            ) {
                data class LabeledLatLng(
                    @SerializedName("label") val label: String?,
                    @SerializedName("lat") val lat: Double?,
                    @SerializedName("lng") val lng: Double?
                )
            }

            data class Stats(
                @SerializedName("checkinsCount") val checkinsCount: Int?,
                @SerializedName("tipCount") val tipCount: Int?,
                @SerializedName("usersCount") val usersCount: Int?,
                @SerializedName("visitsCount") val visitsCount: Int?
            )
        }
    }
}