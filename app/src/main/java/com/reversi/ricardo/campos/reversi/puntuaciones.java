package com.reversi.ricardo.campos.reversi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class puntuaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuaciones);

        BBDDpuntuaciones BBDDP = new BBDDpuntuaciones(this,"puntuaciones", null, 1);
        SQLiteDatabase db = BBDDP.getWritableDatabase();

        if (db != null)
        {
            Cursor c = db.rawQuery("SELECT puntuacion AS _id,nom,puntuacion,fecha FROM puntuaciones ORDER BY puntuacion DESC",null);
            String[] columna = new String[] {"nom","puntuacion","fecha"};
            int[] col = new int[] {R.id.textNombre,R.id.textPunto,R.id.textFecha};
            SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.tablapuntuaciones, c, columna, col,0);
            ListView list = (ListView)findViewById(R.id.lista);
            list.setAdapter(mAdapter);
        }
    }

    /*public void onBackPressed() {

        this.finish();
    }*/

}
