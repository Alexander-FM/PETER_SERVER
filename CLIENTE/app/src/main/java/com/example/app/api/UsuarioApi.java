package com.example.app.api;

import com.example.app.entity.GenericResponse;
import com.example.app.entity.service.Usuario;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsuarioApi {
    //RUTA DEL SERVICIO
    String base = "api/usuario";

    //RUTA DEL SERVICIO + LA RUTA DEL MÉTODO
    @FormUrlEncoded
    @POST(base + "/login")
    Call<GenericResponse<Usuario>> login(@Field("email") String email, @Field("pass") String pass);

}
