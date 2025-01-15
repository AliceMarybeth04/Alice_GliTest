package com.example.glitest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            setContentView(R.layout.activity_main)
            displayStudentList()

            val btnLogout = findViewById<Button>(R.id.btnLogout)
            btnLogout.setOnClickListener {
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", false)
                editor.apply()

                supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, LoginFragment())
                    .commit()
            }
        } else {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, LoginFragment())
                .commit()
        }
    }

    private fun displayStudentList() {
        val studentList = listOf(
            Student("Andi Wijaya", "Jl. Raya No. 1", R.drawable.ic_person),
            Student("Budi Santoso", "Jl. Kebon Jeruk No. 12", R.drawable.ic_person),
            Student("Citra Dewi", "Jl. Melati No. 34", R.drawable.ic_person),
            Student("Dian Sari", "Jl. Duta No. 5", R.drawable.ic_person),
            Student("Eko Prasetyo", "Jl. Sumber No. 2", R.drawable.ic_person),
            Student("Fahmi Alamsyah", "Jl. Cendana No. 7", R.drawable.ic_person),
            Student("Gina Putri", "Jl. Anggrek No. 14", R.drawable.ic_person),
            Student("Hendra Setiawan", "Jl. Pahlawan No. 6", R.drawable.ic_person),
            Student("Ika Maharani", "Jl. Suka No. 3", R.drawable.ic_person),
            Student("Joko Sutrisno", "Jl. Raya Barat No. 18", R.drawable.ic_person)
        )

        // Atur RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_students)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = StudentAdapter(studentList)
    }
}
