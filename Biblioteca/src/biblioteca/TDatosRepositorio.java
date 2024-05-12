/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;

import java.io.Serializable;

/**
 *
 * @author Angel
 */
public class TDatosRepositorio implements Serializable{
    
    
    private String nombre, direccion;
    private int nLibros;

    public TDatosRepositorio() {
    }

    
    public TDatosRepositorio(String nombre, String direccion, int nLibros) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.nLibros = nLibros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getnLibros() {
        return nLibros;
    }

    public void setnLibros(int nLibros) {
        this.nLibros = nLibros;
    }
    
    public void aumentarNLibros(){
        nLibros++;
    }
    
    
}
