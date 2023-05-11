package com.amg.run.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.amg.run.R
import com.amg.run.databinding.ActivityMainBinding
import com.amg.run.other.Constants.ACTION_SHOW_TRACKING_FRAGMENT
//import com.amg.run.ui.fragments.AppDisclosureDialog
import com.amg.run.ui.fragments.CANCEL_TRACKING_DIALOG_TAG
import com.google.android.material.dialog.MaterialAlertDialogBuilder
//import com.example.run.databinding.ActivityMainBinding
//import com.example.run.other.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        navigateToTrackingFragmentIfNeeded(intent)
        setSupportActionBar(binding.toolbar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

        binding.bottomNavigationView.setOnNavigationItemReselectedListener { /* No Operation*/ }

        navHostFragment.findNavController()
            .addOnDestinationChangedListener{
                    _,destination,_ ->
                when(destination.id){
                    R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment ->
                        binding.bottomNavigationView.visibility = View.VISIBLE

                    else -> binding.bottomNavigationView.visibility = View.GONE
                }
            }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }
//
//    private fun showAppDisclosureDialog(){
//       val dialog = MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
//           .setTitle("App Disclosure")
//            .setMessage("RUN collects location data using Google Maps to enable access to current location updates requests even when the app is closed or not in use, for subsequent calculation of your total distance covered.")
//            .setIcon(R.drawable.ic_run)
//            .setPositiveButton("Ok"){_,_ ->
//                yesListener?.let {yes ->
//                    yes()
//                }
//            }
////            .setNegativeButton("Deny"){ dialogInterface,_ ->
////                dialogInterface.cancel()
////            }
//            .create()
//    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        if (intent?.action == ACTION_SHOW_TRACKING_FRAGMENT){
            navHostFragment.findNavController().navigate(R.id.action_global_trackingFragment)
        }
    }
}