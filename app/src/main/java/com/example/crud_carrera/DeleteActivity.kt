package com.example.crud_carrera

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_carrera.databinding.ActivityDeleteBinding
import com.example.crud_carrera.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
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
        binding.deleteButton.setOnClickListener {
            val matricula = binding.deleteMatricula.text.toString()
            if (matricula.isNotEmpty()){
                deleteData(matricula)
            }else{
                Toast.makeText(this, "Por favor ingresa una matricula", Toast.LENGTH_SHORT).show()
            }
        }
        }

    private fun deleteData(matricula: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("User information")
        databaseReference.child(matricula).removeValue().addOnSuccessListener {
            binding.deleteMatricula.text.clear()
            Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
         Toast.makeText(this, "No se pudo eliminar", Toast.LENGTH_SHORT).show()
        }
    }

}