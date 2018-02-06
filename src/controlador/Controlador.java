/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.conexion.Conexion;
import vista.JFramePantallaPrincipal;
import vista.Login;

/**
 *
 * @author vesprada
 */
public class Controlador {

    private Login ventanaLogin;
    private Conexion conexion;
    
    private JFrame ventanaActual;
    
    
    public Controlador() {
        this.ventanaLogin = new Login(this);
        this.conexion = new Conexion(this);
        cambiaVentana(null, this.ventanaLogin);
    }
    
    public void login(String user, String pass) { //presiona boton de login
        if (this.conexion.login(user, pass)) {
            cambiaVentana(ventanaActual, new JFramePantallaPrincipal(this, conexion));
        }
    }

    private void cambiaVentana(JFrame antigua, JFrame nueva) {
        if (antigua != null) {
            antigua.dispose();
        }
        if (nueva != null) {
            nueva.pack();
            nueva.setLocationRelativeTo(null);
            nueva.setVisible(true);
            ventanaActual = nueva;
        }
        
    }


    public void rellenaTabla(JTable tabla, ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd;
        DefaultTableModel dtm = new DefaultTableModel();
        //Creamos el objeto statement y obtenemos el ResultSetMetaData

        rsmd = rs.getMetaData();
        int numCols = rsmd.getColumnCount();
        //vaciarTabla

        for (int col = 1; col <= numCols; col++) {
            //taResultado.append(rsmd.getColumnName(col)+"\t");
            dtm.addColumn(rsmd.getColumnName(col));
        }
        while(rs.next()) {
            Object[] obj = new Object[numCols];
            for(int col=0; col<numCols; col++) {
                obj[col] = (rs.getObject(col+1).toString());

            }
            dtm.addRow(obj);
        }
        tabla.setModel(dtm);
    }

    public void rellenaTablaAgenda(JTable tablaAgenda) throws SQLException{
        ResultSet rsAgenda = this.conexion.getResultSetAgenda("Clase Asistente"); //TODO 
        rellenaTabla(tablaAgenda,rsAgenda);
            
    }

    
    public void rellenaTablaHistorialLlamadas(JTable jTableHistorialLlamadas) throws SQLException {
        ResultSet rsHistorialLLamadas = this.conexion.getResultSetHistorialLLamadas(); //TODO 
        rellenaTabla(jTableHistorialLlamadas,rsHistorialLLamadas);
    }
    public void rellenaTablaListaDependiente(JTable jTableListaDependientes) throws SQLException {
        ResultSet rsListaDependientes = this.conexion.getResultSetListaDependientes("Clase Asistente"); //TODO 
        rellenaTabla(jTableListaDependientes,rsListaDependientes);
    }


    
}
