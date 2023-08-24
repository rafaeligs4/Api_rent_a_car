package com.rafaG.rentacar.Repositories;

import com.rafaG.rentacar.Models.User;
import com.rafaG.rentacar.dao.UserDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class UserRepository implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> listarUsers() {
    String query = "FROM User";
        List<User> usuarios = this.entityManager.createQuery(query).getResultList();
        return usuarios;
    }

    public void eliminar(long id) {
    User user = entityManager.find(User.class,id);
    entityManager.remove(user);
    }

    public int createUser(User user) {
        try {
            entityManager.persist(user);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }


}
