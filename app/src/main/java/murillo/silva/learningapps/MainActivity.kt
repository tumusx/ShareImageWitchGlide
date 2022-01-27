package murillo.silva.learningapps

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import android.provider.MediaStore
import android.text.Layout
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toFile
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var storage: FirebaseStorage?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonDownload.setOnClickListener(this)
        storage = FirebaseStorage.getInstance()
        buttonNext.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            buttonDownload.id -> {
                downloadImagemName()
            }

        }
    }

    private fun downloadImagemName() {
        var storageReferencia = storage?.reference
        var imagesRef: StorageReference? =
            storageReferencia!!.child("storage").child("ticketlog.png")
        val ONE_MEGABYTE: Long = 1024 * 1024
        imagesRef?.getBytes(ONE_MEGABYTE)!!.addOnSuccessListener {
            val uri = imagesRef
            uri.toString()
            uri.downloadUrl.addOnSuccessListener {task->

                val uriImagem = Uri.parse(task.toString())
                Glide.with(baseContext).asBitmap().load(uriImagem).listener(object :RequestListener<Bitmap>{
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                }).into(imagemDownload)
                if (uri != null) {
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "image/jpeg"
                    val bitmap = uri.getFile(Uri.parse(uri.path))
                    val bytes = ByteArrayOutputStream()
                    val uri = Uri.parse(imagesRef.toString())
                    intent.putExtra(Intent.EXTRA_STREAM, uri)
                    startActivity(Intent.createChooser(intent, "Teste"))
                }
            }
            Toast.makeText(baseContext, "error", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {

        }


        val httpsReference = storage?.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/mobile-eab04.appspot.com/o/storage%2Fticketlog.png?alt=media&token=d303d9e5-5937-481f-b7cd-4212c038be2a")

       //TODO começa aqii
        val reference = storage!!.reference.child("storage").child("ticketlog.png")
        val referencePDF = storage!!.reference.child("storage").child("file.pdf")

        reference.downloadUrl.addOnSuccessListener {task ->
           // val uri = Uri.parse(task.toString())
            val uriImagem = Uri.parse(task.toString())
            Glide.with(baseContext).asBitmap().load(uriImagem).listener(object :RequestListener<Bitmap>{
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                    return false
                }
                override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    return false
                }
            }).into(imagemDownload)
            val uri21 = imagemDownload
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                if (uri21.drawable != null) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "image/jpeg"
                val bitmap = uri21.getDrawable().toBitmap()
                val bytes = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
                val path =
                    MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Imagem", null)
                val uri = Uri.parse(path)
                intent.putExtra(Intent.EXTRA_STREAM, uri)
                startActivity(Intent.createChooser(intent, "Teste"))
            }
                  /*
            val sendFile : Intent = Intent().apply {
                action= Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uriImagem)
                type = "image/png"
            }
            val shareIntentFiles = Intent.createChooser(sendFile, null)
            startActivity(shareIntentFiles)*/

        }.addOnFailureListener{error ->
            Log.d("error", error.message.toString())
        }
    }

    private fun downloadImageAll() {
        val urlImage =
            "https://firebasestorage.googleapis.com/v0/b/mobile-eab04.appspot.com/o/storage%2Fticketlog.png?alt=media&token=d303d9e5-5937-481f-b7cd-4212c038be2a"
        Glide.with(baseContext).asBitmap().load(urlImage).into(imagemDownload)
    }

    /*private fun download_imagem2(){
        val urlImage =
            "https://firebasestorage.googleapis.com/v0/b/mobile-eab04.appspot.com/o/storage%2Fticketlog.png?alt=media&token=d303d9e5-5937-481f-b7cd-4212c038be2a"

        Glide.with(baseContext).asBitmap().load(urlImage).listener(object :RequestListener<Bitmap>{
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
            return false
            }

            override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            return false
            }

        })
    }*/


   /* private fun compartilhar(){
        if(imagemDownload.drawable!=null){
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/jpeg"
            val bitmap = imagemDownload.getDrawable().toBitmap()
            val bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Imagem", null)
            val uri = Uri.parse(path)
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            startActivity(Intent.createChooser(intent, "Teste"))

        }
    }*/

}