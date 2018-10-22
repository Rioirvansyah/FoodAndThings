package com.example.rioir.fat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahData extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnSimpan, btnKembali;
    EditText text1, text2, text3, text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        dbHelper = new DataHelper(this);
        text1 = findViewById(R.id.editMenu);
        text2 = findViewById(R.id.editAsal);
        text3 = findViewById(R.id.editHarga);
        text4 = findViewById(R.id.editDeskripsi);

        btnSimpan = findViewById(R.id.simpanBtn);
        btnKembali = findViewById(R.id.kembaliBtn);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO makanan(menu,asal,harga,deskripsi) values ('" +text1.getText().toString()+ "', '" +
                        text2.getText().toString() + "', '" +
                        text3.getText().toString() + "', '" +
                        text4.getText().toString() + "' )");
                Toast.makeText(getApplicationContext(), "BERHASIL DITAMBAHKAN",
                        Toast.LENGTH_LONG).show();

                FragmentHome.layarutama.tampilkanList();
                finish();
            }

        });
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }


}
