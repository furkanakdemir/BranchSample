package net.furkanakdemir.branchsample.domain

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import net.furkanakdemir.branchsample.data.BranchRepository
import net.furkanakdemir.branchsample.result.Result
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_BRANCH_LIST
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_BRANCH_LOCATION_DEFAULT
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_LATITUDE
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_LOCATION
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_LOCATION_ISTANBUL
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_LONGITUDE
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.hamcrest.CoreMatchers.`is` as Is

@ExperimentalCoroutinesApi
class GetBranchesUseCaseTest {

    private lateinit var branchRepository: BranchRepository

    @Before
    fun setUp() {
        branchRepository = Mockito.mock(BranchRepository::class.java)
    }

    @Test
    fun testDefaultConstructor() {

        val actual = GetBranchesUseCase(branchRepository)
        assertThat(actual.location, Is(TEST_BRANCH_LOCATION_DEFAULT))
    }

    @Test
    fun testSetLocation() {

        val actual = GetBranchesUseCase(branchRepository)
        val expected = BranchLocation(TEST_LATITUDE, TEST_LONGITUDE)

        actual.location = BranchLocation(TEST_LATITUDE, TEST_LONGITUDE)
        assertThat(actual.location, Is(expected))
    }

    @Test
    fun testGetIstanbulBranches() = runBlockingTest {

        whenever(branchRepository.getBranches(TEST_LOCATION_ISTANBUL)).thenReturn(
            Result.Success(
                TEST_BRANCH_LIST
            )
        )
        val actual = GetBranchesUseCase(branchRepository).getBranches()

        verify(branchRepository).getBranches(TEST_LOCATION_ISTANBUL)
        assertThat(actual, instanceOf(Result.Success::class.java))
        assertThat((actual as Result.Success).data, Is(TEST_BRANCH_LIST))
    }

    @Test
    fun testGetCurrentLocationBranches() = runBlockingTest {

        whenever(branchRepository.getBranches(TEST_LOCATION)).thenReturn(
            Result.Success(
                TEST_BRANCH_LIST
            )
        )
        val useCase = GetBranchesUseCase(branchRepository)
        useCase.location = BranchLocation(TEST_LATITUDE, TEST_LONGITUDE)

        val actual = useCase.getBranches()

        verify(branchRepository).getBranches(TEST_LOCATION)
        assertThat(actual, instanceOf(Result.Success::class.java))
        assertThat((actual as Result.Success).data, Is(TEST_BRANCH_LIST))
    }
}
