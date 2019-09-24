package net.furkanakdemir.branchsample.ui

data class BranchViewItem(
    val id: String,
    val name: String = "",
    val category: String = "",
    val distance: String = "0",
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
) {

    companion object {
        fun default(): BranchViewItem {
            return BranchViewItem("-1")
        }
    }
}
