package com.gabrielvento.hotelalura.controller;

import com.gabrielvento.hotelalura.DAO.UsuarioInterface;
import com.gabrielvento.hotelalura.model.Usuarios;
import com.gabrielvento.hotelalura.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioInterface usuarioInterface;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuarios usuario) {

            Usuarios usuarioLogado = usuarioInterface.login(usuario);

            if (usuarioLogado != null) {
                return jwtUtil.create(usuarioLogado.getUsername(), String.valueOf(usuarioLogado.getId()));
            } else {
                return "401";
            }
    }

}
