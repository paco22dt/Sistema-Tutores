/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Users;
import Service.UserService;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Francisco
 */

@Controller
public class UsersController {
    private UserService userservice;
    
    @RequestMapping("/index")
    public String setupForm(Map<String, Object> map){
        Users users = new Users();
        map.put("users", users);
        map.put("usersList", userservice.getAllUsers());
        return "users";
    }
    @RequestMapping(value="/users.do", method=RequestMethod.POST)
    public String doActions(@ModelAttribute Users user, BindingResult result, @RequestParam String action, Map<String, Object> map){
        Users usersResult = new Users();
        switch(action.toLowerCase())
        {
            case "add":
                userservice.add(user);
                usersResult = user;
                break;
            case "edit":
                userservice.edit(user);
                usersResult = user;
                break;
            case "delete":
                userservice.delete(user.getUsername());
                usersResult = user;
                break;
            case "search":
                Users usersearched = userservice.getUser(user.getUsername());
                usersResult = usersearched != null ? usersearched : new Users();
                break;
        }
        map.put("users", usersResult);
        map.put("usersList", userservice.getAllUsers());
        return "users";
    }
    
}
