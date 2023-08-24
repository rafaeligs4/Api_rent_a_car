package com.rafaG.rentacar.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafaG.rentacar.Constants.Constants;
import com.rafaG.rentacar.Models.User;
import com.rafaG.rentacar.Services.UserService;
import com.rafaG.rentacar.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class UserController implements Constants {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "get-users", method = RequestMethod.GET)
    public List<User> prueba(){
        return userService.getAllUsers();
    }

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
           return response;
        }
        catch (Exception e){
            e.printStackTrace();
            response.put(TYPE,ERROR_REGISTER);
            response.put(MESSAGE,REGISTER_FAILED);
            return response;
        }
    }

    @RequestMapping(value = "delete-user/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id){
        userDao.eliminar(id);
    }

}
