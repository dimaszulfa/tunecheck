package gfg.bangkit.capstone.tunecheck.auth

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import gfg.bangkit.capstone.tunecheck.HomeActivity
import gfg.bangkit.capstone.tunecheck.MainActivity
import gfg.bangkit.capstone.tunecheck.R
import gfg.bangkit.capstone.tunecheck.database.Database
import gfg.bangkit.capstone.tunecheck.databinding.ActivityLoginBinding
import gfg.bangkit.capstone.tunecheck.model.User
import gfg.bangkit.capstone.tunecheck.utils.Constants
import gfg.bangkit.capstone.tunecheck.utils.UiComponentsActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var myProgressDialog: Dialog

    fun userLoggedInSuccess(user: User){
        hideProgressBar()
        val sharedPreferences = getSharedPreferences(Constants.SHOP_PREFERENCES,MODE_PRIVATE)
        val username = sharedPreferences.getString(Constants.CURRENT_NAME,"")
        val welcomeString : String = "Selamat Datang "+"$username"
        Toast.makeText(this,welcomeString,Toast.LENGTH_LONG).show()
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra(Constants.EXTRA_USER_DETAILS,user)
        startActivity(intent)


        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            val binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)
               val root =  binding.root.rootView
            auth = Firebase.auth
            root.setBackgroundColor(resources.getColor(R.color.primaryApp))


//            if(auth.currentUser != null){
//                val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)
//            }else{
//                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)
//            }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }



        binding.btnLogin.setOnClickListener {
            showProgressBar()

            if(binding.etUsername.text.toString() == "admin@gmail.com" && binding.etPassword.text.toString() == "Admin@123"){
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }else{
                auth.signInWithEmailAndPassword(binding.etUsername.text.toString(), binding.etPassword.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            Database().getCurrentUserDetails(this@LoginActivity)
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("TAG", "signInWithEmail:success")
//                            val user = auth.currentUser
//                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            hideProgressBar()
                            Log.w("TAG", "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed. ${task.exception}",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }

        }



    }

    private fun showProgressBar() {
        myProgressDialog = Dialog(this)
        myProgressDialog.setContentView(R.layout.progress_bar)
        myProgressDialog.setCanceledOnTouchOutside(false)
        myProgressDialog.setCancelable(false)
        val progressText = myProgressDialog.findViewById<TextView>(R.id.tv_progress_text)
        progressText.setText(R.string.please_wait)
        myProgressDialog.show()
    }

    private fun hideProgressBar() {
        myProgressDialog.dismiss()
    }
}