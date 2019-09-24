package net.furkanakdemir.branchsample.mapper

import net.furkanakdemir.branchsample.data.Branch
import net.furkanakdemir.branchsample.data.PlacesResponse
import javax.inject.Inject

class DomainMapper @Inject constructor() : Mapper<PlacesResponse.Response.VenueRaw?, Branch> {
    override fun map(input: PlacesResponse.Response.VenueRaw?): Branch {

        return input?.let {
            with(it) {

                Branch(
                    id.orEmpty(),
                    name.orEmpty(),
                    categories?.first()?.name.orEmpty(),
                    location?.distance.orZero()
                )
            }
        } ?: Branch.default()
    }
}

private fun Int?.orZero(): Int = this ?: 0
