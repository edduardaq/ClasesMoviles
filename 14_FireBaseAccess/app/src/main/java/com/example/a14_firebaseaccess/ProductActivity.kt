package com.example.a14_firebaseaccess

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        // Inicializar los elementos de la interfaz de usuario
        val editTextProductName = findViewById<EditText>(R.id.editTextProductName)
        val editTextProductPrice = findViewById<EditText>(R.id.editTextProductPrice)
        val editTextProductQuantity = findViewById<EditText>(R.id.editTextProductQuantity)
        val editTextProductDiscount = findViewById<EditText>(R.id.editTextProductDiscount)
        val buttonAddProduct = findViewById<Button>(R.id.buttonAddProduct)

        buttonAddProduct.setOnClickListener {
            // Asegúrate de validar la entrada del usuario aquí para evitar errores de conversión
            val name = editTextProductName.text.toString()
            val price = editTextProductPrice.text.toString().toDoubleOrNull()
            val quantity = editTextProductQuantity.text.toString().toIntOrNull()
            val discount = editTextProductDiscount.text.toString().toDoubleOrNull()

            if (name.isNotBlank() && price != null && quantity != null && discount != null) {
                // Crear una instancia del producto
                val product = Product(0, name, price, quantity, discount)

                // Crear una instancia de DatabaseHelper y añadir el producto
                val dbHelper = DatabaseHelper(this)
                val result = dbHelper.addProduct(product)

                // Mostrar un mensaje Toast dependiendo del resultado de la inserción
                if (result) {
                    Toast.makeText(this, "Producto agregado", Toast.LENGTH_SHORT).show()
                    // Puedes finalizar la actividad si deseas volver a la MainActivity inmediatamente después de agregar
                    finish()
                } else {
                    Toast.makeText(this, "Error al agregar producto", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Si alguno de los campos está vacío o tiene un formato incorrecto, muestra un mensaje de error
                Toast.makeText(this, "Por favor, ingresa todos los datos correctamente", Toast.LENGTH_LONG).show()
            }
        }
    }
}
