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

public class TelaCadastroActivity extends AppCompatActivity {

    private EditText etLoginCadastro;
    private EditText etSenhaCadastro;
    private EditText etConfirmaSenhaCadastro;
    private Button btCadastrar;
    private Usuario usuario;
    private UsuarioService usuarioService;

    public EditText getEtLoginCadastro() {
        return etLoginCadastro;
    }

    public void setEtLoginCadastro(EditText etLoginCadastro) {
        this.etLoginCadastro = etLoginCadastro;
    }

    public EditText getEtSenhaCadastro() {
        return etSenhaCadastro;
    }

    public void setEtSenhaCadastro(EditText etSenhaCadastro) {
        this.etSenhaCadastro = etSenhaCadastro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        this.inicializaComponentes();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.usuarioService = retrofit.create(UsuarioService.class);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etConfirmaSenhaCadastro.getText().toString().equals(etSenhaCadastro.getText().toString())) {
                    usuario = new Usuario();
                    usuario.setLogin(etLoginCadastro.getText().toString());
                    usuario.setSenha(etSenhaCadastro.getText().toString());
                    usuarioService.cadastrarUsuario(usuario).enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if(response.isSuccessful()){
                                usuario = response.body();
                                Intent itPerfil = new Intent(TelaCadastroActivity.this, PerfilFragment.class);
                                itPerfil.putExtra("usuario", (Serializable) usuario);
                                startActivity(itPerfil);
                            }

                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            Toast.makeText(TelaCadastroActivity.this, "Servidor temporariamente indisponível :(", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(TelaCadastroActivity.this, "Por favor, reconfirme sua senha!", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public void inicializaComponentes() {
        this.etLoginCadastro = (EditText) findViewById(R.id.et_login_cadastro);
        this.etSenhaCadastro = (EditText) findViewById(R.id.et_senha_cadastro);
        this.etConfirmaSenhaCadastro = (EditText) findViewById(R.id.et_confirma_senha);
        this.btCadastrar = (Button) findViewById(R.id.bt_cadastrar);
    }
}
