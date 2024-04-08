package com.example.crud_carrera

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.*
import com.example.crud_carrera.databinding.ActivityDesarrolloBinding

class CiberseguridadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDesarrolloBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userList: ListView
    private lateinit var list: MutableList<UsersData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesarrolloBinding.inflate(layoutInflater)
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

        userList = binding.userList
        list = mutableListOf()
        databaseReference = FirebaseDatabase.getInstance().getReference("User information")
        val carreraQuery: Query = databaseReference.orderByChild("carrera").equalTo("Ciberseguridad")

        carreraQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    list.clear()
                    for (snapshot in dataSnapshot.children) {
                        val usersData = snapshot.getValue(UsersData::class.java)
                        if (usersData != null) {
                            list.add(usersData)
                        }
                    }
                    val adapter = ArrayAdapter(this@CiberseguridadActivity, android.R.layout.simple_list_item_1, list)
                    userList.adapter = adapter
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Error al leer los datos: ${databaseError.message}")
            }
        })
    }
}
