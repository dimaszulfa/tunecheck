package gfg.bangkit.capstone.tunecheck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import gfg.bangkit.capstone.tunecheck.auth.LoginActivity
import gfg.bangkit.capstone.tunecheck.databinding.FragmentProfileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val firebaseStorage = Firebase.storage
        val storageRef = firebaseStorage.reference

// Create a reference to 'images/mountains.jpg'
        val mountainsRef = storageRef.child("images/mountains.jpg")

// Create a reference to 'images/mountains.jpg'
        val mountainImagesRef = storageRef.child("images/mountains.jpg")

// While the file names are the same, the references point to different files
        mountainsRef.name == mountainImagesRef.name // true
        mountainsRef.path == mountainImagesRef.path // false
        val dataStorageRef = FirebaseStorage.getInstance().reference.child("images")


        dataStorageRef.listAll()
            .addOnSuccessListener { listResult ->
                for (item in listResult.items) {
                    item.downloadUrl
                        .addOnSuccessListener { uri ->
                            val downloadUrl = uri.toString()
                            // Use the download URL of the image
                            Log.d("TAG", "Download URL: $downloadUrl")
                            // Now you can do whatever you need with the download URL
                            // For example, you can store it in a list or use it to load images into an ImageView
                        }
                        .addOnFailureListener { exception ->
                            // Handle any errors
                            Log.e("TAG", "Error getting download URL for item: ${item.name}", exception)
                        }
                }
            }
            .addOnFailureListener { exception ->
                // Handle the error when listing items
                Log.e("TAG", "Error listing items", exception)
            }

//        dataStorageRef.listAll()
//            .addOnSuccessListener { listResult ->
//                for (item in listResult.items) {
//                    // Mendapatkan metadata item
//                    item.metadata.addOnSuccessListener { metadata ->
//                        // Dapatkan URL download dan detail lainnya dari metadata
//                        val contentType = metadata.contentType
//                        val sizeBytes = metadata.sizeBytes
//                        val creationTimeMillis = metadata.creationTimeMillis
//                        // Dan sebagainya
//
//                        // Sekarang Anda bisa melakukan apa pun yang Anda inginkan dengan detail item
//                        Log.d("TAG", "Download URL: $sizeBytes")
//                    }.addOnFailureListener { exception ->
//                        // Penanganan kesalahan ketika gagal mendapatkan metadata
//                        Log.e("TAG", "Error getting metadata for item: ${item.name}", exception)
//                    }
//                }
//            }
//            .addOnFailureListener { exception ->
//                // Penanganan kesalahan ketika gagal mendapatkan daftar item
//                Log.e("TAG", "Error listing items", exception)
//            }
        Log.e("MOUNTAINS REF", mountainsRef.name)
        Log.e("MOUNTAINS REF", mountainsRef.path)
//        return inflater.inflate(R.layout.fragment_profile, container, false)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            val intent = Intent(it.context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}