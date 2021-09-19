/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import Conexion.ClsVendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Alexis
 */
public class ClsVendedor {
    private static final String SQL_SELECT="SELECT * FROM tb_progracomision;";
    private static final String SQL_INSERT= "insert into tb_progracomision(nombre,enero,febrero,marzo) values(?,?,?,?,);";
    private static final String SQL_DELETE="delete from tb_vendedor where codigo=";
    private static final String SQL_UPDATE="update tb_progracomision set nombre=?,enero=?,febrero=?,marzo=? where codigo=?;";
    
    public List<ClsVendedor> SeleccionarTodalaInfo(){
        Connection conexion=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        ClsVendedor MV=null;
        List<ClsVendedor> Comisiones= new  ArrayList<ClsVendedor>();
        
        try {
            conexion=Clsconexion.getConnection();
            statement=conexion.prepareStatement(SQL_SELECT);
            resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                int Codigo=resultSet.getInt("codigo");
                String Nombre=resultSet.getString("nombre");
                Double Enero=resultSet.getDouble("enero");
                Double Febrero= resultSet.getDouble("febrero");
                Double Marzo= resultSet.getDouble("marzo");
                
                MV=new ClsVendedor();
                MV.setCodigo(Codigo);
                MV.setNombre(Nombre);
                MV.setEnero(Enero);
                MV.setFebrero(Febrero);
                MV.setMarzo(Marzo);
              
                Comisiones.add(MV);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Clsconexion.CloseConnection(conexion);
            Clsconexion.CloseResul(resultSet);
            Clsconexion.CloseStatement(statement);
        }
        return Comisiones;
    }

    public int InsertarVendedor(ClsVendedor vendedor){
        int rows=0;
        Connection conexion=null;
        PreparedStatement statement=null;
        try {
            conexion=Clsconexion.getConnection();
            statement=conexion.prepareStatement(SQL_INSERT);
            statement.setString(1, vendedor.getNombre());
            statement.setDouble(2, vendedor.getEnero());
            statement.setDouble(3, vendedor.getFebrero());
            statement.setDouble(4, vendedor.getMarzo());
           
            rows=statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Clsconexion.CloseConnection(conexion);
            Clsconexion.CloseStatement(statement);
        }
        return rows;
    }
    
    
    
    public String VentaTotalMayor(String Total){
        String Respuesta="";
        String Nombre="";
        Double Cantidad=0.0;
        int rows=0;
        Connection conexion=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        
        try {
            conexion=Clsconexion.getConnection();
            statement=conexion.prepareStatement("select nombre,"+Total +" from tb_vendedor where "+ Total+"=(select max("+Total+") from tb_vendedor);");
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                Nombre=resultSet.getString("nombre");
                Cantidad=resultSet.getDouble(Total);   
            }
            
            Respuesta=("La persona que mas vendio en los 3 Meses es: "+Nombre+" y la cantidad es:Q"+Cantidad);
            
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            Clsconexion.CloseConnection(conexion);
            Clsconexion.CloseResul(resultSet);
            Clsconexion.CloseStatement(statement);
        }
        return Respuesta;
    }
    
    public int EliminarVendedor(int codigo){
        int rows=0;
        Connection conexion=null;
        PreparedStatement statement=null;
        try {
            conexion=Clsconexion.getConnection();
            statement=conexion.prepareStatement(SQL_DELETE+codigo);
            rows=statement.executeUpdate();
            
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Clsconexion.CloseConnection(conexion);
            Clsconexion.CloseStatement(statement);
        }
        return rows;
        
    }
    
    public String BusquedadeInfo(Double busqueda){
        String Mes="",Name="";
        String Respuesta="";
        Connection conexion=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        ClsVendedor MV=null;
        List<ClsVendedor> Busqueda= new  ArrayList<ClsVendedor>();
        
        try {
            conexion=Clsconexion.getConnection();
            statement=conexion.prepareStatement("select * from tb_vendedor where marzo like("+busqueda+") or enero like("+busqueda+") or febrero like("+busqueda+");");
            resultSet=statement.executeQuery();
             while(resultSet.next()){
                int Codigo=resultSet.getInt("codigo");
                String Nombre=resultSet.getString("nombre");
                Double Enero=resultSet.getDouble("enero");
                Double Febrero= resultSet.getDouble("febrero");
                Double Marzo= resultSet.getDouble("marzo");
               
                MV=new ClsVendedor();
                MV.setCodigo(Codigo);
                MV.setNombre(Nombre);
                MV.setEnero(Enero);
                MV.setFebrero(Febrero);
                MV.setMarzo(Marzo);
                Busqueda.add(MV);
             }
            
            for (ClsVendedor Xs : Busqueda) {
                if (Xs.getEnero()==busqueda) {
                    Name=Xs.getNombre();
                    Mes="ENERO"; 
                    Respuesta=("El Nombre es: "+Name+" y el Mes de la Venta es: " +Mes);
                }
                if (Xs.getFebrero()==busqueda) {
                    Name=Xs.getNombre();
                    Mes="FEBRERO";     
                    Respuesta=("El Nombre es: "+Name+" y el Mes de la Venta es: " +Mes);
                }
                if (Xs.getMarzo()==busqueda) {
                    Name=Xs.getNombre();
                    Mes="MARZO";   
                    Respuesta=("El Nombre es: "+Name+" y el Mes de la Venta es: " +Mes);
                }
                
            } 
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            Clsconexion.CloseConnection(conexion);
            Clsconexion.CloseResul(resultSet);
            Clsconexion.CloseStatement(statement);
        }
        return Respuesta;
    }
    
    public int Modificar(ClsVendedor vendedor){
        int rows=0;
        Connection conexion=null;
        PreparedStatement statement=null;
        try {
            conexion=Clsconexion.getConnection();
            statement=conexion.prepareStatement(SQL_UPDATE);
            statement.setString(1, vendedor.getNombre());
            statement.setDouble(2, vendedor.getEnero());
            statement.setDouble(3, vendedor.getFebrero());
            statement.setDouble(4, vendedor.getMarzo());
            statement.setInt(7, vendedor.getCodigo());
            rows=statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Clsconexion.CloseConnection(conexion);
            Clsconexion.CloseStatement(statement);
        }
        return rows;
    }

    private void setCodigo(int Codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setNombre(String Nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setEnero(Double Enero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setFebrero(Double Febrero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setMarzo(Double Marzo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double getEnero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double getFebrero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double getMarzo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getCodigo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
