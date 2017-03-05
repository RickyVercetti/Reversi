package com.reversi.ricardo.campos.reversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

    }

    public void btn_jugarOnClick(View view) {

        Intent intent = new Intent(this, juego.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.acerca)
        {
            Intent intent = new Intent(this, acerca.class);
            startActivity(intent);
        }
        else if (id == R.id.instrucciones)
        {
            Intent intent = new Intent(this, instrucciones.class);
            startActivity(intent);
        }
        else if (id == R.id.noticias)
        {
            //Intent intent = new Intent(this, JsonAdapterNoticias.class);
            //startActivity(intent);
            Toast.makeText(this, "Disponible próximamente!", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.puntuaciones)
        {
            Intent intent = new Intent(this, puntuaciones.class);
            startActivity(intent);
        }
        else if (id == R.id.preferencias)
        {
            Intent intent = new Intent(this, preferencias.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
