package gfg.bangkit.capstone.tunecheck.adapter


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import gfg.bangkit.capstone.tunecheck.DetailReportActivity
import gfg.bangkit.capstone.tunecheck.R
import gfg.bangkit.capstone.tunecheck.databinding.ActivityDetailReportBinding
import gfg.bangkit.capstone.tunecheck.model.Report

open class ReportsAdapter(private val context: Context, private var list: ArrayList<Report>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class ReportsViewHolder(view: View) : RecyclerView.ViewHolder(view)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ReportsAdapter.ReportsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_report, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if (holder is ReportsViewHolder) {
//            LoadGlide(context).loadProductImage(model.image, holder.itemView.iv_dashboard_item_image)
            val tvUsername = holder.itemView.findViewById<TextView>(R.id.tvUsername)
            val tvDescReport =  holder.itemView.findViewById<TextView>(R.id.tvDescReport)
            val cvBackground =  holder.itemView.findViewById<CardView>(R.id.cvVerif)
            val tvVerif =  holder.itemView.findViewById<TextView>(R.id.tvVerif)

            tvUsername.setText(model.firstName)
            tvDescReport.setText(model.subjek)
            if(model.verified == 0){
                cvBackground.setBackgroundColor(Color.parseColor("#FF0000"))
            }else{
                cvBackground.setBackgroundColor(Color.parseColor("#5FDE64"))
                tvVerif.setText("Sudah Terverifikasi")

            }
            holder.itemView.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, DetailReportActivity::class.java)
                intent.putExtra("extra_report_id",model.reportId)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}