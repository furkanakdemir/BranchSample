package net.furkanakdemir.branchsample.data

import net.furkanakdemir.branchsample.data.source.BranchDataSource
import net.furkanakdemir.branchsample.result.Result
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RealBranchRepository @Inject constructor(
    private val remoteDataSource: BranchDataSource
) : BranchRepository {
    override suspend fun getBranches(): Result<List<Branch>> {
        return try {
            val releases = remoteDataSource.getBranches()
            Result.Success(releases)
        } catch (e: IOException) {
            Result.Error(e)
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }
}
