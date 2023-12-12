package com.example.a14_firebaseaccess

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableStatement = """
            CREATE TABLE $TABLE_PRODUCTS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_PRICE REAL,
                $COLUMN_QUANTITY INTEGER,
                $COLUMN_DISCOUNT REAL
            )
        """.trimIndent()
        db.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        onCreate(db)
    }


    companion object {
        private const val DATABASE_NAME = "products.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_PRODUCTS = "products"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_QUANTITY = "quantity"
        private const val COLUMN_DISCOUNT = "discount"
    }


    fun addProduct(product: Product): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues().apply {
            // Asume que tienes las constantes COLUMN_NAME, COLUMN_PRICE, etc. definidas
            put(COLUMN_NAME, product.name)
            put(COLUMN_PRICE, product.price)
            put(COLUMN_QUANTITY, product.quantity)
            put(COLUMN_DISCOUNT, product.discount)
        }
        val result = db.insert(TABLE_PRODUCTS, null, cv)
        db.close()
        return (result != -1L) // Retorna 'true' si el producto se insertó correctamente
    }


    fun getAllProducts(): List<Product> {
        val productList = mutableListOf<Product>()
        val db = this.readableDatabase
        val cursor = db.query(TABLE_PRODUCTS, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val product = Product(
                    id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                    price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)),
                    quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)),
                    discount = cursor.getDouble(cursor.getColumnIndex(COLUMN_DISCOUNT))
                )
                productList.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return productList
    }

    fun clearProducts() {
        val db = this.writableDatabase
        db.delete(TABLE_PRODUCTS, null, null)
        db.close()
    }



}
