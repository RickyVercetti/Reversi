package com.reversi.ricardo.campos.reversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class menuInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);
    }


    public void btn_jugarOnClick(View view) {

        Intent intent = new Intent(this, principal.class);
        startActivity(intent);
    }

    /*
    public void btn_instruccionesOnClick(View view) {

        Intent intent = new Intent(this, instrucciones.class);
        startActivity(intent);
    }

    public void btn_puntuacionesOnClick(View view) {

        Intent intent = new Intent(this, puntuaciones.class);
        startActivity(intent);
    }
    */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }
}
