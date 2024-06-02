package gfg.bangkit.capstone.tunecheck.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Report(

    val lastName: String = "",
    val date: String = "",
    val firstName: String = "",
    val imageUrl: String = "",
    val location: String = "",
    val pengaduan: String = "",
    val subjek: String = "",
    var user: String = "",
    var verified: Int = 0,
    var reportId: String = ""

): Parcelable