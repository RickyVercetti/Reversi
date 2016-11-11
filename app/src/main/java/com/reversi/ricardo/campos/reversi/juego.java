package com.reversi.ricardo.campos.reversi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
/*
public class juego extends AppCompatActivity
{

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {

    }
}

    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //crea un layout por codigo
        LinearLayout layout_principal = new LinearLayout (this);
        //le da formato
        layout_principal.setOrientation(LinearLayout.VERTICAL);
        //Se crea un control de forma dinámica y se añade al layout
        TextView tv = new TextView(this);
        tv.setText("TextView en el layout principal!");
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setBackgroundColor(Color.rgb(170, 00, 00));
        layout_principal.addView(tv);
        //Añadimos un layout secundario creado desde recurso
        LayoutInflater inflater = LayoutInflater.from(this);
        View layout_secundario=inflater.inflate(R.layout.layout_secundario, (ViewGroup) layout_principal,false);
        layout_principal.addView(layout_secundario);

        this.setContentView(layout_principal);
    }



            /*Button boton = new Button(this);
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
            dinamico.addView(boton1);*/

