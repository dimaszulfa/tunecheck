package gfg.bangkit.capstone.tunecheck.database

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import gfg.bangkit.capstone.tunecheck.DetailReportActivity
import gfg.bangkit.capstone.tunecheck.FormPengaduanActivity
import gfg.bangkit.capstone.tunecheck.HomeFragment
import gfg.bangkit.capstone.tunecheck.ListFragment
import gfg.bangkit.capstone.tunecheck.MainActivity
import gfg.bangkit.capstone.tunecheck.PengajuanActivity
import gfg.bangkit.capstone.tunecheck.ProfileActivity
import gfg.bangkit.capstone.tunecheck.SplashScreenActivity
import gfg.bangkit.capstone.tunecheck.UpdateProfileActivity
import gfg.bangkit.capstone.tunecheck.auth.LoginActivity
import gfg.bangkit.capstone.tunecheck.auth.RegisterActivity
import gfg.bangkit.capstone.tunecheck.model.Admin
import gfg.bangkit.capstone.tunecheck.model.Report
import gfg.bangkit.capstone.tunecheck.model.User
import gfg.bangkit.capstone.tunecheck.utils.Constants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class Database {




    private val db = FirebaseFirestore.getInstance()


    @RequiresApi(Build.VERSION_CODES.N)
    fun getStatistikCountData(fragment: Fragment){
        val db = FirebaseFirestore.getInstance()
//        val today = Calendar.getInstance()
//        val sevenDaysAgo = Calendar.getInstance().apply {
//            add(Calendar.DAY_OF_YEAR, -7)
//        }
//
//        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//        val todayStr = dateFormat.format(today.time)
//        val sevenDaysAgoStr = dateFormat.format(sevenDaysAgo.time)
//


        val dateFormat = SimpleDateFormat("d/M/yyyy", Locale.getDefault())

        // Initialize the map with dates from the past week
        val dateCountMap = HashMap<String, Int>()
        val calendar = Calendar.getInstance()
        for (i in 0..6) {
            val date = dateFormat.format(calendar.time)
            dateCountMap[date] = 0
            calendar.add(Calendar.DAY_OF_YEAR, -1)
        }

        // Calculate date range for the query
        val today = Calendar.getInstance()
        val sevenDaysAgo = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, -7)
        }

        val todayStr = dateFormat.format(today.time)
        val sevenDaysAgoStr = dateFormat.format(sevenDaysAgo.time)

        db.collection("reports")
//            .whereGreaterThanOrEqualTo("date", sevenDaysAgoStr)
//            .whereLessThanOrEqualTo("date", todayStr)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val myData = document.toObject(Report::class.java)

                    myData?.let {
                        if (it.date in dateCountMap) {
                            dateCountMap[it.date] = dateCountMap.getOrDefault(it.date, 0) + 1
                        }
                    }
                }

                // Convert the map to an array of pairs (date, count)
                val dateCountArray = dateCountMap.toList().toTypedArray()

                // Print the results
                dateCountArray.forEach { (date, count) ->
                    println("DateDatas: $date, Count: ${count.toString()}")
                }
                val sortedDataList = dateCountArray.sortedBy { parseDate(it.first) }.toTypedArray()

                // Cetak hasilnya
                sortedDataList.forEach { println("${it.first}: ${it.second}") }
//                dateCountArray.sortByDescending { it.first }
//
//                val dateCountArrayf = dateCountArray.mapNotNull { dateString ->
//                    val date = parseDate(dateString.first)
//                    date?.let { Pair(it, dateString.first) }
//                }
//
//                // Urutkan berdasarkan objek Date
//                val sortedDateCountArray = dateCountArrayf.sortedBy { it.first }
//
//                // Kembali ke daftar string tanggal yang diurutkan
//                val sortedDateStrings = sortedDateCountArray.map { it.second }

                // Cetak hasilnya
//                sortedDateStrings.forEach { println(it) }

                                when(fragment){
                    is HomeFragment -> {
                        fragment.getStatistikCount(sortedDataList)
                    }
                }
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }

