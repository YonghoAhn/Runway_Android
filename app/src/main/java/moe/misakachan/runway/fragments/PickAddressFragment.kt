package moe.misakachan.runway.fragments

import android.Manifest
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.pick_address_fragment.*

import moe.misakachan.runway.R
import moe.misakachan.runway.viewModels.PickAddressViewModel
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import android.R.string.cancel
import android.content.DialogInterface
import androidx.core.app.ActivityCompat.startActivityForResult
import android.content.Intent
import moe.misakachan.runway.activities.MainActivity
import androidx.core.app.ActivityCompat
import android.widget.Toast
import net.daum.mf.map.n.api.internal.NativeMapLocationManager.setCurrentLocationTrackingMode
import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.core.content.ContextCompat
import androidx.annotation.NonNull
import android.app.AlertDialog
import android.location.LocationManager
import android.content.Context.LOCATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.util.Log
import android.widget.RelativeLayout


class PickAddressFragment : Fragment(){
    companion object {
        fun newInstance() = PickAddressFragment()
    }

    private lateinit var viewModel: PickAddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pick_address_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PickAddressViewModel::class.java)

    }
}
