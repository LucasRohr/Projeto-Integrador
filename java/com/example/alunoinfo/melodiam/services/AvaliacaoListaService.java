package com.example.alunoinfo.melodiam.services;

import com.example.alunoinfo.melodiam.model.AvaliacaoLista;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AvaliacaoListaService {

    @POST("avaliacao-lista/")
    Call<AvaliacaoLista> cadastrarAvaliacao(@Body AvaliacaoLista avaliacaoLista);

    @PUT("avaliacao-lista/")
    Call<AvaliacaoLista> editarAvaliacao(@Body AvaliacaoLista avaliacaoLista);

    @DELETE("avaliacao-lista/{id}")
    Call<AvaliacaoLista> excluirAvaliacao(@Path("id") long id);

    @GET("avaliacao-lista/usuario/{id_usuario}")
    Call<AvaliacaoLista> buscarPorUsuario();

    @GET("avaliacao-lista/{id}")
    Call<AvaliacaoLista> buscarPorId(@Path("id") long id);

    @GET("avaliacao-lista/")
    Call<AvaliacaoLista> buscarTodasAvaliacoes();

    @GET("avaliacao-lista/media/{id_lista}")
    Call<AvaliacaoLista> calcularMediaLista(@Path("id_lista") long id);

    @GET("avaliacao-lista/lista/{id_lista}")
    Call<AvaliacaoLista> buscarPorLista(@Path("id_lista") long id);

}