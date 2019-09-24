package net.furkanakdemir.branchsample.data.source

import net.furkanakdemir.branchsample.data.Branch
import net.furkanakdemir.branchsample.data.PlacesResponse
import net.furkanakdemir.branchsample.mapper.ListMapper
import net.furkanakdemir.branchsample.network.BranchService
import javax.inject.Inject

class RemoteBranchDataSource @Inject constructor(
    private val branchService: BranchService,
    private val domainMapper: ListMapper<PlacesResponse.Response.VenueRaw?, Branch>
) : BranchDataSource {
    override suspend fun getBranches(): List<Branch> {
        val remoteBranches: List<PlacesResponse.Response.VenueRaw?> =
            branchService.getBranches(QUERY)?.response?.venues ?: emptyList()
        return domainMapper.map(remoteBranches)
    }

    companion object {
        // Constant because it is a test case
        private const val QUERY: String = "supplementler"
    }
}
