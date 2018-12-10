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
import com.example.estitirio.newfat.model.CRUDKategori;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kategoriDetail extends AppCompatActivity {

    EditText edtIdKategori, edtNamaKategori;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_detail);

        edtIdKategori = (EditText) findViewById(R.id.edtIdKategori);
        edtNamaKategori = (EditText) findViewById(R.id.edtNamaKategori);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btDelete = (Button) findViewById(R.id.btDelete2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdKategori.setText(mIntent.getStringExtra("id_kategori"));
        edtNamaKategori.setText(mIntent.getStringExtra("nama_kategori"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<CRUDKategori> updatePembelianCall = mApiInterface.putKategori(
                        edtIdKategori.getText().toString(),
                        edtNamaKategori.getText().toString());

                updatePembelianCall.enqueue(new Callback<CRUDKategori>() {
                    @Override
                    public void onResponse(Call<CRUDKategori> call, Response<CRUDKategori> response) {
                        tvMessage.setText(" Retrofit Update: " +
                                "\n " + " Status Update : " +response.body().getStatus() +
                                "\n " + " Message Update : "+ response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<CRUDKategori> call, Throwable t) {
                        tvMessage.setText("Retrofit Update: \n Status Update :"+ t.getMessage());
                    }
                });
            }
        });

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<CRUDKategori> postPembelianCall = mApiInterface.postKategori(
                        edtIdKategori.getText().toString(),
                        edtNamaKategori.getText().toString());
                postPembelianCall.enqueue(new Callback<CRUDKategori>() {
                    @Override
                    public void onResponse(Call<CRUDKategori> call, Response<CRUDKategori> response) {
                        tvMessage.setText(" Retrofit Insert: " +
                                "\n " + " Status Insert : " +
                                response.body().getStatus() +
                                "\n " + " Message Insert : "+ response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<CRUDKategori> call, Throwable t) {
                        tvMessage.setText("Retrofit Insert: \n Status Insert :"+ t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtIdKategori.getText().toString().trim().isEmpty()){

                    Call<CRUDKategori> deletePembelian = mApiInterface.deleteKategori(edtIdKategori.getText().toString());
                    deletePembelian.enqueue(new Callback<CRUDKategori>() {
                        @Override
                        public void onResponse(Call<CRUDKategori> call, Response<CRUDKategori> response) {
                            tvMessage.setText(" Retrofit Delete: " +
                                    "\n " + " Status Delete : " +response.body().getStatus() +
                                    "\n " + " Message Delete : "+ response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<CRUDKategori> call, Throwable t) {
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
                Intent mIntent = new Intent(getApplicationContext(), kategori.class);
                startActivity(mIntent);
            }
        });
    }
}
