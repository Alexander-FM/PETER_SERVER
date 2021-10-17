package com.alexandertutoriales.servidor.controller;

import com.alexandertutoriales.servidor.entity.Usuario;
import com.alexandertutoriales.servidor.service.UsuarioService;
import com.alexandertutoriales.servidor.utils.GenericResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public GenericResponse<Usuario> login(HttpServletRequest request){
        String email = request.getParameter("email");
        String clave = request.getParameter("pass");
        return this.service.login(email, clave);
    }
}
