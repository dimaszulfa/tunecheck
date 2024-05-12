package gfg.bangkit.capstone.tunecheck.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import gfg.bangkit.capstone.tunecheck.R
import gfg.bangkit.capstone.tunecheck.databinding.ActivityLoginBinding
import gfg.bangkit.capstone.tunecheck.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)
        val root = binding.root.rootView
        root.setBackgroundColor(resources.getColor(R.color.primaryApp))

        binding.btnLogin.setOnClickListener {



            auth.createUserWithEmailAndPassword(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            ).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = auth.currentUser
                    user?.updateProfile(userProfileChangeRequest {
                        displayName = "${binding.etFirstName.text.toString()} ${binding.etLastName.text.toString()}"
                    })?.addOnCompleteListener {
                        if (task.isSuccessful) {
                            Log.d("TAG", "User profile updated.")
                        }
                    }
//                    updateUI(user)
                    Toast.makeText(this@RegisterActivity, "Registration success" ,Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this@RegisterActivity, "Registration failure ${task.exception}" ,Toast.LENGTH_SHORT).show()

                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
//                    updateUI(null)
                }
            }
        }
    }
}