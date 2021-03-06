package com.example.alunoinfo.melodiam.services;

import com.example.alunoinfo.melodiam.model.AvaliacaoAlbum;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AvaliacaoAlbumService {

    @POST("avaliacao-album/")
    Call<AvaliacaoAlbum> cadastrarAvaliacao(@Body AvaliacaoAlbum avaliacaoAlbum);

    @PUT("avaliacao-album/")
    Call<AvaliacaoAlbum> editarAvaliacao(@Body AvaliacaoAlbum avaliacaoAlbum);

    @DELETE("avaliacao-album/{id}")
    Call<AvaliacaoAlbum> excluirAvaliacao(@Path("id") long id);

    @GET("avaliacao-album/usuario/{id_usuario}")
    Call<AvaliacaoAlbum> buscarPorUsuario();

    @GET("avaliacao-album/{id}")
    Call<AvaliacaoAlbum> buscarPorId(@Path("id") long id);

    @GET("avaliacao-album/")
    Call<AvaliacaoAlbum> buscarTodasAvaliacoes();

    @GET("avaliacao-album/media/{id_album}")
    Call<AvaliacaoAlbum> calcularMediaAlbum(@Path("id_album") long id);

    @GET("avaliacao-album/album/{id_album}")
    Call<AvaliacaoAlbum> buscarPorAlbum(@Path("id_album") long id);

}