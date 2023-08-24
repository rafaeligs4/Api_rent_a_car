package com.rafaG.rentacar.dao;

import com.rafaG.rentacar.Models.User;

import java.util.List;


public interface UserDao {
    //Listar Usuarios
    List<User> listarUsers();

    void eliminar(long id);

    int createUser(User user);
}
