package com.geight.fix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.geight.fix.com.geight.fix.Cliente
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistroCliente : AppCompatActivity() {

    lateinit var editTextID: EditText
    lateinit var editTextNombres: EditText
    lateinit var editTextApellidos: EditText
    lateinit var editTextCorreo: EditText
    lateinit var editTextContrasena: EditText
    lateinit var buttonRegistrar: Button
    lateinit var fAuth: FirebaseAuth
    lateinit var db: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registroc)

        editTextID= findViewById(R.id.editTextID)
        editTextNombres = findViewById(R.id.editTextNombres)
        editTextApellidos = findViewById(R.id.editTextApellidos)
        editTextCorreo = findViewById(R.id.editTextCorreo)
        editTextContrasena = findViewById(R.id.editTextContrasena)
        buttonRegistrar = findViewById(R.id.buttonRegistrar)
        fAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()

        buttonRegistrar.setOnClickListener {
            registrarCliente()
        }
    }

    private fun registrarCliente(){

        val id = editTextID.text.toString().trim()
        val nombres = editTextNombres.text.toString().trim()
        val apellidos = editTextApellidos.text.toString().trim()
        val email = editTextCorreo.text.toString().trim()
        val contrasena = editTextContrasena.text.toString().trim()

        if(id.isEmpty()){
            editTextID.error = "Debes ingresar tu nombre"
            return
        }
        if(nombres.isEmpty()){
            editTextNombres.error = "Debes ingresar tu nombre"
            return
        }
        if(apellidos.isEmpty()){
            editTextApellidos.error = "Debes ingresar tu apellido"
            return
        }
        if(email.isEmpty()){
            editTextCorreo.error = "Debes ingresar tu correo"
            return
        }
        if(contrasena.isEmpty() || contrasena.length < 6){
            editTextContrasena.error = "Debes establecer una contraseña de al menos 6 caracteres"
            return
        }

        if (email.isNotEmpty() && contrasena.isNotEmpty()){
            fAuth.createUserWithEmailAndPassword(email, contrasena).addOnCompleteListener {
                if(it.isSuccessful){
                    val ref = db.getReference("Clientes")
                    val clienteFBID : String = ref.push().key.toString()
                    val cliente = Cliente(clienteFBID, id, nombres, apellidos, email, contrasena)

                    ref.child(clienteFBID).setValue(cliente).addOnCompleteListener {
                        Toast.makeText(applicationContext, "Usuario registrado", Toast.LENGTH_LONG).show()
                    }
                    mostrarLogin()
                }else{
                    buttonRegistrar.error = "Datos inválidos"
                }

            }
        }
     }


    private fun mostrarLogin() {
        val loginIntent = Intent(this, LoginActivity::class.java).apply{
        }
        startActivity(loginIntent)
    }
}