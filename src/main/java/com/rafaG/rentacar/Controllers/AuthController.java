package com.rafaG.rentacar.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafaG.rentacar.Constants.Constants;
import com.rafaG.rentacar.Models.User;
import com.rafaG.rentacar.Services.UserService;
import com.rafaG.rentacar.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")

public class AuthController implements Constants {

    @Autowired
    UserService userService;

    @Autowired
    JWTUtil jwtUtil;

//    @RequestMapping(value="login",method = RequestMethod.POST)
//    public Map<String,Object> loginUser(@RequestBody String userData){
//        Map<String,Object> response = new HashMap<>();
//        Map<String,Object> json= new HashMap<>();
//        try{
//            json = new ObjectMapper().readerFor(Map.class).readValue(userData);
//            //#1 verificamos la data de los usuarios
//            String username = (json.containsKey(EMAIL) && json.get(EMAIL) != null
//                    && !json.get(EMAIL).toString().isEmpty()) ? json.get(EMAIL).toString() : null;
//
//            String password = (json.containsKey(PASSWORD) && json.get(PASSWORD) != null
//                    && !json.get(PASSWORD).toString().isEmpty()) ? json.get(PASSWORD).toString() : null;
//            //#2 Aplicamos el metodo de verificar las credenciales
//            User user = userService.loginUser(username,password);
//            //#3 Si es nulo, entonces se aplica la excepcion
//            if(user==null)throw  new Exception("Nulo");
//            //#4 Generamos el JWT Y LO ENVIAMOS AL USUARIO
//            System.out.println(user);
//            String jwt = jwtUtil.create(String.valueOf(user.getId()),user.getEmail());
//            System.out.println(jwtUtil.getValue(jwt));
//            response.put(TYPE,TYPE_LOGIN);
//            response.put(MESSAGE,LOGIN_CLEAR);
//            response.put("JWT",jwt);
//            response.put(DATA,user);
//            return  response;
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            response.put(TYPE,TYPE_LOGIN);
//            response.put(MESSAGE,LOGIN_FAILED);
//            return  response;
//        }
//    }

    @RequestMapping(value="create-user",method = RequestMethod.POST)
    public Map<String,Object> createUser(@RequestBody String userData){
        Map<String,Object> json = new HashMap<>();
        Map<String,Object> response = new HashMap<>();
        try{
            json = new ObjectMapper().readerFor(Map.class).readValue(userData);
            String valueResponseRegister =  userService.registerUser(json);
            if(valueResponseRegister.equals(null)){
                throw new Exception("Error en el registro");
            }
            response.put(TYPE,TYPE_REGISTER);
            response.put(MESSAGE,REGISTER_CLEAR+valueResponseRegister);
            response.put(STATUS, CLEAR);
            return response;
        }
        catch (Exception e){
            e.printStackTrace();
            response.put(TYPE,ERROR_USER);
            response.put(MESSAGE,REGISTER_FAILED);
            response.put(STATUS, FAILED);
            return response;
        }
    }

}
