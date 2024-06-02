package gfg.bangkit.capstone.tunecheck

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import gfg.bangkit.capstone.tunecheck.database.Database
import gfg.bangkit.capstone.tunecheck.databinding.ActivityDetailReportBinding
import gfg.bangkit.capstone.tunecheck.model.Report
import gfg.bangkit.capstone.tunecheck.utils.LoadGlide
import gfg.bangkit.capstone.tunecheck.utils.UiComponentsActivity

class DetailReportActivity : UiComponentsActivity() {

    private lateinit var binding: ActivityDetailReportBinding
    private var myReportId: String = ""
    private var editPerm : Int = 0
    private lateinit var productModel: Report
    private lateinit var myProductDetails: Report
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("extra_report_id")){
            myReportId = intent.getStringExtra("extra_report_id")!!
        }




        getProductDetails()


    }


    fun getProductDetails(){
        showProgressBar(getString(R.string.please_wait))
        Database().getProductDetails(this@DetailReportActivity,myReportId)

    }

    fun verifyReport(product: Report){
        showProgressBar(getString(R.string.please_wait))
        Database().verifyReport(this@DetailReportActivity,product)

    }



    fun productDetailsSuccess(product: Report){
        hideProgressBar()
        myProductDetails = product
        LoadGlide(this@DetailReportActivity).loadProductImage(product.imageUrl,binding.ivImageDetailLaporan)
        binding.tvUsername.setText(product.firstName + product.lastName)
        binding.tvDateDetail.setText("Dilaporkan pada tanggal "+product.date)
        binding.tvIsiLaporan.setText(product.pengaduan)
        if(product.verified == 0){
            binding.btnVerif.setBackgroundColor(Color.parseColor("#007BE5"))
            binding.btnVerif.setOnClickListener {
                product.verified = 1
                verifyReport(product)
            }
        }else{
            binding.btnVerif.setBackgroundColor(Color.parseColor("#C4C4C4"))
            binding.btnVerif.isEnabled = false
        }
        product.reportId = myReportId
        productModel = product


    }

    fun productUpdateSuccess(){
        hideProgressBar()
        Toast.makeText(this,"Report verified!", Toast.LENGTH_LONG).show()
        getProductDetails()
    }

}