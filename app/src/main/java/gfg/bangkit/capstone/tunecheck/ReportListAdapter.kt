//package gfg.bangkit.capstone.tunecheck
//
//import android.content.Context
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageButton
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//
//open class ReportListAdapter(private val context: Context, private var list: ArrayList<PaketWisata>, private val fragment: ReportFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.product_item_row, parent, false))
//    }
//
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val model = list[position]
//
//        if (holder is ProductViewHolder) {
//            val iv_item_image = holder.itemView.findViewById<ImageView>(R.id.iv_item_image)
//
//            val tv_item_name = holder.itemView.findViewById<TextView>(R.id.tv_item_name)
//            val tv_item_price= holder.itemView.findViewById<TextView>(R.id.tv_item_price)
//            val ib_delete_product= holder.itemView.findViewById<ImageButton>(R.id.ib_delete_product)
//
//            LoadGlide(context).loadProductImage(model.image, iv_item_image)
//
//            tv_item_name.text = model.title
//            tv_item_price.text = "${model.price}"
//
//
//            ib_delete_product.setOnClickListener {
//                fragment.deleteProduct(model.product_id)
//
//            }
//
//            holder.itemView.setOnClickListener {
//                val context = holder.itemView.context
//                val intent = Intent(context, PaketDetailActivity::class.java)
//                intent.putExtra("extra_product_id", model.product_id)
////                intent.putExtra("extra_product_owner_id",model.user_id)
//                intent.putExtra("extra_edit_perm",1)
//                context.startActivity(intent)
//            }
//
//        }
//    }
//
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//}