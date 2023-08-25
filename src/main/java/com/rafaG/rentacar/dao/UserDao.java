package com.rafaG.rentacar.dao;

import com.rafaG.rentacar.Models.User;

import java.util.List;


public interface UserDao {
    //Listar Usuarios
    List<User> listarUsers();

    User getUser(int id);

    void eliminar(long id);

    int createUser(User user);

    int upadteUser(User user);

    User login(String username,String password);
}
