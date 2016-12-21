package com.iesnervion.mbarrera.androidexamen;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mbarrera on 25/10/16.
 */

public class ViewHolder {

    TextView tvNombre, tvAltura,tvPeso,tvPosicion;
    ImageView ivFoto;

    public ViewHolder(View row,int ivFoto,int tvNombre, int tvAltura,  int tvPeso, int tvPosicion) {
        this.ivFoto = (ImageView) row.findViewById(ivFoto);
        this.tvAltura = (TextView) row.findViewById(tvAltura);
        this.tvNombre = (TextView) row.findViewById(tvNombre);
        this.tvPeso = (TextView) row.findViewById(tvPeso);
        this.tvPosicion = (TextView) row.findViewById(tvPosicion);
    }

    public ImageView getIvFoto() {
        return ivFoto;
    }

    public TextView getTvAltura() {
        return tvAltura;
    }

    public TextView getTvNombre() {
        return tvNombre;
    }

    public TextView getTvPeso() {
        return tvPeso;
    }

    public TextView getTvPosicion() {
        return tvPosicion;
    }
}
