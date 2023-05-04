package com.gabrielvento.hotelalura.DAO;

import com.gabrielvento.hotelalura.model.Usuarios;

import java.util.List;

public interface UsuarioInterface {
    List<Usuarios> findAll();

    Usuarios login(Usuarios usuario);

    void save(Usuarios usuario);

    Usuarios findById(Long id);
}
