package com.reversi.ricardo.campos.reversi;

import android.content.Context;
import android.nfc.Tag;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.reversi.ricardo.campos.reversi.R.id.dinamico;

public class principal extends AppCompatActivity {


    private static int TAM = 8;
    private boolean turno;

    private static int botonTAG = 1;
    //TODO linea para insertar vibración, falta insertar en el manifest
    //Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    //vibrator.vibrate(100); //Dentro del click

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //llamar a juego
        //TODO hacer que cambie de un activity a otro con un Intent
        //Toast.makeText(this, "A Jugar!", Toast.LENGTH_SHORT).show();

        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout layout_secundario = (RelativeLayout) inflater.inflate(R.layout.activity_juego, null, false);
        LinearLayout dinamico = (LinearLayout) layout_secundario.findViewById(R.id.dinamico);

        //Configurar los botones para que todos tengan el mismo peso
        LinearLayout.LayoutParams configuracion = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        configuracion.setMargins(0,0,0,0);
        configuracion.weight=1;
        Button boton;
        //Creación de los botones
        for (int i = 0; i < TAM; i++) {
            LinearLayout filabotones = new LinearLayout(this);
            filabotones.setOrientation(LinearLayout.HORIZONTAL);
            int mitad = TAM/2;
            filabotones.setLayoutParams(configuracion);
            for (int j = 0; j < TAM; j++) {
                boton  = new Button(this);
                boton.setTag(botonTAG);
                tableroInicial(i,j,mitad,boton);
                boton.setLayoutParams(configuracion);
                filabotones.addView(boton);
                boton.setOnClickListener(casillaJuego);
                //casillaPermitida(i,j,botonTAG);
                botonTAG++;
            }
            dinamico.addView(filabotones);
        }
        setContentView(layout_secundario);
    }
    public void tableroInicial(int i, int j,int mitad,Button boton)
    {
        turno = true;

        if (j == mitad && i == mitad)
        {
            boton.setText("X");
        }
        if (j == mitad-1 && i ==mitad-1)
        {
            boton.setText("X");
        }
        if (j == mitad && i == mitad-1)
        {
            boton.setText("O");
        }
        if (j ==mitad-1 && i == mitad)
        {
            boton.setText("O");
        }
    }
    /*public View.OnClickListener botonesJuego = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            ((Button) v).setText("X");
        }
    };*/

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    private View.OnClickListener casillaJuego = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            //TODO compruebo que la casilla está vacia
            if (((Button)v).getText().equals(""))
            {
                //Object tag = ((Button)v).getTag();
                if ((casillaPermitida((Button)v)))
                {
                    if (turno==true)
                    {
                        ((Button)v).setText("X");
                        turno=false;
                    }
                    else
                    {
                        ((Button) v).setText("O");
                        turno = true;
                    }
                }
                //TODO metemos el contador de puntuaciones
                TextView textoJugador = (TextView) findViewById(R.id.puntuacionJugador);
                TextView textoMaquina = (TextView) findViewById(R.id.puntuacionMaquina);
                int contadorMaquina = 0;
                int contadorJugador = 0;
                for (int i = 0; i < TAM;i++) {
                    for (int j = 0; j < TAM; j++) {
                        if (((Button) v).getText() == "X") {
                            contadorJugador++;
                        }else{
                            contadorMaquina++;
                        }
                    }
                }
                textoJugador.setText(contadorJugador);
                textoMaquina.setText(contadorMaquina);
            }
        }
    };

    /*public void onClick(View v) {
        if (v.getId() == R.id.jugar) {
            //llamar a juego
            //TODO hacer que funcione el toast y llevar a la pantalla juego
            Toast.makeText(this, "A Jugar!", Toast.LENGTH_SHORT).show();



        }
    }*/
    public boolean casillaPermitida(Button v)
    {
        //int i=1,j=10;
        //TODO compruebo cual es la casilla pulsada
        int tag = (int)(v.getTag());
        //TODO restringido zona superior izquierda
        if (tag == 1)
        {

        }
        //TODO restringido la zona superior para que no se salga
        if (tag > 1 && tag < TAM-1)
        {

        }
        //TODO restringido la zona superior derecha del panel
        if (tag == TAM)
        {

        }
        //TODO restringido la zona izquierda
        if (tag > TAM && tag < (TAM*TAM)-TAM && (tag-1)%TAM == 0)
        {

        }
        //TODO restringido la zona derecha
        if (tag > TAM && tag < (TAM*TAM)-(TAM-1) && tag%TAM==0)
        {

        }
        //TODO restringido la esquina inferior derecha
        if (tag == (TAM*TAM-1)+1)
        {

        }
        //TODO restringido la parte inferior
        if (tag > (TAM*TAM)+1 && tag < (TAM*TAM)-1)
        {

        }
        else {

        }


        return true;
    }

    public boolean buscarCasillaDireccion()
    {
    return true;
    }
}
