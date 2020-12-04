package com.geight.fix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    lateinit var emailTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle: Bundle? = intent.extras
        val email = bundle?.getString("email")
        emailTextView= findViewById(R.id.emailTextView)
        setup( email ?: "Usuario no encontrado")
    }

    private fun setup(email: String) {
        title = "INICIO"
        emailTextView.text = email
        //onBackPressed()
    }
}