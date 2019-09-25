package net.furkanakdemir.branchsample.util.fakes

import net.furkanakdemir.branchsample.data.Branch
import net.furkanakdemir.branchsample.data.PlacesResponse
import net.furkanakdemir.branchsample.domain.BranchLocation
import net.furkanakdemir.branchsample.ui.BranchViewItem

object TestData {

    const val TEST_ID = "1"
    const val TEST_NAME = "name"
    const val TEST_CATEGORY = "category"
    const val TEST_ADDRESS = "address"

    const val TEST_LATITUDE = 41.0
    const val TEST_LONGITUDE = 29.0
    const val TEST_ORIGIN_POINT = 0.0
    const val TEST_LOCATION = "$TEST_LATITUDE,$TEST_LONGITUDE"
    const val TEST_LOCATION_ISTANBUL = "41.1123421,29.0219696"
    val TEST_BRANCH_LOCATION_DEFAULT = BranchLocation()

    const val TEST_QUERY = "supplementler"
    const val TEST_DISTANCE = 2230
    const val TEST_DISTANCE_VIEW = "$TEST_DISTANCE m"

    val TEST_CATEGORIES = listOf(
        PlacesResponse.Response.VenueRaw.Category(
            TEST_ID,
            TEST_CATEGORY
        )
    )

    val TEST_FORMATTED_ADDRESSES = listOf(TEST_ADDRESS)

    val TEST_LOCATION_RAW = PlacesResponse.Response.VenueRaw.Location(
        TEST_DISTANCE,
        TEST_FORMATTED_ADDRESSES,
        TEST_LATITUDE,
        TEST_LONGITUDE
    )

    val TEST_RESPONSE = PlacesResponse(
        PlacesResponse.Response(
            listOf(
                PlacesResponse.Response.VenueRaw(
                    TEST_ID,
                    TEST_NAME,
                    TEST_CATEGORIES,
                    TEST_LOCATION_RAW
                )
            )
        )
    )

    val TEST_BRANCH = Branch(
        TEST_ID,
        TEST_NAME,
        TEST_CATEGORY,
        TEST_DISTANCE,
        TEST_ADDRESS,
        TEST_LATITUDE,
        TEST_LONGITUDE
    )

    val TEST_BRANCH_VIEW_ITEM = BranchViewItem(
        TEST_ID,
        TEST_NAME,
        TEST_CATEGORY,
        TEST_DISTANCE_VIEW,
        TEST_ADDRESS,
        TEST_LATITUDE,
        TEST_LONGITUDE
    )

    val TEST_BRANCH_LIST = listOf(TEST_BRANCH)
    val TEST_BRANCH_LIST_VIEW = listOf(TEST_BRANCH_VIEW_ITEM)
}
