package com.reversi.ricardo.campos.reversi;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ricardo on 3/3/17.
 */

public class JsonAdapterNoticias  extends BaseAdapter{

    JSONArray noticias;
    Context contexto;
    Drawable[] drawables;

    public JsonAdapterNoticias(Context contexto, JSONArray noticias,Drawable[] drawables) {
        this.noticias = noticias;
        this.contexto = contexto;
        this.drawables = drawables;
    }

    @Override
    public int getCount() {
        return noticias.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return noticias.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JSONObject noticia;
        try {
            noticia = noticias.getJSONObject(position);
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(contexto);
                convertView = inflater.inflate(R.layout.tablanoticias, null);
            }

            TextView titulo = (TextView) convertView.findViewById(R.id.titulo_noticia);
            titulo.setText(noticia.getString("Titulo"));
            TextView cabecera = (TextView) convertView.findViewById(R.id.cabecera_noticia);
            cabecera.setText(noticia.getString("Cabecera"));
            TextView texto = (TextView) convertView.findViewById(R.id.texto_noticia);
            texto.setText(noticia.getString("Texto"));
            ImageView imagen = (ImageView) convertView.findViewById(R.id.imagen_noticia);
            imagen.setImageDrawable(drawables[position]);
            return convertView;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
