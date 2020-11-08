package com.example.vhnotepad3;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MyToast extends AppCompatActivity{

    public MyToast(Context context, String message, Drawable drawable) {
        android.widget.Toast toast = android.widget.Toast.makeText(context,message, android.widget.Toast.LENGTH_SHORT);
        toast.setGravity(0,0,60);
        View view = toast.getView();
        TextView view1 = view.findViewById(android.R.id.message);
        view1.setTextColor(Color.parseColor("#4a3111"));
        view1.setTextSize(24);
        view.setBackgroundResource(R.color.colorPrimaryDark);
        view.setBackground(drawable);
        toast.show();
    }
}
