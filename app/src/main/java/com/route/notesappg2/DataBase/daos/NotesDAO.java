package com.route.notesappg2.DataBase.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.route.notesappg2.DataBase.Model.Note;

import java.util.List;

/**
 * Created by Mohamed Nabil Mohamed on 9/14/2019.
 * m.nabil.fci2015@gmail.com
 */
@Dao
public interface NotesDAO {
    @Insert
    void addNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Update
    void updateNote(Note note);

    @Query("delete from Note where title =:name")
    void deleteNote(String name);
    @Query("select * from Note where id  = :id")
    Note searchNoteById(int id);
    @Query("select * from note")
    List<Note> getAllNotes();
}
