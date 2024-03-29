package net.furkanakdemir.branchsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.furkanakdemir.branchsample.data.Branch
import net.furkanakdemir.branchsample.domain.BranchLocation
import net.furkanakdemir.branchsample.domain.GetBranchesUseCase
import net.furkanakdemir.branchsample.mapper.Mapper
import net.furkanakdemir.branchsample.result.Event
import net.furkanakdemir.branchsample.result.Result
import javax.inject.Inject

class BranchViewModel @Inject constructor(
    private val getBranchesUseCase: GetBranchesUseCase,
    private val viewMapper: Mapper<Branch, BranchViewItem>
) : ViewModel() {

    private val _branchesLiveData = MutableLiveData<List<BranchViewItem>>()
    val branchesLiveData: LiveData<List<BranchViewItem>>
        get() = _branchesLiveData

    private val _branchLiveData = MutableLiveData<BranchViewItem>()
    val branchLiveData: LiveData<BranchViewItem>
        get() = _branchLiveData

    private val _eventLiveData = MutableLiveData<Event<ViewState>>()
    val eventLiveData: LiveData<Event<ViewState>>
        get() = _eventLiveData

    fun getBranches() {
        _eventLiveData.value = Event(ViewState.Loading)
        viewModelScope.launch {
            when (val result = getBranchesUseCase.getBranches()) {
                is Result.Success -> {
                    if (result.data.isNullOrEmpty()) {
                        _eventLiveData.value = Event(ViewState.Empty("Empty List"))
                    } else {
                        val viewList = mutableListOf<BranchViewItem>()

                        val sortedByDistance = result.data
                            .sortedWith(compareBy { it.distance })

                        sortedByDistance.forEach { viewList += viewMapper.map(it) }
                        _branchesLiveData.value = viewList
                    }
                }
                is Result.Error -> {
                    _eventLiveData.value = Event(ViewState.Error("Failed to load the data!"))
                }
            }
        }
    }

    fun selectBranch(item: BranchViewItem) {
        _branchLiveData.value = item
    }

    fun setLocation(branchLocation: BranchLocation) {
        getBranchesUseCase.location = branchLocation
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Empty(val message: String) : ViewState()
        data class Error(val message: String) : ViewState()
    }
}
