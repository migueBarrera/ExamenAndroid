package com.iesnervion.mbarrera.androidexamen;

import android.provider.BaseColumns;

/**
 * Created by mbarrera on 21/12/16.
 */

public class Contrato {

    private Contrato(){};

    public static final class Jugador_DB implements BaseColumns {
        private Jugador_DB() {}
        public static final String JUGADOR_DB_TABLE_NAME="table_jugador";
        public static final String JUGADOR_DB_NOMBRE="nombre";
        public static final String JUGADOR_DB_POSICION="posicion";
        public static final String JUGADOR_DB_ALTURA ="altura";
        public static final String JUGADOR_DB_PESO ="peso";
        public static final String JUGADOR_DB_IMAGEN ="imagen";
        public static final String DEFAULT_SORT_ORDER="pet_name ASC";
    }
}
