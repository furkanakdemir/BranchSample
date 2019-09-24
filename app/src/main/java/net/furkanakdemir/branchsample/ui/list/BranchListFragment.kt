package net.furkanakdemir.branchsample.ui.list

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import kotlinx.android.synthetic.main.fragment_branch_list.*
import net.furkanakdemir.branchsample.R
import net.furkanakdemir.branchsample.image.ImageLoader
import net.furkanakdemir.branchsample.result.EventObserver
import net.furkanakdemir.branchsample.ui.BranchViewModel
import net.furkanakdemir.branchsample.ui.base.BaseFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class BranchListFragment : BaseFragment() {

    private lateinit var branchListAdapter: BranchListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var branchViewModel: BranchViewModel

    @Inject
    lateinit var imageLoader: ImageLoader

    override val layoutId: Int
        get() = R.layout.fragment_branch_list

    override val title: String
        get() = TITLE_LIST

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        branchViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get()

        setupRecyclerView()

        branchViewModel.branchesLiveData.observe(this, Observer {
            branchListAdapter.branches = it.toMutableList()
            showContent()
        })

        branchViewModel.eventLiveData.observe(this, EventObserver {

            when (it) {
                is BranchViewModel.ViewState.Loading -> showLoading()
                is BranchViewModel.ViewState.Empty -> showMessage(it.message)
                is BranchViewModel.ViewState.Error -> showMessage(it.message)
            }
        })

        branchViewModel.getBranches()
    }

    private fun setupRecyclerView() {
        branchListAdapter = BranchListAdapter(imageLoader) { branchViewItem ->
            branchViewModel.selectBranch(branchViewItem)
            findNavController().navigate(R.id.action_branchListFragment_to_branchDetailFragment)
        }

        branchesRecyclerView.apply {
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
            adapter = branchListAdapter
        }
    }

    private fun showLoading() {
        messageTextView.visibility = GONE
        branchesRecyclerView.visibility = GONE
        progressBar.visibility = VISIBLE
    }

    private fun showMessage(message: String) {
        messageTextView.text = message
        messageTextView.visibility = VISIBLE
        branchesRecyclerView.visibility = GONE
        progressBar.visibility = GONE
    }

    private fun showContent() {
        messageTextView.visibility = GONE
        branchesRecyclerView.visibility = VISIBLE
        progressBar.visibility = GONE
    }

    companion object {
        private const val TITLE_LIST = "Branches"
    }
}
