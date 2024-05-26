package gfg.bangkit.capstone.tunecheck.utils

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import gfg.bangkit.capstone.tunecheck.R


open class UiComponentsFragment : Fragment() {

    private lateinit var myProgressDialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ui_components, container, false)
    }

    fun showProgressBar(text: String) {
        myProgressDialog = Dialog(requireActivity())
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