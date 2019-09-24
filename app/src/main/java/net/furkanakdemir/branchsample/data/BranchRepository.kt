package net.furkanakdemir.branchsample.data

import net.furkanakdemir.branchsample.result.Result

interface BranchRepository {
    suspend fun getBranches(latlng: String): Result<List<Branch>>
}
