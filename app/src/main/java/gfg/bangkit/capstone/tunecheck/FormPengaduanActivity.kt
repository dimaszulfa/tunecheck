package gfg.bangkit.capstone.tunecheck

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import gfg.bangkit.capstone.tunecheck.database.Database
import gfg.bangkit.capstone.tunecheck.databinding.ActivityFormPengaduanBinding
import gfg.bangkit.capstone.tunecheck.utils.Constants
import gfg.bangkit.capstone.tunecheck.utils.UiComponentsActivity


class FormPengaduanActivity : UiComponentsActivity() {

    private lateinit var binding : ActivityFormPengaduanBinding
    fun imageUploadSuccess(imageURL : String, report: HashMap<String, Any>){
        Database().addReport(this, report)
    }

    fun reportSuccess(){
        hideProgressBar()
        Toast.makeText(this,"Report success.",Toast.LENGTH_LONG).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val toolbar = findViewById<Toolbar>(R.id.toolbarForm)
//        setSupportActionBar(toolbar)
        val uri: Uri? = intent?.getParcelableExtra("image")
        val location: String? = intent?.getStringExtra("location")
        val date: String? = intent?.getStringExtra("date")
        val userDetailsHashMap = HashMap<String, Any>()
        val sharedPreferences = getSharedPreferences(Constants.SHOP_PREFERENCES,MODE_PRIVATE)
        val firstName = sharedPreferences.getString(Constants.CURRENT_NAME,"")
        val lastName = sharedPreferences.getString(Constants.CURRENT_SURNAME,"")

        userDetailsHashMap["location"] = location.toString()
        userDetailsHashMap["date"] = date.toString()
        userDetailsHashMap["firstName"] = firstName.toString()
        userDetailsHashMap["LastName"] = lastName.toString()

        binding.btnLapor.setOnClickListener {

            var validation = true
            if(binding.etPengaduan.text.isEmpty()){
                validation = false
                Toast.makeText(this, "Pengaduan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }

            if(binding.etSubjek.text.isEmpty()){
                validation = false
                Toast.makeText(this, "Subjek tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }

            if(validation){
                showProgressBar("Loading")
                val subjek = binding.etSubjek.text.trim().toString()
                val pengaduan = binding.etPengaduan.text.trim().toString()
                userDetailsHashMap["subjek"] = subjek
                userDetailsHashMap["pengaduan"] = pengaduan
                userDetailsHashMap["verified"] = 0
                Log.d("URILOCATIONDATE", uri.toString()+location+date)
                if(uri != null){
                    Database().uploadImageToStorage(this, imageUri = uri, "png", userDetailsHashMap)
                }

            }


        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                Log.d("tag", "clicked");
                Toast.makeText(this@FormPengaduanActivity, "Navigate To Profile", Toast.LENGTH_SHORT)
                    .show()
                // Handle profile button click
                val intent = Intent(this@FormPengaduanActivity, ProfileActivity::class.java)
                startActivity(intent)
                true
            }

            else -> {
                Log.d("tag", "clickesd");

                super.onOptionsItemSelected(item)
            }
        }
    }

}