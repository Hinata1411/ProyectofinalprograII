package com.aristidevs.PetParadiseTheme

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.aristidevs.darkmodeexample.R
import com.google.android.material.switchmaterial.SwitchMaterial


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val swDarkMode = findViewById<SwitchMaterial>(R.id.swDarkMode)
        val ivModo = findViewById<ImageView>(R.id.ivModo)

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
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()

    }

    //Función para desactivar el modo oscuro
    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }


}




