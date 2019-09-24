package net.furkanakdemir.branchsample.mapper

import net.furkanakdemir.branchsample.data.Branch
import net.furkanakdemir.branchsample.ui.BranchViewItem
import javax.inject.Inject

class ViewMapper @Inject constructor() : Mapper<Branch, BranchViewItem> {
    override fun map(input: Branch?): BranchViewItem {
        return input?.let {
            with(it) {

                BranchViewItem(
                    id,
                    name,
                    category,
                    formatDistance(distance),
                    address
                )
            }
        } ?: BranchViewItem.default()
    }

    private fun formatDistance(distance: Int): String = "$distance m"
}
