package com.koaledu.appimc

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        // Boilerplate code
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Actual source code
        val editWeight = findViewById<EditText>(R.id.edit_weight)
        val editHeight = findViewById<EditText>(R.id.edit_height)
        val buttonCalculate = findViewById<Button>(R.id.button_calculate)
        val textResult = findViewById<TextView>(R.id.text_result)
        val buttonClear = findViewById<Button>(R.id.button_clear)

        buttonCalculate.setOnClickListener {
            val weight = editWeight.text.toString().toFloatOrNull()
            val height = editHeight.text.toString().toFloatOrNull()

            if (weight != null && height != null) {
                val bmi = weight / (height * height)
                textResult.text = String.format(Locale.getDefault(), "%.2f", bmi)
            } else {
                textResult.text = String.format("Ingrese valores válidos por favor")
            }
        }

        buttonClear.setOnClickListener {
            editWeight.text = null
            editHeight.text = null
            textResult.text = null
        }

        val textTime = findViewById<TextView>(R.id.text_time)
        val textDate = findViewById<TextView>(R.id.text_date)
        val handler = Handler(Looper.getMainLooper())
        val runnable: Runnable
        val timeFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        runnable = object : Runnable {
            override fun run() {
                val now = Date()
                textTime.text = timeFormat.format(now)
                textDate.text = dateFormat.format(now)
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }
}