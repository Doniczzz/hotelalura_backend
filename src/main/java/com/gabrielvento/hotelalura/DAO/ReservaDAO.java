package com.gabrielvento.hotelalura.DAO;

import com.gabrielvento.hotelalura.model.Huesped;
import com.gabrielvento.hotelalura.model.Reserva;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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
    @Transactional
    public void eliminarReserva(long id) {
        Query query = em.createNativeQuery("DELETE FROM huespedes WHERE reserva_id = :reservaId");
        query.setParameter("reservaId", id);
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM reservas WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
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
