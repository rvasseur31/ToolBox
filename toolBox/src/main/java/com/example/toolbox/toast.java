package com.example.toolbox;

import android.content.Context;
import android.widget.Toast;

public class toast {
    public void Stoast(Context context, String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
