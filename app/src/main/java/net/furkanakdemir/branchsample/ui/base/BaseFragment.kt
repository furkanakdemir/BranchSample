package net.furkanakdemir.branchsample.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dagger.android.support.DaggerFragment
import net.furkanakdemir.branchsample.ui.MainActivity

abstract class BaseFragment : DaggerFragment() {

    abstract val layoutId: Int

    abstract val title: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setTitle(title)
    }
}
