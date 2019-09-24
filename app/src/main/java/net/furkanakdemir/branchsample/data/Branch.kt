package net.furkanakdemir.branchsample.data

data class Branch(
    val id: String,
    val name: String = "",
    val category: String = "",
    val distance: Int = 0,
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0

) {

    companion object {
        fun default(): Branch {
            return Branch("-1")
        }
    }
}
