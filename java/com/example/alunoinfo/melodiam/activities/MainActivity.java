package com.example.alunoinfo.melodiam.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.melodiam.R;

public class MainActivity extends AppCompatActivity {

    private Button btLogin;
    private TextView tvCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.inicializaComponentes();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(MainActivity.this, NavMenuActivity.class);
                startActivity(intentLogin);
            }
        });

        tvCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCadastro = new Intent(MainActivity.this, TelaCadastroActivity.class);
                startActivity(intentCadastro);
            }
        });



    }

    public void inicializaComponentes() {
        this.btLogin = (Button) findViewById(R.id.bt_login);
        this.tvCadastro = (TextView) findViewById(R.id.tv_cadastro);
    }
}
