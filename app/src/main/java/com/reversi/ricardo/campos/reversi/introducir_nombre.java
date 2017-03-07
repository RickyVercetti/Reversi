package com.reversi.ricardo.campos.reversi;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class introducir_nombre extends AppCompatActivity {

    String partida;
    String puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir_nombre);
        Intent intent = getIntent();
        partida = intent.getStringExtra("PARTIDA");
        puntuacion = intent.getStringExtra("PUNTUACION");
        TextView text = (TextView) findViewById(R.id.muestra_partida);
        text.setText(partida);
    }

    public void onClik_Aceptar(View view)
    {
        EditText texto = (EditText) findViewById(R.id.ET_nombre);
        String nombre = texto.getText().toString();

        //Log.e("Mierda","Nombre = "+nombre);
        if(nombre != "")
        {
            if(nombre.length() > 10)
            {
                nombre = nombre.substring(0,9);
            }
            BBDDpuntuaciones db_puntuaciones = new BBDDpuntuaciones(getApplicationContext(),"puntuaciones", null, 1);
            SQLiteDatabase db = db_puntuaciones.getWritableDatabase();
            String sql = "INSERT INTO puntuaciones (nom,puntuacion,fecha) VALUES ('"+nombre+"','"+puntuacion+"','"+new java.sql.Date(new Date().getTime())+"');";
            db.execSQL(sql);
            db.close();
            Intent intent = new Intent(this,puntuaciones.class);
            startActivity(intent);
            this.finish();
        }
        else
        {
            Toast.makeText(this, "Introduce un nombre o nick", Toast.LENGTH_SHORT).show();
        }
    }
    /*public void onBackPressed() {

        this.finish();

    }*/
}
