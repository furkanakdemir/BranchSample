package net.furkanakdemir.branchsample.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.furkanakdemir.branchsample.R
import net.furkanakdemir.branchsample.domain.BranchLocation
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var branchViewModel: BranchViewModel

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupFusedLocationClient()
        setupViewModel()

        checkLocationPermission()
    }

    private fun setupViewModel() {
        branchViewModel = ViewModelProviders.of(this, viewModelFactory).get()
    }

    private fun setupFusedLocationClient() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)

        // Set up the ActionBar to stay in sync with the NavController
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
    }

    private fun checkLocationPermission() {
        val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
        val permissionResult = ActivityCompat.checkSelfPermission(this, locationPermission)
        if (permissionResult != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION_PERMISSION
            )
        } else {
            getLastKnownLocation()
        }
    }

    private fun getLastKnownLocation() {
        fusedLocationClient.lastLocation
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val lat = it.result?.latitude
                    val lng = it.result?.longitude

                    branchViewModel.setLocation(BranchLocation(lat, lng))
                }

                branchViewModel.getBranches()
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION) {
            if (grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                getLastKnownLocation()
            } else {
                branchViewModel.getBranches()
            }
        }
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

    fun setTitle(text: String) {
        supportActionBar?.title = text
    }

    companion object {
        private const val REQUEST_CODE_LOCATION_PERMISSION: Int = 99
    }
}
