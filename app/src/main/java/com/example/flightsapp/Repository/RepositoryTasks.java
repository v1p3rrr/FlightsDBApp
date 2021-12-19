package com.example.flightsapp.Repository;

import androidx.lifecycle.LiveData;

import com.example.flightsapp.Data.Room.Note;

import java.util.List;

public interface RepositoryTasks {
    <T extends Note> LiveData<List<T>> getAllNotes();
    void addNote(Note note);
    void deleteNote(Note note);
    void updateNote (Note note);
}

