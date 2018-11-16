package com.example.alunoinfo.melodiam.services;

import com.example.alunoinfo.melodiam.model.Lista;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ListaService {

    @POST("lista/")
    Call<Lista> cadastrarLista(@Body Lista lista);

    @PUT("lista/")
    Call<Lista> editarLista(@Body Lista lista);

    @DELETE("lista/{id}")
    Call<Lista> excluir(@Path("id") long id);

    @GET("lista/")
    Call<Lista> buscarTodasListas();

    @GET("lista/id/{id}")
    Call<Lista> buscarPorId(@Path("id") long id);

    @GET("lista/autor/{id_usuario}")
    Call<Lista> buscarPorAutor(@Path("id_usuario") long id);

    @GET("lista/listas/{id_usuario}")
    Call<Float> retornarNumeroListas(@Path("id_usuario") long idUsuario);

}