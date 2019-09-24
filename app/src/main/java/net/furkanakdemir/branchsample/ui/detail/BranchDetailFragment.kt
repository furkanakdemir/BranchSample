package net.furkanakdemir.branchsample.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.fragment_branch_detail.*
import net.furkanakdemir.branchsample.R
import net.furkanakdemir.branchsample.image.ImageLoader
import net.furkanakdemir.branchsample.ui.BranchViewModel
import net.furkanakdemir.branchsample.ui.base.BaseFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class BranchDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var branchViewModel: BranchViewModel

    @Inject
    lateinit var imageLoader: ImageLoader

    override val title: String
        get() = TITLE_DETAIL

    override val layoutId: Int
        get() = R.layout.fragment_branch_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        branchViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get()

        branchViewModel.branchLiveData.observe(viewLifecycleOwner, Observer {
            nameTextView.text = it.name
            categoryTextView.text = it.category
        })
    }

    companion object {
        private const val TITLE_DETAIL: String = "Detail"
    }
}
