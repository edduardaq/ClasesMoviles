package com.example.a14_firebaseaccess

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmacionCompraActivity : AppCompatActivity() {

    private lateinit var textViewTotalProductos: TextView
    private lateinit var textViewTotalPrecio: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacion_compra)

        // Obtener la cantidad total de productos y el total de precio de la actividad anterior
        val totalProductos = intent.getIntExtra("totalProductos", 0)
        val totalPrecio = intent.getDoubleExtra("totalPrecio", 0.0)

        // Inicializar las vistas
        textViewTotalProductos = findViewById(R.id.textViewTotalProductos)
        textViewTotalPrecio = findViewById(R.id.textViewTotalPrecio)

        // Mostrar la cantidad total de productos y el total de precio en las vistas
        textViewTotalProductos.text = "Total de Productos: $totalProductos"
        textViewTotalPrecio.text = "Total Precio: ₡$totalPrecio" // Usar el símbolo de colones aquí

    }
}
