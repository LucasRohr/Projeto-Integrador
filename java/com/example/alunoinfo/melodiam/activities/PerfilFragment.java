package com.example.alunoinfo.melodiam.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alunoinfo.melodiam.services.UsuarioService;
import com.example.user.melodiam.R;
import com.example.alunoinfo.melodiam.model.Lista;
import com.example.alunoinfo.melodiam.model.Usuario;
import com.example.alunoinfo.melodiam.services.ListaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private TextView tvNome;
    private TextView tvNumeroListas;
    private ListaService listaService;
    private Button btMudarLogin;
    private Button btMudarSenha;
    private Button btExcluirConta;
    private Button btLogout;
    private Call<Lista> numeroListas;
    //private AlertDialog alerta;
    private UsuarioService usuarioService;
    private Usuario usuario;
    private float numero;
    private EditText input;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.usuarioService = retrofit.create(UsuarioService.class);
        this.listaService = retrofit.create(ListaService.class);

        this.tvNome = (TextView) view.findViewById(R.id.tv_nome);
        this.tvNumeroListas = (TextView) view.findViewById(R.id.tv_numero_listas);
        this.btMudarLogin = (Button) view.findViewById(R.id.bt_mudar_login);
        this.btMudarSenha = (Button) view.findViewById(R.id.bt_mudar_senha);
        this.btExcluirConta = (Button) view.findViewById(R.id.bt_excluir_conta);
        this.btLogout = (Button) view.findViewById(R.id.bt_logout);
        this.input = (EditText) view.findViewById(R.id.et_login_dialog);

        Toast.makeText(getActivity(), "Bem-vindo ao seu perfil Melodiam!", Toast.LENGTH_LONG).show();

        Intent intent = getActivity().getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");
//        Log.i("LOGIN", usuario.getLogin() + " " + usuario.getSenha());
////
////
//        usuarioService.buscarPorLoginESenha(usuario.getLogin(), usuario.getSenha()).enqueue(new Callback<Usuario>() {
//            @Override
//            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
//                usuario = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<Usuario> call, Throwable t) {
//
//            }
//        });
//
//
////
////        if(tvNome == null || tvNumeroListas == null || btMudarLogin == null || btMudarSenha == null || btExcluirConta == null || btLogout == null)
////            Log.i("NULL", "Tá tudo null :c");
//        this.tvNome.setText(usuario.getLogin());
//        listaService.retornarNumeroListas(usuario.getIdUsuario()).enqueue(new Callback<Float>() {
//            @Override
//            public void onResponse(Call<Float> call, Response<Float> response) {
//                numero = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<Float> call, Throwable t) {
//
//            }
//        });
//        this.tvNumeroListas.setText(numero + " listas criadas");
//
//
        this.btMudarLogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.layout_dialog_login, null);

                builder.setView(input);
                builder.setTitle("Atualizar login:");
                builder.setMessage("Digite um novo login:");

                builder.setPositiveButton("Atualizar login", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        usuarioService.editarUsuario(usuario);
                        tvNome.setText(input.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(), "Operação cancelada!", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });


        this.btMudarSenha.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override

            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Atualizar senha:");
                builder.setMessage("Digite uma nova senha:");
                builder.setView(input);

                builder.setPositiveButton("Atualizar senha", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        usuarioService.editarUsuario(usuario);

                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(), "Operação cancelada!", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });


        this.btExcluirConta.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Excluir a conta do Melodiam:");
                builder.setMessage("Você deseja mesmo excluir sua conta?");

                builder.setPositiveButton("Excluir conta", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        usuarioService.excluirUsuario(usuario.getIdUsuario());
                        Intent itTelaMain = new Intent(getActivity(), MainActivity.class);
                        startActivity(itTelaMain);
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(), "Operação cancelada!", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });



        //atributo da classe.


//        private void exemplo_simples() {
//            //Cria o gerador do AlertDialog
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            //define o titulo
//            builder.setTitle("Titulo");
//            //define a mensagem
//            builder.setMessage("Qualifique este software");
//            //define um botão como positivo
//            builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface arg0, int arg1) {
//                    Toast.makeText(MainActivity.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
//                }
//            });
//            //define um botão como negativo.
//            builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface arg0, int arg1) {
//                    Toast.makeText(MainActivity.this, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
//                }
//            });
//            //cria o AlertDialog
//            alerta = builder.create();
//            //Exibe
//            alerta.show();
//        }

//        SETAR UM ET NO DIALOG(escrever dentro dele)
//        final EditText input = new EditText(this);
//        mensagem.setView(input);
        // EXEMPLO:

//        public void exibirMensagemEdt(String titulo, String texto){
//
//            AlertDialog.Builder mensagem = new AlertDialog.Builder(TelaCardapio.this);
//            mensagem.setTitle(titulo);
//            mensagem.setMessage(texto);
//            // DECLARACAO DO EDITTEXT
//            final EditText input = new EditText(this);
//            mensagem.setView(input);
//            mensagem.setNeutralButton("OK", new DialogInterface.OnClickListener() {
//
//                public void onClick(DialogInterface dialog, int which) {
//
//                    Toast.makeText(getApplicationContext(), input.getText().toString().trim(),
//                            Toast.LENGTH_SHORT).show();
//                }
//
//            });
//
//            mensagem.show();
//            // FORÇA O TECLADO APARECER AO ABRIR O ALERT
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
//        }







        this.btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Fazer logout:");
                builder.setMessage("Você deseja mesmo sair da sua conta?");

                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent itTelaMain = new Intent(getActivity(), MainActivity.class);
                        startActivity(itTelaMain);
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(), "Operação cancelada!", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });



        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }


}
