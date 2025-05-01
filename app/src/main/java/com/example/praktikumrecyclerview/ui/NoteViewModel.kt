package com.example.praktikumrecyclerview.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.praktikumrecyclerview.Database.Note
import com.example.praktikumrecyclerview.repository.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NoteRepository(application)

    fun getAllNotes(): LiveData<List<Note>> = repository.getAllNotes()
    fun insert(note: Note) = repository.insert(note)
    fun update(note: Note) = repository.update(note)
    fun delete(note: Note) = repository.delete(note)
}