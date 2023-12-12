package com.example.a14_firebaseaccess

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ProductsAdapter(private var products: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textViewProductName)
        val textViewPrice: TextView = view.findViewById(R.id.textViewProductPrice)
        val textViewQuantity: TextView = view.findViewById(R.id.textViewProductQuantity)
        // Añade otros elementos de la vista si es necesario
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.textViewName.text = product.name
        holder.textViewPrice.text = "Precio: ₡${String.format("%.2f", product.price)}"
        holder.textViewQuantity.text = "Cantidad: ${product.quantity}"
        // Configura otros elementos de la vista si es necesario
    }

    override fun getItemCount() = products.size

    fun updateData(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    fun getTotalPrice(): Double {
        products.forEach { product ->
            Log.d("ProductAdapter", "Producto: ${product.name}, Precio: ${product.price}, Cantidad: ${product.quantity}")
        }
        val totalPrice = products.sumOf { it.price * it.quantity }
        Log.d("ProductAdapter", "Precio Total Calculado: $totalPrice")
        return totalPrice
    }



}
