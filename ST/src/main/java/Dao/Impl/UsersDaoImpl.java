/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.Impl;

import Dao.UsersDao;
import Model.Users;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Francisco
 */

@Repository
public class UsersDaoImpl implements UsersDao {

    @Autowired
    private SessionFactory session;
    
    @Override
    public void add(Users user) {
       session.getCurrentSession().save(user);
    }

    @Override
    public void edit(Users user) {
        session.getCurrentSession().update(user);
        
    }

    @Override
    public void delete(String user) {
        session.getCurrentSession().delete(getUser(user));
    }

    @Override
    public Users getUser(String user) {
        return (Users)session.getCurrentSession().get(Users.class, user);
    }

    @Override
    public List<Users> getAllUsers() {
        return session.getCurrentSession().createQuery("from users").list();
    }
    
}
