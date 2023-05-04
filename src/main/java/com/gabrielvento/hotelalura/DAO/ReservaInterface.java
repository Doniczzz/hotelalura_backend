package com.gabrielvento.hotelalura.DAO;

import com.gabrielvento.hotelalura.model.Reserva;

import java.util.List;

public interface ReservaInterface {

    List<Reserva> obtenerReservas();

    Reserva obtenerReservaPorId(int id);

    void guardarReserva(Reserva reserva);

    void eliminarReserva(long id);

    void actualizarReserva(long id, Reserva reserva);
}
