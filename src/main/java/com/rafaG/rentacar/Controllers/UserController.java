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
@CrossOrigin(origins = "*")
public class UserController implements Constants {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "get-users", method = RequestMethod.GET)
    public List<User> prueba(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "get-user/{id}", method = RequestMethod.GET)
    public Map<String,Object>  getUser(@PathVariable int id){
        Map<String,Object> response = new HashMap<>();
        try{
            User user = userService.getUser(id);
            if(id<=0 || user==null) throw  new Exception("Nueva excepcion");
            response.put(TYPE,TYPE_GET);
            response.put(DATA,user);
            return response;
        }
        catch (Exception e){
            e.printStackTrace();
            response.put(TYPE,ERROR_USER);
            response.put(MESSAGE,"No se encontró un usuario con el ID: "+id);
            return response;
        }
    }

    @RequestMapping(value="update-user",method = RequestMethod.PUT)
    public Map<String,Object> updateUser(@RequestBody String userData){
        Map<String,Object> json = new HashMap<>();
        Map<String,Object> response = new HashMap<>();
        try{
            json = new ObjectMapper().readerFor(Map.class).readValue(userData);

            String valueResponseUpdate = userService.updateUser(json);
            if(valueResponseUpdate.equals(null)){
                throw new Exception("Error en la actualizacion");
            }

            response.put(TYPE,TYPE_UPDATE);
            response.put(MESSAGE,UPDATE_CLEAR);
            return response;
        }
        catch (Exception e){
            e.printStackTrace();
            response.put(TYPE,UPDATE_CLEAR);
            response.put(MESSAGE,UPDATE_CLEAR);
            return response;
        }
    }

    @RequestMapping(value = "delete-user/{id}", method = RequestMethod.DELETE)
    public Map<String,Object>  delete(@PathVariable int id){
        Map<String,Object> response = new HashMap<>();
            try{
                boolean isDeletedUser = userService.deleteUser(id);
                if(id<=0 || !isDeletedUser) throw  new Exception("Nueva excepcion");
                response.put(TYPE,TYPE_DELETE);
                response.put(MESSAGE,DELETE_CLEAR);
                response.put(DATA, true);
                return response;
            }
            catch (Exception e){
                e.printStackTrace();
                response.put(TYPE,ERROR_USER);
                response.put(MESSAGE,DELETE_FAILED);
                response.put(DATA, false);
                return response;
            }
    }


}
