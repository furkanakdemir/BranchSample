package net.furkanakdemir.branchsample.data

data class Branch(val id: String) {


    companion object {

        @JvmOverloads
        fun default(): Branch {
            return Branch("-1")
        }
    }
}