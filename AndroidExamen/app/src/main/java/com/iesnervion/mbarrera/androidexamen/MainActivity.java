package com.iesnervion.mbarrera.androidexamen;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import com.iesnervion.mbarrera.androidexamen.Contrato.Jugador_DB;

public class MainActivity extends AppCompatActivity{

    //SQLITE
    String consultaJugadores = "SELECT * FROM "+Jugador_DB.JUGADOR_DB_TABLE_NAME+";";
    JugadorDatabaseHelper manejadoraJugadores ;

    ListView miLista;
    MyArrayAdapterTotal<Jugador> miArrayAdapter;
    ArrayList<Jugador> jugadores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Inicializacion
        miLista = (ListView) findViewById(R.id.miLista);


        //BOTON FLOTANTE AÑADIR
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.botonFlotanteADD);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para ir ala actividad Formulario
                Intent i = new Intent(getApplicationContext(),FormularioActivity.class);
                startActivity(i);
            }
        });

        //Obtener Datos
        //NO RECIBE BIEN LOS DATOS
        //jugadores = ((Datos) this.getApplication()).getJugadores();

        //SQLITE
        jugadores = obtenerListado();

        //Linea de prueba
        //jugadores.add(new Jugador("migue",R.drawable.jugador00,1.92,80,"Alero"));

        //Preparo en array para introducirlo a ArrayAdapter
        Jugador[] jugadoresArray =  jugadores.toArray(new Jugador[0]);
        miArrayAdapter = new MyArrayAdapterTotal<Jugador>(this,R.layout.fila_personalizada,R.id.tvNombre, jugadoresArray);
        miLista.setAdapter(miArrayAdapter);

        //Listenner para la lista
        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                showPopupMenu(v,position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showPopupMenu(final View view, final int position)
    {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_pop, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                ListView milista = (ListView) view.getParent();

                switch (item.getItemId()){
                    case R.id.borrarItem:
                        //BorrarJugador
                        //((Datos) MainActivity.this.getApplication()).borrarJugador(position);
                        borrarJugador(position);
                        //Obtener la lista actualizada
                        //jugadores =  ((Datos) MainActivity.this.getApplication()).getJugadores();
                        jugadores = obtenerListado();
                        Jugador[] jugadoresArray =  jugadores.toArray(new Jugador[0]);
                        //Crear adapter con la lista actualizada
                        miArrayAdapter = new MyArrayAdapterTotal<Jugador>(getApplicationContext(),R.layout.fila_personalizada,R.id.tvNombre, jugadoresArray);
                        //EnviarAdapter
                        MainActivity.this.miLista.setAdapter(miArrayAdapter);

                        //Notificar al usuario
                        Toast.makeText(getApplicationContext(),"Se ha borrado un jugador",Toast.LENGTH_LONG).show();

                        break;


                    case R.id.editarItem:


                        Intent i = new Intent(getApplicationContext(),FormularioActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("playerAEditar",(Jugador)milista.getItemAtPosition(position));
                        i.putExtras(bundle);
                        startActivity(i);
                        break;

                }
                return true;
            }
        });
        //dont forget to show the menu
        popupMenu.show();
    }

    private void borrarJugador(int position) {
        Jugador j = jugadores.get(position);
        String cadena = "DELETE FROM "+Jugador_DB.JUGADOR_DB_TABLE_NAME+" WHERE "+Jugador_DB.JUGADOR_DB_NOMBRE+" = '"+j.getNombre()+"' ;";
        SQLiteDatabase db = manejadoraJugadores.getWritableDatabase();
        db.execSQL(cadena);
        db.close();
    }

    public ArrayList<Jugador> obtenerListado(){
        ArrayList<Jugador> js = new ArrayList<Jugador>();
        manejadoraJugadores = new JugadorDatabaseHelper(this);
        SQLiteDatabase db = manejadoraJugadores.getReadableDatabase();

        Cursor cursor = db.rawQuery(consultaJugadores,null);

        if(cursor.moveToFirst()){
            do{
                String nombre = cursor.getString(cursor.getColumnIndex(Jugador_DB.JUGADOR_DB_NOMBRE));
                String posicion = cursor.getString(cursor.getColumnIndex(Jugador_DB.JUGADOR_DB_POSICION));
                double altura = cursor.getDouble(cursor.getColumnIndex(Jugador_DB.JUGADOR_DB_ALTURA));
                double peso = cursor.getDouble(cursor.getColumnIndex(Jugador_DB.JUGADOR_DB_PESO));
                int img = cursor.getInt(cursor.getColumnIndex(Jugador_DB.JUGADOR_DB_IMAGEN));

                Jugador j = new Jugador(nombre,img,altura,peso,posicion);
                js.add(j);
            }while (cursor.moveToNext());
        }

        db.close();

        return (js);
    }


}
