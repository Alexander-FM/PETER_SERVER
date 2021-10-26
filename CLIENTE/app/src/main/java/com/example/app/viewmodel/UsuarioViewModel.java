package com.example.app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.app.entity.GenericResponse;
import com.example.app.entity.service.Usuario;
import com.example.app.repository.UsuarioRepository;

public class UsuarioViewModel extends AndroidViewModel {
    private final UsuarioRepository repository;

    public UsuarioViewModel(@NonNull Application application) {
        super(application);
        this.repository = UsuarioRepository.getInstance();
    }

    public LiveData<GenericResponse<Usuario>> login(String email, String password) {
        return this.repository.login(email, password);
    }
}
