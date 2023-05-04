package com.gabrielvento.hotelalura.controller;

import com.gabrielvento.hotelalura.DAO.UsuarioInterface;
import com.gabrielvento.hotelalura.model.Usuarios;
import com.gabrielvento.hotelalura.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioInterface usuarioInterface;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuarios> listaUsuarios(@RequestHeader("Authorization") String token) {
        if (!validarToken(token)) {
            return null;
        }

        return usuarioInterface.findAll();
    }

    @RequestMapping(value = "api/usuarios/registro", method = RequestMethod.POST)
    public void registroUsuario(Usuarios usuario) {

        Argon2 argon2 = Argon2Factory.create( Argon2Factory.Argon2Types.ARGON2id );
        String hash = argon2.hash( 1, 1024, 1, usuario.getPassword() );
        usuario.setPassword(hash);

        usuarioInterface.save(usuario);
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuarios getUsuario(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        return usuarioInterface.findById(id);
    }
}
