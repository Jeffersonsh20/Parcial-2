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
public class ClsVendedores {
    private int codig;
    private String Nom;
    private double Ene;
    private double febre;
    private double Marz;

    @Override
    public String toString() {
        return "Clsvender{" + "codigo=" + codig + ", Nombre=" + Nom + ", Enero=" + Ene + ", febrero=" + febre + ", Marzo=" + Marz + '}';
    }

    public int getCodigo() {
        return codig;
    }

    public void setCodigo(int codigo) {
        this.codig = codigo;
    }

    public String getNombre() {
        return Nom;
    }

    public void setNombre(String Nombre) {
        this.Nom = Nombre;
    }

    public double getEnero() {
        return Ene;
    }

    public void setEnero(double Enero) {
        this.Ene = Enero;
    }

    public double getFebrero() {
        return febre;
    }

    public void setFebrero(double febrero) {
        this.febre = febrero;
    }

    public double getMarzo() {
        return Marz;
    }

    public void setMarzo(double Marzo) {
        this.Marz = Marzo;
    }
}
