package com.gabrielvento.hotelalura.controller;

import com.gabrielvento.hotelalura.DAO.ReservaInterface;
import com.gabrielvento.hotelalura.model.Reserva;
import com.gabrielvento.hotelalura.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private ReservaInterface reservaInterface;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/reservas", method = RequestMethod.GET)
    public List<Reserva> listaReservas(@RequestHeader("Authorization") String token) {
        if (!validarToken(token)) {
            return null;
        }

        return reservaInterface.obtenerReservas();
    }

    @RequestMapping(value = "api/reservas/{id}", method = RequestMethod.GET)
    public Reserva obtenerReservaPorId(@RequestHeader("Authorization") String token, @PathVariable("id") int id) {
        if (!validarToken(token)) {
            return null;
        }

        return reservaInterface.obtenerReservaPorId(id);
    }

    @RequestMapping(value = "api/reservas/delete/{id}", method = RequestMethod.DELETE)
    public void eliminarReserva(@RequestHeader("Authorization") String token, @PathVariable("id") long id) {
        if (!validarToken(token)) {
            return;
        }

        reservaInterface.eliminarReserva(id);
    }

    @RequestMapping(value = "api/reservas/crear", method = RequestMethod.POST)
    public Reserva crearReserva(@RequestHeader("Authorization") String token, @RequestBody Reserva reserva) {
        if (!validarToken(token)) {
            return null;
        }

        reservaInterface.guardarReserva(reserva);
        return reserva;
    }

    @RequestMapping(value = "api/reservas/update/{id}", method = RequestMethod.PUT)
    public Reserva actualizarReserva(@RequestHeader("Authorization") String token, @PathVariable("id") long id, @RequestBody Reserva reserva) {
        if (!validarToken(token)) {
            return null;
        }

        reservaInterface.actualizarReserva(id, reserva);
        return reserva;
    }

}
