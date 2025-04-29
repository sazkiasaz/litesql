package com.example.praktikumrecyclerview

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikumrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var editTextInput: EditText
    private lateinit var buttonAdd: Button
    private lateinit var adapter: ItemAdapter
    private val itemList = mutableListOf<ItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        editTextInput = findViewById(R.id.editTextInput)
        buttonAdd = findViewById(R.id.buttonAdd)

        // Inisialisasi Adapter dengan menangani klik tombol Delete
        adapter = ItemAdapter(itemList) { position ->
            // Menghapus item saat tombol Delete ditekan
            itemList.removeAt(position)
            adapter.notifyItemRemoved(position)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Menambahkan item baru
        buttonAdd.setOnClickListener {
            val inputText = editTextInput.text.toString()
            if (inputText.isNotBlank()) {
                itemList.add(ItemData(inputText))
                adapter.notifyItemInserted(itemList.size - 1)
                editTextInput.text.clear()
            } else {
                Toast.makeText(this, "Teks tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
