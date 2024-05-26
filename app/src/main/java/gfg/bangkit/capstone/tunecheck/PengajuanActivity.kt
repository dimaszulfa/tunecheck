package gfg.bangkit.capstone.tunecheck

import android.app.Activity
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.*
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
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.lifecycleScope
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


class PengajuanActivity : AppCompatActivity() {
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

    fun imageUploadSuccess(imageURL : String){
//        updateUserProfileDetails()
    }

    //    private lateinit var bottomBar: SmoothBottomBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan)
//        val byteArray = intent.get("image")
        val uri: Uri? = intent?.getParcelableExtra("image")
//        val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
//    val uri: Uri? = uriString?.let { Uri.parse(it) }
//
        inputImageView = findViewById(R.id.imageView)
        btnDate = findViewById<LinearLayout>(R.id.btnMonth)
        btnAjukanPengaduan = findViewById<Button>(R.id.btnAjukanPengaduan)
        tvPlaceholder = findViewById(R.id.tvPlaceholder)
        tvDate = findViewById(R.id.tvDate)
        etLocation = findViewById(R.id.etLocation)
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        bottomBar = findViewById(R.id.bottomBar)
        inputImageView.setImageURI(uri)
        btnDate.setOnClickListener {
            showDatePickerDialog { selectedDate ->
//                dateTextView.text = selectedDate
            Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show()
                tvDate.text = selectedDate;
            }
        }

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
                Toast.makeText(this, "Masuk", Toast.LENGTH_SHORT).show()
            }
        }

//        bottomBar.onItemSelected = {
//            Toast.makeText(this@MainActivity,"Item $it selected",Toast.LENGTH_SHORT).show()
//        }
//
//        bottomBar.onItemReselected = {
//            Toast.makeText(this@MainActivity,"Item $it re selected",Toast.LENGTH_SHORT).show()
//        }

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


    /**
     * onClick(v: View?)
     *      Detect touches on the UI components
     */


    /**
     * getCapturedImage():
     *      Decodes and crops the captured image from camera.
     */

    /**
     * getSampleImage():
     *      Get image form drawable and convert to bitmap.
     */
    private fun getSampleImage(drawable: Int): Bitmap {
        return BitmapFactory.decodeResource(resources, drawable, BitmapFactory.Options().apply {
            inMutable = true
        })
    }

    /**
     * rotateImage():
     *     Decodes and crops the captured image from camera.
     */
    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            source, 0, 0, source.width, source.height,
            matrix, true
        )
    }
}
