package com.gabrielvento.hotelalura.DAO;

import com.gabrielvento.hotelalura.model.Huesped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class HuespedDAO implements HuespedInterface{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Huesped> obtenerHuespedes() {
        return em.createQuery("FROM Huesped", Huesped.class).getResultList();
    }

    @Override
    public long obtenerCantidadHuespedes() {
        return em.createQuery("SELECT COUNT(*) FROM Huesped", Long.class).getSingleResult();
    }

    @Override
    public Huesped obtenerHuespedPorNombre(String nombre) {
        return em.createQuery("FROM Huesped WHERE nombre = :nombre", Huesped.class)
                .setParameter("nombre", nombre)
                .getSingleResult();
    }

    @Override
    public void eliminarHuesped(long id) {
        em.remove(em.find(Huesped.class, id));
    }

    @Override
    public void guardarHuesped(Huesped huesped) {
        em.persist(huesped);
    }

    @Override
    public void actualizarHuesped(long id, Huesped huesped) {
        Huesped huespedActualizado = em.find(Huesped.class, id);
        huespedActualizado.setNombre(huesped.getNombre());
        huespedActualizado.setApellido(huesped.getApellido());
        huespedActualizado.setFechaNacimiento(huesped.getFechaNacimiento());
        huespedActualizado.setNacionalidad(huesped.getNacionalidad());
        huespedActualizado.setTelefono(huesped.getTelefono());
        em.merge(huespedActualizado);
    }

    @Override
    public Huesped obtenerHuespedPorId(long id) {
        return em.find(Huesped.class, id);
    }
}
