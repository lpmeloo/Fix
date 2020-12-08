package com.geight.fix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class CategoriasActivity : AppCompatActivity() {

    lateinit var accesoriosButton: ImageButton
    lateinit var computadoresButton: ImageButton
    lateinit var electrodomesticosB: ImageButton
    lateinit var celularesB: ImageButton
    lateinit var televisoresB: ImageButton
    lateinit var impresorasB: ImageButton
    lateinit var sonidoB: ImageButton
    lateinit var cerrarB: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)
        accesoriosButton = findViewById(R.id.accesoriosB);
        setUp()


    }
    private fun setUp() {

        accesoriosButton.setOnClickListener{
            mostrarAccesorios()
        }
    }


    private fun mostrarAccesorios() {
        val homeIntent = Intent(this, ModuloAcActivity::class.java).apply{
        }
        startActivity(homeIntent)
    }
}