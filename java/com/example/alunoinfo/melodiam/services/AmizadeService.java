package com.example.alunoinfo.melodiam.services;

import com.example.alunoinfo.melodiam.model.Amizade;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AmizadeService {

    @POST("amizade/solicitar-amizade/")
    Call<Amizade> solicitarAmizade(@Body Amizade amizade);

    @PUT("aceitar/{id_amizade}")
    Call<Amizade> aceitarUsuario(@Path("id_amizade") long idAmizade);

    @DELETE("amizade/id/{id_amizade}")
    Call<Amizade> deletarAmizade(@Path("id_amizade") long idAmizade);

    @GET("amigos/")
    Call<Amizade> buscarTodasAmizades();

    @GET("amizade/amigos/{id_usuario}")
    Call<Amizade> buscarAmigosPorUsuario(@Path("id_usuario") long idUsuario);

    @GET("amizade/pendentes/{id_usuario}")
    Call<Amizade> buscarPendentesPorUsuario(@Path("id_usuario") long idUsuario);

}