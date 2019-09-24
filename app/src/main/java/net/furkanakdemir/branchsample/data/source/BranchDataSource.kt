package net.furkanakdemir.branchsample.data.source

import net.furkanakdemir.branchsample.data.Branch

interface BranchDataSource {
    suspend fun getBranches(): List<Branch>
}
