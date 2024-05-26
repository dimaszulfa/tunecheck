package gfg.bangkit.capstone.tunecheck

import android.os.Bundle

import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import gfg.bangkit.capstone.tunecheck.databinding.ActivityReportDetailBinding
import gfg.bangkit.capstone.tunecheck.model.Report
import gfg.bangkit.capstone.tunecheck.utils.UiComponentsActivity


class ReportDetailActivity : UiComponentsActivity(),View.OnClickListener {

    private var myProductId: String = ""
    private var editPerm : Int = 0
    private lateinit var reportModel: Report
    var productOwnerId : String = ""
//    private lateinit var myProductDetails: PaketWisata
    private lateinit var binding: ActivityReportDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(intent.hasExtra("extra_product_id")){
            myProductId = intent.getStringExtra("extra_product_id")!!
            Log.d("Produk ID", myProductId)
            editPerm = intent.getIntExtra("extra_edit_perm",0)

        }


        if(intent.hasExtra("extra_product_owner_id")){
            productOwnerId = intent.getStringExtra("extra_product_owner_id").toString()
        }






//*        getProductDetails()




    }




//*    fun getProductDetails(){
//        showProgressBar("Harap menunggu")
//        Database().getProductDetails(this@PaketDetailActivity,myProductId)
//
//    }



//*    fun productDetailsSuccess(product: Report){
//        myProductDetails = product
//        Log.d("DETAIL DATA PRODUCT PAKET WISATA", product.toString())
//        LoadGlide(this@PaketDetailActivity).loadProductImage(product.image,binding.ivProductDetailImage)
//       binding.tvProductDetailsAvailableQuantity.text = product.stock_quantity
//        binding.tvProductDetailsDescription.text = product.description
//        binding.tvProductDetailsPrice.text = "Rp. ${product.price}"
//        binding.tvProductDetailsTitle.text = product.title
//        product.product_id = myProductId
//        reportModel = product
//
//            if(Database().getUserID() == product.user_id){
//                hideProgressBar()
//
//            }
//
//
//
//    }
    override fun onClick(v: View?) {

    }








}