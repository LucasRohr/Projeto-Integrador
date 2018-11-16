package com.example.alunoinfo.melodiam.services;

import com.example.alunoinfo.melodiam.model.AlbumLista;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AlbumListaService {

    @POST("album-lista/")
    Call<AlbumLista> inserirEmLista(@Body AlbumLista albumLista);

    @DELETE("album-lista/{id}")
    Call<AlbumLista> excluirDaLista(@Path("id") long id);

    @GET("album-lista/lista/{id_lista}")
    Call<AlbumLista> buscarPorLista(@Path("id_lista") long id);

}