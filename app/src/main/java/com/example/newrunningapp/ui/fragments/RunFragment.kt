package com.example.newrunningapp.ui.fragments

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newrunningapp.R
import com.example.newrunningapp.databinding.ActivityMainBinding
import com.example.newrunningapp.other.Constants.REQUEST_CODE_LOCATION_PERMISSION
import com.example.newrunningapp.other.TrackingUtility
import com.example.newrunningapp.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class RunFragment: Fragment(R.layout.fragment_run), EasyPermissions.PermissionCallbacks {
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /// requestPermissons()

        val fab: View = view.findViewById(R.id.fab)


        fab.setOnClickListener{findNavController().navigate(R.id.action_runFragment_to_trackingFragment)
        }


    }

        private fun requestPermissons () {
        if(TrackingUtility.hasLocationPermissions(requireContext())) {
            return
        }

            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
                EasyPermissions.requestPermissions(
                    this,
                "You need to accept location permission to use this app.",
                    REQUEST_CODE_LOCATION_PERMISSION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION

                )

            } else {
                EasyPermissions.requestPermissions(
                    this,
                    "You need to accept location permission to use this app.",
                    REQUEST_CODE_LOCATION_PERMISSION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    ///Manifest.permission.ACCESS_BACKGROUND_LOCATION

                )
            }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {

            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissons()
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )

    {
        onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

}