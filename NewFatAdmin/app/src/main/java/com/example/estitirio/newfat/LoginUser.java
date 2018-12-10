package com.example.estitirio.newfat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.estitirio.newfat.Rest.ApiClient;
import com.example.estitirio.newfat.Rest.ApiInterface;
import com.example.estitirio.newfat.model.GetLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUser extends AppCompatActivity {
    private Button btnLogin;
    private EditText txtUsername;
    private EditText txtPassword;
    ApiInterface mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);


        txtUsername = (EditText) findViewById(R.id.txt_username);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        // Cek apakah ada status isloggedin = true di shared pref
        // Jika true, maka lanjut ke layar home
        if(this.isLoggedIn()) {
            Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
            this.startActivity(intent);
        }

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                // Cek form login
                if (!username.isEmpty() && !password.isEmpty()) {
                    // Lakukan login
                    doLogin(username, password);
                } else {
                    // Notif user
                    Toast.makeText(getApplicationContext(),
                            "Isikan username dan password!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

    }

    private boolean isLoggedIn() {
        // Cek apakah ada shared pref login
        SharedPreferences pref = getSharedPreferences("key", Context.MODE_PRIVATE);
        return pref.getBoolean("isloggedin", false);
    }
    private void openHome() {

        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        this.startActivity(intent);
    }
    private void saveLogin(String username, String id_makanan) {
        // Simpan data login ke shared pref
        SharedPreferences sharedpref = getSharedPreferences("key", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();

        // Simpan isloggedin true berupa boolean
        editor.putBoolean("isloggedin", true);
        // Simpan data lainnya berupa string
        editor.putString("username", username);
        editor.putString("id_makanan", id_makanan);
        editor.apply();
    }

    private void doLogin(final String username, final String password) {
        // Panggil request Api
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call loginCall = mApiInterface.loginUser(username, password);

        loginCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                // Jika request sukses
                if (response.isSuccessful()) {

                    // Buat object model GetLogin dari response
                    GetLogin loginobject = (GetLogin) response.body();
                    String id_makanan = loginobject.getResult();

                    // Jika status = success (sesuai respon dari REST server)
                    if (loginobject.getStatus().equals("success")) {

                        // Simpan data email user ke sharedpref
                        saveLogin(username, id_makanan);
                        // Buka layar home
                        openHome();

                    } else {
                        Toast.makeText(LoginUser.this,
                                "Email atau password salah",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginUser.this,
                            "Error! Coba lagi!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                // Jika gagal, beri notif
                Toast.makeText(LoginUser.this, "Gagal:" + t.getMessage(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}