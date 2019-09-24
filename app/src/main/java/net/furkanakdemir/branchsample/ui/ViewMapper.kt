package net.furkanakdemir.branchsample.ui

import net.furkanakdemir.branchsample.data.Branch
import net.furkanakdemir.branchsample.mapper.Mapper
import javax.inject.Inject

class ViewMapper @Inject constructor() : Mapper<Branch, BranchViewItem> {
    override fun map(input: Branch?): BranchViewItem {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
