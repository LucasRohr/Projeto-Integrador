package com.example.user.melodiam.activities;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.melodiam.R;
import com.example.user.melodiam.model.Lista;
import com.example.user.melodiam.model.Usuario;
import com.example.user.melodiam.services.ListaService;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends android.support.v4.app.Fragment {

    private TextView tvNome;
    private TextView tvNumeroListas;
    private ListaService listaService;
    private Button btMudarLogin;
    private Button btMudarSenha;
    private Button btExcluirConta;
    private Button btLogout;


    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inicializaComponentes();
        Toast.makeText(getActivity(), "Bem-vindo ao Melodiam!", Toast.LENGTH_LONG).show();
        Intent intent = getActivity().getIntent();
        Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");

        String login = this.tvNome.getText().toString();
        login = usuario.getLogin();

        long numeroListas = Long.parseLong(this.tvNumeroListas.getText().toString());
        //numeroListas = listaService.retornarNumeroListas(usuario.getIdUsuario());











        this.btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itLogout = new Intent(getActivity(), MainActivity.class);
                startActivity(itLogout);
            }
        });



        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    public void inicializaComponentes() {
        this.tvNome = getActivity().findViewById(R.id.tv_nome);
        this.tvNumeroListas = getActivity().findViewById(R.id.tv_numero_listas);
        this.btMudarLogin = (Button) getActivity().findViewById(R.id.bt_mudar_login);
        this.btMudarSenha = (Button) getActivity().findViewById(R.id.bt_mudar_senha);
        this.btExcluirConta = (Button) getActivity().findViewById(R.id.bt_excluir_conta);
        this.btLogout = (Button) getActivity().findViewById(R.id.bt_logout);
    }

}
