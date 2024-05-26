package gfg.bangkit.capstone.tunecheck

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import gfg.bangkit.capstone.tunecheck.auth.LoginActivity
import gfg.bangkit.capstone.tunecheck.database.Database

class SplashScreenActivity : AppCompatActivity() {

    fun checkUserIsLoggedIn(admin: Boolean){
        if (admin) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private val SPLASH_TIME_OUT: Long = 3000 // Durasi SplashScreen
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
// Initialize Firebase Auth
        auth = Firebase.auth
        // Handler untuk menjalankan tindakan setelah SPLASH_TIME_OUT
        Handler().postDelayed({
            // Tindakan setelah durasi SplashScreen
            // Contohnya, navigasi ke MainActivity
//            checkUserIsLoggedIn(auth)
        if(auth.currentUser != null){
            Database().getAdminList(this)
        }else{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
          // Tutup activity SplashScreen agar tidak kembali ke SplashScreen saat menekan tombol "Kembali"
        }, SPLASH_TIME_OUT)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser

    }
}