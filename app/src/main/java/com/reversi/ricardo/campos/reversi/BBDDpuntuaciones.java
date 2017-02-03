package com.reversi.ricardo.campos.reversi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alumno on 03/02/2017.
 */

public class BBDDpuntuaciones extends SQLiteOpenHelper{

    String sqlCreate = "CREATE TABLE puntuaciones (puntuacion TEXT, fecha TEXT)";

    public BBDDpuntuaciones(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(contexto, nombre, factory, version);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sqlCreate);
        if (db != null)
        {
            db.execSQL("INSERT INTO puntuaciones (puntuacion,fecha) VALUES ('20','03-02-2017')");
            db.execSQL("INSERT INTO puntuaciones (puntuacion,fecha) VALUES ('25','01-02-2017')");
        }
    }

    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva)
    {
        db.execSQL("DROP TABLE IF EXISTS puntuaciones");
        db. execSQL(sqlCreate);
    }

}
