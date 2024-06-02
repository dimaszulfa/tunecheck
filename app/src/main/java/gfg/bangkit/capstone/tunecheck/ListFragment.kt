package gfg.bangkit.capstone.tunecheck

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import gfg.bangkit.capstone.tunecheck.adapter.ReportsAdapter
import gfg.bangkit.capstone.tunecheck.model.User
import gfg.bangkit.capstone.tunecheck.adapter.UsersAdapter
import gfg.bangkit.capstone.tunecheck.database.Database
import gfg.bangkit.capstone.tunecheck.databinding.FragmentListBinding
import gfg.bangkit.capstone.tunecheck.databinding.FragmentProfileBinding
import gfg.bangkit.capstone.tunecheck.model.Report
import gfg.bangkit.capstone.tunecheck.utils.UiComponentsFragment


class ListFragment : UiComponentsFragment() {


    private var _binding: FragmentListBinding? = null
    private lateinit var reports: ArrayList<Report>

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentListBinding.inflate(layoutInflater,container,false)


        binding.btnSearch.setOnClickListener {
            search(binding.searchEditText.text.toString())
        }

        return binding.root;




    }

    private fun search(searchText: String) {
        showProgressBar("Sedang mencari data")
      Database().searchReports(this, searchText)
    }

    fun successSearch(productList: ArrayList<Report>){
        hideProgressBar();
        binding.rvReports.adapter = null
        val adapterDashboard = ReportsAdapter(requireActivity(),productList)
        binding.rvReports.adapter = adapterDashboard
        binding.rvReports.adapter?.notifyDataSetChanged()

    }

    override fun onResume() {
        super.onResume()
        getProductsFromFB()
    }

    private fun getProductsFromFB(){
        showProgressBar(getString(R.string.please_wait))
        Database().getAllReport(this)

    }


    fun successGetReports(allProductList : ArrayList<Report>){
        hideProgressBar()
        if(allProductList.size>0){
//            tv_no_dashboard_items_found.visibility = View.GONE
            binding.rvReports.visibility = View.VISIBLE
            binding.rvReports.layoutManager = LinearLayoutManager(activity)
            binding.rvReports.setHasFixedSize(true)
            val adapterDashboard = ReportsAdapter(requireActivity(),allProductList)
            binding.rvReports.adapter = adapterDashboard
        }else{
            binding.rvReports.visibility = View.GONE
//            tv_no_dashboard_items_found.visibility = View.VISIBLE
        }


    }

}