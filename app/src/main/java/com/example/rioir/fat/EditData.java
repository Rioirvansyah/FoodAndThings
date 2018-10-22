package com.example.rioir.fat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn1, btn2;
    TextView text1, text2, text3, text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        dbHelper = new DataHelper(this);
        text1 = findViewById(R.id.editMenuUpt);
        text2 = findViewById(R.id.editAsalUpt);
        text3 = findViewById(R.id.editHargaUpt);
        text4 = findViewById(R.id.editDeskripsiUpt);

        btn1 = findViewById(R.id.editBtn);
        btn2 = findViewById(R.id.kembaliBtn);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM makanan WHERE menu = '" +getIntent().getStringExtra("menu")+ "'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(1));
            text2.setText(cursor.getString(2));
            text3.setText(cursor.getString(3));
            text4.setText(cursor.getString(4));
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
                sqLiteDatabase.execSQL("UPDATE makanan set menu = '"+text1.getText().toString()+"', asal = '"+text2.getText().
                        toString()+"', harga = '"+text3.getText().toString()+"', deskripsi = '"+text4.getText()
                        .toString()+"' WHERE menu = '"+getIntent().getStringExtra("menu")+"'");
                Toast.makeText(getApplicationContext(), "Berhasil Diedit", Toast.LENGTH_LONG).show();

                FragmentHome.layarutama.tampilkanList();
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
