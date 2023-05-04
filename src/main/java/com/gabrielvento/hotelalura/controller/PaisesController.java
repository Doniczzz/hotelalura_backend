package com.gabrielvento.hotelalura.controller;

import com.gabrielvento.hotelalura.DAO.PaisesInterface;
import com.gabrielvento.hotelalura.model.Paises;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PaisesController {

    @Autowired
    private PaisesInterface paisesInterface;

    @RequestMapping("/api/paises")
    public List<Paises> paises() {
        return paisesInterface.paisesOrdenadosPorNome();
    }

}
