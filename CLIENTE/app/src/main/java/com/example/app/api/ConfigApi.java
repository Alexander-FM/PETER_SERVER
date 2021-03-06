package com.example.app.api;

import com.example.app.utils.DateSerializer;
import com.example.app.utils.TimeSerializer;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigApi {
    public static final String baseUrlE = "http://10.0.2.2:9090";
    public static Retrofit retrofit;

    private static UsuarioApi usuarioApi;

    static {
        initClient();
    }

    private static void initClient() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer()) //Estamos llamando
                //al paquete de java.sql
                .registerTypeAdapter(Time.class, new TimeSerializer())
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrlE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient())
                .build();

    }

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(); //Instanciamos la clase
        logging.level(HttpLoggingInterceptor.Level.BODY);

        StethoInterceptor stetho = new StethoInterceptor();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(logging)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                //.addInterceptor(authInterceptor)
                .addNetworkInterceptor(stetho);

        return builder.build();
    }

    public static UsuarioApi getUsuarioApi(){
        if(usuarioApi == null){
            usuarioApi = retrofit.create(UsuarioApi.class);
        }
        return usuarioApi;
    }
}
