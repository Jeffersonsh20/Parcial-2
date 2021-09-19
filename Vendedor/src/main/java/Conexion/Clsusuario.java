/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import Dominio.usuario;
import java.sql.*;
import java.sql.PreparedStatement;

/**
 *
 * @author Alexis
 */
public class Clsusuario {
        private static final String SQL_INSERT="insert into tb_progracomision (user,password) values (?,?);";
    
    public int insert(usuario usuario){
        String texto="",encriptado ="";
         Connection conexion=null;
        PreparedStatement statement=null;
        int rows=0;
        try{
            conexion=Clsconexion.getConnection();
            statement=conexion.prepareStatement(SQL_INSERT);
            statement.setString(1, usuario.getUsername());
            texto=usuario.getPassword();
            
            statement.setString(2, encriptado);
            rows=statement.executeUpdate();
        }
         catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }finally{
           Clsconexion.CloseStatement(statement);
           Clsconexion.CloseConnection(conexion);
        }
        return rows;
    }

    public boolean  Validar(usuario datos){
         Connection conexion=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        usuario usuario =new usuario();
        String texto="",Mostrar ="";
        boolean tienepermiso=false;
       
        
        try {
            conexion=Clsconexion.getConnection();
            texto=datos.getPassword();
                     
            String Condicion="SELECT * FROM tb_usuario where user='"+datos.getUsername()+"'"
                    + " and password='"+Mostrar+"';";
            statement=conexion.prepareStatement(Condicion);
            rs=statement.executeQuery();
            
            while (rs.next()) {
                    tienepermiso=true;
            }      
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }finally{
           Clsconexion.CloseConnection(conexion);
           Clsconexion.CloseResul(rs);
           Clsconexion.CloseStatement(statement);
        }
        
        return tienepermiso;
    }
}
