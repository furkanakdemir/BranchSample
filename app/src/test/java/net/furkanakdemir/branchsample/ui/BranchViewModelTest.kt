package net.furkanakdemir.branchsample.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import net.furkanakdemir.branchsample.domain.GetBranchesUseCase
import net.furkanakdemir.branchsample.mapper.ViewMapper
import net.furkanakdemir.branchsample.result.Result
import net.furkanakdemir.branchsample.util.CoroutinesTestRule
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_BRANCH_LIST
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_BRANCH_LIST_VIEW
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_BRANCH_LOCATION_DEFAULT
import net.furkanakdemir.branchsample.util.fakes.TestData.TEST_BRANCH_VIEW_ITEM
import net.furkanakdemir.branchsample.util.testObserver
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import org.hamcrest.CoreMatchers.`is` as Is
import org.mockito.Mockito.`when` as whenever

@ExperimentalCoroutinesApi
class BranchViewModelTest {

    private lateinit var viewMapper: ViewMapper

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setup() {
        viewMapper = ViewMapper()
    }

    @Test
    fun testEmptyBranches() = runBlockingTest {
        val getBranchesUseCase = Mockito.mock(GetBranchesUseCase::class.java)
        whenever(getBranchesUseCase.getBranches()).thenReturn(Result.Success(emptyList()))

        val branchViewModel = BranchViewModel(getBranchesUseCase, viewMapper)

        val testObserver = branchViewModel.eventLiveData.testObserver()
        branchViewModel.getBranches()
        verify(getBranchesUseCase).getBranches()

        assertThat(testObserver.observedValues.size, Is(2))
        assertThat(
            testObserver.observedValues[0]?.getContentIfNotHandled(),
            instanceOf(BranchViewModel.ViewState.Loading::class.java)
        )
        assertThat(
            testObserver.observedValues[1]?.getContentIfNotHandled(),
            instanceOf(BranchViewModel.ViewState.Empty::class.java)
        )
    }

    @Test
    fun testValidBranches() = runBlockingTest {
        val getBranchesUseCase: GetBranchesUseCase = Mockito.mock(GetBranchesUseCase::class.java)
        val branchViewModel = BranchViewModel(
            getBranchesUseCase,
            viewMapper
        )
        whenever(getBranchesUseCase.getBranches()).thenReturn(Result.Success(TEST_BRANCH_LIST))

        val testObserver = branchViewModel.eventLiveData.testObserver()
        val testBranchObserver = branchViewModel.branchesLiveData.testObserver()
        branchViewModel.getBranches()

        assertThat(testObserver.observedValues.size, Is(1))
        assertThat(
            testObserver.observedValues[0]?.getContentIfNotHandled(),
            instanceOf(BranchViewModel.ViewState.Loading::class.java)
        )

        print(testBranchObserver.observedValues)

        assertThat(testBranchObserver.observedValues.size, Is(1))
        assertThat(testBranchObserver.observedValues[0], Is(TEST_BRANCH_LIST_VIEW))
    }

    @Test
    fun testErrorOnLoading() = runBlockingTest {
        val getBranchesUseCase: GetBranchesUseCase = Mockito.mock(GetBranchesUseCase::class.java)
        val branchViewModel = BranchViewModel(
            getBranchesUseCase,
            viewMapper
        )
        whenever(getBranchesUseCase.getBranches()).thenReturn(Result.Error(MockitoException("FAILURE")))

        val testObserver = branchViewModel.eventLiveData.testObserver()
        branchViewModel.getBranches()
        verify(getBranchesUseCase).getBranches()

        assertThat(testObserver.observedValues.size, Is(2))
        assertThat(
            testObserver.observedValues[0]?.getContentIfNotHandled(),
            instanceOf(BranchViewModel.ViewState.Loading::class.java)
        )
        assertThat(
            testObserver.observedValues[1]?.getContentIfNotHandled(),
            instanceOf(BranchViewModel.ViewState.Error::class.java)
        )
    }

    @Test
    fun selectBranch() {
        val getBranchesUseCase: GetBranchesUseCase = Mockito.mock(GetBranchesUseCase::class.java)
        val branchViewModel = BranchViewModel(
            getBranchesUseCase,
            viewMapper
        )
        val testObserver = branchViewModel.branchLiveData.testObserver()
        branchViewModel.selectBranch(TEST_BRANCH_VIEW_ITEM)

        assertThat(testObserver.observedValues.size, Is(1))
        assertThat(testObserver.observedValues[0], Is(TEST_BRANCH_VIEW_ITEM))
    }

    @Test
    fun testSetLocation() {
        val getBranchesUseCase: GetBranchesUseCase = Mockito.mock(GetBranchesUseCase::class.java)
        val branchViewModel = BranchViewModel(
            getBranchesUseCase,
            viewMapper
        )

        branchViewModel.setLocation(TEST_BRANCH_LOCATION_DEFAULT)
        verify(getBranchesUseCase).location = TEST_BRANCH_LOCATION_DEFAULT
    }
}