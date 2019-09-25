package net.furkanakdemir.branchsample.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get

import dagger.android.support.DaggerFragment
import net.furkanakdemir.branchsample.ui.BranchViewModel
import net.furkanakdemir.branchsample.ui.MainActivity
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    abstract val layoutId: Int

    abstract val title: String

    protected abstract fun observeViewModel()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var branchViewModel: BranchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setTitle(title)
        branchViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()
    }
}
