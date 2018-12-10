package com.example.estitirio.newfat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.estitirio.newfat.Adapter.KategoriAdapter;
import com.example.estitirio.newfat.Rest.ApiClient;
import com.example.estitirio.newfat.Rest.ApiInterface;
import com.example.estitirio.newfat.model.GetKategori;
import com.example.estitirio.newfat.model.GetKategori;
import com.example.estitirio.newfat.model.Kategori;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kategori extends AppCompatActivity {
    Button btGet;
    ApiInterface mApiInterface;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        btGet = (Button) findViewById(R.id.btGet);

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface  = ApiClient.getClient().create(ApiInterface.class);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GetKategori> kategoriCall = mApiInterface.getKategori();
                kategoriCall.enqueue(new Callback<GetKategori>() {
                    @Override
                    public void onResponse(Call<GetKategori> call, Response<GetKategori> response) {
                        List<Kategori> kategoriList = response.body().getListDataKategori();
                        Log.d("Retrofit Get", "Jumlah data kategori: " + String.valueOf(kategoriList.size()));

                        mAdapter = new KategoriAdapter(kategoriList);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetKategori> call, Throwable t) {
                        // Log error
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });
    }
}
