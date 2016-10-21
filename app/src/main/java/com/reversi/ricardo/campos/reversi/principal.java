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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout layout_secundario=(RelativeLayout) inflater.inflate(R.layout.activity_juego, null,false);
        LinearLayout dinamico = (LinearLayout) layout_secundario.findViewById(R.id.dinamico);
        Button boton = new Button(this);
        boton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dinamico.addView(boton);
        setContentView(layout_secundario);
    }
}
