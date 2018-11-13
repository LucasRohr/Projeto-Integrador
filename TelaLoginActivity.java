package com.example.user.melodiam.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.melodiam.R;
import com.example.user.melodiam.model.Usuario;
import com.example.user.melodiam.services.UsuarioService;

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
    private Usuario usuarioTelaLogin;
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

        btLogar.setOnClickListener(new View.OnClickListener() {
            TelaCadastroActivity telaCadastro = new TelaCadastroActivity();
            @Override
            public void onClick(View v) {
                usuario = telaCadastro.getUsuario();
                usuarioTelaLogin = new Usuario();
                usuarioTelaLogin.setLogin(etLoginLogin.getText().toString());
                usuarioTelaLogin.setSenha(etSenhaLogin.getText().toString());
                Call<Usuario> userService = usuarioService.buscarPorLoginESenha(usuario.getLogin(), usuario.getSenha());

                if(userService.toString().equals(usuarioTelaLogin.toString())) {
                    Intent itPerfil = new Intent(TelaLoginActivity.this, PerfilFragment.class);
                    itPerfil.putExtra("usuario", (Serializable) usuario);
                    startActivity(itPerfil);

                }else{
                    Toast.makeText(TelaLoginActivity.this, "Login ou senha incorretos!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void inicializaComponentes() {
        this.etLoginLogin = (EditText) findViewById(R.id.et_login_login);
        this.etSenhaLogin = (EditText) findViewById(R.id.et_senha_login);
        this.btLogar = (Button) findViewById(R.id.bt_logar);
    }
}
