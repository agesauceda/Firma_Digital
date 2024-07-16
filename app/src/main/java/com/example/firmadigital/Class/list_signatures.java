package com.example.firmadigital.Class;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firmadigital.Adapter.cardview_ca;
import com.example.firmadigital.R;
import com.example.firmadigital.Repository.database;

import java.util.ArrayList;
import java.util.List;

public class list_signatures extends AppCompatActivity {
    private RecyclerView rv;
    cardview_ca custom_adap;
    private database db;
    private List<signatures> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_signatures);

        rv = findViewById(R.id.lista_firmas);
        rv.setLayoutManager(new LinearLayoutManager(this));
        GET_DATOS();
        List<signatures> list_firmas = GET_ALL_FIRMAS();

        custom_adap = new cardview_ca(getApplicationContext(), list_firmas);
        rv.setAdapter(custom_adap);
    }
    public List<signatures> GET_ALL_FIRMAS() {
        List<signatures> firmas = new ArrayList<>();
        db = new database(this, database.DATABASE_NAME, null, 1);
        SQLiteDatabase sql = db.getReadableDatabase();
        Cursor cursor = sql.rawQuery(database.SELECT_TABLE_FIRMAS, null);

        if (cursor.moveToFirst()) {
            do {
                int columnF = cursor.getColumnIndex(database.FirmaDigital);
                int columnD = cursor.getColumnIndex(database.Descripcion);

                signatures signature = new signatures();
                signature.setFirmaDigital(cursor.getBlob(columnF));
                signature.setDescripcion(cursor.getString(columnD));
                firmas.add(signature);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sql.close();
        return firmas;
    }

    private void GET_DATOS() {
        db = new database(this, database.DATABASE_NAME, null, 1);
        SQLiteDatabase sql = db.getReadableDatabase();
        signatures firmas = null;
        Cursor cursor = sql.rawQuery(database.SELECT_TABLE_FIRMAS, null);

        while (cursor.moveToNext()) {
            firmas = new signatures();
            firmas.setDescripcion(cursor.getString(1));
            lista.add(firmas);
        }
        cursor.close();
    }
}
