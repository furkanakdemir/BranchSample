package net.furkanakdemir.branchsample.data

import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import net.furkanakdemir.branchsample.data.source.BranchDataSource
import net.furkanakdemir.branchsample.result.Result
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_BRANCH_LIST
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_LOCATION
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import java.io.IOException

@ExperimentalCoroutinesApi
class BranchRepositoryTest {

    private lateinit var branchRepository: BranchRepository
    private lateinit var branchDataSource: BranchDataSource

    @Before
    fun setUp() {
        branchDataSource = Mockito.mock(BranchDataSource::class.java)
        branchRepository = RealBranchRepository(branchDataSource)
    }

    @Test
    fun testErrorList() = runBlockingTest {

        BDDMockito.given(branchDataSource.getBranches(TEST_LOCATION))
            .willAnswer { throw IOException() }

        val actual = branchRepository.getBranches(TEST_LOCATION)

        assertThat(actual, instanceOf(Result.Error::class.java))
        assertThat((actual as Result.Error).exception, instanceOf(IOException::class.java))
    }

    @Test
    fun testEmptyList() = runBlockingTest {
        whenever(branchDataSource.getBranches(TEST_LOCATION)).thenReturn(emptyList())

        val actual = branchRepository.getBranches(TEST_LOCATION)
        val expected = emptyList<Branch>()

        assertThat(actual, instanceOf(Result.Success::class.java))
        assertThat((actual as Result.Success).data, Is.`is`(expected))
    }

    @Test
    fun testValidList() = runBlockingTest {
        whenever(branchDataSource.getBranches(TEST_LOCATION)).thenReturn(TEST_BRANCH_LIST)

        val actual = branchRepository.getBranches(TEST_LOCATION)
        val expected = TEST_BRANCH_LIST

        assertThat(actual, instanceOf(Result.Success::class.java))
        assertThat((actual as Result.Success).data, Is.`is`(expected))
    }
}
