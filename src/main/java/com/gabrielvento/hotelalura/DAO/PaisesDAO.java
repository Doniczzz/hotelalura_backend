package com.gabrielvento.hotelalura.DAO;

import com.gabrielvento.hotelalura.model.Paises;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PaisesDAO implements PaisesInterface{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Paises> paisesOrdenadosPorNome() {
        return em.createQuery("SELECT p FROM Paises p ORDER BY p.name", Paises.class).getResultList();
    }
}
