package com.aristidevs.PetParadiseTheme

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.aristidevs.darkmodeexample.R
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.firebase.auth.FirebaseAuth


//Creamos un enum providerType para los tipos de autenticación
//En este caso solo tenemos el básico por email y contraseña
enum class ProviderType{
    BASIC
}
class HomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val swDarkMode = findViewById<SwitchMaterial>(R.id.swDarkMode)
        val ivModo = findViewById<ImageView>(R.id.ivModo)


        //Setup con bundle
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

        //Aplicar los temas claro y oscuro
        swDarkMode.setOnCheckedChangeListener { _, isSelected ->
            if (isSelected){
                enableDarkMode();
            }else{
                disableDarkMode()
            }

        }

    }

    //Función para activar el modo oscuro
    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()

    }

    //Función para desactivar el modo oscuro
    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }


    //Creamos una función Setup que reciba los parámetros email y provider que será el tipo de autenticación
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