//        db.collection("reports")
//            .whereGreaterThanOrEqualTo("date", sevenDaysAgoStr)
//            .whereLessThanOrEqualTo("date", todayStr)
//            .get()
//            .addOnSuccessListener { querySnapshot ->
//                val dateCountMap = HashMap<String, Int>()
//
//                for (document in querySnapshot.documents) {
//                    val myData = document.toObject(Report::class.java)
//                    myData?.let {
//                        dateCountMap[it.date] = dateCountMap.getOrDefault(it.date, 0) + 1
//                    }
//                }
//
//                // Convert the map to an array of pairs (date, count)
//                val dateCountArray = dateCountMap.toList().toTypedArray()
//                dateCountArray.forEach { (date, count) ->
//                    println("Date: $date, Count: $count")
//                }
//
//                when(fragment){
//                    is HomeFragment -> {
//                        fragment.getStatistikCount(dateCountArray)
//                    }
//                }
//            }
//            .addOnFailureListener { exception ->
//                println("Error getting documents: $exception")
//            }

    }


    fun parseDate(dateString: String): Date? {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return try {
            format.parse(dateString)
        } catch (e: Exception) {
            null
        }
    }
    fun getCountPelaporan(fragment: Fragment){

        db.collection("reports")
            .get()
            .addOnSuccessListener { document ->
//                return document.documents.size
                var countPelaporan = document.documents.size
                var countVerified = 0
                for(p in document.documents){
                    val report = p.toObject(Report::class.java)
                    if (report != null) {
                        // Do something with the user object
                        if(report.verified == 1){
                            countVerified += 1
                        }
                    }
                }
                Log.d("COUNT USER", countPelaporan.toString())

                when(fragment){
                    is HomeFragment -> {
                        fragment.pelaporanCount(countPelaporan, countVerified)
                    }
                }
            }

    }

        fun getCountUsers(fragment: Fragment){
        val dataCount = mutableListOf<User?>();
        var countUser = 0;
        db.collection("users")
            .get()
            .addOnSuccessListener { document ->
//                return document.documents.size
              countUser = document.documents.size
                Log.d("COUNT USER", countUser.toString())

                when(fragment){
                    is HomeFragment -> {
                        fragment.userCount(countUser)
                    }
                }
            }

    }

    fun getAdminList(activity: Activity){
        var isAdmin = false
        db.collection("admins")
            .whereEqualTo("userId",getUserID())
            .get()
            .addOnSuccessListener { document ->
//                val productList : ArrayList<Admin> = ArrayList()
                for(p in document.documents){
                    val user = p.toObject(Admin::class.java)
                    if (user != null) {
                        // Do something with the user object
                        isAdmin = true
                        println("User with userId 'admin': $user")
                    }else{
                        isAdmin = false
                    }
                }
//
                when (activity) {
                    is SplashScreenActivity -> {
                        activity.checkUserIsLoggedIn(isAdmin)
                    }
//                }

                }
            }

    }

    fun registerUser(activity: RegisterActivity, userInfo: User) {

        db.collection("users")
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressBar()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user.",
                    e
                )
            }
    }

    fun getUserID(): String {

        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID

    }

    fun addReport(activity: Activity,userDetailsHashmap : HashMap<String,Any>){
        Log.d("Hit report", "Hit report")
        db.collection("reports").document()
            .set(userDetailsHashmap)
            .addOnSuccessListener {
                when(activity){
                    is FormPengaduanActivity ->{
                        activity.reportSuccess()
                    }
                }
            }
            .addOnFailureListener {
                when(activity){
                    is FormPengaduanActivity ->{
                        activity.hideProgressBar()
                    }



                }
                println("Error while updating datas.")
            }





    }

    fun getCurrentUserDetails(activity: Activity) {

        db.collection("users")
            .document(getUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.i(javaClass.simpleName, document.toString())
                val user = document.toObject(User::class.java)

                //Save Name and Surname to SP.
                val sharedPreferences =
                    activity.getSharedPreferences(Constants.SHOP_PREFERENCES, Context.MODE_PRIVATE)
                sharedPreferences.edit().putString(Constants.CURRENT_NAME, user!!.firstName).apply()
                sharedPreferences.edit().putString(Constants.CURRENT_SURNAME, user.lastName).apply()
            Log.d("DATA USER", user.toString())

                when (activity) {
                    is LoginActivity -> {
                        activity.userLoggedInSuccess(user)
                    }
                    is MainActivity -> {
                         activity.getDataUser(user)
                    }
                }
//                when(activity){
//                    is UserProfileActivity -> {
//                        activity.et_email.setText(user.email)
//                        activity.et_first_name.setText(user.firstName)
//                        activity.et_last_name.setText(user.lastName)
//                    }
//
//                    is SettingsActivity ->{
//                        activity.userDetailsSuccess(user)
//                    }
//
//                }
            }


    }

    fun updateProfileDetails(activity: Activity, userDetailsHashmap: HashMap<String, Any>) {

        db.collection("users").document(getUserID())
            .update(userDetailsHashmap)
            .addOnSuccessListener {
                when (activity) {
                    is UpdateProfileActivity -> {
                        activity.userDetailsUpdateSuccess()
                    }
                }
            }
            .addOnFailureListener {
                when (activity) {
                    is ProfileActivity -> {
                        activity.hideProgressBar()
                    }


                }
                println("Error while updating datas.")
            }


    }


    fun uploadImageToStorage(activity: Activity, imageUri: Uri, imageType: String, report: HashMap<String, Any> ) {
        val storage: StorageReference = FirebaseStorage.getInstance().reference.child(
            imageType + System.currentTimeMillis() + "." +
                    Constants.getFileExt(
                        activity, imageUri
                    )
        )

        storage.putFile(imageUri)
            .addOnSuccessListener { taskSnapshot ->
                // The image upload is success
                Log.e(
                    "Firebase Image URL",
                    taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
                )

                // Get the downloadable url from the task snapshot
                taskSnapshot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { uri ->

                        Log.e("Downloadable Image URL", uri.toString())
                        report["imageUrl"] = uri.toString()
                        report["user"] = getUserID()


                        when (activity) {
                            is UpdateProfileActivity -> {
                                activity.imageUploadSuccess(uri.toString())
                            }
                            is FormPengaduanActivity -> {
                                activity.imageUploadSuccess(uri.toString(), report)
                            }
//                            is AddProductActivity -> {
//                                activity.imageUploadSuccess(uri.toString())
//                            }
                        }

                    }
            }
            .addOnFailureListener { exception ->

                when (activity) {
                    is UpdateProfileActivity -> {
                        activity.hideProgressBar()
                    }
//                    is AddProductActivity -> {
//                        activity.hideProgressBar()
//                    }
                }

                Log.e(
                    activity.javaClass.simpleName,
                    exception.message,
                    exception
                )
            }
    }
    fun getAllReport(fragment: Fragment){
        db.collection("reports")
            .get()
            .addOnSuccessListener { document ->

                val productsList: ArrayList<Report> = ArrayList()
                for (i in document.documents) {

                    val product = i.toObject(Report::class.java)
                    product!!.reportId = i.id

                    productsList.add(product)
                }

                when (fragment) {
                    is ListFragment -> {
                        fragment.successGetReports(productsList)
                    }



                }
            }
            .addOnFailureListener { e ->
                when (fragment) {
                    is ListFragment -> {
                        fragment.hideProgressBar()
                    }

                }

            }
    }


    fun getProductDetails(activity: DetailReportActivity, productId: String){
        db.collection("reports")
            .document(productId)
            .get()
            .addOnSuccessListener {document->
                val product = document.toObject(Report::class.java)
                activity.productDetailsSuccess(product!!)

            }
            .addOnFailureListener {
                println("Error while getting reports details!")
                activity.hideProgressBar()
            }


    }



    fun verifyReport(activity: DetailReportActivity, report: Report){
        val newReport = HashMap<String,Any>()
        newReport.put("LastName", report.lastName)
        newReport.put("date",report.date)
        newReport.put("firstName",report.firstName)
        newReport.put("imageUrl",report.imageUrl)
        newReport.put("pengaduan",report.pengaduan)
        newReport.put("subjek",report.subjek)
        newReport.put("user",report.user)
        newReport.put("verified",1)
        db.collection("reports").document(report.reportId)
            .update(
                newReport
            )
            .addOnSuccessListener {
                activity.productUpdateSuccess()
            }
            .addOnFailureListener {
                activity.hideProgressBar()
                println("Failed to update product."+it.toString())
            }

    }

    fun searchReports(fragment: Fragment, searchText: String){

        db.collection("reports")
            .orderBy("firstName")
            .startAt(searchText)
            .endAt(searchText + "\uf8ff")
            .get().addOnSuccessListener { document ->

                val productsList: ArrayList<Report> = ArrayList()
                for (i in document.documents) {

                    val product = i.toObject(Report::class.java)
                    product!!.reportId = i.id

                    productsList.add(product)
                }

                when (fragment) {
                    is ListFragment -> {
                        fragment.successSearch(productsList)
                    }



                }
            }
            .addOnFailureListener { e ->
                when (fragment) {
                    is ListFragment -> {
                        fragment.hideProgressBar()
                    }

                }

            }




    }


}







