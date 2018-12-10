package com.example.estitirio.newfat;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.estitirio.newfat.Rest.ApiClient;
import com.example.estitirio.newfat.Rest.ApiInterface;
import com.example.estitirio.newfat.model.GetMakanan;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editMakanan extends AppCompatActivity {

    ImageView mPhotoUrl;
    EditText edtIdMakanan, edtAddMenuMakanan, edtAddHarga, edtAddDeskripsi;
    TextView tvMessage;
    Context mContext;
    Button btUpdate, btDelete, btBack, btPhotoUrl;
    String pathImage="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_makanan);
        mContext = getApplicationContext();

        mPhotoUrl = (ImageView) findViewById(R.id.imgPhotoUrl);
        edtIdMakanan = (EditText) findViewById(R.id.edtIdMakanan);
        edtAddMenuMakanan = (EditText) findViewById(R.id.edtMenuMakanan);
        edtAddHarga = (EditText) findViewById(R.id.edtHargaMakanan);
        edtAddDeskripsi = (EditText) findViewById(R.id.edtDeskripsiMakanan);

        tvMessage = (TextView) findViewById(R.id.tvMessage);

        btUpdate = (Button) findViewById(R.id.btUpdate);
        btDelete = (Button) findViewById(R.id.btDelete);
        btBack = (Button) findViewById(R.id.btBack);
        btPhotoUrl = (Button) findViewById(R.id.btPhotoUrl);

        Intent mIntent = getIntent();

        edtIdMakanan.setText(mIntent.getStringExtra("id_makanan"));
        edtAddMenuMakanan.setText(mIntent.getStringExtra("menu_makanan"));
        edtAddHarga.setText(mIntent.getStringExtra("harga_makanan"));
        edtAddDeskripsi.setText(mIntent.getStringExtra("deskripsi_makanan"));

        if (mIntent.getStringExtra("photo_url") != null)
            Glide.with(mContext).load(ApiClient.BASE_URL + mIntent.getStringExtra("photo_url")).into(mPhotoUrl);
        else
            Glide.with(mContext).load(R.drawable.user).into(mPhotoUrl);

        pathImage = mIntent.getStringExtra("photo_url");
        setListener();
    }

    private void setListener() {
        final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MultipartBody.Part body = null;
                //dicek apakah image sama dengan yang ada di server atau berubah
                //jika sama dikirim lagi jika berbeda akan dikirim ke server
                if ((!pathImage.contains("uploads/" + edtIdMakanan.getText().toString())) &&
                        (pathImage.length()>0)){

                    //File creating from selected URL
                    File file = new File(pathImage);

                    // create RequestBody instance from file
                    RequestBody requestFile = RequestBody.create(
                            MediaType.parse("multipart/form-data"), file);

                    // MultipartBody.Part is used to send also the actual file name
                    body = MultipartBody.Part.createFormData("photo_url", file.getName(),
                            requestFile);
                }

                RequestBody reqIdPembeli = MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtIdMakanan.getText().toString().isEmpty())?
                                        "" : edtIdMakanan.getText().toString());

                RequestBody reqNama = MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtAddMenuMakanan.getText().toString().isEmpty())?
                                        "" : edtAddMenuMakanan.getText().toString());

                RequestBody reqAlamat = MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtAddHarga.getText().toString().isEmpty())?
                                        "" : edtAddHarga.getText().toString());

                RequestBody reqTelp = MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtAddDeskripsi.getText().toString().isEmpty())?
                                        "" : edtAddDeskripsi.getText().toString());

                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"), "update");

                Call<GetMakanan> callUpdate = mApiInterface.putMakanan(body, reqIdPembeli, reqNama,
                        reqAlamat, reqTelp, reqAction);

                callUpdate.enqueue(new Callback<GetMakanan>() {
                    @Override
                    public void onResponse(Call<GetMakanan> call, Response<GetMakanan> response) {
                        //Log.d("Update Retrofit ", response.body().getStatus());
                        if (response.body().getStatus().equals("failed")){
                            tvMessage.setText("Retrofit Update \n Status = " + response.body()
                                    .getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+"\n");
                        }else{
                            String detail = "\n"+
                                    "id_makanan = "+response.body().getResult().get(0).getId_makanan()+"\n"+
                                    "menu_makanan = "+response.body().getResult().get(0).getMenu_makanan()+"\n"+
                                    "harga_makanan = "+response.body().getResult().get(0).getHarga_makanan()+"\n"+
                                    "deskripsi_makanan = "+response.body().getResult().get(0).getDeskripsi_makanan()+"\n"+
                                    "photo_url = "+response.body().getResult().get(0).getPhotoUrl()
                                    +"\n";
                            tvMessage.setText("Retrofit Update \n Status = "+response.body().getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetMakanan> call, Throwable t) {
                        //Log.d("Update Retrofit ", t.getMessage());
                        tvMessage.setText("Retrofit Update \n Status = "+ t.getMessage());
                    }
                });

            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBody reqIdPembeli =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtIdMakanan.getText().toString().isEmpty())?
                                        "" : edtIdMakanan.getText().toString());
                RequestBody reqAction =
                        MultipartBody.create(MediaType.parse("multipart/form-data"), "delete");

                Call<GetMakanan> callDelete = mApiInterface.deleteMakanan(reqIdPembeli,reqAction);
                callDelete.enqueue(new Callback<GetMakanan>() {
                    @Override
                    public void onResponse(Call<GetMakanan> call, Response<GetMakanan> response) {
                        tvMessage.setText("Retrofit Delete \n Status = "+response.body()
                                .getStatus() +"\n"+
                                "Message = "+response.body().getMessage()+"\n");
                    }

                    @Override
                    public void onFailure(Call<GetMakanan> call, Throwable t) {
                        tvMessage.setText("Retrofit Delete \n Status = "+ t.getMessage());
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tempIntent = new Intent(mContext, makanan.class);
                startActivity(tempIntent);
            }
        });

        btPhotoUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(galleryIntent, "Pilih foto untuk " +
                        "di-upload");
                startActivityForResult(intentChoose, 10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode ==10) {
            if (data == null) {
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
                return;
            }
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                pathImage = cursor.getString(columnIndex);

                //Picasso.with(mContext).load(new File(imagePath)).fit().into(mImageView);
                Glide.with(mContext).load(new File(pathImage)).into(mPhotoUrl);
                cursor.close();
            } else {
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
            }
        }
    }
}
