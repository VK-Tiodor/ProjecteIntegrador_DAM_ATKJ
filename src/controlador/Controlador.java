/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import hibernate.Asistencia;
import hibernate.Dependiente;
import hibernate.TareasPendientes;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
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

    private final Login ventanaLogin;
    private final Conexion conexion;

    private JFrame ventanaActual;
    private JFramePantallaPrincipal pantallaPrincipal;

    public Controlador() {
        this.ventanaLogin = new Login(this);
        this.conexion = new Conexion(this);
        cambiaVentana(this.ventanaLogin);
    }

    public void login(String user, String pass) { //presiona boton de login
        if (this.conexion.login(user, pass)) {
            pantallaPrincipal = new JFramePantallaPrincipal(this, conexion);
            cambiaVentana(pantallaPrincipal);
        }
    }

    public void cambiaVentana(JFrame nueva) {
        if (ventanaActual != null) {
            ventanaActual.dispose();
        }
        if (nueva != null) {
            nueva.pack();
            nueva.setLocationRelativeTo(null);
            nueva.setVisible(true);
            ventanaActual = nueva;
        }

    }

    public void abreFrame(JFrame nueva) {
        if (nueva != null) {
            nueva.pack();
            nueva.setLocationRelativeTo(null);
            nueva.setVisible(true);
        }
    }

    public void abreDialog(JDialog dialog, boolean modal) {
        dialog.pack();
        dialog.setVisible(true);
        dialog.setModal(modal);
        dialog.setLocationRelativeTo(null);
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
        while (rs.next()) {
            Object[] obj = new Object[numCols];
            for (int col = 0; col < numCols; col++) {
                obj[col] = (rs.getObject(col + 1).toString());

            }
            dtm.addRow(obj);
        }
        tabla.setModel(dtm);
    }

    public void rellenaTablaAgenda(JTable tablaAgenda) {
        DefaultTableModel model = new DefaultTableModel();

        TareasPendientes.setColumns(model);

        for (TareasPendientes tareasPendiente : this.conexion.getTareasPendientes()) {
            model.addRow(tareasPendiente.getTareaPendienteForTable());
        }

        tablaAgenda.setModel(model);

    }

    public void rellenaTablaHistorialLlamadas(JTable jTableHistorialLlamadas) {
        DefaultTableModel model = new DefaultTableModel();

        Asistencia.setColumns(model);

        for (Asistencia asistencia : this.conexion.getAsistencias()) {
            model.addRow(asistencia.getAsistenciaForTable());
        }

        jTableHistorialLlamadas.setModel(model);

    }

    public void rellenaTablaListaDependiente(JTable jTableListaDependientes)  {
        DefaultTableModel model = new DefaultTableModel();

        Dependiente.setColumns(model);

        for (Dependiente dependiente : this.conexion.getDependientes()) {
            model.addRow(dependiente.getDependienteForTable());
        }

        jTableListaDependientes.setModel(model);

    }

    public void lanzaAlerta(int id) {
        //TODO
        System.out.println("Llamada recibida del dependiente " + id);
        pantallaPrincipal.abreDialogAlerta(id);

    }

    public void creaTarea(Dependiente dependiente, Calendar fecha, String hora, String encabezado, String descripcion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void crearContacto(String dni, String nombre, String apellidos, Date time, String genero, Dependiente dependienteSeleccionado, ArrayList<String[]> telefonos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
