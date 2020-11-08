package com.example.vhnotepad3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class List extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    Button buttonClearAll;
    Button buttonAddTheNote;
    ListView listView;
    static ArrayList<String> listForAdapter = new ArrayList<>();
    static ArrayList<String> listDataOfPages = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        buttonClearAll = findViewById(R.id.buttonClearAll);
        buttonClearAll.setOnClickListener(this);

        buttonAddTheNote = findViewById(R.id.buttonAddTheNote);
        buttonAddTheNote.setOnClickListener(this);

        listView = findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.layout_for_list_item, listForAdapter);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.buttonClearAll : {
                Start.DBHelper.clearAll();
                Start.DBHelper.addOne("temp", "temp");
                listForAdapter.clear();
                recreate();
                break;
            }
            case R.id.buttonAddTheNote: {
                Page.flagForButtonAddTheNote = true;
                Page.textForEditText = "";
                intent = new Intent(this, Page.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("---ON ITEM CLICK---");
        //флаг добавляет
        //Page.flagForButtonAddTheNote = true;
        String str = (String) listView.getItemAtPosition(position);
        Page.listItemId = (int) listView.getItemIdAtPosition(position);
        Page.pageName = str;
        Start.DBHelper.loadPage(str);
        Intent intent = new Intent(this, Page.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ItemAction.itemName = (String) listView.getItemAtPosition(position);
        Intent intent = new Intent(this, ItemAction.class);
        startActivity(intent);
        return true;
    }
}