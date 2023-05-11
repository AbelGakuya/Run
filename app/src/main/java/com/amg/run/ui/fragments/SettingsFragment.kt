package com.amg.run.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.amg.run.R
import com.amg.run.databinding.FragmentSettingsBinding
import com.amg.run.other.Constants.KEY_NAME
import com.amg.run.other.Constants.KEY_WEIGHT
//import com.example.run.databinding.FragmentSettingsBinding
//import com.example.run.other.Constants.KEY_NAME
//import com.example.run.other.Constants.KEY_WEIGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SettingsFragment : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        val view = binding.root


        loadFieldsFromSharedPref()
        binding.btnApplyChanges.setOnClickListener {
            val success = applyChangesToSharedPref()
            if (success){
                Snackbar.make(view,"Saved changes", Snackbar.LENGTH_LONG).show()
            } else{
                Snackbar.make(view,"Please fill out all fields", Snackbar.LENGTH_LONG).show()
            }


        }
        return view
    }

    private fun loadFieldsFromSharedPref(){
        val name = sharedPreferences.getString(KEY_NAME,"")
        val weight = sharedPreferences.getFloat(KEY_WEIGHT,80f)
        binding.etName.setText(name)
        binding.etWeight.setText(weight.toString())
    }

    private fun applyChangesToSharedPref(): Boolean{

        val name = binding.etName.text.toString()
        val weight = binding.etWeight.text.toString()
        if (name.isEmpty() || weight.isEmpty()){
            return false
        }
        sharedPreferences.edit()
            .putString(KEY_NAME, name)
            .putFloat(KEY_WEIGHT,weight.toFloat())
            .apply()

       val toolText = "Let's go, $name!"
        val toolbar = requireActivity().findViewById<TextView>(R.id.tvToolbarTitle)
        toolbar.text = toolText
        return true

    }

}