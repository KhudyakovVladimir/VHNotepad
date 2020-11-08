package com.example.vhnotepad3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class Page extends AppCompatActivity implements View.OnClickListener {
    ImageButton imageButtonSave;
    static EditText editText;
    static String pageName;
    static int listItemId;
    static int pageId;
    String noteData;
    String noteName;
    static String textForEditText = "";
    static boolean flagForButtonAddTheNote = false;
    StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void onBackPressed() {
        Intent intent;
        if(editText.getText().length() != 0){
            stringBuilder.append(editText.getText().toString());

            noteName = stringBuilder.toString();
            noteData = stringBuilder.toString();

            if(flagForButtonAddTheNote){
                Start.DBHelper.addOne(noteName, noteData);
                List.listForAdapter.add(noteName);
            }
            else {
                Start.DBHelper.updateOne(pageId, noteName, noteData);
                List.listForAdapter.set(listItemId, noteName);
            }
            flagForButtonAddTheNote = false;

            stringBuilder.delete(0, stringBuilder.length());

            intent = new Intent(this, List.class);
            startActivity(intent);

        }
        else {
            Drawable drawable = getDrawable(R.drawable.list_scroll);
            new MyToast(this, "Please write something", drawable);
        }
        intent = new Intent(this, List.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //save();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);

        Start.DBHelper.showDataBase();

        imageButtonSave = findViewById(R.id.imageButtonSave);
        imageButtonSave.setOnClickListener(this);

        editText = findViewById(R.id.editTextFromNote);
        editText.setText(textForEditText);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.imageButtonSave: {
                if(editText.getText().length() != 0){
                    stringBuilder.append(editText.getText().toString());

                    noteName = stringBuilder.toString();
                    noteData = stringBuilder.toString();

                    if(flagForButtonAddTheNote){
                        Start.DBHelper.addOne(noteName, noteData);
                        List.listForAdapter.add(noteName);
                    }
                    else {
                        Start.DBHelper.updateOne(pageId, noteName, noteData);
                        List.listForAdapter.set(listItemId, noteName);
                    }
                    flagForButtonAddTheNote = false;

                    stringBuilder.delete(0, stringBuilder.length());

                    intent = new Intent(this, List.class);
                    startActivity(intent);
                    break;
                }
                else {
                    Drawable drawable = getDrawable(R.drawable.list_scroll);
                    new MyToast(this, "Please write something", drawable);
                }
            }
        }
    }

    void save(){
        if(editText.getText().length() != 0){
            stringBuilder.append(editText.getText().toString());

            noteName = stringBuilder.toString();
            noteData = stringBuilder.toString();

            if(flagForButtonAddTheNote){
                Start.DBHelper.addOne(noteName, noteData);
                List.listForAdapter.add(noteName);
            }
            else {
                Start.DBHelper.updateOne(pageId, noteName, noteData);
                List.listForAdapter.set(listItemId, noteName);
            }
            flagForButtonAddTheNote = false;

            stringBuilder.delete(0, stringBuilder.length());
        }
        else {
            Drawable drawable = getDrawable(R.drawable.list_scroll);
            new MyToast(this, "Please write something", drawable);
        }
    }

}