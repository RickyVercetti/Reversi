package com.reversi.ricardo.campos.reversi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BBDDpuntuaciones extends SQLiteOpenHelper{

    private String sqlCreate = "CREATE TABLE puntuaciones (nom TEXT, puntuacion TEXT, fecha TEXT)";

    public BBDDpuntuaciones(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(contexto, nombre, factory, version);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sqlCreate);
        /*if (db != null)
        {
            db.execSQL("INSERT INTO puntuaciones (nom,puntuacion,fecha) VALUES ('Elon','20','03-02-2017')");
            db.execSQL("INSERT INTO puntuaciones (nom,puntuacion,fecha) VALUES ('Ricardo','30','01-02-2017')");
        }*/
    }

    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva)
    {
        db.execSQL("DROP TABLE IF EXISTS puntuaciones");
        db.execSQL(sqlCreate);
    }

}
