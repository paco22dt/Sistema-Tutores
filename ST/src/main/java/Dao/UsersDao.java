/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Users;
import java.util.List;

/**
 *
 * @author Francisco
 */

//Dao significa Data access object
public interface UsersDao {
    
    public void add(Users user);
    
    public void edit(Users user);
    
    public void delete(String user);
    
    public Users getUser(String user);
    
    public List<Users> getAllUsers();
    
}
