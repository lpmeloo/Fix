@file:Suppress("UNCHECKED_CAST")

package com.geight.fix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.geight.fix.com.geight.fix.Cliente
import com.google.firebase.database.*

@Suppress("UNCHECKED_CAST")

class PerfilActivity : AppCompatActivity() {

    lateinit var textInputID: TextView
    lateinit var textInputNombres: TextView
    lateinit var textInputApellidos: TextView
    lateinit var textInputCorreo: TextView
    lateinit var textInputContra: TextView
    lateinit var textUsuario: TextView
    lateinit var buttonEditar: Button
    lateinit var buttonAceptar: Button
    lateinit var userFBID: String

    //Firebase variables
    private lateinit var db: FirebaseDatabase
    private lateinit var ref: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val bundle: Bundle? = intent.extras
        val email = bundle?.getString("email").toString()

        //ui
        textInputID = findViewById(R.id.textInputID)
        textInputNombres = findViewById(R.id.textInputNombres)
        textInputApellidos = findViewById(R.id.textInputApellidos)
        textInputCorreo = findViewById(R.id.textInputCorreo)
        textInputContra= findViewById(R.id.textInputContra)
        textInputContra.isVisible = false
        buttonEditar = findViewById(R.id.buttonEditar)
        buttonAceptar = findViewById(R.id.buttonAceptar)
        buttonAceptar.isVisible = false
        textUsuario = findViewById(R.id.textUsuario)
        textUsuario.isVisible = false

        //Base de Datos
        db = FirebaseDatabase.getInstance()
        ref = db.getReference("Clientes")

        cargarDatos(email ?: "Usuario no encontrado")

        buttonEditar.setOnClickListener {
            hacerEditables()
        }

        buttonAceptar.setOnClickListener {
            actualizarDatos(userFBID)
            hacerIneditables()
        }
    }

    private fun hacerEditables() {
        textInputNombres.isEnabled = true
        textInputApellidos.isEnabled = true
        buttonAceptar.isVisible = true
        buttonEditar.isVisible = false
    }

    private fun hacerIneditables() {
        textInputNombres.isEnabled = false
        textInputApellidos.isEnabled = false
        buttonAceptar.isVisible = false
        buttonEditar.isVisible = true
    }

    private fun actualizarDatos(FBID: String) {
        var nombres = textInputNombres.text.toString()
        var apellidos = textInputApellidos.text.toString()
        var contra = textInputContra.text.toString()

        var actualizado = Cliente(FBID, textInputID.text.toString(), nombres, apellidos, textInputCorreo.text.toString(), contra)

        ref.child(FBID).setValue(actualizado).addOnCompleteListener {
            Toast.makeText(applicationContext, "Datos actualizados", Toast.LENGTH_LONG).show()
        }
    }


    private fun cargarDatos(email: String) {
        FirebaseDatabase.getInstance().reference.child("Clientes").orderByChild("email")
            .equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot){
                    var map: Map<String, Any> = p0.children.first().value as Map<String, Any>
                    textInputID.text = map["id"].toString()
                    textInputNombres.text = map["nombre"].toString()
                    textInputApellidos.text = map["apellidos"].toString()
                    textInputCorreo.text = map["email"].toString()
                    textInputContra.text = map["contrasena"].toString()
                    //textUsuario.text = map["contrasena"].toString()
                    userFBID = map["fbid"].toString()
                }
                override fun onCancelled(error: DatabaseError) {

                }
            })

    }

    /*private fun setup(email: String) {
        title = "INICIO"
        textUsuario.text = email
    }*/
}