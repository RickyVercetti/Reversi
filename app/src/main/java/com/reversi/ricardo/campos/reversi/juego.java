package com.reversi.ricardo.campos.reversi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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


}
