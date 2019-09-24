package net.furkanakdemir.branchsample.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import net.furkanakdemir.branchsample.R
import net.furkanakdemir.branchsample.image.ImageLoader
import net.furkanakdemir.branchsample.ui.base.BaseFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class BranchDetailFragment : BaseFragment() {

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
    }

    companion object {
        private val TITLE_DETAIL: String = "Detail"
    }
}
