package gfg.bangkit.capstone.tunecheck.auth

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import gfg.bangkit.capstone.tunecheck.R
import gfg.bangkit.capstone.tunecheck.database.Database
import gfg.bangkit.capstone.tunecheck.databinding.ActivityLoginBinding
import gfg.bangkit.capstone.tunecheck.databinding.ActivityRegisterBinding
import gfg.bangkit.capstone.tunecheck.model.User
import gfg.bangkit.capstone.tunecheck.utils.UiComponentsActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var myProgressDialog: Dialog
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    private fun checkRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.etFirstName.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this,"Masukkan nama depan yang benar",Toast.LENGTH_LONG).show()
                false
            }

            TextUtils.isEmpty(binding.etLastName.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this,"Masukkan nama belkang yang benar",Toast.LENGTH_LONG).show()
                false
            }

            TextUtils.isEmpty(binding.etEmail.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this,"Masukkan email yang benar",Toast.LENGTH_LONG).show()
                false
            }

            TextUtils.isEmpty(binding.etPassword.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this,"Masukkan password",Toast.LENGTH_LONG).show()
                false
            }

            else -> {
                true
            }
        }
    }

    private fun registerUser(){

        if(checkRegisterDetails()){
            showProgressBar()
            val email : String = binding.etEmail.text.toString().trim{it<= ' '}
            val password : String = binding.etPassword.text.toString().trim{it<= ' '}

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {task->
                    if(task.isSuccessful){
                        val currentUser = com.google.firebase.ktx.Firebase.auth.currentUser
                        val firstName = binding.etFirstName.text.toString()
                        val lastName = binding.etLastName.text.toString()
                        val email = binding.etEmail.text.toString()

                        val user = User(currentUser!!.uid,firstName,lastName,email)
                        Database().registerUser(this@RegisterActivity, user)


                    }else{
                        hideProgressBar()
                        Toast.makeText(this,"Registration failed.",Toast.LENGTH_LONG).show()
                    }

                }




        }


    }

    fun userRegistrationSuccess() {
        hideProgressBar()
        Toast.makeText(this, "You've registered successfully.", Toast.LENGTH_LONG).show()
        FirebaseAuth.getInstance().signOut()
        finish()
    }


    fun showProgressBar() {
        myProgressDialog = Dialog(this)
        myProgressDialog.setContentView(R.layout.progress_bar)
        myProgressDialog.setCanceledOnTouchOutside(false)
        myProgressDialog.setCancelable(false)
        val progressText = myProgressDialog.findViewById<TextView>(R.id.tv_progress_text)
        progressText.setText(R.string.please_wait)
        myProgressDialog.show()
    }

    fun hideProgressBar() {
        myProgressDialog.dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityRegisterBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)
        val root = binding.root.rootView
        root.setBackgroundColor(resources.getColor(R.color.primaryApp))


        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


        binding.btnLogin.setOnClickListener {
            registerUser()

//
//            auth.createUserWithEmailAndPassword(
//                binding.etEmail.text.toString(),
//                binding.etPassword.text.toString()
//            ).addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d("TAG", "createUserWithEmail:success")
//                    val user = auth.currentUser
//                    user?.updateProfile(userProfileChangeRequest {
//                        displayName = "${binding.etFirstName.text.toString()} ${binding.etLastName.text.toString()}"
//                    })?.addOnCompleteListener {
//                        if (task.isSuccessful) {
//                            Log.d("TAG", "User profile updated.")
//                        }
//                    }
////                    updateUI(user)
//                    Toast.makeText(this@RegisterActivity, "Registration success" ,Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(intent)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(this@RegisterActivity, "Registration failure ${task.exception}" ,Toast.LENGTH_SHORT).show()
//
//                    Toast.makeText(
//                        baseContext,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                    updateUI(null)
                }
            }
        }

