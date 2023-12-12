package com.example.a14_firebaseaccess

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var btnCancelarCompra: Button
    private lateinit var btnAplicarCompra: Button
    private lateinit var textViewCantidadArticulos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewCantidadArticulos = findViewById(R.id.textViewCantidadArticulos)

        recyclerView = findViewById(R.id.recyclerViewProducts)
        dbHelper = DatabaseHelper(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        productsAdapter = ProductsAdapter(listOf()) // Inicialmente vacío
        recyclerView.adapter = productsAdapter

        btnCancelarCompra = findViewById<Button>(R.id.btnCancelarCompra)
        btnAplicarCompra = findViewById<Button>(R.id.btnAplicarCompra)

        btnCancelarCompra.setOnClickListener {
            dbHelper.clearProducts() // Método para borrar todos los productos de la base de datos
            loadProducts() // Cargar la lista de productos actualizada (ahora vacía)
        }

        btnAplicarCompra.setOnClickListener {
            val totalProductos = productsAdapter.itemCount
            val totalPrecio = productsAdapter.getTotalPrice() // Debes implementar este método en tu adaptador

            val intent = Intent(this, ConfirmacionCompraActivity::class.java)
            intent.putExtra("totalProductos", totalProductos)
            intent.putExtra("totalPrecio", totalPrecio)
            startActivity(intent)
        }

        val btnAgregarProducto = findViewById<Button>(R.id.btnAgregarProducto)
        btnAgregarProducto.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadProducts()
    }

    private fun loadProducts() {
        val productList = dbHelper.getAllProducts()
        productsAdapter.updateData(productList)
        textViewCantidadArticulos.text = "Cantidad de Artículos: ${productList.size}"
    }
}
