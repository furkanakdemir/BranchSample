package net.furkanakdemir.branchsample.ui

data class BranchViewItem(
    val id: String,
    val name: String = "",
    val category: String = "",
    val distance: String = "0"
) {

    companion object {
        fun default(): BranchViewItem {
            return BranchViewItem("-1")
        }
    }
}
