package com.example.calculadora_basica

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadora_basica.ui.theme.Calculadora_BasicaTheme

class MainActivity : ComponentActivity() {
    private lateinit var editTextPantalla: EditText
    private var operando1: Double = 0.0
    private var operador: Char = ' '

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextPantalla = findViewById(R.id.editTextPantalla)

        val botones = arrayOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.buttonClear, R.id.buttonSumar,
            R.id.buttonRestar, R.id.buttonMultiplicar, R.id.buttonDividir, R.id.buttonIgual,
        )

        for (botonId in botones) {
            val boton = findViewById<Button>(botonId)
            boton.setOnClickListener {
                when (boton.text.toString()) {
                    "+" -> {
                        operador = '+'
                        guardarOperando()
                    }
                    "-" -> {
                        operador = '-'
                        guardarOperando()
                    }
                    "*" -> {
                        operador = '*'
                        guardarOperando()
                    }
                    "/" -> {
                        operador = '/'
                        guardarOperando()
                    }
                    "=" -> {
                        calcularResultado()
                    }
                    "C" -> { // Lógica para el botón Clear
                        editTextPantalla.text.clear()
                        operando1 = 0.0
                        operador = ' '
                    }
                    else -> {
                        editTextPantalla.append(boton.text)
                    }
                }
            }
        }
    }

    private fun guardarOperando() {
        operando1 = editTextPantalla.text.toString().toDoubleOrNull() ?: 0.0
        editTextPantalla.text.clear()
    }

    private fun calcularResultado() {
        val operando2 = editTextPantalla.text.toString().toDoubleOrNull() ?: 0.0
        val resultado = when (operador) {
            '+' -> operando1 + operando2
            '-' -> operando1 - operando2
            '*' -> operando1 * operando2
            '/' -> operando1 / operando2
            else -> operando2 // Si no hay operador, mostrar el segundo operando
        }
        editTextPantalla.setText(resultado.toString())
        operando1 = 0.0 // Reiniciar para la próxima operación
        operador = ' '
    }
}