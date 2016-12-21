package com.iesnervion.mbarrera.androidexamen;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mbarrera on 7/12/16.
 */

public class Jugador implements Parcelable{
    private int img;
    private String nombre;
    private String posicion;
    private double altura;
    private double peso;

    public Jugador() {
        this.nombre = "";
        this.altura = 0;
        this.img = R.drawable.jugador00;
        this.nombre = "";
        this.peso = 0;
        this.posicion = "Bulto";
    }

    public Jugador(String nombre, int img , double altura, double peso, String posicion) {
        this.altura = altura;
        this.img = img;
        this.nombre = nombre;
        this.peso = peso;
        this.posicion = posicion;
    }

    protected Jugador(Parcel in) {
        img = in.readInt();
        nombre = in.readString();
        posicion = in.readString();
        altura = in.readDouble();
        peso = in.readDouble();
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }


    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(img);
        dest.writeString(nombre);
        dest.writeString(posicion);
        dest.writeDouble(altura);
        dest.writeDouble(peso);
    }
}
