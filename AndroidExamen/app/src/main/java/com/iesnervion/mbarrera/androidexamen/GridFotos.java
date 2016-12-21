package com.iesnervion.mbarrera.androidexamen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

public class GridFotos extends AppCompatActivity {

    GridView gv;
    public int CAMBIAR_IMAGEN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_fotos);
        gv = (GridView) findViewById(R.id.activity_grid_fotos);
        //No Me da tiempo a poner esto bien
        int[] foto = {R.drawable.jugador00,R.drawable.jugador01,R.drawable.jugador02,R.drawable.jugador03,R.drawable.jugador04};
        //gv.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,foto));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Intent i = new Intent(getApplicationContext(),FormularioActivity.class);
                startActivity(i);
            }
        });
    }




}
