package com.example.douglas.melodiam.services;

import com.example.douglas.melodiam.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AlbumService {

    @POST("album/")
    Call<Album> cadastrarAlbum(@Body Album album);

    @GET("album/")
    Call<List<Album>> buscarTodosAlbums();

    @GET("album/{id}")
    Call<Album> buscarPorId(@Path("id") long id);

    @GET("album/spotify/{id_spotify}")
    Call<Album> buscarIdSpotify(@Path("id_spotify") String idSpotify);

}