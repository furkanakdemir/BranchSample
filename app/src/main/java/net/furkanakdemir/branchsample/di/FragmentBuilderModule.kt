package net.furkanakdemir.branchsample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.furkanakdemir.branchsample.ui.detail.BranchDetailFragment
import net.furkanakdemir.branchsample.ui.list.BranchListFragment

@ExperimentalCoroutinesApi
@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun bindBranchListFragment(): BranchListFragment

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun bindBranchDetailFragment(): BranchDetailFragment
}
