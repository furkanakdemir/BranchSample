package net.furkanakdemir.branchsample.ui

import net.furkanakdemir.branchsample.data.Branch
import net.furkanakdemir.branchsample.mapper.Mapper
import javax.inject.Inject

class ViewMapper @Inject constructor() : Mapper<Branch, BranchViewItem> {
    override fun map(input: Branch?): BranchViewItem {
        return input?.let {
            with(it) {

                BranchViewItem(
                    id,
                    name,
                    category,
                    formatDistance(distance)
                )
            }
        } ?: BranchViewItem.default()
    }

    private fun formatDistance(distance: Int): String = "$distance m"
}
