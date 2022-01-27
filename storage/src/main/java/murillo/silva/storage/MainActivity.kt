package murillo.silva.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.storage.internal.Util

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val butonDownload = findViewById<Button>(R.id.buttonDownload)
        butonDownload.setOnClickListener(this)
    }

    override fun onClick(items: View?) {
            val buttonDownload = findViewById<Button>(R.id.buttonDownload)
            when (items?.id){
                buttonDownload.id->{
                    val text = "Hello toast!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()

                }



        }
    }
}