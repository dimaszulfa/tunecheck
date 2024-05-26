package gfg.bangkit.capstone.tunecheck.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Admin(
    val userId: String = ""
): Parcelable