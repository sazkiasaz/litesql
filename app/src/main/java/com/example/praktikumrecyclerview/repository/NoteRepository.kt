package com.example.praktikumrecyclerview.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.praktikumrecyclerview.Database.Note
import com.example.praktikumrecyclerview.Database.NoteDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val noteDao = NoteDatabase.getDatabase(application).noteDao()
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    fun getAllNotes(): LiveData<List<Note>> = noteDao.getAllNotes()

    fun insert(note: Note) = executor.execute { noteDao.insert(note) }
    fun update(note: Note) = executor.execute { noteDao.update(note) }
    fun delete(note: Note) = executor.execute { noteDao.delete(note) }
}
