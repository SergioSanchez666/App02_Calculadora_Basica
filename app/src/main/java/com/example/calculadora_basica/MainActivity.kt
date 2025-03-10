package com.example.calculadora_basica
//Miembros de equipo:
//Coronel Meza Sergio Daniel
//Sanchez Cruz Sergio Antonio

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

    /**
     * EditText para mostrar los números ingresados y el resultado.
     */
    private lateinit var editTextPantalla: EditText

    /**
     * Almacena el primer operando de la operación.
     */
    private var operando1: Double = 0.0

    /**
     * Almacena el operador de la operación (+, -, *, /).
     */
    private var operador: Char = ' '

    /**
     * Método llamado al crear la actividad.
     * Inicializa la vista, configura los listeners de los botones.
     */
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa el EditText para la pantalla de la calculadora.
        editTextPantalla = findViewById(R.id.editTextPantalla)

        // Array con los IDs de los botones de la calculadora.
        val botones = arrayOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.buttonClear, R.id.buttonSumar,
            R.id.buttonRestar, R.id.buttonMultiplicar, R.id.buttonDividir, R.id.buttonIgual,
        )

        // Configura los listeners de los botones.
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

    /**
     * Guarda el operando actual del EditText y limpia el EditText.
     */
    private fun guardarOperando() {
        operando1 = editTextPantalla.text.toString().toDoubleOrNull() ?: 0.0
        editTextPantalla.text.clear()
    }

    /**
     * Calcula el resultado de la operación y lo muestra en el EditText.
     * Reinicia los operandos y el operador para la próxima operación.
     */
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