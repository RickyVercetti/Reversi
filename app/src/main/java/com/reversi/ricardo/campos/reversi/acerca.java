package com.reversi.ricardo.campos.reversi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class acerca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);
    }
    public void click_icono(View view)
    {
        Toast.makeText(this, "Saludos desde Petrer!", Toast.LENGTH_SHORT).show();
    }
}
