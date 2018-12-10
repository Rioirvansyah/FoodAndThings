package com.example.rioir.fat;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class Data extends AppCompatActivity {

    ListView ListView01;
    protected Cursor cursor;
    DataHelper datamakanan;
    ImageButton floatButton;
    public static Data layar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        floatButton = findViewById(R.id.btn_tambah);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TambahData.class);
                startActivity(intent);
            }
        });

        layar = this;
        TampilkanList();
    }

    public void TampilkanList(){
        datamakanan = new DataHelper(this);
        SQLiteDatabase sqLiteDatabase = datamakanan.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM makanan", null);
        final String[] databaru = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            databaru[cc] = cursor.getString(1);
        }
        ListView01 = findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, databaru));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                final String selection = databaru[i];
                final CharSequence[] dialogitem = {"Lihat Makanan","Update Makanan", "Hapus Makanan"};

                AlertDialog.Builder builder = new AlertDialog.Builder(Data.this);
                builder.setTitle("Pilih");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0 :
                                Intent intent0 = new Intent(getApplicationContext(), TampilData.class);
                                intent0.putExtra("menu",selection);
                                startActivity(intent0);
                                break;

                            case 1 :
                                Intent intent1 = new Intent(getApplicationContext(), EditData.class);
                                intent1.putExtra("menu",selection);
                                startActivity(intent1);
                                break;

                            case 2 :
                                SQLiteDatabase db = datamakanan.getWritableDatabase();
                                db.execSQL("DELETE FROM makanan where menu = '"+selection+"'");
                                TampilkanList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
        cursor.close();
    }
}