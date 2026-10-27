package com.example.simplecalculator

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val intent = android.content.Intent(
                this,
                AddNumbersActivity::class.java
            )
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener {
            val intent = android.content.Intent(
                this,
                SubtractNumbersActivity::class.java
            )
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnDivide).setOnClickListener {
            val intent = Intent(this, DivideNumbersActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnMultiply).setOnClickListener {
            val intent = Intent(this, MultiplyNumbersActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnHelp).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Calculator Help")
                .setMessage(
                    "Welcome to Simple Calculator!\n\n" +
                            "1. Select an operation.\n" +
                            "2. Enter the first number.\n" +
                            "3. Enter the second number.\n" +
                            "4. Tap Calculate to see the answer.\n" +
                            "5. Tap Back to Home to return.\n\n" +
                            "Note: You cannot divide by zero."
                )
                .setPositiveButton("OK", null)
                .show()
        }
    }

    private fun openCalculator(operation: String) {
        val firstNumber = EditText(this).apply {
            hint = "Enter First Number"
            inputType = 8194
        }

        val secondNumber = EditText(this).apply {
            hint = "Enter Second Number"
            inputType = 8194
        }

        val answer = TextView(this).apply {
            text = "Answer: "
            textSize = 20f
            setPadding(0, 20, 0, 20)
        }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(30, 10, 30, 10)
            addView(firstNumber)
            addView(secondNumber)
            addView(answer)
        }

        AlertDialog.Builder(this)
            .setTitle("Calculate: $operation")
            .setView(layout)
            .setPositiveButton("Calculate") { _, _ ->
                val a = firstNumber.text.toString().toDoubleOrNull()
                val b = secondNumber.text.toString().toDoubleOrNull()

                if (a == null || b == null) {
                    Toast.makeText(
                        this,
                        "Please enter two valid numbers.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (operation == "/" && b == 0.0) {
                    Toast.makeText(
                        this,
                        "Cannot divide by zero.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val result = when (operation) {
                        "+" -> a + b
                        "-" -> a - b
                        "/" -> a / b
                        "*" -> a * b
                        else -> 0.0
                    }

                    answer.text = "Answer: $result"
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}