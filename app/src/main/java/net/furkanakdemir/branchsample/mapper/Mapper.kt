package net.furkanakdemir.branchsample.mapper

interface Mapper<I, O> {
    fun map(input: I?): O
}
