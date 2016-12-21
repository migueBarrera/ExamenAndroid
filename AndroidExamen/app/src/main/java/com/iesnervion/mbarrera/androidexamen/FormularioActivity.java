package com.iesnervion.mbarrera.androidexamen;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import com.iesnervion.mbarrera.androidexamen.Contrato.Jugador_DB;

public class FormularioActivity extends AppCompatActivity {

    public int CAMBIAR_IMAGEN = 1;
    Spinner spinerAltura,spinnerPeso;
    EditText editTextNombre;
    ImageView fotoPerfil;
    Jugador jugador;
    RadioGroup radio;
    JugadorDatabaseHelper manejadoraJugadores ;

    Datos datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Inicializacion
        spinnerPeso = (Spinner) findViewById(R.id.spPeso);
        spinerAltura = (Spinner) findViewById(R.id.spAltura);
        editTextNombre = (EditText) findViewById(R.id.formName);
        fotoPerfil = (ImageView) findViewById(R.id.formFoto);
        radio = (RadioGroup) findViewById(R.id.radrioGroup);


        //BOTON FLOTANTE GUARDAR
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Validamos Datos
                if(editTextNombre.getText()!=null){

                    //Rellenamos el jugador
                    jugador = new Jugador();
                    jugador.setNombre(editTextNombre.getText().toString());

                    //jugador.setAltura(); NOSE MUY BIEN QUE METODO USAR PARA COJER EL VALOR DEL SPINNER
                    //jugador.setPeso();
                    //jugador.setPosicion();

                    //Guardamos los datos
                    //((Datos) FormularioActivity.this.getApplication()).setPlayer(jugador);
                    //((Datos) FormularioActivity.this.getApplication()).guardarJugador();

                    //SQLITE
                    insertarJugador(jugador);


                    //Regresamos a la actividad principal
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(getApplicationContext(),"Hay algun dato erroneo",Toast.LENGTH_LONG).show();
                }
            }
        });

        //Spinner Peso
        Double[] listadoPeso;
        spinnerPeso.setAdapter(new ArrayAdapter<Double>(this,android.R.layout.simple_list_item_1,listadoDePeso()));

        //Spinner Altura

        spinerAltura.setAdapter(new ArrayAdapter<Double>(this,android.R.layout.simple_list_item_1,listadoDeAltura()));

        //Comprobamos si venimos para editar o para crear uno nuevo
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        if(bundle!=null){
            //Venimos para editar
            jugador = bundle.getParcelable("playerAEditar");
            editTextNombre.setText(jugador.getNombre());
            fotoPerfil.setImageResource(jugador.getImg());


        }else {

        }

    }


    public void cambiarImagen(View v){
        Intent i = new Intent(getApplicationContext(),GridFotos.class);
        startActivityForResult(i,CAMBIAR_IMAGEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==CAMBIAR_IMAGEN && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Drawable nombreFoto = (Drawable) extras.get("foto");
            fotoPerfil.setImageDrawable(nombreFoto);

            //imageView.setImageBitmap(imageBitmap);
        }else{
            Toast.makeText(this,"Algo salio mal al traer la foto",Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<Double> listadoDePeso(){
        ArrayList<Double> listado = new ArrayList<Double>();

        double i = 45;
        while (i<251){
            listado.add(i);
            i++;
        }

        return listado;

    }

    public ArrayList<Double> listadoDeAltura(){
        ArrayList<Double> listado = new ArrayList<Double>();

        double i = 150;
        while (i<251){
            listado.add(i);
            i++;
        }

        return listado;

    }

    public void insertarJugador(Jugador j){
        manejadoraJugadores = new JugadorDatabaseHelper(this);
        SQLiteDatabase db = manejadoraJugadores.getWritableDatabase();
        String insertJugador = "INSERT INTO "+ Jugador_DB.JUGADOR_DB_TABLE_NAME+" ("+Jugador_DB.JUGADOR_DB_NOMBRE
                                                +","+Jugador_DB.JUGADOR_DB_IMAGEN+","+Jugador_DB.JUGADOR_DB_ALTURA
                                                +","+Jugador_DB.JUGADOR_DB_PESO+","+Jugador_DB.JUGADOR_DB_POSICION
                                                +") VALUES ('"+j.getNombre()+"',"+j.getImg()+","
                                                +j.getAltura()+","+j.getPeso()+",'"+j.getPosicion()+"');";
        db.execSQL(insertJugador);
        db.close();
    }

}
