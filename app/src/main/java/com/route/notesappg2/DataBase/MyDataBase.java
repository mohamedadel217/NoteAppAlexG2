package com.route.notesappg2.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.route.notesappg2.DataBase.Model.Note;
import com.route.notesappg2.DataBase.daos.NotesDAO;

/**
 * Created by Mohamed Nabil Mohamed on 9/14/2019.
 * m.nabil.fci2015@gmail.com
 */
@Database(entities = {Note.class },version = 1,exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
    private static MyDataBase dataBase;
    private final static String DB_NAME ="Notes DataBase";
    public abstract NotesDAO notesDAO();
    public MyDataBase(){

    }

    public static MyDataBase getInstance(Context context){
        if(dataBase==null){
            //create object
            dataBase = Room.databaseBuilder(context,
                    MyDataBase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return dataBase;
    }
}
