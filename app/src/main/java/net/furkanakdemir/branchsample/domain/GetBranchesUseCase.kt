package net.furkanakdemir.branchsample.domain

import net.furkanakdemir.branchsample.data.Branch
import net.furkanakdemir.branchsample.data.BranchRepository
import net.furkanakdemir.branchsample.result.Result
import javax.inject.Inject

class GetBranchesUseCase @Inject constructor(
    private val branchRepository: BranchRepository
) {

    var location = BranchLocation()

    suspend fun getBranches(): Result<List<Branch>> {
        return if (location.isAssigned()) {
            branchRepository.getBranches(location.toString())
        } else {
            branchRepository.getBranches(LOCATION_ISTANBUL)
        }
    }

    companion object {
        private const val LOCATION_ISTANBUL = "41.1123421,29.0219696"
    }
}
