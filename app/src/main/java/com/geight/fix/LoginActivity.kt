package com.geight.fix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var buttonLogin: Button
    lateinit var buttonRegistrarLogin: Button
    lateinit var editTextEmail: EditText
    lateinit var editTextContrasenaLogin: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        editTextEmail= findViewById(R.id.editTextEmail)
        editTextContrasenaLogin= findViewById(R.id.editTextContrasenaLogin)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegistrarLogin = findViewById(R.id.buttonRegistrarLogin)

        setup()
    }

    private fun setup() {
        title = "Autentificación"

        //Registro de cliente 'manual'
        buttonRegistrarLogin.setOnClickListener {
            mostrarRegistroC()
        }

        buttonLogin.setOnClickListener {
            //mostrarHome(editTextEmail.text.toString())
            if (editTextContrasenaLogin.text.isNotEmpty() && editTextEmail.text.isNotEmpty() ||
                    editTextContrasenaLogin.text.equals("Contraseña") || editTextEmail.text.equals("Email")){
                mostrarHome()
            }else{
                buttonLogin.error = "Debes ingresar tu nombre y contraseña"
            }

            /*if (editTextEmail.text.isNotEmpty() && editTextContrasenaLogin.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(editTextEmail.text.toString(), editTextContrasenaLogin.text.toString()).addOnCompleteListener {
                    //mostrarHome(it.result?.user?.email ?: "")
                    if(it.isSuccessful){
                        mostrarHome(it.result?.user?.email ?: "")
                    }else{
                        buttonLogin.error = "Debes ingresar tu nombre"
                    }
                }

            }*/
        }

    }

    private fun mostrarHome(email: String) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply{
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }

    private fun mostrarHome() {
        val homeIntent = Intent(this, HomeActivity::class.java).apply{
        }
        startActivity(homeIntent)
    }

    private fun mostrarRegistroC() {
        val registroCIntent = Intent(this, RegistroCliente::class.java).apply{
        }
        startActivity(registroCIntent)
    }
}
