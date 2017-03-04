package com.reversi.ricardo.campos.reversi;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ricardo on 3/3/17.
 */

public class JsonAsyncTaskNoticias  extends AsyncTask {

    private Context context;
    private String cadena = "";
    private JSONArray jsonArray;
    private Drawable[] drawables;
    private Boolean conexionRealizada = false;
    private final String IP="10.0.2.2";

    public JsonAsyncTaskNoticias(Context context) {

        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        BufferedReader reader = null;
        try {
            URL url = new URL("http://"+IP+"/infodb.php");
            HttpURLConnection con = (HttpURLConnection) url
                    .openConnection();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                cadena += line;
            }

            jsonArray = new JSONArray(cadena);
            drawables = new Drawable[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                url = new URL("http://"+IP+"/imagenes/" + jsonArray.getJSONObject(i).getString("image"));
                con = (HttpURLConnection) url
                        .openConnection();
                drawables[i] = Drawable.createFromStream(con.getInputStream(), "src name");
            }
            conexionRealizada = true;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_noticias, null);
        ListView lista_noticias = (ListView) view.findViewById(R.id.lista_noticias);
        lista_noticias.setAdapter(new JsonAdapterNoticias(context, jsonArray, drawables));

    }


}
