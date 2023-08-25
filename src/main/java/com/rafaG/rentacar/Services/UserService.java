package com.rafaG.rentacar.Services;

import com.rafaG.rentacar.Constants.Constants;
import com.rafaG.rentacar.Models.User;
import com.rafaG.rentacar.dao.UserDao;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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

////**********************SERVICIOS RELACIONADOS AL CRUD DE LOS USUARIOS **/////////////////////
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

    public String updateUser(Map<String,Object> map){
        //#1 colocamos la data dentro de un tipo User
        User user = parseMaptoUser(map);
        //#2 enviamos la informacion del usuario;
      int responseNumber =   userDao.upadteUser(user);
      String valorRetorno = responseNumber == 1 ? " " : null;
        return valorRetorno;
    }

    public boolean deleteUser(int id){
        try{
            userDao.eliminar(id);
            return  true;
        }
        catch (Exception e){
        e.printStackTrace();
        return false;
        }
    }

    public List<User> getAllUsers(){
        return userDao.listarUsers();
    }

    public User getUser(int id){return userDao.getUser(id);}

    //***********SERVICIO DE LOGIN *************//
    public User loginUser(String username, String password){
        try{
            User userInfo = userDao.login(username,password);
            if(userInfo.equals(null)) throw new NullPointerException("Archivo malo");
            return userInfo;
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    private User parseMaptoUser(Map<String,Object> map){
        User user = new User();
        DateTimeFormatter formatterTZ = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(map.get(ID)!=null)user.setId(Integer.parseInt(map.get(ID).toString()));
        if(map.get(NAME)!=null)user.setName(map.get(NAME).toString());
        if(map.get(EMAIL)!=null)user.setEmail(map.get(EMAIL).toString());


        if(map.get(ESTADO)!=null)user.setEstado(Boolean.parseBoolean(map.get(ESTADO).toString()));
        if(map.get(ROL)!=null)user.setRol(map.get(ROL).toString());
        if(map.get(APELLIDO)!=null)user.setApellido(map.get(Constants.APELLIDO).toString());
        if(map.get(REMEMBER_TOKEN)!=null)user.setRemember_token(map.get(Constants.REMEMBER_TOKEN).toString());
        if(map.get(EMAIL_VERIFIED_AT)!=null)user.setEmail_verified_at(LocalDateTime.parse(map.get(Constants.EMAIL_VERIFIED_AT.toString()).toString(),formatterTZ));
        if(map.get(CEDULA)!=null)user.setCedula(map.get(Constants.CEDULA).toString());
        if(map.get(NO_LICENCIA)!=null)user.setNo_licencia(map.get(Constants.NO_LICENCIA).toString());
        if(map.get(FOTO_PERFIL)!=null)user.setFoto_perfil(map.get(Constants.FOTO_PERFIL).toString());
        if(map.get(FOTO_LICENCIA)!=null)user.setFoto_licencia(map.get(Constants.FOTO_LICENCIA).toString());
        if(map.get(FECHA_NAC)!=null)user.setFecha_nac(LocalDate.parse(map.get(Constants.FECHA_NAC.toString()).toString(),formatterDate));
        if(map.get(FECHA_VENC)!=null)user.setFecha_venc(LocalDate.parse(map.get(Constants.FECHA_VENC.toString()).toString(),formatterDate));
        if(map.get(CREATED_AT)!=null)user.setCreated_at(LocalDateTime.parse(map.get(Constants.CREATED_AT.toString()).toString(),formatterTZ));
        if(map.get(UPDATED_AT)!=null)user.setUpdated_at(LocalDateTime.parse(map.get(Constants.UPDATED_AT.toString()).toString(),formatterTZ));

        if(map.get(PASSWORD)!=null){
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
            String hash = argon2.hash(1,1024,1,map.get(PASSWORD).toString());
            user.setPassword(hash);
        }

        return user;


    }

    private User parseMaptoRegisterUserInfo(Map<String,Object> map){
        User user = new User();
        user.setName(map.get(NAME).toString());
        user.setEmail(map.get(EMAIL).toString());

        user.setRol(map.get(ROL).toString());
        user.setCreated_at(LocalDateTime.now());
        //HASHEAR CONTRASEÑA
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
        String hash = argon2.hash(1,1024,1,map.get(PASSWORD).toString());
        user.setPassword(hash);

    return user;
    }
}
