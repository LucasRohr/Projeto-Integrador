package com.example.alunoinfo.melodiam.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.melodiam.R;
import com.example.alunoinfo.melodiam.model.Usuario;
import com.example.alunoinfo.melodiam.services.UsuarioService;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TelaLoginActivity extends AppCompatActivity {

    private EditText etLoginLogin;
    private EditText etSenhaLogin;
    private Button btLogar;
    private Usuario usuario;
    private UsuarioService usuarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        this.inicializaComponentes();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.usuarioService = retrofit.create(UsuarioService.class);

        this.btLogar.setOnClickListener(new View.OnClickListener() {
            TelaCadastroActivity telaCadastro = new TelaCadastroActivity();
            @Override
            public void onClick(View v) {

                Log.i("USUARIO", etLoginLogin.getText().toString()+" "+etSenhaLogin.getText().toString());
                usuarioService.buscarPorLoginESenha(etLoginLogin.getText().toString(), etSenhaLogin.getText().toString()).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                        if(response.isSuccessful()) {
                            usuario = response.body();
                            if(usuario == null){
                                Toast.makeText(TelaLoginActivity.this, "Login ou senha incorretos!", Toast.LENGTH_LONG).show();
                            } else {
                                Intent itPerfil = new Intent(TelaLoginActivity.this, NavMenuActivity.class);
                                itPerfil.putExtra("usuario", usuario);
                                startActivity(itPerfil);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(TelaLoginActivity.this, "Erro de autentificação", Toast.LENGTH_LONG).show();
                    }
                });



            }
        });




    }

    public void inicializaComponentes() {
        this.etLoginLogin = (EditText) findViewById(R.id.et_login_login);
        this.etSenhaLogin = (EditText) findViewById(R.id.et_senha_login);
        this.btLogar = (Button) findViewById(R.id.bt_logar);
    }
}
