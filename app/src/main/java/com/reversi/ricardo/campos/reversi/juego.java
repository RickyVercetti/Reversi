package com.reversi.ricardo.campos.reversi;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import android.widget.Toast;

public class juego extends AppCompatActivity {


    SharedPreferences sharedPreferences;
    private int TAM;
    private boolean ayuda;
    private String dificultad;

    Button boton[][];
    TextView textoJugador = null;
    TextView textoMaquina = null;
    private boolean turnoJugador;
    private static int botonTAG = 1;
    private int casillasVacias;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        TAM = Integer.parseInt(sharedPreferences.getString("tamTablero","8"));
        ayuda = sharedPreferences.getBoolean("ayuda",false);
        dificultad = sharedPreferences.getString("dificultad","Normal");
        boton = new Button[TAM][TAM];
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

            filabotones.setLayoutParams(configuracion);
            for (int j = 0; j < TAM; j++) {
                boton[i][j]  = new Button(this); //TODO corregir la creación de botones
                boton[i][j].setTag(botonTAG);
                boton[i][j].setText("");
                boton[i][j].setLayoutParams(configuracion);
                filabotones.addView(boton[i][j]);
                boton[i][j].setOnClickListener(casillaJuego);

                //casillaPermitida(i,j,botonTAG);
                botonTAG++;
            }
            dinamico.addView(filabotones);
        }
        setContentView(layout_secundario);
        tableroInicial();
    }
    public void tableroInicial()
    {
        turnoJugador = true;

        int mitad = TAM/2;

        boton[mitad][mitad].setText("J");
        boton[mitad-1][mitad-1].setText("J");
        boton[mitad][mitad-1].setText("M");
        boton[mitad-1][mitad].setText("M");
        buscarCasillasPermitidas();
    }
    /*public void tableroInicial(int i, int j,int mitad,Button v) {
        turnoJugador = true;

        if (j == mitad && i == mitad)
        {
            v.setText("J");
        }
        if (j == mitad-1 && i == mitad-1)
        {
            v.setText("J");
        }
        if (j == mitad && i == mitad-1)
        {
            v.setText("M");
        }
        if (j ==mitad-1 && i == mitad)
        {
            v.setText("M");
        }
        //buscarCasillasPermitidas();
    }*/
    private View.OnClickListener casillaJuego = new View.OnClickListener() {
        public void onClick(View v)
        {
            casillaPulsada(v);
            buscarCasillasPermitidas();
        }
    };



    private void casillaPulsada(View v) {

        if (((Button)v).getText().equals("P"))
        {

            textoJugador = (TextView) findViewById(R.id.puntuacionJugador);
            textoMaquina = (TextView) findViewById(R.id.puntuacionMaquina);
            int contadorMaquina = 0;
            int contadorJugador = 0;
            for (int i = 0; i < TAM;i++) {
                for (int j = 0; j < TAM; j++) {
                    if (boton[i][j].getText() == "J") {
                        contadorJugador++;
                    }
                    if(boton[i][j].getText() == "M"){
                        contadorMaquina++;
                    }else{
                        casillasVacias++;
                    }
                }
            }
            textoJugador.setText(String.valueOf(contadorJugador));
            textoMaquina.setText(String.valueOf(contadorMaquina));
            if (casillasVacias==0)
            {

            }
        }
        if (((Button)v).getText().equals("J") || ((Button)v).getText().equals("M"))
        {
            Toast.makeText(this, "Aquí ya ahí una ficha!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Aquí no puedes!", Toast.LENGTH_SHORT).show();
        }


        //TODO metemos el contador de puntuaciones

        }
    private void buscarCasillasPermitidas() {
        for (int i=0;i<TAM;i++)
        {
            for (int j=0;j<TAM;j++)
            {
                if (boton[i][j].getText()=="") {

                    NO(boton, i, j, turnoJugador);
                    N(boton, i, j, turnoJugador);
                    NE(boton, i, j, turnoJugador);
                    O(boton, i, j, turnoJugador);
                    E(boton, i, j, turnoJugador);
                    SO(boton, i, j, turnoJugador);
                    S(boton, i, j, turnoJugador);
                    SE(boton, i, j, turnoJugador);
                }
            }
        }
    }

    private void NO(Button boton[][],int i, int j,boolean turnoJugador) {

        String fichacontraria = null;
        String fichapropia = null;

        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }
        do
        {
            if (boton[i][j].getText()!=fichacontraria)
            {
                break;
            }
            if (i==0 || j==0)
            {
                break;
            }
            i--;
            j--;
        }while (boton[i][j].getText()==fichacontraria);
        if (boton[i][j].getText()==fichapropia)
        {
            boton[i][j].setText("P");
        }
    }
    private void N(Button boton[][],int i, int j,boolean turnoJugador) {

        String fichacontraria = null;
        String fichapropia = null;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }
        do
        {
            if (boton[i][j].getText()!=fichacontraria)
            {
                break;
            }
            if (i==0)
            {
                break;
            }
            i--;
        }while (boton[i][j].getText()==fichacontraria);
        if (boton[i][j].getText()==fichapropia)
        {
            boton[i][j].setText("P");
        }
    }
    private void NE(Button boton[][],int i, int j,boolean turnoJugador) {

        String fichacontraria = null;
        String fichapropia = null;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }
        do
        {
            if (boton[i][j].getText()!=fichacontraria)
            {
                break;
            }
            if (i==0 || j%TAM==0)
            {
                break;
            }
            i--;
            j++;
        }while (boton[i][j].getText()==fichacontraria);
        if (boton[i][j].getText()==fichapropia)
        {
            boton[i][j].setText("P");
        }
    }
    private void O(Button boton[][],int i, int j,boolean turnoJugador) {

        String fichacontraria = null;
        String fichapropia = null;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }
        do
        {
            if (boton[i][j].getText()!=fichacontraria)
            {
                break;
            }
            if (j==0)
            {
                break;
            }
            j--;
        }while (boton[i][j].getText()==fichacontraria);
        if (boton[i][j].getText()==fichapropia)
        {
            boton[i][j].setText("P");
        }
    }
    private void E(Button boton[][],int i, int j,boolean turnoJugador) {

        String fichacontraria = null;
        String fichapropia = null;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }
        do
        {
            if (boton[i][j].getText()!=fichacontraria)
            {
                break;
            }
            if ( j%TAM==0)
            {
                break;
            }
            j++;
        }while (boton[i][j].getText()==fichacontraria);
        if (boton[i][j].getText()==fichapropia)
        {
            boton[i][j].setText("P");
        }
    }
    private void SO(Button boton[][],int i, int j,boolean turnoJugador) {

        String fichacontraria = null;
        String fichapropia = null;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }
        do
        {
            if (boton[i][j].getText()!=fichacontraria)
            {
                break;
            }
            if (i%TAM==0 || j<0)
            {
                break;
            }
            i++;
            j--;
        }while (boton[i][j].getText()==fichacontraria);
        if (boton[i][j].getText()==fichapropia)
        {
            boton[i][j].setText("P");
        }
    }
    private void S(Button boton[][],int i, int j,boolean turnoJugador) {

        String fichacontraria = null;
        String fichapropia = null;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }
        do
        {
            if (boton[i][j].getText()!=fichacontraria)
            {
                break;
            }
            if (i==TAM)
            {
                break;
            }
            i++;
        }while (boton[i][j].getText()==fichacontraria);
        if (boton[i][j].getText()==fichapropia)
        {
            boton[i][j].setText("P");
        }
    }
    private void SE(Button boton[][],int i, int j,boolean turnoJugador) {

        String fichacontraria = null;
        String fichapropia = null;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }
        do
        {
            if (boton[i][j].getText()!=fichacontraria)
            {
                break;
            }
            if (i>TAM || j==TAM)
            {
                break;
            }
            i++;
            j++;
        }while (boton[i][j].getText()==fichacontraria);
        if (boton[i][j].getText()==fichapropia)
        {
            boton[i][j].setText("P");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_juego, menu);
        return true;
    }
}
