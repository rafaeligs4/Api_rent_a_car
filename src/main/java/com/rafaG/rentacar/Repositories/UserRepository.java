package com.rafaG.rentacar.Repositories;

import com.rafaG.rentacar.Models.User;
import com.rafaG.rentacar.dao.UserDao;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override

    public void eliminar(long id) {
    try{
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
    }
    catch (Exception e){
        e.printStackTrace();
    }
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

    @Override
    public int upadteUser(User user) {
        try{
            entityManager.merge(user);
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    public User getUser(int id) {
        try{
            User user = entityManager.find(User.class,id);
            return user;
        }
        catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }
    public User login(String username,String password){
        try{
            String query =  "FROM User WHERE email=:email";
            List<User> user = new ArrayList<>();
            user = entityManager.createQuery(query)
                    .setParameter("email",username)
                    .getResultList();
            if(user.isEmpty()) throw new NullPointerException("Ex");
            //#Verificando contraseña
            String passDBHashed = user.get(0).getPassword();
            System.out.println(passDBHashed);
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);

            boolean verifyPassword = argon2.verify(passDBHashed,password);
            System.out.println("EL ELEMENTO ES "+verifyPassword);
            if(!verifyPassword)  throw new Exception("Ex");

            return user.get(0);
        }

        catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    public User getUerByEmail(String username){
        try{
            System.out.println("User name: "+username);
            String query =  "FROM User WHERE email=:email";

            List<User> user = new ArrayList<>();
            user = entityManager.createQuery(query)
                    .setParameter("email",username)
                    .getResultList();
            if(user.isEmpty()) throw new NullPointerException("Ex");
            return user.get(0);
        }

        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
