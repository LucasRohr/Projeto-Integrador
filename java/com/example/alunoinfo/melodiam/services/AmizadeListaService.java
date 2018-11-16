package com.example.alunoinfo.melodiam.services;

import com.example.alunoinfo.melodiam.model.AmizadeLista;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AmizadeListaService {

    @POST("amizade-lista/")
    Call<AmizadeLista> compartilhar(@Body AmizadeLista amizadeLista);

    @GET("amizade-lista/")
    Call<AmizadeLista> buscarTodos();

    @GET("amizade-lista/recebidos/{id_usuario}")
    Call<AmizadeLista> buscarRecebidosPorUsuario(@Path("id_usuario") long id);

    @GET("amizade-lista/{id_autor}/{id_leitor}")
    Call<AmizadeLista> buscarPorAutorELeitor(@Path("id_autor") long idAutor, @Path("id_leitor") long idLeitor);

    @GET("amizade-lista/compartilhados/{id_usuario}")
    Call<AmizadeLista> buscarCompartilhadosPorUsuario(@Path("id_usuario") long id);

}