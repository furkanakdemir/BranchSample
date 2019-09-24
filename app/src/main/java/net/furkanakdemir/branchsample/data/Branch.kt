package net.furkanakdemir.branchsample.data

data class Branch(
    val id: String,
    val name: String = "",
    val category: String = "",
    val distance: Int = 0
) {

    companion object {
        fun default(): Branch {
            return Branch("-1")
        }
    }
}
