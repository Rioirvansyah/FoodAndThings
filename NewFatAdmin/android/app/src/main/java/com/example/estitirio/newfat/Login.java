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

public class Login extends AppCompatActivity {
    private CheckBox chkRemember;
    private Button btnLogin;
    private EditText txtUsername;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.simpanDataLogin();
        this.initComponents();
    }

    private void initComponents(){
        this.chkRemember = this.findViewById(R.id.chk_remember);
        this.btnLogin = this.findViewById(R.id.btn_login);
        this.txtUsername = this.findViewById(R.id.txt_username);
        this.txtPassword = this.findViewById(R.id.txt_password);

    }
    public void button_onClick(View view) {
        this.login();
    }

    private void simpanDataLogin() {
        SharedPreferences handler = this.getSharedPreferences("key", Context.MODE_PRIVATE);

        String username = handler.getString("username", "");
        String password = handler.getString("password", "");

        boolean loginCorrect = this.cekLogin(username, password);
        if (loginCorrect)
            this.openPage(username);
    }

    private void login() {
        String username = this.txtUsername.getText().toString();
        String password = this.txtPassword.getText().toString();

        boolean loginCorrect = this.cekLogin(username, password);

        if (loginCorrect)
        {
            boolean remember = this.chkRemember.isChecked();
            if (remember)
            {
                this.simpanLogin();
            }
            this.openPage(username);
        }
        else
        {
            Toast.makeText(this.getApplicationContext(), "Invalid username and/or password! ", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean cekLogin(String username, String password) {
        if(username.equals("admin") && password.equals("admin"))
            return  true;
        else
            return false;
    }

    private void simpanLogin() {
        SharedPreferences handler = this.getSharedPreferences("key", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = handler.edit();

        editor.putString("username", this.txtUsername.getText().toString());
        editor.putString("password", this.txtPassword.getText().toString());

        editor.apply();
    }


    private void openPage(String username) {
        Intent i = new Intent(this.getApplicationContext(), MainActivity.class);
        i.putExtra("username" , username);
        this.startActivity(i);
    }



}
