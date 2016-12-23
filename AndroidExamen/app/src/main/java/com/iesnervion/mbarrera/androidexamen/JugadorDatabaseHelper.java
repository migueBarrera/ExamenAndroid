package com.iesnervion.mbarrera.androidexamen;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iesnervion.mbarrera.androidexamen.Contrato.Jugador_DB;

/**
 * Created by mbarrera on 21/12/16.
 */

public class JugadorDatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "dabaseJugadores.db";
    private static final int VERSION = 2;

    //Cadenas
        //CrearTablas
        private static final String tablaJugador = "CREATE TABLE "+Jugador_DB.JUGADOR_DB_TABLE_NAME+
                                                    "("+Jugador_DB._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                        +Jugador_DB.JUGADOR_DB_NOMBRE
                                                        +","+Jugador_DB.JUGADOR_DB_IMAGEN
                                                        +","+Jugador_DB.JUGADOR_DB_ALTURA
                                                        +","+Jugador_DB.JUGADOR_DB_PESO
                                                        +","+Jugador_DB.JUGADOR_DB_POSICION+");";

    public JugadorDatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(tablaJugador);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +Jugador_DB.JUGADOR_DB_TABLE_NAME);
        onCreate(db);
    }
}
