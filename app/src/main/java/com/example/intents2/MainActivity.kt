package com.example.intents2

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

// Definición de requestCode
const val REQUEST_IMAGE_CAPTURE = 1
const val RESULT_TWO = 2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Botones
        var cameraButton = findViewById<Button>(R.id.cameraButton)
        var addButton = findViewById<Button>(R.id.addButton)

        // Intents
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val addIntent = Intent(this, AddActivity::class.java)


        cameraButton.setOnClickListener {
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
        }

        addButton.setOnClickListener {

            // Datos para la segunda activity
            addIntent.putExtra("n1", random())
            addIntent.putExtra("n2", random())
            startActivityForResult(addIntent, RESULT_TWO)
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // ImageView
        val photoView = findViewById<ImageView>(R.id.photoView)


        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            val imageBitmap = data.extras!!.get("data") as Bitmap
            photoView.setImageBitmap(imageBitmap)
        } else if (requestCode == RESULT_TWO && resultCode == RESULT_OK && data != null) {
            val resultView = findViewById<TextView>(R.id.resultView)
            var aux = data?.getStringExtra("answer")
            resultView.text = "$aux"
        }
    }

    fun random(): Int? {
        val num = (1..10).random()
        Log.d("State", num.toString())
        return num
    }

}