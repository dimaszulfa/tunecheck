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
import gfg.bangkit.capstone.tunecheck.FormPengaduanActivity
import gfg.bangkit.capstone.tunecheck.HomeFragment
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
            .whereGreaterThanOrEqualTo("date", sevenDaysAgoStr)
            .whereLessThanOrEqualTo("date", todayStr)
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

                                when(fragment){
                    is HomeFragment -> {
                        fragment.getStatistikCount(dateCountArray)
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
}




//
//
//    }
//    fun uploadProductDetails(activity: AddProductActivity, productInfo: Product){
//
//        db.collection("products")
//            .document()
//            .set(productInfo,SetOptions.merge())
//            .addOnSuccessListener {
//                activity.productUploadSuccess()
//            }
//            .addOnFailureListener {
//                activity.hideProgressBar()
//                Toast.makeText(activity,"Error!",Toast.LENGTH_LONG).show()
//            }
//
//
//    }
//    fun getProductList(fragment: Fragment){
//        db.collection("products")
//            .whereEqualTo("user_id",getUserID())
//            .get()
//            .addOnSuccessListener { document->
//                val productList : ArrayList<Product> = ArrayList()
//                for(p in document.documents){
//                    val product = p.toObject(Product::class.java)
//                    product!!.product_id = p.id
//                    productList.add(product)
//                }
//
//                when(fragment){
//                    is ProductFragment ->{
//                        fragment.successProductListFS(productList)
//                    }
//                }
//            }
//
//    }
//    fun getItemsForDashboard(fragment: DashboardFragment){
//        db.collection("products")
//            .get()
//            .addOnSuccessListener { document ->
//                println(document.documents.toString())
//                val productList : ArrayList<Product> = ArrayList()
//                for(p in document.documents){
//                    val product = p.toObject(Product::class.java)
//                    product!!.product_id = p.id
//                    productList.add(product)
//                }
//                fragment.successDashboardList(productList)
//            }
//            .addOnFailureListener {
//                fragment.hideProgressBar()
//                println("Error with dashboard items.")
//            }
//    }
//    fun deleteProduct(fragment: ProductFragment, productId: String){
//        db.collection("products")
//            .document(productId)
//            .delete()
//            .addOnSuccessListener {
//                fragment.productDeleteSuccess()
//
//
//            }.addOnFailureListener {
//                println("Error while deleting product")
//                fragment.hideProgressBar()
//            }
//
//
//    }
//    fun getProductDetails(activity: ProductDetailsActivity, productId: String){
//        db.collection("products")
//            .document(productId)
//            .get()
//            .addOnSuccessListener {document->
//                val product = document.toObject(Product::class.java)
//                activity.productDetailsSuccess(product!!)
//
//            }
//            .addOnFailureListener {
//                println("Error while getting product details!")
//                activity.hideProgressBar()
//            }
//
//
//    }
//    fun updateProduct(activity: AddProductActivity, productList: HashMap<String,Any>, product: Product){
//        db.collection("products").document(product.product_id)
//            .update(productList)
//            .addOnSuccessListener {
//                activity.productUpdateSuccess()
//            }
//            .addOnFailureListener {
//                activity.hideProgressBar()
//                println("Failed to update product."+it.toString())
//            }
//
//    }
//    fun addItemToCart(activity: ProductDetailsActivity, addToCart: Cart){
//
//        db.collection("cart_items")
//            .document()
//            .set(addToCart, SetOptions.merge())
//            .addOnSuccessListener {
//                activity.addToCartSuccess()
//
//            }.addOnFailureListener {
//                println("Error while creating the cart doc.")
//            }
//
//
//    }
//    fun checkIfItemExistsInCart(activity: ProductDetailsActivity, productId: String){
//
//        db.collection("cart_items")
//            .whereEqualTo("user_id",getUserID())
//            .whereEqualTo("product_id",productId)
//            .get()
//            .addOnSuccessListener { document->
//                if(document.documents.size>0){
//                    activity.productExistsInCard()
//                }
//                else{
//                    activity.hideProgressBar()
//                }
//
//            }
//            .addOnFailureListener {
//                activity.hideProgressBar()
//            }
//
//
//
//    }
//    fun getAllProductsList(activity: Activity){
//        db.collection("products")
//            .get()
//            .addOnSuccessListener { document ->
//
//                val productsList: ArrayList<Product> = ArrayList()
//                for (i in document.documents) {
//
//                    val product = i.toObject(Product::class.java)
//                    product!!.product_id = i.id
//
//                    productsList.add(product)
//                }
//
//                when (activity) {
//                    is CartListActivity -> {
//                        activity.successProductListFromFS(productsList)
//                    }
//
//
//
//                    is CheckoutActivity -> {
//                        activity.successProductListFromFS(productsList)
//                    }
//
//                }
//            }
//            .addOnFailureListener { e ->
//                when (activity) {
//                    is CartListActivity -> {
//                        activity.hideProgressBar()
//                    }
//
//
//                    is CheckoutActivity -> {
//                        activity.hideProgressBar()
//                    }
//                }
//
//            }
//    }
//    fun getCartList(activity: Activity){
//        db.collection("cart_items")
//            .whereEqualTo("user_id",getUserID())
//            .get()
//            .addOnSuccessListener {document->
//                val list: ArrayList<Cart> = ArrayList()
//                for (cart in document.documents){
//                    val cartItem = cart.toObject(Cart::class.java)
//                    cartItem!!.id = cart.id
//                    list.add(cartItem)
//                }
//
//                when(activity){
//                    is CartListActivity ->{
//                        activity.successCartItemList(list)
//                    }
//                    is CheckoutActivity ->{
//                        activity.successCartItemList(list)
//                    }
//                }
//            }.addOnFailureListener {
//                when(activity){
//                    is CartListActivity ->{
//                        activity.hideProgressBar()
//                    }
//                    is CheckoutActivity ->{
//                        activity.hideProgressBar()
//                    }
//                }
//            }
//
//
//    }
//    fun updateCartList(context: Context,cartId: String,itemHashMap: HashMap<String,Any>){
//        db.collection("cart_items")
//            .document(cartId)
//            .update(itemHashMap)
//            .addOnSuccessListener {
//                when(context){
//                    is CartListActivity ->{
//                        context.itemUpdateSuccess()
//                    }
//                }
//
//
//            }
//            .addOnFailureListener { when(context){
//                is CartListActivity ->{
//                    context.hideProgressBar()
//                }
//            } }
//
//    }
//   fun removeItemInCart(context: Context,cartId: String){
//       db.collection("cart_items")
//           .document(cartId)
//           .delete()
//           .addOnSuccessListener {
//               when(context){
//                   is CartListActivity ->{
//                       context.itemRemovedSuccess()
//                   }
//               }
//
//
//           }.addOnFailureListener {
//               when(context){
//                   is CartListActivity ->{
//                       context.hideProgressBar()
//                       println("Error while removing cart Item.")
//                   }
//               }
//           }
//   }
//    fun addAddress(activity: EditAddAddressActivity, addressInfo: Address){
//
//        db.collection("addresses")
//            .document()
//            .set(addressInfo, SetOptions.merge())
//            .addOnSuccessListener {
//                activity.editAddAddressToDBSuccess()
//            }.addOnFailureListener {
//                activity.hideProgressBar()
//            }
//    }
//    fun getAddresses(activity: AddressActivity){
//        db.collection("addresses")
//            .whereEqualTo("userId",getUserID())
//            .get()
//            .addOnSuccessListener {document->
//                val addressList : ArrayList<Address> = ArrayList()
//                for(i in document.documents){
//                    val address = i.toObject(Address::class.java)
//                    address!!.id = i.id //burada her adress diye bir adres objesi oluşturup bu objeye id veriyoruz. Bu id (i.id) belgemizin ismi. Yani random bir id.
//                    addressList.add(address)
//                }
//                activity.addressListFromDBSuccess(addressList)
//
//            }
//            .addOnFailureListener {
//                activity.hideProgressBar()
//            }
//    }
//    fun updateAddress(activity: EditAddAddressActivity, addressInfo: Address, addressId: String) {
//
//        db.collection("addresses")
//            .document(addressId)
//            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge
//            .set(addressInfo, SetOptions.merge())
//            .addOnSuccessListener {
//
//                // Here call a function of base activity for transferring the result to it.
//                activity.editAddAddressToDBSuccess()
//            }
//            .addOnFailureListener { e ->
//                activity.hideProgressBar()
//                Log.e(
//                    activity.javaClass.simpleName,
//                    "Error while updating the Address.",
//                    e
//                )
//            }
//    }
//    fun deleteAddress(activity: AddressActivity, addressId: String){
//        db.collection("addresses")
//            .document(addressId)
//            .delete()
//            .addOnSuccessListener {
//                activity.deleteAddressSuccessful()
//            }
//            .addOnFailureListener {
//                activity.hideProgressBar()
//            }
//    }
//    fun createOrder(activity: CheckoutActivity, order: Order){
//        db.collection("orders")
//            .document()
//            .set(order, SetOptions.merge())
//            .addOnSuccessListener {
//                activity.orderCreatedSuccess()
//            }
//            .addOnFailureListener {
//                activity.hideProgressBar()
//            }
//    }
//    fun updateProductCartDetails(activity: CheckoutActivity, cartList: ArrayList<Cart>, order: Order){
//        val write = db.batch()
//
//        for (cart in cartList) {
//
//            val soldProduct = SoldProduct(
//                cart.product_owner_id,
//                cart.title,
//                cart.price,
//                cart.cart_quantity,
//                cart.image,
//                order.title,
//                order.order_date,
//                order.sub_total_amount,
//                order.shipping_charge,
//                order.total_amount,
//                order.address
//            )
//
//            val documentReference = db.collection("sold_products")
//                .document(cart.product_id)
//
//            write.set(documentReference, soldProduct)
//        }
//
//        for (cart in cartList) {
//
//            val documentReference = db.collection("cart_items")
//                .document(cart.id)
//            write.delete(documentReference)
//        }
//
//        write.commit().addOnSuccessListener {
//
//            activity.cartDetailsUpdatedSuccessfully()
//
//        }.addOnFailureListener {
//            activity.hideProgressBar()
//        }
//
//    }
//
//    fun getOrderList(fragment: OrdersFragment){
//        db.collection("orders")
//            .whereEqualTo("user_id",getUserID())
//            .get()
//            .addOnSuccessListener { document->
//                val list: ArrayList<Order> = ArrayList()
//                for(i in document.documents){
//                    val orderItem = i.toObject(Order::class.java)
//                    orderItem!!.id = i.id
//                    list.add(orderItem)
//                }
//                fragment.showOrderList(list)
//
//            }
//            .addOnFailureListener {
//                fragment.hideProgressBar()
//            }
//
//    }
//
//    fun getSoldProductsList(activity: SoldProductsActivity){
//        db.collection("sold_products")
//            .whereEqualTo("user_id",getUserID())
//            .get()
//            .addOnSuccessListener {document->
//                val list: ArrayList<SoldProduct> = ArrayList()
//                for(i in document.documents){
//                    val soldProduct = i.toObject(SoldProduct::class.java)!!
//                    soldProduct.id = i.id
//                    list.add(soldProduct)
//                }
//                activity.successSoldProductList(list)
//
//            }
//            .addOnFailureListener {
//                println("error while getting sold products")
//                it.printStackTrace()
//                activity.hideProgressBar()
//            }
//    }
//







