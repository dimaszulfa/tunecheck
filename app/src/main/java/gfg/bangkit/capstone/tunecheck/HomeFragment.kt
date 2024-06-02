package gfg.bangkit.capstone.tunecheck

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aachartcreator.aa_toAAOptions
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AADataLabels
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAXAxis
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAZonesElement
import gfg.bangkit.capstone.tunecheck.database.Database
import gfg.bangkit.capstone.tunecheck.utils.UiComponentsFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : UiComponentsFragment() {
    // TODO: Rename and change types of parameters

    private lateinit var  view: View

    fun pelaporanCount(pelaporanCount: Int, verifiedCount: Int){
        val pelaporanCountTv = view.findViewById<TextView>(R.id.tvJumlahPelaporan)
        val verificationCountTv = view.findViewById<TextView>(R.id.tvJumlahVerifikasi)
        pelaporanCountTv.setText(pelaporanCount.toString())
        verificationCountTv.setText(verifiedCount.toString())
    }
    fun userCount(userCount: Int){
        val userCountTextView = view.findViewById<TextView>(R.id.tvTotalUser)
        userCountTextView.setText(userCount.toString())
    }
    private lateinit var statistikData: Array<Pair<String, Int>>
    fun getStatistikCount(data: Array<Pair<String, Int>>){
        statistikData = data
        var dataStastikCount = mutableListOf<Int>()
        statistikData.forEachIndexed { index, pair ->
            val date = pair.first
            val count = pair.second
            dataStastikCount.add(count)
        }.apply {

            val aaChartView = view.findViewById<AAChartView>(R.id.aa_chart_view)
            val aaChartModel: AAChartModel = AAChartModel()
                .chartType(AAChartType.Areaspline)
                .title("Statistik Pelaporan")
                .backgroundColor("#FFFFFF")
                .dataLabelsEnabled(true)
                .series(
                    arrayOf(
                        AASeriesElement()
                            .name("Jumlah Pelaporan per-hari dalam rentang waktu satu minggu")

                            .data(

                                arrayOf(listOf(statistikData[0].first,statistikData[0].second),listOf(statistikData[1].first,statistikData[1].second),listOf(statistikData[2].first,statistikData[2].second),listOf(statistikData[3].first,statistikData[3].second),listOf(statistikData[4].first,statistikData[4].second),listOf(statistikData[5].first,statistikData[5].second),listOf(statistikData[6].first,statistikData[6].second))
                                    ).dataLabels(AADataLabels())





                    ),
                )

            val aaOptions = aaChartModel.aa_toAAOptions()
            aaOptions.xAxis = AAXAxis()
                .categories(arrayOf(statistikData[0].first,statistikData[1].first,statistikData[2].first,statistikData[3].first,statistikData[4].first,statistikData[5].first,statistikData[6].first))

            aaChartView.aa_drawChartWithChartOptions(aaOptions)
        }
        Log.d("DATA DATE", statistikData[0].toString())
        hideProgressBar()

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        showProgressBar("Loading..")
        Database().getStatistikCountData(this)
        Database().getCountUsers(this)
        Database().getCountPelaporan(this)

        view = inflater.inflate(R.layout.fragment_home, container, false)




        return view
    }
    }

