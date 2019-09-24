package net.furkanakdemir.branchsample.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_branch_detail.*
import net.furkanakdemir.branchsample.R
import net.furkanakdemir.branchsample.ui.BranchViewModel
import net.furkanakdemir.branchsample.ui.base.BaseFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class BranchDetailFragment : BaseFragment() {

    private lateinit var mMap: GoogleMap

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var branchViewModel: BranchViewModel

    override val title: String
        get() = TITLE_DETAIL

    override val layoutId: Int
        get() = R.layout.fragment_branch_detail

    private var position = LatLng(0.0, 0.0)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        branchViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get()

        branchViewModel.branchLiveData.observe(viewLifecycleOwner, Observer {
            nameTextView.text = it.name
            categoryTextView.text = it.category
            addressTextView.text = it.address
            position = LatLng(it.latitude, it.longitude)
        })


        with(mapView) {
            // Initialise the MapView
            onCreate(savedInstanceState)
            // Set the map ready callback to receive the GoogleMap object
            getMapAsync {
                with(it) {
                    uiSettings.setAllGesturesEnabled(false)
                    moveCamera(CameraUpdateFactory.newLatLngZoom(position, ZOOM_LEVEL))
                    addMarker(MarkerOptions().position(position))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroyView() {
        mapView.onDestroy()
        super.onDestroyView()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    companion object {
        private const val TITLE_DETAIL: String = "Detail"
        private const val ZOOM_LEVEL: Float = 15f
    }
}
