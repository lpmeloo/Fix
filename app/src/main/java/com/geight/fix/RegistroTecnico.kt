package com.geight.fix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geight.fix.com.geight.fix.Tecnico
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistroTecnico : AppCompatActivity() {
    lateinit var textInputID: TextInputEditText
    lateinit var textInputNombres: TextInputEditText
    lateinit var textInputApellidos: TextInputEditText
    lateinit var textInputCorreo: TextInputEditText
    lateinit var textInputContrasena: TextInputEditText
    lateinit var buttonRegistrar3: Button
    lateinit var fAuth: FirebaseAuth
    lateinit var db: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrot)

        textInputID= findViewById(R.id.textInputID)
        textInputNombres = findViewById(R.id.textInputNombres)
        textInputApellidos = findViewById(R.id.textInputApellidos)
        textInputCorreo = findViewById(R.id.textInputCorreo)
        textInputContrasena = findViewById(R.id.textInputContrasena)
        buttonRegistrar3 = findViewById(R.id.buttonEditar)
        fAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()

        buttonRegistrar3.setOnClickListener {
            registrarTecnico()
        }

    }

    private fun registrarTecnico(){

        val id = textInputID.text.toString().trim()
        val nombres = textInputNombres.text.toString().trim()
        val apellidos = textInputApellidos.text.toString().trim()
        val email = textInputCorreo.text.toString().trim()
        val contrasena = textInputContrasena.text.toString().trim()

        if(id.isEmpty()){
            textInputID.setError("Debes ingresar tu documento")
            return
        }
        if(nombres.isEmpty()){
            textInputNombres.setError("Debes ingresar tu nombre")
            return
        }
        if(apellidos.isEmpty()){
            textInputApellidos.setError("Debes ingresar tu apellido")
            return
        }
        if(email.isEmpty()){
            textInputCorreo.setError("Debes ingresar tu correo")
            return
        }
        if(contrasena.isEmpty() || contrasena.length < 6){
            textInputContrasena.setError("Debes establecer una contraseña de al menos 6 caracteres")
            return
        }

        if (email.isNotEmpty() && contrasena.isNotEmpty()){
            fAuth.createUserWithEmailAndPassword(email, contrasena).addOnCompleteListener {
                if(it.isSuccessful){
                    val ref = db.getReference("Tecnicos")
                    val tecnicoFBID : String = ref.push().key.toString()
                    val tecnico = Tecnico(tecnicoFBID, id, nombres, apellidos, email, contrasena)

                    ref.child(tecnicoFBID).setValue(tecnico).addOnCompleteListener {
                        Toast.makeText(applicationContext, "Usuario registrado", Toast.LENGTH_LONG).show()
                    }
                    mostrarLogin()
                }else{
                    Toast.makeText(applicationContext, "Datos inválidos", Toast.LENGTH_LONG).show()
                    //buttonRegistrar3.error = "Datos inválidos"
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