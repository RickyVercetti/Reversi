package com.reversi.ricardo.campos.reversi;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.GREEN;

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
                boton[i][j].setTag(i+""+j);
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


        //TODO EN el centro
        boton[mitad][mitad].setText("J");
        boton[mitad-1][mitad-1].setText("J");
        boton[mitad][mitad-1].setText("M");
        boton[mitad-1][mitad].setText("M");
        buscarCasillasPermitidas();

        /*
        boton[1][1].setText("J");
        boton[0][0].setText("J");
        boton[0][1].setText("M");
        boton[1][0].setText("M");

        boton[0][TAM-1].setText("J");
        boton[1][TAM-2].setText("J");
        boton[0][TAM-2].setText("M");
        boton[1][TAM-1].setText("M");

        boton[TAM-1][0].setText("J");
        boton[TAM-2][1].setText("J");
        boton[TAM-1][1].setText("M");
        boton[TAM-2][0].setText("M");

        boton[TAM-1][TAM-1].setText("J");
        boton[TAM-2][TAM-2].setText("J");
        boton[TAM-1][TAM-2].setText("M");
        boton[TAM-2][TAM-1].setText("M");

    */


    }

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
            if (turnoJugador==true)
            {
                ((Button) v).setText("J");
                String tag = ((Button)v).getTag().toString();
                int i = Integer.parseInt(tag.substring(0,1));
                int j = Integer.parseInt(tag.substring(1));
                girarCasillas(i,j);

                turnoJugador=false;
            }
            else
            {
                ((Button) v).setText("M");
                turnoJugador=true;
            }

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
            //Toast.makeText(this, "Aquí ya ahí una ficha!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Toast.makeText(this, "Aquí no puedes!", Toast.LENGTH_SHORT).show();
        }


        //TODO metemos el contador de puntuaciones

        }


    private void buscarCasillasPermitidas() {
        for (int i=0;i<TAM;i++)
        {
            for (int j=0;j<TAM;j++)
            {
                if (boton[i][j].getText()=="") {

                    NO(i, j);
                    Log.w("???","NO - i="+i+" j="+j);
                    N(i, j);
                    Log.w("???","N - i="+i+" j="+j);
                    NE(i, j);
                    Log.w("???","NE - i="+i+" j="+j);
                    O(i, j);
                    Log.w("???","O - i="+i+" j="+j);
                    E(i, j);
                    Log.w("???","E - i="+i+" j="+j);
                    SO(i, j);
                    Log.w("???","SO - i="+i+" j="+j);
                    S(i, j);
                    Log.w("???","S - i="+i+" j="+j);
                    SE(i, j);
                    Log.w("???","SE - i="+i+" j="+j);
                }
            }
        }
    }

    private void NO(int i, int j) {

        String fichacontraria = null;
        String fichapropia = null;
        int iInicial = i,jInicial = j;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }
        if (i>=2 && j>=2)
        {
            if (boton[i-1][j-1].getText()==fichacontraria)
            {
                //j--;i--;
                while(j>=1 || i>=1)
                {
                    if (boton[i-1][j-1].getText()==fichacontraria)
                    {
                        j--;i--;
                    }
                    else if (boton[i-1][j-1].getText()==fichapropia)
                    {
                        boton[iInicial][jInicial].setText("P");
                        break;
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
    }
    private void N(int i, int j) {

        String fichacontraria = null;
        String fichapropia = null;
        int iInicial = i,jInicial = j;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i>=2)
        {
            if (boton[i-1][j].getText()==fichacontraria)
            {
                //i--;
                while(i>=1)
                {
                    if (boton[i-1][j].getText()==fichacontraria)
                    {
                        i--;
                    }
                    else if (boton[i-1][j].getText()==fichapropia)
                    {
                    boton[iInicial][jInicial].setText("P");
                    break;
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
    }
    private void NE(int i, int j) {

        String fichacontraria = null;
        String fichapropia = null;
        int iInicial = i,jInicial = j;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }
        if (i>=2 && j<=TAM-2)
        {
            if (boton[i-1][j+1].getText()==fichacontraria)
            {
                //j++;i--;
                while(j<=TAM-1 || i>=1)
                {
                    if (boton[i-1][j+1].getText()==fichacontraria)
                    {
                        j++;i--;
                    }
                    else if (boton[i-1][j+1].getText()==fichapropia)
                    {
                        boton[iInicial][jInicial].setText("P");
                        break;
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
    }
    private void O(int i, int j) {

        String fichacontraria = null;
        String fichapropia = null;
        int iInicial = i,jInicial = j;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }
        //TODO Cambiado metodo de busqueda
        if(j>=2)
        {
            //j--;//TODO posible quitar
            if(boton[i][j-1].getText()==fichacontraria)
            {
                while(j>=1)
                {
                    if (boton[i][1].getText()==fichacontraria && boton[i][0].getText()==fichapropia)
                    {
                        boton[iInicial][jInicial].setText("P");
                        break;
                    }
                    if (boton[i][j-1].getText()==fichacontraria)
                    {
                        j--;
                    }
                    else if (boton[i][j-1].getText()==fichapropia)
                    {
                        boton[iInicial][jInicial].setText("P");
                        break;
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
    }
    private void E(int i, int j) {

        String fichacontraria = null;
        String fichapropia = null;
        int iInicial = i,jInicial = j;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (j<=TAM-2)
        {
            if (boton[i][j+1].getText()==fichacontraria)
            {
                //j++;
                while(j<=TAM-1)
                {
                    if (boton[i][j+1].getText()==fichacontraria)
                    {
                        j++;
                    }
                    else if (boton[i][j+1].getText()==fichapropia)
                    {
                        boton[iInicial][jInicial].setText("P");
                        break;
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
    }
    private void SO(int i, int j) {

        String fichacontraria = null;
        String fichapropia = null;
        int iInicial = i,jInicial = j;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }
        if (i<=TAM-2 && j>=2)
        {
            if (boton[i+1][j-1].getText()==fichacontraria)
            {
                //j--;i++;
                while(j>=1 || i<=TAM-1)
                {
                    if (boton[i+1][j-1].getText()==fichacontraria)
                    {
                        j--;i++;
                    }
                    else if (boton[i+1][j-1].getText()==fichapropia)
                    {
                        boton[iInicial][jInicial].setText("P");
                        break;
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
    }
    private void S(int i, int j) {

        String fichacontraria = null;
        String fichapropia = null;
        int iInicial = i,jInicial = j;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i<=TAM-2)
        {
            if (boton[i+1][j].getText()==fichacontraria)
            {
                //i++;
                while(i<=TAM-1)
                {
                    if (boton[i+1][j].getText()==fichacontraria)
                    {
                        i++;
                    }
                    else if (boton[i+1][j].getText()==fichapropia)
                    {
                        boton[iInicial][jInicial].setText("P");
                        break;
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
    }
    private void SE(int i, int j) {

        String fichacontraria = null;
        String fichapropia = null;
        int iInicial = i,jInicial = j;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }
        if (i<=TAM-2 && j<=TAM-2)
        {
            if (boton[i+1][j+1].getText()==fichacontraria)
            {
                //j++;i++;
                while(j<=TAM-1 || i<=TAM-1)
                {
                    if (boton[i+1][j+1].getText()==fichacontraria)
                    {
                        j++;i++;
                    }
                    else if (boton[i+1][j+1].getText()==fichapropia)
                    {
                        boton[iInicial][jInicial].setText("P");
                        break;
                    }else
                    {
                        break;
                    }
                }
            }
        }
    }


    private void girarCasillas(int i, int j) {

        girarNO(i,j);
        girarN(i,j);
        girarNE(i,j);
        girarO(i,j);
        girarE(i,j);
        girarSO(i,j);
        girarS(i,j);
        girarSE(i,j);
    }


    private void girarNO(int i, int j) {

        String fichacontraria = null;
        String fichapropia = null;
        int iInicial = i,jInicial = j;
        int contador = 0;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

    }

    private void girarN(int i, int j) {

        String fichacontraria = null;
        String fichapropia = null;
        int iInicial = i,jInicial = j;
        int contador = 1;
        if (turnoJugador==true){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i >=2)
        {
            i--;
            Log.w("MIERDA",String.valueOf(i));
            while (boton[i][j].getText().equals(fichacontraria) && i>=1)
            {
                Log.w("MIERDA",String.valueOf(i));
                contador++;
                i--;
            }
            Log.w("MIERDA",String.valueOf(i));
            if (boton[i][j].getText().equals(fichapropia))
            {
                for(int z = 1; z < contador; z++)
                {
                    boton[iInicial-z][j].setText(fichapropia);
                }
            }
        }
    }

    private void girarNE(int i, int j) {
    }

    private void girarO(int i, int j) {
    }

    private void girarE(int i, int j) {
    }

    private void girarSO(int i, int j) {
    }

    private void girarS(int i, int j) {
    }

    private void girarSE(int i, int j) {
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_juego, menu);
        return true;
    }
}
