package com.reversi.ricardo.campos.reversi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class principal extends AppCompatActivity {


    //TODO  Cambiar los botones por layouts dentro de un for y luego añadir los botones
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return true;
    }

    public void onClick(View v)
    {
        if (v.getId()==R.id.jugar)
        {
            //llamar a juego
            //TODO hacer que funcione el toast y llevar a la pantalla juego
            Toast.makeText(this, "A Jugar!", Toast.LENGTH_SHORT).show();
            //setContentView(R.layout.activity_juego);
        }
    }
}
