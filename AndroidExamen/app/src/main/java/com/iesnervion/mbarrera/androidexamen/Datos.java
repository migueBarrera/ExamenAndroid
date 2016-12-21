package com.iesnervion.mbarrera.androidexamen;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by mbarrera on 7/12/16.
 */

public class Datos extends Application {

    private Jugador player;
    ArrayList<Jugador> jugadores;

    public Datos(){
        jugadores = new ArrayList<Jugador>();
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Jugador getPlayer() {

        return player;
    }

    //AL HACER EL SET LO METEMOS EN LA LISTA
    public void setPlayer(Jugador player) {
        jugadores.add(player);
        this.player = player;
    }

    public void guardarJugador(){
        jugadores.add(player);
    }

    public void borrarJugador(int position)
    {
        jugadores.remove(position);
    }
}

