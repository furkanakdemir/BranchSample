package net.furkanakdemir.branchsample.domain

import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_LATITUDE
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_LONGITUDE
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_ORIGIN_POINT
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class BranchLocationTest {

    @Test
    fun testDefaultConstructor() {
        val actual = BranchLocation()

        assertThat(actual.latitude, `is`(TEST_ORIGIN_POINT))
        assertThat(actual.longitude, `is`(TEST_ORIGIN_POINT))
    }

    @Test
    fun testNullConstructor() {
        val actual = BranchLocation(null, null)

        assertThat(actual.latitude, `is`(TEST_ORIGIN_POINT))
        assertThat(actual.longitude, `is`(TEST_ORIGIN_POINT))
    }

    @Test
    fun testValidConstructor() {
        val actual = BranchLocation(TEST_LATITUDE, TEST_LONGITUDE)

        assertThat(actual.latitude, `is`(TEST_LATITUDE))
        assertThat(actual.longitude, `is`(TEST_LONGITUDE))
    }

    @Test
    fun testAssignedWithDefault() {

        val actual = BranchLocation()
        assertFalse(actual.isAssigned())
    }

    @Test
    fun testAssignedWithInvalid() {

        val actual = BranchLocation(null, null)
        assertFalse(actual.isAssigned())
    }

    @Test
    fun testAssignedWithValid() {

        val actual = BranchLocation(TEST_LATITUDE, TEST_LONGITUDE)
        assertTrue(actual.isAssigned())
    }
}
