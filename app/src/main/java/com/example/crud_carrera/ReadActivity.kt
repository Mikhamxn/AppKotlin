package com.example.crud_carrera

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_carrera.databinding.ActivityMainBinding
import com.example.crud_carrera.databinding.ActivityReadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var searchMatricula: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.searchButton.setOnClickListener{
            val searchMatricula: String = binding.searchMatricula.text.toString()
            if (searchMatricula.isNotEmpty()){
                readData(searchMatricula)
            }else{
                Toast.makeText(this, "Ingresa la matricula", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(matricula:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("User information")
        databaseReference.child(matricula).get().addOnSuccessListener {
            if (it.exists()){
                val nombre = it.child("nombre").value
                val correo = it.child("correo").value
                val matricula = it.child("matricula").value
                val carrera = it.child("carrera").value
                Toast.makeText(this, "Resultados encontrados", Toast.LENGTH_SHORT).show()
                binding.searchMatricula.text.clear()
                binding.correo.text = correo.toString()
                binding.nombre.text = nombre.toString()
                binding.matricula.text = matricula.toString()
                binding.carrera.text = carrera.toString()
            }else{
                Toast.makeText(this, "La matricula no coincide.", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "La matricula no existe", Toast.LENGTH_SHORT).show()
        }
    }
}

