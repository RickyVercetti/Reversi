package com.reversi.ricardo.campos.reversi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class juego extends AppCompatActivity {


    private static int TAM = 8;
    private boolean turnoJugador;
    private static int botonTAG = 1;

    TextView textoJugador = null;
    TextView textoMaquina = null;

    Button boton = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Creamos el Layout dinámico
        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout layout_secundario = (RelativeLayout) inflater.inflate(R.layout.activity_juego, null, false);
        LinearLayout dinamico = (LinearLayout) layout_secundario.findViewById(R.id.dinamico);

        //Configurar los botones para que todos tengan el mismo peso
        LinearLayout.LayoutParams configuracion = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        configuracion.setMargins(0,0,0,0);
        configuracion.weight=1;

        //Creación de los botones
        for (int i = 0; i < TAM; i++) {
            LinearLayout filabotones = new LinearLayout(this);
            filabotones.setOrientation(LinearLayout.HORIZONTAL);
            int mitad = TAM/2;
            filabotones.setLayoutParams(configuracion);
            for (int j = 0; j < TAM; j++) {
                Button boton  = new Button(this); //TODO corregir la creación de botones
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
        turnoJugador = true;

        if (j == mitad && i == mitad)
        {
            boton.setText("J");
        }
        if (j == mitad-1 && i == mitad-1)
        {
            boton.setText("J");
        }
        if (j == mitad && i == mitad-1)
        {
            boton.setText("M");
        }
        if (j ==mitad-1 && i == mitad)
        {
            boton.setText("M");
        }

    }

    private View.OnClickListener casillaJuego = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            casillaPulsada(v);

        }
    };

    private void casillaPulsada(View v) {

        if (((Button)v).getText().equals(""))
        {
            if ((casillaPermitida((Button)v))) //TODO 2. Comprobamos que la casilla está permitida
            {

            }



            //Object tag = ((Button)v).getTag();

            //TODO metemos el contador de puntuaciones
            textoJugador = (TextView) findViewById(R.id.puntuacionJugador);
            textoMaquina = (TextView) findViewById(R.id.puntuacionMaquina);
            int contadorMaquina = 0;
            int contadorJugador = 0;
            for (int i = 0; i < TAM;i++) {
                for (int j = 0; j < TAM; j++) {
                    if (((Button) v).getText() == "X") {
                        contadorJugador++;
                    }else if(((Button)v).getText() == "O"){
                        contadorMaquina++;
                    }else{

                    }
                }
            }
            textoJugador.setText(contadorJugador);
            textoMaquina.setText(contadorMaquina);
        }
    }

    public boolean casillaPermitida(Button v)
    {
        //int i=1,j=10;
        //TODO compruebo cual es la casilla pulsada
        int tag = (int)(v.getTag());
        //TODO restringido zona superior izquierda
        if (tag == 1)
        {
            if (v.getTag() == "J")
            {
                if (turnoJugador == true)
                {
                    ((Button)v).setText("J");
                    turnoJugador = false;
                }
                else
                {
                    ((Button) v).setText("O");
                    turnoJugador = true;
                }
            }
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_juego, menu);
        return true;
    }
}
