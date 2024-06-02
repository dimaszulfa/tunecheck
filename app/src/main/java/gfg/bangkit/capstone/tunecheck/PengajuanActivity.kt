package gfg.bangkit.capstone.tunecheck

import android.app.Activity
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import gfg.bangkit.capstone.tunecheck.database.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.vision.detector.Detection
import org.tensorflow.lite.task.vision.detector.ObjectDetector
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.max
import kotlin.math.min


class PengajuanActivity : AppCompatActivity(), OnMapReadyCallback {
    companion object {
        const val TAG = "TFLite - ODT"
        const val REQUEST_IMAGE_CAPTURE: Int = 1
        private const val MAX_FONT_SIZE = 96F
    }

    private lateinit var bitmap: Bitmap
    private lateinit var inputImageView: ImageView
    private lateinit var tvPlaceholder: TextView
    private lateinit var btnDate :LinearLayout
    private lateinit var tvDate :TextView
    private lateinit var btnAjukanPengaduan :Button
    private lateinit var etLocation :EditText
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var mMap: GoogleMap

    fun imageUploadSuccess(imageURL : String){
//        updateUserProfileDetails()
    }

    //    private lateinit var bottomBar: SmoothBottomBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan)
//        val byteArray = intent.get("image")
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_fragment_container) as SupportMapFragment
        mapFragment.getMapAsync(this)
        // Check for location permissions and request them if not already granted

        // Check for location permissions and request them if not already granted
        checkLocationPermission()
        val uri: Uri? = intent?.getParcelableExtra("image")
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        // Dapatkan tanggal hari ini
        val today = Date()

        // Format tanggal hari ini menjadi string
        val todayString = dateFormat.format(today)

        inputImageView = findViewById(R.id.imageView)
        btnDate = findViewById<LinearLayout>(R.id.btnMonth)
        btnAjukanPengaduan = findViewById<Button>(R.id.btnAjukanPengaduan)
        tvPlaceholder = findViewById(R.id.tvPlaceholder)
        tvDate = findViewById(R.id.tvDate)
        etLocation = findViewById(R.id.etLocation)

        inputImageView.setImageURI(uri)


        tvDate.setText(todayString)

        btnAjukanPengaduan.setOnClickListener {
            val date = tvDate.text.trim().toString()
            val etLocation = etLocation.text.trim().toString()
            var validation = true
            if(date == "dd/mm/yy"){
                validation = false
                Toast.makeText(this, "Tanggal tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }

            if(etLocation.isEmpty()){
                validation = false
                Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }


            if(validation){
                val intent = Intent(this, FormPengaduanActivity::class.java)
                intent.putExtra("date", date)
                intent.putExtra("location", etLocation)
                intent.putExtra("image", uri)
                startActivity(intent)
            }
        }



    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLastKnownLocation()
    }

    private fun showLocationOnMap(latitude: Double, longitude: Double) {
        val currentLocation = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(currentLocation).title("You are here"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
    }
    private fun showDatePickerDialog(onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = "${selectedDay}/${selectedMonth + 1}/$selectedYear"
                onDateSelected(formattedDate)
            },
            year, month, day
        )

        datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        datePickerDialog.setOnShowListener {
            val okButton = datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
            val cancelButton = datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)

            okButton.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            okButton.setTextColor(ContextCompat.getColor(this, R.color.white))

            cancelButton.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            cancelButton.setTextColor(ContextCompat.getColor(this, R.color.white))
        }


        datePickerDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                Log.d("tag", "clicked");
                Toast.makeText(this@PengajuanActivity, "Navigate To Profile", Toast.LENGTH_SHORT)
                    .show()
                // Handle profile button click
                val intent = Intent(this@PengajuanActivity, ProfileActivity::class.java)
                startActivity(intent)
                true
            }

            else -> {
                Log.d("tag", "clickesd");

                super.onOptionsItemSelected(item)
            }
        }
    }


    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            getLastKnownLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastKnownLocation()
            } else {
                // Permission denied
                Toast.makeText(this, "Kamu harus mengaktifkan lokasi untuk mendapatkan lokasi saat init", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    val latitude = it.latitude
                    val longitude = it.longitude
                    showLocationOnMap(latitude, longitude)
                    getAddressFromLocation(latitude,longitude)
                } ?: run {
                    // Location is null, handle the case
                    Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                // Handle exception
                Toast.makeText(this, "Error getting location: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getAddressFromLocation(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        if (addresses!!.isNotEmpty()) {
            val address = addresses[0]
            val subDistrict = address.subLocality // Kecamatan
            val city = address.locality // City
            etLocation.setText(subDistrict+", "+city)
            Log.d("ADDRESS", "${address}+${subDistrict}+${city}")

            // Use the subDistrict and city as needed
        }
    }
}
