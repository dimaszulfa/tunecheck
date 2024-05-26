package gfg.bangkit.capstone.tunecheck

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import gfg.bangkit.capstone.tunecheck.auth.LoginActivity
import gfg.bangkit.capstone.tunecheck.database.Database
import gfg.bangkit.capstone.tunecheck.databinding.ActivityProfileBinding
import gfg.bangkit.capstone.tunecheck.utils.UiComponentsActivity

class ProfileActivity : UiComponentsActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.btnLogout.setOnClickListener {
            auth.signOut().apply {
                val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }


    }
}