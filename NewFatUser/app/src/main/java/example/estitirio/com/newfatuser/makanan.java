package example.estitirio.com.newfatuser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import example.estitirio.com.newfatuser.Adapter.MakananAdapter;
import example.estitirio.com.newfatuser.Rest.ApiClient;
import example.estitirio.com.newfatuser.Rest.ApiInterface;
import example.estitirio.com.newfatuser.model.GetMakanan;
import example.estitirio.com.newfatuser.model.Makanan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class makanan extends AppCompatActivity {

    Button btGet, btnAddData;
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