package com.aristidevs.PetParadiseTheme

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.aristidevs.darkmodeexample.R
import com.google.firebase.auth.FirebaseAuth

class AuthActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Setup
        setup()


    }


        //Función para la pantalla de autenticación
    @SuppressLint("WrongViewCast")
    private fun setup(){
        title  = "Autenticación"

        val signUpButton = findViewById<Button>(R.id.logOutButton)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)


        //Lógica para el botón Registrar
        signUpButton.setOnClickListener{
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString()).addOnCompleteListener {
                    //Verificar si los datos se han introducido correctamente
                    if(it.isSuccessful){
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    }else{
                        //Se muestra un Alert de error
                        showAlert()
                    }
                }
            }
        }

        //Lógica para el botón Acceder
        loginButton.setOnClickListener{
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString()).addOnCompleteListener {
                    //Verificar si los datos se han introducido correctamente
                    if(it.isSuccessful){
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    }else{
                        //Se muestra un Alert de error
                        showAlert()
                    }
                }
            }
        }


    }
    //Creamos una función que de una Alerta que indique al usuario que ha habido un error
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Creamos una funcion para mandar a la pantalla Home al usuario

    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, HomeActivity::class.java ).apply{
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        //Llamamos a StarActivity
        startActivity(homeIntent)
    }

}