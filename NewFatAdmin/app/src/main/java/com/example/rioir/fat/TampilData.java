package com.example.rioir.fat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TampilData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn2;
    TextView text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);

        dbHelper = new DataHelper(this);
        text1 = findViewById(R.id.id);
        text2 = findViewById(R.id.menu);
        text3 = findViewById(R.id.asal);
        text4 = findViewById(R.id.harga);
        text5 = findViewById(R.id.deskripsi);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM makanan WHERE menu = '" + getIntent().getStringExtra("menu") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0));
            text2.setText(cursor.getString(1));
            text3.setText(cursor.getString(2));
            text4.setText(cursor.getString(3));
            text5.setText(cursor.getString(4));
        }

        btn2 = findViewById(R.id.btn_kembali);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}