package gfg.bangkit.capstone.tunecheck

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import gfg.bangkit.capstone.tunecheck.auth.LoginActivity
import gfg.bangkit.capstone.tunecheck.database.Database
import gfg.bangkit.capstone.tunecheck.databinding.ActivityUpdateProfileBinding
import gfg.bangkit.capstone.tunecheck.model.User
import gfg.bangkit.capstone.tunecheck.utils.Constants
import gfg.bangkit.capstone.tunecheck.utils.LoadGlide
import gfg.bangkit.capstone.tunecheck.utils.UiComponentsActivity

class UpdateProfileActivity : UiComponentsActivity() {
    private lateinit var myUserDetails : User
    var selectedPicture: Uri? = null
    var userProfileImageURL : String = ""
    private lateinit var auth: FirebaseAuth

    fun userDetailsUpdateSuccess(){
        hideProgressBar()
        Toast.makeText(this,"Your profile has been updated successfully.", Toast.LENGTH_LONG).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun imageUploadSuccess(imageURL : String){
        userProfileImageURL = imageURL
        updateUserProfileDetails()
    }

    private fun updateUserProfileDetails(){

        val userDetailsHashMap = HashMap<String, Any>()

        val firstName = binding.etFirstName.text.toString().trim { it <= ' ' }
        if (firstName != myUserDetails.firstName) {
            userDetailsHashMap["firstName"] = firstName
        }

        val lastName = binding.etLastName.text.toString().trim { it <= ' ' }
        if (lastName != myUserDetails.lastName) {
            userDetailsHashMap["lastName"] = lastName
        }

        if(userProfileImageURL.isNotEmpty()){
            userDetailsHashMap["image"] = userProfileImageURL
        }

        if(userProfileImageURL.isNotEmpty()){
            userDetailsHashMap["image"] = userProfileImageURL
        }


        Database().updateProfileDetails(this,userDetailsHashMap)

    }

    private lateinit var binding : ActivityUpdateProfileBinding

//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
         binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)) {
            // Get the user details from intent as a ParcelableExtra.
            myUserDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
            binding.etFirstName.setText(myUserDetails.firstName)
            binding.etLastName.setText(myUserDetails.lastName)
            binding.etEmail.setText(myUserDetails.email)
        }


        binding.btnCustom.setOnClickListener {
            showProgressBar("Loadingg")
            auth.signOut().apply {
                hideProgressBar()
                Toast.makeText(this@UpdateProfileActivity, "Successfull Logout", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UpdateProfileActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }

        }

        binding.btnUpdate.setOnClickListener{
            var validate = true

            if(binding.etFirstName.text?.trim()!!.isEmpty()){
                validate = false
                Toast.makeText(this, "Nama depan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
            if(binding.etLastName.text?.trim()!!.isEmpty()){
                validate = false
                Toast.makeText(this, "Nama belakang tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }

            if(validate){
                showProgressBar("Loading..");
                updateUserProfileDetails()

            }
//            if(checkUserDetails()){
//                if(selectedPicture != null){
//                    Database().uploadImageToStorage(this,selectedPicture!!,"User_Profile_Image")
//                }
//                else{
//                }
//            }
        }




    }




}