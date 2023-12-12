package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario)

        // Referencia a los elementos de la UI
        val codigoEditText: EditText = findViewById(R.id.codigoEditText)
        val descripcionEditText: EditText = findViewById(R.id.descripcionEditText)
        val categoriaSpinner: Spinner = findViewById(R.id.categoriaSpinner)
        val minimoEditText: EditText = findViewById(R.id.minimoEditText)
        val maximoEditText: EditText = findViewById(R.id.maximoEditText)
        val descontinuadoRadioGroup: RadioGroup = findViewById(R.id.descontinuadoRadioGroup)
        val botonAceptar: Button = findViewById(R.id.botonAceptar)

        botonAceptar.setOnClickListener {
            if (datosCompletos(codigoEditText, descripcionEditText, categoriaSpinner, minimoEditText, maximoEditText, descontinuadoRadioGroup)) {
                Toast.makeText(this, "Todos los campos están completos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun datosCompletos(codigo: EditText, descripcion: EditText, categoria: Spinner, minimo: EditText, maximo: EditText, descontinuado: RadioGroup): Boolean {
        return codigo.text.isNotEmpty() &&
                descripcion.text.isNotEmpty() &&
                minimo.text.isNotEmpty() &&
                maximo.text.isNotEmpty() &&
                descontinuado.checkedRadioButtonId != -1 // -1 indica que ningún radio button está seleccionado
    }
}
