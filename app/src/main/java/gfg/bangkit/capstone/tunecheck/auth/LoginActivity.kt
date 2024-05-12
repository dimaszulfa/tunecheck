package gfg.bangkit.capstone.tunecheck.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import gfg.bangkit.capstone.tunecheck.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            val binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)
               val root =  binding.root.rootView
            auth = Firebase.auth
            root.setBackgroundColor(resources.getColor(R.color.primaryApp))


        binding.btnLogin.setOnClickListener {

            if(binding.etUsername.text.toString() == "admin@gmail.com" && binding.etPassword.text.toString() == "Admin@123"){
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }else{
                auth.signInWithEmailAndPassword(binding.etUsername.text.toString(), binding.etPassword.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success")
                            val user = auth.currentUser
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
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
}