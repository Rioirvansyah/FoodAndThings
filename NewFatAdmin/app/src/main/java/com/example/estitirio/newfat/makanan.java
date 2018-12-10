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

    Button btGet, btnAddData;
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

        btnAddData = (Button) findViewById(R.id.btAddData);
        btGet = (Button) findViewById(R.id.btGet);
        mContext = getApplicationContext();
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetMakanan> mMakananCall = mApiInterface.getMakanan();
                mMakananCall.enqueue(new Callback<GetMakanan>() {
                    @Override
                    public void onResponse(Call<GetMakanan> call, Response<GetMakanan> response) {
                        Log.d("Get Makanan",response.body().getStatus());
                        List<Makanan> listMakanan = response.body().getResult();
                        mAdapter = new MakananAdapter(listMakanan);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetMakanan> call, Throwable t) {
                        Log.d("Get Makanan",t.getMessage());
                    }
                });
            }
        });

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, insertMakanan.class);
                startActivity(intent);
            }
        });
    }
}
