/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author Alexis
 */
public class usuario {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toUpperCase();
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    private int id;
    private String username;
    private String Password;
    
    public usuario(){
         
    }
    
    public usuario(int id_usuario){
        this.id=id_usuario;
        
    }
    
    public usuario(int id_usuario,String username,String password){
      this.id=id_usuario;
      this.username=username;
      this.Password=password;
    }
}
