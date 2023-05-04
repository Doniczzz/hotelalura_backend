package com.gabrielvento.hotelalura.controller;

import com.gabrielvento.hotelalura.DAO.HuespedInterface;
import com.gabrielvento.hotelalura.model.Huesped;
import com.gabrielvento.hotelalura.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HuespedController {

    @Autowired
    private HuespedInterface huespedInterface;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/huespedes", method = RequestMethod.GET)
    public List<Huesped> listaHuespedes(@RequestHeader("Authorization") String token) {
        if (!validarToken(token)) {
            return null;
        }

        return huespedInterface.obtenerHuespedes();
    }

    @RequestMapping(value = "api/huespedes/count", method = RequestMethod.GET)
    public long cantidadHuespedes(@RequestHeader("Authorization") String token) {
        if (!validarToken(token)) {
            return 0L;
        }

        return huespedInterface.obtenerCantidadHuespedes();
    }

    @RequestMapping(value = "api/huespedes/{nombre}", method = RequestMethod.GET)
    public Huesped obtenerHuespedPorNombre(@RequestHeader("Authorization") String token, @PathVariable("nombre") String nombre) {
        if (!validarToken(token)) {
            return null;
        }

        return huespedInterface.obtenerHuespedPorNombre(nombre);
    }

    @RequestMapping(value = "api/huespedes/delete/{id}", method = RequestMethod.DELETE)
    public void eliminarHuesped(@RequestHeader("Authorization") String token, @PathVariable("id") long id) {
        if (!validarToken(token)) {
            return;
        }

        huespedInterface.eliminarHuesped(id);
    }

    @RequestMapping(value = "api/huespedes/add", method = RequestMethod.POST)
    public Huesped crearHuesped(@RequestHeader("Authorization") String token, @RequestBody Huesped huesped) {
        if (!validarToken(token)) {
            return null;
        }

        huespedInterface.guardarHuesped(huesped);
        return huesped;
    }

    @RequestMapping(value = "api/huespedes/update/{id}", method = RequestMethod.PUT)
    public Huesped actualizarHuesped(@RequestHeader("Authorization") String token, @PathVariable("id") long id, @RequestBody Huesped huesped) {
        if (!validarToken(token)) {
            return null;
        }

        huespedInterface.actualizarHuesped(id, huesped);
        return huesped;
    }

    @RequestMapping(value = "api/huespedes/buscar/{id}", method = RequestMethod.GET)
    public Huesped buscarHuesped(@RequestHeader("Authorization") String token, @PathVariable("id") long id) {
        if (!validarToken(token)) {
            return null;
        }

        return huespedInterface.obtenerHuespedPorId(id);
    }
}
