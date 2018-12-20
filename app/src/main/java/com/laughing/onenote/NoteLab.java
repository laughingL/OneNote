package com.laughing.onenote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoteLab {
    private static  NoteLab sNoteLab;
    private  Context mContext;
    private SQLiteDatabase mDatabase;

    public  static  NoteLab get(Context context){
        if(sNoteLab == null){
            sNoteLab= new NoteLab(context);
        }
        return  sNoteLab;
    }
    private NoteLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase =new NoteBaseHelper(mContext).getWritableDatabase();
    }
    public  void addNote(Note n){
        ContentValues values =getContentValues(n);
        mDatabase.insert(NoteDbSchema.NoteTable.NAME,null,values);    //将这个ContentValue 插入 数据库

    }
    public List<Note> getNotes(){
        List<Note> notes = new ArrayList<>();
        NoteCursorWapper cursor = queryNotes(null,null);
        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                notes.add(cursor.getNote());
                cursor.moveToNext();
            }
        }finally {
            cursor.moveToNext();
        }
        return notes;
    }
    public  Note getNote(UUID id){

        NoteCursorWapper cursor = queryNotes(NoteDbSchema.NoteTable.Cols.UUID+"=?",new String[]{id.toString()});
        try {
            if(cursor.getCount()==0){
                return null;
            }
            cursor.moveToFirst();
            return  cursor.getNote();

        }finally {
            cursor.close();
        }
    }

    public  void updateNote(Note note){     //这里用来更新数据
        String uuidString=note.getId().toString();
        ContentValues values =getContentValues(note);
        mDatabase.update(NoteDbSchema.NoteTable.NAME,values, NoteDbSchema.NoteTable.Cols.UUID+"= ?",new String[]{uuidString}); //? 占位 值就是uuidString 避免sql 注入
        //表名 对象 要更改的行(where 子句 指定where中的参数值)
    }
    private NoteCursorWapper queryNotes(String whereClause,String[] whereArgs){ //读取数据库中的内容
        Cursor cursor = mDatabase.query(
                NoteDbSchema.NoteTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        //return cursor;
        return new NoteCursorWapper(cursor);

    }
    private  static ContentValues getContentValues(Note note){ //这边是用来使用 ContentValues
        ContentValues values=new ContentValues();
        values.put(NoteDbSchema.NoteTable.Cols.UUID,note.getId().toString());
        values.put(NoteDbSchema.NoteTable.Cols.TITLE,note.getTitle());
        values.put(NoteDbSchema.NoteTable.Cols.DATE,note.getDate());
        values.put(NoteDbSchema.NoteTable.Cols.LOCATION,note.getLocation());
        values.put(NoteDbSchema.NoteTable.Cols.TEXT,note.getText());
        values.put(NoteDbSchema.NoteTable.Cols.TYPE,note.getType());

        return values;
    }


}
