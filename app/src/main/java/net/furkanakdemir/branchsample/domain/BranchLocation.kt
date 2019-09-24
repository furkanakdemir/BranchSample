package net.furkanakdemir.branchsample.domain

data class BranchLocation(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
) {
    fun isAssigned(): Boolean = latitude != 0.0 && longitude != 0.0
    override fun toString(): String {
        return "$latitude, $longitude)"
    }
}
