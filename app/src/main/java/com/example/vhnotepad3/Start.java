package com.example.vhnotepad3;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

public class Start extends AppCompatActivity implements View.OnClickListener{
    public static DBHelper dbHelper;
    public static SQLiteDatabase sqLiteDatabase;
    public static boolean loadFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        dbHelper = new DBHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        DBHelper.loadDataBase();
        loadFlag = false;

        ImageButton startImageButton = findViewById(R.id.startImageButton);
        startImageButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("about");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, List.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }

    static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(@Nullable Context context) {
            super(context, "VHNotepadDataBase", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table pages (" +
                    "_id integer primary key autoincrement," +
                    "page_name text," +
                    "data text" +
                    ")");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

        static void loadDataBase(){
            if(loadFlag){
                Cursor cursor = sqLiteDatabase.query("pages", null, null, null, null, null, null);
                if(cursor.moveToFirst()){
                    while ((cursor.moveToNext())) {
                        String name = cursor.getString(1);
                        String data = cursor.getString(2);
                        List.listForAdapter.add(name);
                        List.listDataOfPages.add(data);
                    }
                }
            }
        }

        static void addOne(String name, String data){
            ContentValues contentValues = new ContentValues();
            contentValues.put("page_name", name);
            contentValues.put("data", data);
            sqLiteDatabase.insert("pages", null, contentValues);
        }

        static void removeOne(String name){
            sqLiteDatabase.delete("pages", "page_name =?",new String[]{name});
        }

        ////////// метод работает некорректно,вероятно требуется обновление по id элемента в базе////////////////
        static void updateOne(int id, String name, String data){
            System.out.println("---UPDATE ONE---");
            ContentValues contentValues = new ContentValues();
            contentValues.put("page_name", name);
            contentValues.put("data", data);
            //sqLiteDatabase.update("pages", contentValues,"page_name =?",new String[]{name});
            //sqLiteDatabase.update("pages", contentValues,"_id =?",new String[]{String.valueOf(id)});
            System.out.println(id);
            //sqLiteDatabase.update("pages", contentValues,"_id =?" + "=" + id, null);
            sqLiteDatabase.update("pages", contentValues,"_id" + "=" + id, null);
        }
        
        static void clearAll(){
            sqLiteDatabase.delete("pages", null, null);
        }

        static void showDataBase(){
            Cursor cursor = sqLiteDatabase.query("pages", null, null, null, null, null, null);
            if(cursor.moveToFirst()){
                while ((cursor.moveToNext())){
                    String s = cursor.getString(0)+ "-" + cursor.getString(1) + "-" + cursor.getString(2);
                    System.out.println("SHOW DATA BASE = " + s);
                }
            }
        }

        static void loadPage(String name){
            System.out.println("---LOAD PAGE---");
            Cursor cursor = sqLiteDatabase.query("pages", null, null, null, null, null, null);
            if(cursor.moveToFirst()){
                while ((cursor.moveToNext())) {
                    int id = cursor.getInt(0);
                    String name1 = cursor.getString(1);
                    String data = cursor.getString(2);
                    if(name.equals(name1)){
                        Page.textForEditText = data;
                        Page.pageId = id;
                        //Start.DBHelper.removeOne(name);
                        //Start.DBHelper.updateOne(id, name1, data);
                        //Page.flagForButtonAddTheNote = false;
                        //?
                        //List.listForAdapter.remove(name);
                    }
                }
            }
        }
    }
}
