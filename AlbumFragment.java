package com.example.douglas.melodiam.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.douglas.melodiam.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {

    private View view;
    private ImageView ivCapa;
    private TextView tvNomeAlbum, tvArtistaAlbum, tvLancamentoAlbum, tvMediaAlbum, tvCopy;
    private ImageButton ibAddAlbum, ibComentarAlbum, ibAvaliarAlbum, ibMostraMusicas, ibMostraComentarios;
    private ListView lvMusicas, lvComentarios, lvDialogAdd;
    private RatingBar ratingBar;
    private float numeroRating;

    private BuscaFragment itemBusca = new BuscaFragment();

    private String[] lista = {"rock", "kpop", "pop anos 80"};
    private String[] musicas = {"My Blood", "The Hype"};

    private EditText etInput;

    private ArrayList<String> comentarios = new ArrayList<>();

    public AlbumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container, false);

        this.ivCapa = (ImageView) view.findViewById(R.id.iv_album);
        this.tvNomeAlbum = (TextView) view.findViewById(R.id.tv_nome_album_album);
        this.tvArtistaAlbum = (TextView) view.findViewById(R.id.tv_artista_album);
        this.tvLancamentoAlbum = (TextView) view.findViewById(R.id.tv_ano_album);
        this.tvMediaAlbum = (TextView) view.findViewById(R.id.tv_avaliacao_media_album);
        this.tvCopy = (TextView) view.findViewById(R.id.tv_copyright_album);
        this.ibAddAlbum = (ImageButton) view.findViewById(R.id.bt_adicionar_album_album);
        this.ibComentarAlbum = (ImageButton) view.findViewById(R.id.bt_comentar_album);
        this.ibAvaliarAlbum = (ImageButton) view.findViewById(R.id.bt_avaliar_album);
        this.ibMostraMusicas = (ImageButton) view.findViewById(R.id.ib_ver_musicas_album);
        this.ibMostraComentarios = (ImageButton) view.findViewById(R.id.ib_ver_comentarios_album);

        this.ivCapa.setImageResource(itemBusca.getCapa());
        this.tvNomeAlbum.setText(itemBusca.getNomeAlbum());
        this.tvArtistaAlbum.setText(itemBusca.getArtistaAlbum());

        // ESSES SERAO PEGOS PELA PRIMEIRA VEZ AQUI, VINDOS DO OBJETO DO ITEM DO LV
        this.tvLancamentoAlbum.setText("2018");
        this.tvCopy.setText("ToP");
        this.tvMediaAlbum.setText("2");


        this.ibAddAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                builder.setTitle("Adicionar álbum à lista:");
                builder.setMessage("Selecione a lista: ");

                lvDialogAdd = new ListView(getActivity());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lista);
                lvDialogAdd.setAdapter(adapter);

                builder.setView(lvDialogAdd);
                lvDialogAdd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getActivity(), "Lista selecionada", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setPositiveButton("Adicionar álbum", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(), "Álbum adicionado à lista", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(), "Operação cancelada", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
                }
        });

        this.ibComentarAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                builder.setTitle("Comentário:");
                builder.setMessage("Faça seu comentário: ");

                etInput = new EditText(getActivity());
                etInput.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(etInput);


                builder.setPositiveButton("Comentar álbum", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        comentarios.add(etInput.getText().toString());
                        Toast.makeText(getActivity(), "Comentário efetuado", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(), "Operação cancelada", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        ArrayAdapter<String> adapterComentarios = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, comentarios);
        lvComentarios.setAdapter(adapterComentarios);

        ArrayAdapter<String> adapterMusicas = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, musicas);
        lvMusicas.setAdapter(adapterMusicas);


        this.ibAvaliarAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                builder.setTitle("Avaliação:");
                builder.setMessage("Faça sua avaliação: ");

                ratingBar = new RatingBar(getActivity());
                ratingBar.setNumStars(5);
                numeroRating = ratingBar.getRating();
                builder.setView(ratingBar);

                builder.setPositiveButton("Avaliar álbum", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        Toast.makeText(getActivity(), "Avaliação efetuada", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(), "Operação cancelada", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        this.ibMostraMusicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lvMusicas.getVisibility() == View.VISIBLE) {
                    lvMusicas.setVisibility(View.INVISIBLE);
                }else{
                    lvMusicas.setVisibility(View.VISIBLE);
                }


            }
        });

        this.ibMostraComentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lvComentarios.getVisibility() == View.VISIBLE) {
                    lvComentarios.setVisibility(View.INVISIBLE);
                }else{
                    lvComentarios.setVisibility(View.VISIBLE);
                }


            }
        });







        return view;
    }

    public static AlbumFragment newInstance(){
        return new AlbumFragment();
    }


}
