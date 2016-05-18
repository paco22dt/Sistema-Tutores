/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author Francisco
 */

@Entity
public class Users {
    
    //Anotaciones JPA
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "username", nullable = false, length = 10)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "password", nullable = false, length = 10)
    private String password;
    
    public Users()
    {
    }
    
    public Users(String user, String pass)
    {
        this.username=user;
        this.password=pass;
    }
    

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    
}
