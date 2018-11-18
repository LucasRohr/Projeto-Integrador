package com.example.douglas.melodiam.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.douglas.melodiam.R;
import com.example.douglas.melodiam.model.Album;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuscaFragment extends Fragment {

    private View view;
    private ListView lvBusca;
    private Switch aSwitch;
    private AlbumFragment albumFragment;
    private String nomeAlbum;
    private String artistaAlbum;
    private Integer capa;

    String[] nomesAlbuns = {"Trench", "Hot Fuss", "Blurryface", "In Rainbows"};
    String[] artistasAlbuns = {"Twenty One Pilots", "The Killers", "Twenty One Pilots", "Radiohead"};
    Integer[] idsCapas = {R.drawable.ic_avatar, R.drawable.ic_listas, R.drawable.ic_album, R.drawable.ic_amigos};
    CustomListView customListView;



    public BuscaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_buscar, container, false);
        this.lvBusca = (ListView) view.findViewById(R.id.lv_busca);
        this.aSwitch = (Switch) getActivity().findViewById(R.id.st_album_usuario);

        setHasOptionsMenu(true);
        customListView = new CustomListView(getActivity(), nomesAlbuns, artistasAlbuns, idsCapas);
        lvBusca.setAdapter(customListView);

        lvBusca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                nomeAlbum  = nomesAlbuns[i];
                artistaAlbum  = artistasAlbuns[i];
                capa = idsCapas[i];

                albumFragment = new AlbumFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, albumFragment);
                fragmentTransaction.addToBackStack(null);
                lvBusca.getSelectedItemPosition();
                fragmentTransaction.commit();
            }
        });


        return view;
    }


    public String getNomeAlbum() {
        return nomeAlbum;
    }

    public String getArtistaAlbum() {
        return artistaAlbum;
    }

    public Integer getCapa() {
        return capa;
    }


    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem item = menu.findItem(R.id.m_search);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                customListView.getFilter().filter(s);

                return false;
            }
        });

        //return super.onCreateOptionsMenu(menu);
    }

    public static BuscaFragment newInstance(){
        return new BuscaFragment();
    }



//    this.button.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            LayoutInflater inflater = getActivity().getLayoutInflater();
//            builder.setTitle("Adicionar álbum à lista:");
//            builder.setMessage("Selecione a lista: ");
//
//            listView = new ListView(getActivity());
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lista);
//            listView.setAdapter(adapter);
//
//            builder.setView(listView);
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    Toast.makeText(getActivity(), "Lista selecionada", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            builder.setPositiveButton("Adicionar álbum", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface arg0, int arg1) {
//                    Toast.makeText(getActivity(), "Álbum adicionado à lista", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface arg0, int arg1) {
//                    Toast.makeText(getActivity(), "Operação cancelada", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            AlertDialog alert = builder.create();
//            alert.show();
//
//        }
//    });


}
