package com.example.douglas.melodiam.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.douglas.melodiam.R;

public class CustomListView extends ArrayAdapter<String> {

    private String[] nomesAlbuns;
    private String[] artistasAlbuns;
    private Integer[] idsCapas;
    private Activity context;

    @SuppressLint("ResourceType")
    public CustomListView(Activity context, String[] nomesAlbuns, String[] artistasAlbuns, Integer[] idsCapas) {
        super(context, R.id.lv_suas_listas, nomesAlbuns);

        this.context = context;
        this.nomesAlbuns = nomesAlbuns;
        this.artistasAlbuns = artistasAlbuns;
        this.idsCapas = idsCapas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder = null;

        if(r == null) {

            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.lv_estilo, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) r.getTag();

        }

        viewHolder.ivw.setImageResource(idsCapas[position]);
        viewHolder.tvw1.setText(nomesAlbuns[position]);
        viewHolder.tvw2.setText(artistasAlbuns[position]);
        return r;
    }



    class ViewHolder {

        TextView tvw1;
        TextView tvw2;
        ImageView ivw;

        ViewHolder(View v) {
            this.tvw1 = (TextView) v.findViewById(R.id.tv_nome_album);
            this.tvw2 = (TextView) v.findViewById(R.id.tv_nome_artista);
            this.ivw = (ImageView) v.findViewById(R.id.iv_capa);
        }

    }

}
