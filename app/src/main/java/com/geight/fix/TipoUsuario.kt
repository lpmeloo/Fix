package com.geight.fix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TipoUsuario : AppCompatActivity() {

    lateinit var bTecnico: Button
    lateinit var bCliente: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipousuario)

        bTecnico = findViewById(R.id.bTecnico)
        bCliente = findViewById(R.id.bCliente)

        bTecnico.setOnClickListener {
            mostrarRegistroT()
        }

        bCliente.setOnClickListener {
            //mostrarRegistroC()
            mostrarLogin()
        }
    }

    private fun mostrarRegistroC() {
        val registroCIntent = Intent(this, RegistroCliente::class.java).apply{
        }
        startActivity(registroCIntent)
    }

    private fun mostrarLogin() {
        val loginIntent = Intent(this, LoginActivity::class.java).apply{
        }
        startActivity(loginIntent)
    }

    private fun mostrarRegistroT() {
        val registroTIntent = Intent(this, RegistroTecnico::class.java).apply{
        }
        startActivity(registroTIntent)
    }
}