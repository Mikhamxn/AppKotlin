package com.example.crud_carrera

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_carrera.databinding.ActivityCarrerasBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CarrerasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarrerasBinding // Y cambia esto
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarrerasBinding.inflate(layoutInflater) // Y esto
        setContentView(binding.root)

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)

            val backButton = findViewById<ImageButton>(R.id.backButton)
            if (supportActionBar != null) {
                supportActionBar?.setDisplayShowTitleEnabled(false)
            }

            backButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        binding.desarrollo.setOnClickListener{
            val intent = Intent(this@CarrerasActivity, DesarrolloActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.redes.setOnClickListener{
            val intent = Intent(this@CarrerasActivity, RedesActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.ciberseguridad.setOnClickListener{
            val intent = Intent(this@CarrerasActivity, CiberseguridadActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
