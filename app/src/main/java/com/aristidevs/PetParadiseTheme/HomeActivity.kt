package com.aristidevs.PetParadiseTheme

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aristidevs.darkmodeexample.R
import com.google.firebase.auth.FirebaseAuth


//Creamos un enum providerType para los tipos de autenticación
//En este caso solo tenemos el básico por email y contraseña
enum class ProviderType{
    BASIC
}
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Setup con bundle
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")
    }

    @SuppressLint("WrongViewCast")
    private fun setup(email: String, provider: String){
        title = "Inicio"
        val emailTextView = findViewById<TextView>(R.id.emailTextView)
        val providerTextView = findViewById<TextView>(R.id.providerTextView)
        val logOutButton = findViewById<Button>(R.id.logOutButton)


        emailTextView.text = email
        providerTextView.text = provider

        //Regresar a la pantalla principal luego de ingresar
        logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}