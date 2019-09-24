package net.furkanakdemir.branchsample.mapper

import net.furkanakdemir.branchsample.data.Branch
import net.furkanakdemir.branchsample.data.PlacesResponse
import javax.inject.Inject

@Suppress("ReturnCount", "LongMethod", "ComplexMethod")
class DomainMapper @Inject constructor() : Mapper<PlacesResponse.Response.VenueRaw?, Branch> {
    override fun map(input: PlacesResponse.Response.VenueRaw?): Branch {

        return input?.let {
            Branch.default()
        } ?: Branch.default()


    }
}
