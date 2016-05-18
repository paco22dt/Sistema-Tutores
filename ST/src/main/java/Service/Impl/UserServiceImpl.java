/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Impl;

import Dao.UsersDao;
import Model.Users;
import Service.UserService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Francisco
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UsersDao userdao;
    
    @Transactional
    @Override
    public void add(Users user) {
        userdao.add(user);
    }

    @Transactional
    @Override
    public void edit(Users user) {
        userdao.edit(user);
    }

    @Transactional
    @Override
    public void delete(String user) {
        userdao.delete(user);
    }

    @Transactional
    @Override
    public Users getUser(String user) {
        return userdao.getUser(user);
    }

    @Transactional
    @Override
    public List<Users> getAllUsers() {
        return userdao.getAllUsers();
    }
    
}
