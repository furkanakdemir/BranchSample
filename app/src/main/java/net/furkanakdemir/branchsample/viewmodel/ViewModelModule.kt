package net.furkanakdemir.branchsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.furkanakdemir.branchsample.ui.BranchViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BranchViewModel::class)
    abstract fun bindMessageViewModel(branchViewModel: BranchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: BranchViewModelFactory): ViewModelProvider.Factory
}
