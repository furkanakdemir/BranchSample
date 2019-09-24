package net.furkanakdemir.branchsample.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_branch_detail.*
import net.furkanakdemir.branchsample.R
import net.furkanakdemir.branchsample.ui.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class BranchDetailFragment : BaseFragment() {

    override val title: String
        get() = TITLE_DETAIL

    override val layoutId: Int
        get() = R.layout.fragment_branch_detail

    private var position = LatLng(0.0, 0.0)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    override fun observeViewModel() {
        branchViewModel.branchLiveData.observe(viewLifecycleOwner, Observer {
            nameTextView.text = it.name
            categoryTextView.text = it.category
            addressTextView.text = it.address
            position = LatLng(it.latitude, it.longitude)
        })
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
