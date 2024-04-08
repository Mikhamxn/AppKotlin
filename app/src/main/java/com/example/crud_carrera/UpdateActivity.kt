package com.example.crud_carrera

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_carrera.databinding.ActivityReadBinding
import com.example.crud_carrera.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
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
        binding.updateButton.setOnClickListener {
            val referenceMatricula = binding.referenceMatricula.text.toString()
            val updateNombre = binding.updateNombre.text.toString()
            val updateCorreo = binding.updateCorreo.text.toString()
            val updateCarrera = binding.updateCarrera.text.toString()

            updateData(referenceMatricula, updateNombre, updateCorreo, updateCarrera)
        }
    }



    private fun updateData(matricula: String, nombre: String, correo: String, carrera: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("User information")
        val nombre = mapOf<String, String>("nombre" to nombre, "correo" to correo, "carrera" to carrera)
        databaseReference.child(matricula).updateChildren(nombre).addOnSuccessListener {
            binding.referenceMatricula.text.clear()
            binding.updateCarrera.text.clear()
            binding.updateNombre.text.clear()
            binding.updateCorreo.text.clear()
            Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "No se puede actualizar", Toast.LENGTH_SHORT).show()
        }
    }
}