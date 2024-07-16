package com.example.firmadigital.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BDFirmas";
    public static final String TABLE_NAME = "Firmas";
    public static final String Id = "Id";
    public static final String FirmaDigital = "FirmaDigital";
    public static final String Descripcion = "Descripcion";

    public static final String CREATE_TABLE_FIRMAS = "CREATE TABLE Firmas "+"(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "FirmaDigital BLOB, " +
            "Descripcion TEXT)";

    public static final String SELECT_TABLE_FIRMAS = "SELECT FirmaDigital, Descripcion FROM " + TABLE_NAME;


    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(database.CREATE_TABLE_FIRMAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
