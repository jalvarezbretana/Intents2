package com.example.intents2


import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // Elementos gráficos
        val txtView = findViewById<TextView>(R.id.numbersView)
        val editTxt = findViewById<EditText>(R.id.editTextAnswer)
        val checkButton = findViewById<Button>(R.id.checkButton)

        // Variable que recoge el anterior Intent
        val intent = intent

        // Recibir valores de Intent
        val n1 = intent.getIntExtra("n1", 0)
        val n2 = intent.getIntExtra("n2", 0)

        // Mostrar números en el txtView
        txtView.text = "$n1+$n2=?"

        // Dar valores a las variables
        var result = n1 + n2
        Log.d("State", "Result: $result")

        checkButton.setOnClickListener {
            var aux = editTxt.text
            var answer = Integer.parseInt(editTxt.text.toString())
            Log.d("State", "Result: $result, answer: $answer")
            var toret = "Error"
            if (result == answer) {
                toret = "Acertaste, de suerte no?"
            } else {
                toret = "Fallaste, dedícate a otra cosa..."
            }
            intent.putExtra("answer", toret)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}