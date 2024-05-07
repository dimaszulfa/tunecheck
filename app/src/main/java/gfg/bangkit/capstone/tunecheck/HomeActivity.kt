package gfg.bangkit.capstone.tunecheck

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import me.ibrahimsn.lib.SmoothBottomBar

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var bottomBar: SmoothBottomBar

        setContentView(R.layout.activity_home)
        loadFragment(HomeFragment())
        bottomBar = findViewById(R.id.bottomNav)
        bottomBar.onItemSelected = {
//            when (it) {
                Log.d("TAG TEST", it.toString())
            when(it){
                0 -> {
                    loadFragment(HomeFragment())
                }
                1 -> {
                    loadFragment(ListFragment())
                }
                2 -> {
                    loadFragment(ProfileFragment())
                  }

            }
        }



    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}