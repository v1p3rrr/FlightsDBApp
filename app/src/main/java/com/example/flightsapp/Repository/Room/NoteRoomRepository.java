package com.example.flightsapp.Repository.Room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.flightsapp.Data.Room.Note;
import com.example.flightsapp.Repository.Room.DAO.NoteDAO;
import com.example.flightsapp.Repository.RepositoryTasks;

import java.util.List;

public class NoteRoomRepository implements RepositoryTasks {
    private NoteDAO mNoteDao;

    private static NoteRoomRepository instance;

    private NoteRoomRepository() {

    }

    public static NoteRoomRepository getInstance() {
        if (instance == null)
            instance = new NoteRoomRepository();
        return instance;
    }

    public NoteRoomRepository(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getRoomDatabaseInstance(application);
        mNoteDao = db.noteDAO();
    }

    @Override
    public LiveData<List<Note>> getAllNotes() {
        return mNoteDao.getAllNotes();
    }

    @Override
    public void updateNote(Note note){
        mNoteDao.updateNote(note);
    }

    @Override
    public void addNote(Note note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.addNote(note);
        });
    }

    @Override
    public void deleteNote(Note note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.deleteNote(note);
        });
    }

    public Note getNoteById(int id) {
        return mNoteDao.getNoteById(id);
    }

    public Note getLastNote(){
        return mNoteDao.getLastNote();
    }
}
