package com.example.myfirstappp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtUsuario: EditText = findViewById(R.id.txtUsuario)
        val txtContra: EditText = findViewById(R.id.txtContra)
        val btnValidar: Button = findViewById(R.id.btnValidar)

        btnValidar.setOnClickListener {
            var aplicar = true
            if(txtUsuario.text.toString().trim().isEmpty()){
                val segunda = Intent(this, Ventana2::class.java)
                startActivity(segunda)
                //Toast.makeText(this,R.string.val_usuario, Toast.LENGTH_SHORT).show()
                txtUsuario.requestFocus()
                aplicar = false
            }else if(txtContra.text.toString().trim().isEmpty()){
                //Toast.makeText(this,R.string.val_contra, Toast.LENGTH_SHORT).show()
                txtContra.requestFocus()
                aplicar = false
            }

            if(aplicar){
                //Toast.makeText(this,R.string.msg_fin, Toast.LENGTH_SHORT).show()
            }
        }
    }

}
