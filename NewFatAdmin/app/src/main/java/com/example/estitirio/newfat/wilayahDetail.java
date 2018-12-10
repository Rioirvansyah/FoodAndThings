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
import com.example.estitirio.newfat.model.CRUDWilayah;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class wilayahDetail extends AppCompatActivity {

    EditText edtIdWilayah, edtNamaWilayah, edtKota;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilayah_detail);

        edtIdWilayah = (EditText) findViewById(R.id.edtIdWilayah);
        edtNamaWilayah = (EditText) findViewById(R.id.edtNamaWilayah);
        edtKota = (EditText) findViewById(R.id.edtKota);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btDelete = (Button) findViewById(R.id.btDelete2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdWilayah.setText(mIntent.getStringExtra("id_wilayah"));
        edtNamaWilayah.setText(mIntent.getStringExtra("nama_wilayah"));
        edtKota.setText(mIntent.getStringExtra("kota"));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<CRUDWilayah> updateWilayahCall = mApiInterface.putWilayah(
                        edtIdWilayah.getText().toString(),
                        edtNamaWilayah.getText().toString(),
                        edtKota.getText().toString());

                updateWilayahCall.enqueue(new Callback<CRUDWilayah>() {
                    @Override
                    public void onResponse(Call<CRUDWilayah> call, Response<CRUDWilayah> response) {
                        tvMessage.setText(" Retrofit Update: " +
                                "\n " + " Status Update : " +response.body().getStatus() +
                                "\n " + " Message Update : "+ response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<CRUDWilayah> call, Throwable t) {
                        tvMessage.setText("Retrofit Update: \n Status Update :"+ t.getMessage());
                    }
                });
            }
        });

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<CRUDWilayah> postWilayahCall = mApiInterface.postWilayah(
                        edtIdWilayah.getText().toString(),
                        edtNamaWilayah.getText().toString(),
                        edtKota.getText().toString());;
                postWilayahCall.enqueue(new Callback<CRUDWilayah>() {
                    @Override
                    public void onResponse(Call<CRUDWilayah> call, Response<CRUDWilayah> response) {
                        tvMessage.setText(" Retrofit Insert: " +
                                "\n " + " Status Insert : " +
                                response.body().getStatus() +
                                "\n " + " Message Insert : "+ response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<CRUDWilayah> call, Throwable t) {
                        tvMessage.setText("Retrofit Insert: \n Status Insert :"+ t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtIdWilayah.getText().toString().trim().isEmpty()){

                    Call<CRUDWilayah> deleteWilayah = mApiInterface.deleteWilayah(edtIdWilayah.getText().toString());
                    deleteWilayah.enqueue(new Callback<CRUDWilayah>() {
                        @Override
                        public void onResponse(Call<CRUDWilayah> call, Response<CRUDWilayah> response) {
                            tvMessage.setText(" Retrofit Delete: " +
                                    "\n " + " Status Delete : " +response.body().getStatus() +
                                    "\n " + " Message Delete : "+ response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<CRUDWilayah> call, Throwable t) {
                            tvMessage.setText("Retrofit Delete: \n Status Delete :"+ t.getMessage());
                        }
                    });
                }else{
                    tvMessage.setText("id_wilayah harus diisi");
                }
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(), wilayah.class);
                startActivity(mIntent);
            }
        });
    }
}
