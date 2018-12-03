package com.example.estitirio.newfat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.estitirio.newfat.Adapter.KategoriAdapter;
import com.example.estitirio.newfat.Adapter.WilayahAdapter;
import com.example.estitirio.newfat.Rest.ApiClient;
import com.example.estitirio.newfat.Rest.ApiInterface;
import com.example.estitirio.newfat.model.GetWilayah;
import com.example.estitirio.newfat.model.Kategori;
import com.example.estitirio.newfat.model.Wilayah;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class wilayah extends AppCompatActivity {

    Button btGet;
    ApiInterface mApiInterface;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilayah);

        btGet = (Button) findViewById(R.id.btGet);

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface  = ApiClient.getClient().create(ApiInterface.class);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GetWilayah> wilayahCall = mApiInterface.getWilayah();
                wilayahCall.enqueue(new Callback<GetWilayah>() {
                    @Override
                    public void onResponse(Call<GetWilayah> call, Response<GetWilayah> response) {
                        List<Wilayah> wilayahList = response.body().getListDataWilayah();
                        Log.d("Retrofit Get", "Jumlah data wilayah: " + String.valueOf(wilayahList.size()));

                        mAdapter = new WilayahAdapter(wilayahList);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetWilayah> call, Throwable t) {
                        // Log error
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });
    }
}
