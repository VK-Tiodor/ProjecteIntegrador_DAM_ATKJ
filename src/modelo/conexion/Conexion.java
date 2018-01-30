/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.conexion;

import controlador.Controlador;

/**
 *
 * @author Juan Carlos Blanco
 */
public class Conexion {
    private Controlador controlador;
    
    public Conexion(Controlador controlador){
        this.controlador = controlador;
    }
    
   public boolean login(String userName, String password){
       return true;
   }
}
