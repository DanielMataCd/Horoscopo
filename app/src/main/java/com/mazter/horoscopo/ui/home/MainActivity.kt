package com.mazter.horoscopo.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mazter.horoscopo.R
import com.mazter.horoscopo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/*
Todo lo visual de android esta en esta capa de clean arquitecture
ui = interfaz de usuario
Toda la logica esta en domain
las peticiones http
Data es donde va a salir la informacion */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navControler:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initUI()

    }
    private fun initUI(){
        initNavigation()
    }
    private fun initNavigation(){

        val navHost = supportFragmentManager.findFragmentById(R.id.fcvHoroscopo) as NavHostFragment
        navControler = navHost.navController
        binding.btnNavView.setupWithNavController(navControler)
    }
}