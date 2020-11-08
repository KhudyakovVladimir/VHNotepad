package com.example.vhnotepad3;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ItemAction extends AppCompatActivity implements View.OnClickListener{
    Button buttonDelete;
    Button buttonReturn;
    public static String itemName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_action);

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(this);

        buttonReturn = findViewById(R.id.buttonReturn);
        buttonReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonDelete : {
                Start.DBHelper.removeOne(itemName);
                List.listForAdapter.remove(itemName);
                itemName = "";
                Intent intent = new Intent(this, List.class);
                startActivity(intent);
                break;
            }
            case R.id.buttonReturn : {
                Intent intent = new Intent(this, List.class);
                startActivity(intent);
                break;
            }
        }
    }
}
