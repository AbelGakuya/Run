package com.amg.run.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import com.amg.run.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AppDisclosureDialog : DialogFragment() {
    private var yesListener: (() -> Unit)? = null

    fun setYesListener(listener: () -> Unit){
        yesListener = listener
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("App Disclosure")
            .setMessage("RUN collects location data using Google Maps to enable access to current location updates requests even when the app is closed or not in use, for subsequent calculation of your total distance covered.")
            .setIcon(R.drawable.ic_run)
            .setPositiveButton("Ok"){dialogInterface,_ ->
               dialogInterface.cancel()
//                yesListener?.let {yes ->
//                    yes()
//                }
            }
//            .setNegativeButton("Deny"){ dialogInterface,_ ->
//                dialogInterface.cancel()
//            }
            .create()
    }
}