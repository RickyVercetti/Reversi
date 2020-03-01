package com.reversi.ricardo.campos.reversi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class juego extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private int TAM;
    private boolean ayuda;
    private String dificultad;

    MiBoton [][] boton;
    TextView textoJugador = null;
    TextView textoMaquina = null;
    private boolean turnoJugador;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.onSaveInstanceState(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        TAM = Integer.parseInt(sharedPreferences.getString("tamTablero","8"));
        ayuda = sharedPreferences.getBoolean("ayuda",false);
        dificultad = sharedPreferences.getString("dificultad","Individual");
        boton =  new MiBoton[TAM][TAM];
        //Creamos el Layout dinámico
        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout layout_secundario = (RelativeLayout) inflater.inflate(R.layout.activity_juego, null, false);
        LinearLayout dinamico = layout_secundario.findViewById(R.id.dinamico);

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
                boton[i][j]  = new MiBoton(this);
                boton[i][j].setTag(i+""+j);
                boton[i][j].setText("");
                boton[i][j].setLayoutParams(configuracion);
                filabotones.addView(boton[i][j]);
                boton[i][j].setOnClickListener(casillaJuego);
            }
            dinamico.addView(filabotones);
        }
        setContentView(layout_secundario);
        tableroInicial();
    }


    public void tableroInicial() {
        turnoJugador = true;
        int mitad = TAM/2;

        boton[mitad][mitad].setText("J");
        mostrarFichaJugador(mitad,mitad);
        boton[mitad-1][mitad-1].setText("J");
        mostrarFichaJugador(mitad-1,mitad-1);
        boton[mitad][mitad-1].setText("M");
        mostrarFichaMaquina(mitad,mitad-1);
        boton[mitad-1][mitad].setText("M");
        mostrarFichaMaquina(mitad-1,mitad);
        buscarCasillasPermitidas();
    }


    private View.OnClickListener casillaJuego = new View.OnClickListener()
        {public void onClick(View v) {
            casillaPulsada(v);
            buscarCasillasPermitidas();
            int posible = 0;
            int jugador = 0;
            int maquina = 0;
            ArrayList<String> random = new ArrayList<>(0);
            String tag;
            for (int i = 0; i < TAM;i++) {
                for (int j = 0; j < TAM; j++) {
                    if (boton[i][j].getText().equals("P"))
                    {
                        posible++;
                        if (!turnoJugador)
                        {
                            random.add(i+""+j);
                        }
                    }
                    if (boton[i][j].getText().equals("J"))
                    {
                        jugador++;
                    }
                    if (boton[i][j].getText().equals("M"))
                    {
                        maquina++;
                    }
                }
            }

            if (jugador + maquina != TAM * TAM)
            {
                if (posible==0)
                {
                    cambioTurno();
                }else if (dificultad.equals("Individual"))
                {
                    if (!turnoJugador)
                    {
                        int aleatorio = (int) Math.floor(Math.random()*(random.size()));
                        tag = random.get(aleatorio);
                        int pos_i = Integer.parseInt(tag.substring(0,1));
                        int pos_j = Integer.parseInt(tag.substring(1));
                        casillaJuego.onClick(boton[pos_i][pos_j]);
                    }
                }
            }

        }
    };
    
    private void cambioTurno() {

        Toast.makeText(getApplicationContext(), "Imposible insertar una ficha, cambio de turno!", Toast.LENGTH_LONG).show();
        turnoJugador = !turnoJugador;
        buscarCasillasPermitidas();
    }


    private void casillaPulsada(View v) {

        if (((Button)v).getText().equals("P"))
        {
            if (turnoJugador)
            {
                ((Button) v).setText("J");
                String tag = v.getTag().toString();
                int i = Integer.parseInt(tag.substring(0,1));
                int j = Integer.parseInt(tag.substring(1));
                mostrarFichaJugador(i,j);
                girarCasillas(i,j);
                restaurarCasillas();
                turnoJugador=false;
            }
            else
            {
                ((Button) v).setText("M");
                String tag = v.getTag().toString();
                int i = Integer.parseInt(tag.substring(0,1));
                int j = Integer.parseInt(tag.substring(1));
                mostrarFichaMaquina(i,j);
                girarCasillas(i,j);
                restaurarCasillas();
                turnoJugador=true;
            }

            textoJugador = findViewById(R.id.puntuacionJugador);
            textoMaquina = findViewById(R.id.puntuacionMaquina);
            int contadorMaquina = 0;
            int contadorJugador = 0;
            int casillasVacias = 0;

            for (int i = 0; i < TAM;i++) {
                for (int j = 0; j < TAM; j++) {
                    if (boton[i][j].getText() == "J") {
                        contadorJugador++;
                        mostrarFichaJugador(i,j);
                    }
                    if(boton[i][j].getText() == "M"){
                        contadorMaquina++;
                        mostrarFichaMaquina(i,j);
                    }
                    if(boton[i][j].getText() == ""){
                        casillasVacias++;
                    }
                }
            }
            textoJugador.setText(String.valueOf(contadorJugador));
            textoMaquina.setText(String.valueOf(contadorMaquina));

            if (casillasVacias==0)
            {
                String partida;
                String puntos = String.valueOf(contadorJugador);

                if (contadorJugador>contadorMaquina) { partida = "Has Ganado!";}
                else if (contadorJugador<contadorMaquina) { partida = "Has Perdido!";}
                else { partida = "Empate!";}

                Intent intent = new Intent(this,introducir_nombre.class);
                intent.putExtra("PARTIDA",partida);
                intent.putExtra("PUNTUACION",puntos);
                startActivity(intent);
                this.finish();
            }
        }
    }


    private void buscarCasillasPermitidas() {
        for (int i=0;i<TAM;i++)
        {
            for (int j=0;j<TAM;j++)
            {
                if (boton[i][j].getText()=="") {

                    buscarNO(i, j);
                    buscarN(i, j);
                    buscarNE(i, j);
                    buscarO(i, j);
                    buscarE(i, j);
                    buscarSO(i, j);
                    buscarS(i, j);
                    buscarSE(i, j);
                }
            }
        }
    }


    private void buscarNO(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int iInicial = i,jInicial = j;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }


        if (i>=2 && j>=2)
        {
            i--;j--;
            while (boton[i][j].getText().equals(fichacontraria) && i>=1 && j>=1)
            {
                i--;j--;

            }
            if (!((i+1 == iInicial) && (j+1 == jInicial)))
            {
                if (boton[i][j].getText().equals(fichapropia))
                {
                    boton[iInicial][jInicial].setText("P");
                    mostrarFichaPosible(iInicial,jInicial);
                }
            }
        }
    }
    private void buscarN(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int iInicial = i;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i>=2)
        {
            i--;
            while (boton[i][j].getText().equals(fichacontraria) && i>=1)
            {
                i--;
            }
            if (!(i+1 == iInicial))
            {
                if (boton[i][j].getText().equals(fichapropia))
                {
                    boton[iInicial][j].setText("P");
                    mostrarFichaPosible(iInicial,j);
                }
            }
        }
    }
    private void buscarNE(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int iInicial = i,jInicial = j;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i>=2 && j<=TAM-2)
        {
            i--;j++;
            while (boton[i][j].getText().equals(fichacontraria) && i>=1 && j<=TAM-2)
            {
                i--;j++;
            }
            if (!((i+1 == iInicial) && (j-1 == jInicial)))
            {
                if (boton[i][j].getText().equals(fichapropia))
                {
                    boton[iInicial][jInicial].setText("P");
                    mostrarFichaPosible(iInicial,jInicial);
                }
            }
        }
    }
    private void buscarO(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int jInicial = j;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (j>=2)
        {
            j--;
            while (boton[i][j].getText().equals(fichacontraria) && j>=1)
            {
                j--;
            }
            if (!(j+1 == jInicial))
            {
                if (boton[i][j].getText().equals(fichapropia))
                {
                    boton[i][jInicial].setText("P");
                    mostrarFichaPosible(i,jInicial);
                }
            }
        }
    }
    private void buscarE(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int jInicial = j;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (j<=TAM-2)
        {
            j++;
            while (boton[i][j].getText().equals(fichacontraria) && j<=TAM-2)
            {
                j++;
            }
            if (!(j-1 == jInicial))
            {
                if (boton[i][j].getText().equals(fichapropia))
                {
                    boton[i][jInicial].setText("P");
                    mostrarFichaPosible(i,jInicial);
                }
            }
        }
    }
    private void buscarSO(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int iInicial = i,jInicial = j;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i<=TAM-2 && j>=2)
        {
            i++;j--;
            while (boton[i][j].getText().equals(fichacontraria) && i<=TAM-2 && j>=1)
            {
                i++;j--;

            }
            if (!((i-1 == iInicial) && (j+1 == jInicial)))
            {
                if (boton[i][j].getText().equals(fichapropia))
                {
                    boton[iInicial][jInicial].setText("P");
                    mostrarFichaPosible(iInicial,jInicial);
                }
            }
        }
    }
    private void buscarS(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int iInicial = i;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i<=TAM-2)
        {
            i++;
            while (boton[i][j].getText().equals(fichacontraria) && i<=TAM-2)
            {
                i++;
            }
            if (!(i-1 == iInicial))
            {
                if (boton[i][j].getText().equals(fichapropia))
                {
                    boton[iInicial][j].setText("P");
                    mostrarFichaPosible(iInicial,j);
                }
            }
        }
    }
    private void buscarSE(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int iInicial = i,jInicial = j;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i<=TAM - 2 && j<= TAM - 2)
        {
            i++; j++;
            while (boton[i][j].getText().equals(fichacontraria) && i<=TAM-2 && j<=TAM-2)
            {
                i++;j++;
            }
            if (!((i-1 == iInicial) && (j-1 == jInicial)))
            {
                if (boton[i][j].getText().equals(fichapropia))
                {
                    boton[iInicial][jInicial].setText("P");
                    mostrarFichaPosible(iInicial,jInicial);
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

        String fichacontraria;
        String fichapropia;
        int iInicial = i,jInicial = j;
        int contador = 1;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i>=2 && j>=2)
        {
            i--; j--;
            while (boton[i][j].getText().equals(fichacontraria) && i>=1 && j>=1)
            {
                contador++;
                i--; j--;
            }
            if (boton[i][j].getText().equals(fichapropia))
            {
                for (int z = 1; z<contador;z++)
                {
                    boton[iInicial-z][jInicial-z].setText(fichapropia);
                }
            }
        }
    }
    private void girarN(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int iInicial = i;
        int contador = 1;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i >=2)
        {
            i--;
            while (boton[i][j].getText().equals(fichacontraria) && i>=1)
            {
                contador++;
                i--;
            }

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

        String fichacontraria;
        String fichapropia;
        int iInicial = i,jInicial = j;
        int contador = 1;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i>=2 && j<=TAM-2)
        {
            i--; j++;
            while (boton[i][j].getText().equals(fichacontraria) && i>=1 && j<=TAM-2)
            {
                contador++;
                i--; j++;
            }
            if (boton[i][j].getText().equals(fichapropia))
            {
                for (int z = 1; z<contador;z++)
                {
                    boton[iInicial-z][jInicial+z].setText(fichapropia);
                }
            }
        }
    }
    private void girarO(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int jInicial = j;
        int contador = 1;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (j>=2)
        {
            j--;
            while (boton[i][j].getText().equals(fichacontraria) && j>=1)
            {
                contador++;
                j--;
            }
            if (boton[i][j].getText().equals(fichapropia))
            {
                for (int z = 1; z<contador;z++)
                {
                    boton[i][jInicial-z].setText(fichapropia);
                }
            }
        }
    }
    private void girarE(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int jInicial = j;
        int contador = 1;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (j<=TAM-2)
        {
            j++;
            while (boton[i][j].getText().equals(fichacontraria) && j<=TAM-2)
            {
                contador++;
                j++;
            }
            if (boton[i][j].getText().equals(fichapropia))
            {
                for (int z = 1; z<contador;z++)
                {
                    boton[i][jInicial+z].setText(fichapropia);
                }
            }
        }
    }
    private void girarSO(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int iInicial = i,jInicial = j;
        int contador = 1;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i<=TAM-2 && j>=2)
        {
            i++; j--;
            while (boton[i][j].getText().equals(fichacontraria) && i<=TAM-2 && j>=1)
            {
                contador++;
                i++; j--;
            }
            if (boton[i][j].getText().equals(fichapropia))
            {
                for (int z = 1; z<contador;z++)
                {
                    boton[iInicial+z][jInicial-z].setText(fichapropia);
                }
            }
        }
    }
    private void girarS(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int iInicial = i;
        int contador = 1;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i<=TAM-2)
        {
            i++;
            while (boton[i][j].getText().equals(fichacontraria) && i<=TAM-2)
            {
                contador++;
                i++;
            }
            if (boton[i][j].getText().equals(fichapropia))
            {
                for(int z = 1; z < contador; z++)
                {
                    boton[iInicial+z][j].setText(fichapropia);
                }
            }
        }
    }
    private void girarSE(int i, int j) {

        String fichacontraria;
        String fichapropia;
        int iInicial = i,jInicial = j;
        int contador = 1;
        if (turnoJugador){ fichacontraria = "M"; fichapropia = "J"; }
        else{ fichacontraria = "J"; fichapropia="M"; }

        if (i<=TAM-2 && j<=TAM-2)
        {
            i++; j++;
            while (boton[i][j].getText().equals(fichacontraria) && i<=TAM-2 && j<=TAM-2)
            {
                contador++;
                i++; j++;
            }
            if (boton[i][j].getText().equals(fichapropia))
            {
                for (int z = 1; z<contador;z++)
                {
                    boton[iInicial+z][jInicial+z].setText(fichapropia);
                }
            }
        }
    }


    private void restaurarCasillas() {

        int i,j;

        for (i=0;i<TAM;i++)
        {
            for (j=0;j<TAM;j++)
            {
                if (boton[i][j].getText().equals("P"))
                {
                    boton[i][j].setText("");
                    boton[i][j].paint.setARGB(0,148,148,148);
                    boton[i][j].invalidate();
                }
            }
        }
    }


    private void mostrarFichaJugador(int i,int j) {
        boton[i][j].paint.setARGB(255,250,250,250);
        boton[i][j].invalidate();
    }
    private void mostrarFichaMaquina(int i,int j) {
        boton[i][j].paint.setARGB(255,0,0,0);
        boton[i][j].invalidate();
    }
    private void mostrarFichaPosible(int i, int j) {

        if (ayuda)
        {
            /*boton[i][j].paint.setARGB(255,148,148,148);*/
            boton[i][j].paint.setARGB(255,250 ,50,50);
            boton[i][j].invalidate();
        }
        else
        {
            boton[i][j].paint.setARGB(255,215,215,215);
            boton[i][j].invalidate();
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_juego, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.ayuda) {
            ayuda = !ayuda;

            for (int i=0;i<TAM;i++) {
                for (int j = 0; j < TAM; j++) {
                    if (boton[i][j].getText() == "P") {
                        mostrarFichaPosible(i,j);
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}