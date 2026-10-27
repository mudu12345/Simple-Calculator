package com.example.simplecalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MultiplyNumbersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiply_numbers)

        val firstNumber = findViewById<EditText>(R.id.etFirstNumber)
        val secondNumber = findViewById<EditText>(R.id.etSecondNumber)
        val calculateButton = findViewById<Button>(R.id.btnCalculate)
        val answer = findViewById<TextView>(R.id.tvAnswer)
        val backButton = findViewById<Button>(R.id.btnBack)

        calculateButton.setOnClickListener {
            val a = firstNumber.text.toString().toDoubleOrNull()
            val b = secondNumber.text.toString().toDoubleOrNull()

            if (a == null || b == null) {
                Toast.makeText(
                    this,
                    "Please enter both numbers",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val result = a * b
                answer.text = "Answer: $result"
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}