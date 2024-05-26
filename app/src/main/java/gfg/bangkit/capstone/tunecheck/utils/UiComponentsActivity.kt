package gfg.bangkit.capstone.tunecheck.utils

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import gfg.bangkit.capstone.tunecheck.R

//This activity is for gathering common features that app uses in different activities.
//For now I created a few lines just for progressBar.

open class UiComponentsActivity : AppCompatActivity() {

    private lateinit var myProgressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_components)
    }


    fun showProgressBar(text: String) {
        myProgressDialog = Dialog(this)
        myProgressDialog.setContentView(R.layout.dialog_progress)
        val tv = myProgressDialog.findViewById<TextView>(R.id.tv_progress_text)
        tv.text = text
        myProgressDialog.setCancelable(false)
        myProgressDialog.setCanceledOnTouchOutside(false)
        myProgressDialog.show()
    }

    fun hideProgressBar() {
        myProgressDialog.dismiss()
    }






}