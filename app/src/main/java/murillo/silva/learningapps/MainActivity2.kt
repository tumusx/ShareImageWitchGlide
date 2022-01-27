package murillo.silva.learningapps

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import androidx.core.content.FileProvider
import androidx.core.content.IntentCompat
import murillo.silva.learningapps.databinding.ActivityMain2Binding
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.ByteArrayOutputStream


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private val imageUri: Uri? = null
    private lateinit var button: Button
    var storage: FirebaseStorage? = null
    var firebaseStorage: FirebaseStorage? = null
    var storageReference: StorageReference? = null
    var ref: StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        storage = FirebaseStorage.getInstance()

        val reference = storage!!.reference.child("storage").child("ticketlog.png")
        val referencePDF = storage!!.reference.child("storage").child("file.pdf")
        binding.button.setOnClickListener {
            val url = editTextTextPersonName.text.toString()
            reference.toString()
            val request = DownloadManager.Request(Uri.parse(reference.toString()))
                .setTitle("File")
                .setDescription("Downloading...")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedOverMetered(true)
            val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)


        }

    }


    /*fun download() {
        storageReference = firebaseStorage.getInstance().getReference()
        sun.jvm.hotspot.oops.CellTypeState.ref = storageReference.child("storage").child("file.pdf")
        sun.jvm.hotspot.oops.CellTypeState.ref.getDownloadUrl()
            .addOnSuccessListener(object : OnSuccessListener<Uri?> {
                fun onSucess(uri: Uri?) {
                    downloadFile()
                }
            }).addOnFailureListener(OnFailureListener { })
    }*/


    /* private fun downloadFile(context : Context, fileName : String, fileExtension : String, destinationDirectory : String, url : String){
        val dowloadmanager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalFilesDir(
            context,
            destinationDirectory,
            fileName + fileExtension
        );
        dowloadmanager.enqueue(request)

    }*/

}
