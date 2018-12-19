package com.laughing.onenote;


import android.database.Cursor;
import android.database.CursorWrapper;
import java.util.UUID;

public class NoteCursorWapper extends CursorWrapper{
    public   NoteCursorWapper(Cursor cursor){
        super(cursor);
    }

    public Note getNote(){
        String uuidString =getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.UUID));
        String title=getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.TITLE));
        String date = getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.DATE));
        String text = getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.TEXT));
        String location = getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.LOCATION));
        String type = getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.TYPE));


        Note note =new Note(UUID.fromString(uuidString));
        note.setTitle(title);
        note.setDate(date);
        note.setLocation(location);
        note.setText(text);
        note.setType(type);

        return note;



    }

}
