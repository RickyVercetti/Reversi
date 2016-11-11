package com.reversi.ricardo.campos.reversi;

import android.support.v7.app.ActionBar;
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

        //llamar a juego
        //TODO hacer que funcione el toast y llevar a la pantalla juego
        Toast.makeText(this, "A Jugar!", Toast.LENGTH_SHORT).show();

        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout layout_secundario = (RelativeLayout) inflater.inflate(R.layout.activity_juego, null, false);
        LinearLayout dinamico = (LinearLayout) layout_secundario.findViewById(R.id.dinamico);

        //Configurar los botones para que todos tengan el mismo peso
        LinearLayout.LayoutParams configuracion = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        configuracion.setMargins(0,0,0,0);
        configuracion.weight=1;

        for (int i = 0; i < 7; i++) {
            LinearLayout filabotones = new LinearLayout(this);
            filabotones.setOrientation(LinearLayout.HORIZONTAL);

            filabotones.setLayoutParams(configuracion);
            for (int j = 0; i < 7; i++) {
                Button boton = new Button(this);
                boton.setLayoutParams(configuracion);
                filabotones.addView(boton);
            }
            dinamico.addView(filabotones);
        }
        setContentView(layout_secundario);


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    public void onClick(View v) {
        if (v.getId() == R.id.jugar) {
            //llamar a juego
            //TODO hacer que funcione el toast y llevar a la pantalla juego
            Toast.makeText(this, "A Jugar!", Toast.LENGTH_SHORT).show();



        }
        //Añadir más botones si los hay
    }
    /*public void pantalla_juego()
    {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout layout_secundario = (RelativeLayout) inflater.inflate(R.layout.activity_juego, null, false);
        LinearLayout dinamico[] = (LinearLayout[]) layout_secundario.findViewById(R.id.dinamico);
        //Button buttons[][];
        for (int i = 0; i < 7; i++) {
            dinamico[i].setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; i < 7; i++) {
                dinamico[j] = (new LinearLayout(this));
                dinamico[j].setOrientation(LinearLayout.VERTICAL);
                Button button[i][j] = new Button(this);
                button[i][j].setHeight(20);
                button[i][j].setWidth(20);
                dinamico[i].addView(button[i][j]);
            }
        }
        setContentView(layout_secundario);
    }*/
}
