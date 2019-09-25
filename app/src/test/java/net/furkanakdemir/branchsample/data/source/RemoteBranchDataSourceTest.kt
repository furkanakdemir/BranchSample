package net.furkanakdemir.branchsample.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import net.furkanakdemir.branchsample.data.Branch
import net.furkanakdemir.branchsample.data.PlacesResponse
import net.furkanakdemir.branchsample.mapper.DomainMapper
import net.furkanakdemir.branchsample.mapper.ListMapper
import net.furkanakdemir.branchsample.mapper.RealListMapper
import net.furkanakdemir.branchsample.network.BranchService
import net.furkanakdemir.branchsample.util.CoroutinesTestRule
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_BRANCH_LIST
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_LOCATION
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_QUERY
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_RESPONSE
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import java.io.IOException
import org.hamcrest.CoreMatchers.`is` as Is

@ExperimentalCoroutinesApi
class RemoteBranchDataSourceTest {

    private lateinit var branchDataSource: BranchDataSource
    private lateinit var branchService: BranchService
    private lateinit var mapper: ListMapper<PlacesResponse.Response.VenueRaw?, Branch>


    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        mapper = RealListMapper(DomainMapper())
        branchService = Mockito.mock(BranchService::class.java)
        branchDataSource = RemoteBranchDataSource(branchService, mapper)

    }

    @Test(expected = IOException::class)
    fun testFailedRequest() = runBlockingTest {
        given(
            branchService.getBranches(
                TEST_QUERY,
                TEST_LOCATION
            )
        ).willAnswer { throw IOException() }
        val actual = branchDataSource.getBranches(TEST_LOCATION)
    }

    @Test
    fun testNullResponse() = runBlockingTest {
        given(branchService.getBranches(TEST_QUERY, TEST_LOCATION)).willReturn(null)
        val actual: List<Branch> = branchDataSource.getBranches(TEST_LOCATION)

        assertThat(actual.size, Is(0))
    }

    @Test
    fun testSuccessfulRequest() = runBlockingTest {
        given(branchService.getBranches(TEST_QUERY, TEST_LOCATION)).willReturn(
            TEST_RESPONSE
        )
        val actual: List<Branch> = branchDataSource.getBranches(TEST_LOCATION)

        assertThat(actual.size, Is(1))
        assertThat(actual, Is(TEST_BRANCH_LIST))
    }
}
