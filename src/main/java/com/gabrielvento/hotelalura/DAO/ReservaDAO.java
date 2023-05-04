package com.gabrielvento.hotelalura.DAO;

import com.gabrielvento.hotelalura.model.Reserva;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ReservaDAO implements ReservaInterface{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Reserva> obtenerReservas() {
        return em.createQuery("FROM Reserva", Reserva.class).getResultList();
    }

    @Override
    public Reserva obtenerReservaPorId(int id) {
        return em.find(Reserva.class, id);
    }

    @Override
    public void guardarReserva(Reserva reserva) {
        em.persist(reserva);
    }

    @Override
    public void eliminarReserva(long id) {
        em.remove(em.find(Reserva.class, id));
    }

    @Override
    public void actualizarReserva(long id, Reserva reserva) {
        Reserva reservaActual = em.find(Reserva.class, id);
        reservaActual.setFechaIngreso(reserva.getFechaIngreso());
        reservaActual.setFechaEgreso(reserva.getFechaEgreso());
        reservaActual.setValor(reserva.getValor());
        reservaActual.setFormaPago(reserva.getFormaPago());
        em.merge(reservaActual);
    }
}
