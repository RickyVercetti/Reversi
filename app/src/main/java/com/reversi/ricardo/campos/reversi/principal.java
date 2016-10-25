package com.reversi.ricardo.campos.reversi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class principal extends AppCompatActivity {

    @Override
    //protected int casillas=7;

    //TODO  Cambiar los botones por layouts dentro de un for y luego añadir los botones
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout layout_secundario=(RelativeLayout) inflater.inflate(R.layout.activity_juego, null,false);
        LinearLayout dinamico = (LinearLayout) layout_secundario.findViewById(R.id.dinamico);
        Button boton = new Button(this);
        boton.setText("HOLA");
        boton.setWidth(20);
        boton.setHeight(20);
        //boton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dinamico.addView(boton);
        Button boton1 = new Button(this);

        boton1.setText("ADIOS");
        boton1.setWidth(20);
        boton1.setHeight(20);
        //boton1 .setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dinamico.addView(boton1);
        setContentView(layout_secundario);
    }
}
