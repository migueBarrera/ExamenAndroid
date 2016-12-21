package com.iesnervion.mbarrera.androidexamen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by mbarrera on 25/10/16.
 */

public class MyArrayAdapterTotal<T> extends ArrayAdapter<T> {

    public MyArrayAdapterTotal(Context context, int resource, int textViewResourceId, T[] objects) {
        super(context, resource, textViewResourceId, objects);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row =convertView;
        ViewHolder holder = null;
        Jugador player=(Jugador) getItem(position);


        if(row==null){//Si la fila no esta creada la creamos

            row = super.getView(position,convertView,parent);
             holder = new ViewHolder(row,R.id.fotoPerfil,R.id.tvNombre,R.id.tvAltura,R.id.tvPeso,R.id.tvPosicion);
            row.setTag(holder);

        }else{
            //Si ya estaba creada cojemos su holder para ahorrar llamadas
            holder = (ViewHolder) row.getTag();
        }

        holder.getTvNombre().setText(player.getNombre());
        holder.getTvAltura().setText(String.valueOf(player.getAltura()));
        holder.getTvPeso().setText(String.valueOf(player.getPeso()));
        holder.getTvPosicion().setText(player.getPosicion());
        holder.getIvFoto().setImageResource(player.getImg());

        return row;
    }
}
