package com.example.estitirio.newfat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.estitirio.newfat.Rest.ApiClient;
import com.example.estitirio.newfat.Rest.ApiInterface;
import com.example.estitirio.newfat.model.CRUDMakanan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class makananDetail extends AppCompatActivity {

    EditText edtIdMakanan, edtMenuMakanan, edtHargaMakanan, edtDeskripsiMakanan, edtIdKategori, edtIdWilayah;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan_detail);

        edtIdMakanan = (EditText) findViewById(R.id.edtIdMakanan);
        edtMenuMakanan = (EditText) findViewById(R.id.edtMenuMakanan);
        edtHargaMakanan = (EditText) findViewById(R.id.edtHargaMakanan);
        edtDeskripsiMakanan = (EditText) findViewById(R.id.edtDeskripsiMakanan);
        edtIdKategori = (EditText) findViewById(R.id.edtIdKategori);
        edtIdWilayah = (EditText) findViewById(R.id.edtIdWilayah);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btDelete = (Button) findViewById(R.id.btDelete2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdMakanan.setText(mIntent.getStringExtra("id_makanan"));
        edtMenuMakanan.setText(mIntent.getStringExtra("menu_makanan"));
        edtHargaMakanan.setText(mIntent.getStringExtra("harga_makanan"));
        edtDeskripsiMakanan.setText(mIntent.getStringExtra("deskripsi_makanan"));
        edtIdKategori.setText(mIntent.getStringExtra("id_kategori"));
        edtIdWilayah.setText(mIntent.getStringExtra("id_wilayah"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<CRUDMakanan> updatePembelianCall = mApiInterface.putMakanan(
                        edtIdMakanan.getText().toString(),
                        edtMenuMakanan.getText().toString(),
                        edtHargaMakanan.getText().toString(),
                        edtDeskripsiMakanan.getText().toString(),
                        edtIdKategori.getText().toString(),
                        edtIdWilayah.getText().toString());

                updatePembelianCall.enqueue(new Callback<CRUDMakanan>() {
                    @Override
                    public void onResponse(Call<CRUDMakanan> call, Response<CRUDMakanan> response) {
                        tvMessage.setText(" Retrofit Update: " +
                                "\n " + " Status Update : " +response.body().getStatus() +
                                "\n " + " Message Update : "+ response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<CRUDMakanan> call, Throwable t) {
                        tvMessage.setText("Retrofit Update: \n Status Update :"+ t.getMessage());
                    }
                });
            }
        });

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<CRUDMakanan> postPembelianCall = mApiInterface.postMakanan(
                        edtIdMakanan.getText().toString(),
                        edtMenuMakanan.getText().toString(),
                        edtHargaMakanan.getText().toString(),
                        edtDeskripsiMakanan.getText().toString(),
                        edtIdKategori.getText().toString(),
                        edtIdWilayah.getText().toString());
                postPembelianCall.enqueue(new Callback<CRUDMakanan>() {
                    @Override
                    public void onResponse(Call<CRUDMakanan> call, Response<CRUDMakanan> response) {
                        tvMessage.setText(" Retrofit Insert: " +
                                "\n " + " Status Insert : " +
                                response.body().getStatus() +
                                "\n " + " Message Insert : "+ response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<CRUDMakanan> call, Throwable t) {
                        tvMessage.setText("Retrofit Insert: \n Status Insert :"+ t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtIdMakanan.getText().toString().trim().isEmpty()){

                    Call<CRUDMakanan> deletePembelian = mApiInterface.deleteMakanan(edtIdMakanan.getText().toString());
                    deletePembelian.enqueue(new Callback<CRUDMakanan>() {
                        @Override
                        public void onResponse(Call<CRUDMakanan> call, Response<CRUDMakanan> response) {
                            tvMessage.setText(" Retrofit Delete: " +
                                    "\n " + " Status Delete : " +response.body().getStatus() +
                                    "\n " + " Message Delete : "+ response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<CRUDMakanan> call, Throwable t) {
                            tvMessage.setText("Retrofit Delete: \n Status Delete :"+ t.getMessage());
                        }
                    });
                }else{
                    tvMessage.setText("id_pembelian harus diisi");
                }
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mIntent);
            }
        });
    }
}
