package com.alexandertutoriales.servidor.service;

import com.alexandertutoriales.servidor.entity.Usuario;
import com.alexandertutoriales.servidor.repository.UsuarioRepository;
import com.alexandertutoriales.servidor.utils.GenericResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.alexandertutoriales.servidor.utils.Global.*;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
    //Método para iniciar sesión
    public GenericResponse<Usuario> login(String email, String clave){
        Optional<Usuario> optU = this.repository.login(email, clave);
        if(optU.isPresent()){
            return new GenericResponse<Usuario>(TIPO_DATA, RPTA_OK, "Haz iniciado sesión exitosamente", optU.get());
        }else{
            return new GenericResponse<Usuario>(TIPO_DATA, RPTA_WARNING, "Lo sentimos, ese usuario no existe :v", new Usuario());
        }
    }
}
