package com.geight.fix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.firebase.ui.auth.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    lateinit var buttonLogin: Button
    lateinit var buttonRegistrarLogin: Button
    lateinit var editTextEmail: EditText
    lateinit var editTextContrasenaLogin: EditText
    lateinit var fAuth: FirebaseAuth
    lateinit var db: FirebaseDatabase
    internal var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextContrasenaLogin = findViewById(R.id.editTextContrasenaLogin)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegistrarLogin = findViewById(R.id.buttonRegistrarLogin)
        fAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()

        setup()
    }

    private fun setup() {
        title = "Autenticación"

        //Registro de cliente 'manual'
        buttonRegistrarLogin.setOnClickListener {
            mostrarRegistroC()
        }

        buttonLogin.setOnClickListener {
            //mostrarHome(editTextEmail.text.toString())
            if (editTextContrasenaLogin.text.isNotEmpty() && editTextEmail.text.isNotEmpty() ||
                    editTextContrasenaLogin.text.equals("Contraseña") || editTextEmail.text.equals("Email")){
                verificarDatos(editTextEmail.text.toString(), editTextContrasenaLogin.text.toString())
            }else{
                buttonLogin.error = "Debes ingresar tu nombre y contraseña"
            }
        }

    }

    private fun verificarDatos(email: String, contrasena: String){
                fAuth.signInWithEmailAndPassword(email, contrasena).addOnCompleteListener {
                    //mostrarHome(it.result?.user?.email ?: "")
                    if(it.isSuccessful){
                        mostrarPerfil(it.result?.user?.email ?: "")
                        //mostrarCategorias()
                    }else{
                        buttonLogin.error = "Debes ingresar tu nombre"
                    }
                }

    }

    /*private fun mostrarHome(email: String) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply{
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }*/

    private fun mostrarHome(email: String) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply{
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }

    private fun mostrarPerfil(email: String) {
        val perfilIntent = Intent(this, PerfilActivity::class.java).apply{
            putExtra("email", email)
        }
        startActivity(perfilIntent)
    }

    private fun mostrarCategorias() {
        val catIntent = Intent(this, CategoriasActivity::class.java).apply{
        }
        startActivity(catIntent)
    }

    private fun mostrarRegistroC() {
        val registroCIntent = Intent(this, RegistroCliente::class.java).apply{
        }
        startActivity(registroCIntent)
    }
}
