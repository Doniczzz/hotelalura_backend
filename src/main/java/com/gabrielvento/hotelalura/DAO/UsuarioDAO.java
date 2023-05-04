package com.gabrielvento.hotelalura.DAO;

import com.gabrielvento.hotelalura.model.Usuarios;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioDAO implements UsuarioInterface{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Usuarios> findAll() {
        return em.createQuery("SELECT u FROM Usuarios u", Usuarios.class).getResultList();
    }

    @Override
    public Usuarios login(Usuarios usuario) {

        try {
            String query = "SELECT u FROM Usuarios u WHERE u.username = :username";
            Usuarios usuarioLogado = em.createQuery(query, Usuarios.class)
                    .setParameter("username", usuario.getUsername())
                    .getSingleResult();
            if (usuarioLogado == null) {
                return null;
            }

            Argon2 argon2 = Argon2Factory.create( Argon2Factory.Argon2Types.ARGON2id );
            if (argon2.verify(usuarioLogado.getPassword(), usuario.getPassword())) {
                return usuarioLogado;
            } else {
                return null;
            }
        }catch (NoResultException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public void save(Usuarios usuario) {
        em.persist(usuario);
    }

    @Override
    public Usuarios findById(Long id) {
        return em.find(Usuarios.class, id);
    }
}
