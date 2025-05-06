package com.example.praktikumrecyclerview.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikumrecyclerview.Database.Note
import com.example.praktikumrecyclerview.R


class MainActivity : AppCompatActivity() {

    private lateinit var noteAdapter: NoteAdapter
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val inputText: EditText = findViewById(R.id.editTextInput)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)

        noteAdapter = NoteAdapter(mutableListOf()) { note ->
            noteViewModel.delete(note)
        }

        recyclerView.adapter = noteAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        noteViewModel.getAllNotes().observe(this) { notes ->
            noteAdapter.setNotes(notes)
        }

        buttonAdd.setOnClickListener {
            val title = inputText.text.toString()
            if (title.isNotBlank()) {
                val note = Note(title = title, description = "")
                noteViewModel.insert(note)
                inputText.text.clear()
            }
        }
    }
}
