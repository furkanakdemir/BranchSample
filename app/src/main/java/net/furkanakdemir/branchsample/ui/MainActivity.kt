package net.furkanakdemir.branchsample.ui

import android.location.Location
import android.os.Bundle
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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        branchViewModel = ViewModelProviders.of(this, viewModelFactory).get()

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    branchViewModel.setLocation(BranchLocation(it.latitude, it.longitude))
                }
            }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)

        // Set up the ActionBar to stay in sync with the NavController
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

    fun setTitle(text: String) {
        supportActionBar?.setTitle(text)
    }
}
