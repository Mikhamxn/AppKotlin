package com.example.crud_carrera

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.crud_carrera.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference
    private var carrera: String = "" // Variable para almacenar la opción seleccionada del spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
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
        val spinner: Spinner = findViewById(R.id.uploadCarrera)
        val items = listOf("Desarrollo", "Redes", "Ciberseguridad", "Mecánica")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                carrera = items[position] // Almacena la opción seleccionada en la variable carrera
                Toast.makeText(this@UploadActivity, "$carrera", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.saveButton.setOnClickListener{
            val nombre = binding.uploadNombre.text.toString()
            val correo = binding.uploadCorreo.text.toString()
            val matricula = binding.uploadMatricula.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("User information")
            val userData = UserData(nombre, correo, matricula, carrera) // Incluye la carrera en los datos del usuario
            databaseReference.child(matricula).setValue(userData).addOnSuccessListener {
                binding.uploadNombre.text.clear()
                binding.uploadCorreo.text.clear()
                binding.uploadMatricula.text.clear()

                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }

    }
}
