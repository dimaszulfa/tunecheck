package gfg.bangkit.capstone.tunecheck.utils

import android.app.Activity
import android.content.ContentResolver
import android.net.Uri
import android.webkit.MimeTypeMap

object Constants {

    //SP values
    const val SHOP_PREFERENCES: String = "ShopPreferences"
    const val CURRENT_NAME: String = "CurrentName"
    const val CURRENT_SURNAME: String = "CurrentSurname"
    const val EXTRA_USER_DETAILS: String = "extra_user_details"



    //get file extension
    fun getFileExt(activity: Activity,uri: Uri?):String?{
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }


}