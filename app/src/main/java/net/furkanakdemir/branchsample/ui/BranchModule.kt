package net.furkanakdemir.branchsample.ui

import dagger.Binds
import dagger.Module
import dagger.Provides
import net.furkanakdemir.branchsample.data.Branch
import net.furkanakdemir.branchsample.data.BranchRepository
import net.furkanakdemir.branchsample.data.PlacesResponse
import net.furkanakdemir.branchsample.data.RealBranchRepository
import net.furkanakdemir.branchsample.data.source.BranchDataSource
import net.furkanakdemir.branchsample.data.source.RemoteBranchDataSource
import net.furkanakdemir.branchsample.mapper.DomainMapper
import net.furkanakdemir.branchsample.mapper.ListMapper
import net.furkanakdemir.branchsample.mapper.Mapper
import net.furkanakdemir.branchsample.mapper.RealListMapper
import net.furkanakdemir.branchsample.network.NetworkModule

@Module(includes = [NetworkModule::class])
abstract class BranchModule {

    @Binds
    abstract fun bindBranchRepository(repository: RealBranchRepository): BranchRepository

    @Binds
    abstract fun bindRemoteBranchDataSource(remoteBranchDataSource: RemoteBranchDataSource): BranchDataSource

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun bindListDomainMapper(mapper: Mapper<PlacesResponse.Response.VenueRaw?, Branch>):
                ListMapper<PlacesResponse.Response.VenueRaw?, Branch> = RealListMapper(mapper)

        @JvmStatic
        @Provides
        fun bindDomainMapper(): Mapper<PlacesResponse.Response.VenueRaw?, Branch> = DomainMapper()

        @JvmStatic
        @Provides
        fun bindViewMapper(): Mapper<Branch, BranchViewItem> =
            ViewMapper()
    }
}
