package com.example.rioir.fat;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rioir on 10/21/2018.
 */

public class DataHelper extends SQLiteOpenHelper {

    private static final String Database = "cobaFAT2.db"; //dbnya
    private static final String Tabel = "makanan"; //tablenya
    private static final int Versi = 1; //versinya
    //kolomnya
    private static final String ID = "_id";
    private static final String MENU = "menu";
    private static final String ASAL = "asal";
    private static final String HARGA = "harga";
    private static final String DESKRIPSI = "deskripsi";

    private static final String CREATE_TABLE =
            "CREATE TABLE "
                    + Tabel
                    + " ("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + MENU + " VARCHAR(255), "
                    + ASAL + " VARCHAR(255), "
                    + HARGA + " VARCHAR(255), "
                    + DESKRIPSI + " VARCHAR(255) "
                    + ");";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ Tabel;

    public DataHelper(Context context) {
        super(context, Database, null, Versi);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
//
//    public Cursor getInformation(SQLiteDatabase db){
//        String[] projection = {
//                MENU, ASAL, HARGA, DESKRIPSI
//        };
//        Cursor cursor = db.query(Tabel, projection, null, null, null, null, null);
//        return cursor;
//    }
}
