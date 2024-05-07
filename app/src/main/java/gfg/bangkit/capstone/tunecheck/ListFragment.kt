package gfg.bangkit.capstone.tunecheck

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import gfg.bangkit.capstone.tunecheck.model.User
import gfg.bangkit.capstone.tunecheck.adapter.UsersAdapter


class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        Log.d("CREATED", "CREATED")
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val firestore = Firebase.firestore
        val collectionReference = firestore.collection("users")

//        firestore.collection("users")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d("SUKSES", "${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w("ERROR", "Error getting documents.", exception)
//            }


        collectionReference.addSnapshotListener { snapshot, exception ->
            Log.e("fetch", "fetching data fetching data: ${snapshot.toString()}")

            if (exception != null) {
                Log.e("TAG", "Error fetching data: $exception")
                return@addSnapshotListener
            }

            Log.e("TAG", "berhasil get data")

            val dataList = ArrayList<User>()

            snapshot?.forEach { documentSnapshot ->
                Log.e("TAG", "doc snapshot")


                if (documentSnapshot.exists()) {
                    Log.e("TAG", "EXISTS")

                    try {
                        Log.e("TAG", "PROCESS GET DATA")

                        val data = documentSnapshot.toObject(User::class.java)
                        data?.let { dataList.add(it) }
                    } catch (e: Exception) {
                        Log.e("TAG", "Error converting snapshot to object: ${e.message}")
                    }
                } else {
                    Log.d("TAG", "Document does not exist")
                }
            }

            val adapter = UsersAdapter(dataList)
            recyclerView.adapter = adapter
        }

        return view;
    }


}