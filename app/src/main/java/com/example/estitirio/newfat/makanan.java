package com.example.estitirio.newfat;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.estitirio.newfat.Adapter.MakananAdapter;
import com.example.estitirio.newfat.Rest.ApiClient;
import com.example.estitirio.newfat.Rest.ApiInterface;
import com.example.estitirio.newfat.model.GetMakanan;
import com.example.estitirio.newfat.model.Makanan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class makanan extends AppCompatActivity {

    Button btGet;
//            , btUpdate, btInsert, btDelete;
    ApiInterface mApiInterface;
    Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan);

        btGet = (Button) findViewById(R.id.btGet);
//        btUpdate = (Button) findViewById(R.id.btUpdate);
//        btInsert = (Button) findViewById(R.id.btInsert);
//        btDelete = (Button) findViewById(R.id.btDelete);

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface  = ApiClient.getClient().create(ApiInterface.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), makanan.class);
                startActivity(i);
            }
        });

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetMakanan> mMakananCall = mApiInterface.getMakanan();
                mMakananCall.enqueue(new Callback<GetMakanan>() {
                    @Override
                    public void onResponse(Call<GetMakanan> call, Response<GetMakanan> response) {
                        Log.d("Get Pembeli",response.body().getStatus());
                        List<Makanan> listMakanan = response.body().getResult();
                        mAdapter = new MakananAdapter(listMakanan);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetMakanan> call, Throwable t) {
                        Log.d("Get Pembeli",t.getMessage());
                    }
                });
            }
        });


//        btUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Call<CRUDMakanan> updateMakananCall = mApiInterface.putMakanan("1","Batagor","20000","enak","2","1");
//                updateMakananCall.enqueue(new Callback<CRUDMakanan>() {
//                    @Override
//                    public void onResponse(Call<CRUDMakanan> call, Response<CRUDMakanan> response) {
//                        Log.d("Retrofit Update", "Status Update: " + String.valueOf(response.body().getStatus()));
//                    }
//
//                    @Override
//                    public void onFailure(Call<CRUDMakanan> call, Throwable t) {
//                        Log.d("Retrofit Update", t.getMessage());
//                    }
//                });
//            }
//        });
//
//        btInsert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Call<CRUDMakanan> postMakananCall = mApiInterface.postMakanan("16", "Chicken","10000","Mantap","03","02");
//                postMakananCall.enqueue(new Callback<CRUDMakanan>() {
//                    @Override
//                    public void onResponse(Call<CRUDMakanan> call, Response<CRUDMakanan> response) {
//                        Log.d("Retrofit Insert", "Status Insert: " + String.valueOf(response.body().getStatus()));
//                    }
//
//                    @Override
//                    public void onFailure(Call<CRUDMakanan> call, Throwable t) {
//                        Log.d("Retrofit Insert", t.getMessage());
//                    }
//                });
//            }
//        });
//
//        btDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Call<CRUDMakanan> deleteMakanan = mApiInterface.deleteMakanan("16");
//                deleteMakanan.enqueue(new Callback<CRUDMakanan>() {
//                    @Override
//                    public void onResponse(Call<CRUDMakanan> call, Response<CRUDMakanan> response) {
//                        Log.i("Retrofit Delete", "Status Delete: " + String.valueOf(response.body().getStatus()));
//                    }
//
//                    @Override
//                    public void onFailure(Call<CRUDMakanan> call, Throwable t) {
//                        Log.i("Retrofit Delete", t.getMessage());
//                    }
//                });
//            }
//        });
    }
}
