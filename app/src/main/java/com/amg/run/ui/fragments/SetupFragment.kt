package com.amg.run.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.amg.run.R
import com.amg.run.databinding.FragmentSetupBinding
import com.amg.run.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.amg.run.other.Constants.KEY_NAME
import com.amg.run.other.Constants.KEY_WEIGHT
//import com.example.run.databinding.FragmentSetupBinding
//import com.example.run.other.Constants.KEY_FIRST_TIME_TOGGLE
//import com.example.run.other.Constants.KEY_NAME
//import com.example.run.other.Constants.KEY_WEIGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SetupFragment : Fragment() {
    private lateinit var binding: FragmentSetupBinding

    @Inject
    lateinit var sharedPref: SharedPreferences

    @set:Inject
    var isFirstAppOpen = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetupBinding.inflate(inflater,container,false)
        val view = binding.root

        if (!isFirstAppOpen){
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.setupFragment,true)
                .build()
            findNavController().navigate(
                R.id.action_setupFragment_to_runFragment,
                savedInstanceState,
                navOptions
            )
        }

        val tvContinue = binding.tvContinue
        tvContinue.setOnClickListener {
            val success = writePersonalDataToSharedPref()
            if (success){
                findNavController().navigate(R.id.action_setupFragment_to_runFragment)
            } else {
                Snackbar.make(requireView(), "Please Enter All the Fields!", Snackbar.LENGTH_SHORT).show()
            }


        }

        return view
    }

    private fun writePersonalDataToSharedPref() : Boolean{
        val name = binding.etName.text.toString()
        val weight = binding.etWeight.text.toString()
        if (name.isEmpty() || weight.isEmpty()){
            return false
        }

        sharedPref.edit()
            .putString(KEY_NAME,name)
            .putFloat(KEY_WEIGHT, weight.toFloat())
            .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
            .apply()

        val toolText = "Let's go, $name!"
        val toolbar = requireActivity().findViewById<TextView>(R.id.tvToolbarTitle)
        toolbar.text = toolText
//        val toolbarText = "Let's go, $name!"
//        val toolbar = requireActivity().findViewById<Toolbar>(R.id.tvToolbarTitle)
        return true
    }


}