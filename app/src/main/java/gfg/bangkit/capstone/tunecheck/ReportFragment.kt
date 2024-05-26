//package gfg.bangkit.capstone.tunecheck
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.*
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//
//import gfg.bangkit.capstone.tunecheck.utils.UiComponentsFragment
//
//
//class ReportFragment : UiComponentsFragment() {
//    private var _binding: FragmentPaketWisataBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        _binding = FragmentPaketWisataBinding.inflate(inflater, container, false)
//        return binding.root
//
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//        getProductList()
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.add_paket_wisata_top_menu,menu)
//        super.onCreateOptionsMenu(menu, inflater)
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        (activity as? AppCompatActivity)?.supportActionBar?.show()
//        binding.fabAdd.setOnClickListener {
//            startActivity(Intent(activity, AddPaketWisataActivity::class.java))
//        }
//    }
//
//
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        when (id) {
//            R.id.add_paket_wisata -> {
//                startActivity(Intent(activity, AddPaketWisataActivity::class.java))
//                return true
//            }
//            R.id.sold_products ->{
//                startActivity(Intent(activity, AddPaketWisataActivity::class.java))
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    fun successProductListFS(productList: ArrayList<PaketWisata>){
//        hideProgressBar()
//        if(productList.size>0){
//            binding.rvMyProductItems.visibility = View.VISIBLE
//            binding.tvNoProductsFound.visibility = View.GONE
//
//            binding.rvMyProductItems.layoutManager = LinearLayoutManager(activity)
//            binding.rvMyProductItems.setHasFixedSize(true)
//            val adapterProducts = ReportListAdapter(requireActivity(),productList,this)
//            binding.rvMyProductItems.adapter = adapterProducts
//        }else{
//            binding.rvMyProductItems.visibility = View.GONE
//            binding.tvNoProductsFound.visibility = View.VISIBLE
//        }
//
//
//
//    }
//
//    fun getProductList(){
//        showProgressBar("Mohon menunggu...")
//        Database().getProductList(this)
//
//
//    }
//
//
//
//    fun deleteProduct(productId: String) {
//
//        val builder = AlertDialog.Builder(requireActivity())
//        builder.setTitle("Hapus Paket wisata")
//        builder.setMessage("Apakah anda yakin akan menghapus paket wisata")
//        builder.setIcon(R.drawable.ic_baseline_warning_24)
//        builder.setPositiveButton("Ya"){ d, _ ->
//            showProgressBar("Mohon Menunggu")
//            Database().deleteProduct(this,productId)
//            d.dismiss()
//        }
//        builder.setNegativeButton("Tidak"){ d, _ ->
//            d.dismiss()
//
//        }
//        val alert : AlertDialog = builder.create()
//        alert.setCancelable(false)
//        alert.show()
//
//
//    }
//
//
//    fun productDeleteSuccess(){
//        hideProgressBar()
//        getProductList()
//
//    }
//
//
//
//}
