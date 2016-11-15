package com.reversi.ricardo.campos.reversi;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class principal extends AppCompatActivity {


    private static int FACIL = 6;
    private static int MEDIO = 8;
    private static int DIFICIL = 10;

    private static int botonTAG = 0;


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

        for (int i = 0; i < DIFICIL; i++) {
            LinearLayout filabotones = new LinearLayout(this);
            filabotones.setOrientation(LinearLayout.HORIZONTAL);

            filabotones.setLayoutParams(configuracion);
            for (int j = 0; j < DIFICIL; j++) {
                Button boton  = new Button(this);
                boton.setTag(botonTAG);
                boton.setLayoutParams(configuracion);
                filabotones.addView(boton);
                casillaPermitida(i,j,botonTAG);
                botonTAG++;
            }
            dinamico.addView(filabotones);
        }
        setContentView(layout_secundario);


    }
    public boolean casillaPermitida(int i, int j, int dir)
    {
        NO
        {
            i-=1;
            j-=1;
        }
        N
        {
            j -=1;
        }
        NE
        {
            j-=1;
            i+=1;
        }
        E
        {
            i+=1;
        }
        SE
        {
            i+=1;
            j+=1;
        }
        S
        {
            j+=1;
        }
        SO
        {
            j+=1;
            i-=1;
        }
        O
        {
            i-=1;
        }


    }

    public boolean buscarCasillaDireccion()
    {

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

    }
}
