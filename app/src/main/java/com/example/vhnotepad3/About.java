package com.example.vhnotepad3;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class About extends AppCompatActivity implements View.OnClickListener{
    ImageButton imageAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        imageAbout = findViewById(R.id.imageAbout);
        imageAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imageAbout){
            Intent intent = new Intent(this, Start.class);
            startActivity(intent);
        }
    }
}
