package com.reversi.ricardo.campos.reversi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class juego extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
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
    }*/




    /*LayoutInflater inflater = LayoutInflater.from(this);
    RelativeLayout layout_secundario=(RelativeLayout) inflater.inflate(R.layout.activity_juego, null,false);
    LinearLayout dinamico = (LinearLayout) layout_secundario.findViewById(R.id.dinamico);
    for (int i=0;i<casillas;i++)
    {
        dinamico[i].setOrientation(LinearLayout.HORIZONTAL);
        for (int j=0; i<casillas;i++)
        {
            linear_vertical[j] = (new LinearLayout(this));
            linear_vertical[j].setOrientation(LinearLayout.VERTICAL);
            boton[i][j] = new Button(this);
            boton[i][j].setHeight(20);
            boton[i][j].setWidth(20);
            dinamico.addView(boton[i][j]);
        }
    }
    setContentView(layout_secundario);*/

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


}
