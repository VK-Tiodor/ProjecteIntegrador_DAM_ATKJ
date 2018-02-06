/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.conexion;

import controlador.Controlador;
import java.sql.ResultSet;

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
    
    /**
     * Metodo para conseguir el Result set de las tareas pendientes de un asistente.
     * Tiene que devolver: Dependiente, fecha, hora, encabezado y descripcion 
     * 
     * @param clase_Asistente tiene que ser Asistente en vez de string pero aun no está
     * @return 
     * 
     */
    public ResultSet getResultSetAgenda(String clase_Asistente) {
        
        return null;
    }

    public ResultSet getResultSetHistorialLLamadas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResultSet getResultSetListaDependientes(String clase_Asistente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
