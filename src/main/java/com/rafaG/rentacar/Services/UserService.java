package com.rafaG.rentacar.Services;

import com.rafaG.rentacar.Constants.Constants;
import com.rafaG.rentacar.Models.User;
import com.rafaG.rentacar.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements Constants{
    @Autowired
    private UserDao userDao;


    public String registerUser(Map<String,Object> map){
           Map<String,Object>  finalResponse = new HashMap<>();
           String valorRetorno = "";
           //#1 parsear la respuesta map-user
           User user = parseMaptoRegisterUserInfo(map);
           //#2 Crear el usuario

           int responseNumber =  userDao.createUser(user);
           //#3 retornar la respuesta

           valorRetorno = responseNumber == 1 ? user.getName() : null;
           return valorRetorno;
    }

    public List<User> getAllUsers(){
        return userDao.listarUsers();
    }

    private User parseMaptoUser(Map<String,Object> map){
        User user = new User();

        DateTimeFormatter formatterTZ = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        user.setName(map.get(NAME).toString());
        user.setEmail(map.get(EMAIL).toString());
        user.setPassword(map.get(PASSWORD).toString());
        user.setEstado(Boolean.parseBoolean(map.get(ESTADO).toString()));
        user.setRol(map.get(ROL).toString());

        user.setApellido(map.get(Constants.APELLIDO).toString());
        user.setRemember_token(map.get(Constants.REMEMBER_TOKEN).toString());
        user.setEmail_verified_at(LocalDateTime.parse(map.get(Constants.EMAIL_VERIFIED_AT.toString()).toString(),formatterTZ));
        user.setCedula(map.get(Constants.CEDULA).toString());
        user.setNo_licencia(map.get(Constants.NO_LICENCIA).toString());
        user.setFoto_perfil(map.get(Constants.FOTO_PERFIL).toString());
        user.setFoto_licencia(map.get(Constants.FOTO_LICENCIA).toString());

        user.setFecha_nac(LocalDate.parse(map.get(Constants.FECHA_NAC.toString()).toString(),formatterDate));
        user.setFecha_venc(LocalDate.parse(map.get(Constants.FECHA_VENC.toString()).toString(),formatterDate));
        user.setCreated_at(LocalDateTime.parse(map.get(Constants.CREATED_AT.toString()).toString(),formatterTZ));
        user.setUpdated_at(LocalDateTime.parse(map.get(Constants.UPDATED_AT.toString()).toString(),formatterTZ));
        return user;
    }

    private User parseMaptoRegisterUserInfo(Map<String,Object> map){
        User user = new User();

        user.setName(map.get(NAME).toString());
        user.setEmail(map.get(EMAIL).toString());
        user.setPassword(map.get(PASSWORD).toString());
        user.setRol(map.get(ROL).toString());
        user.setCreated_at(LocalDateTime.now());
    return user;
    }
}
