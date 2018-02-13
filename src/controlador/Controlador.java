/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import hibernate.Asistencia;
import hibernate.Dependiente;
import hibernate.TareasPendientes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.conexion.Conexion;
import vista.JFramePantallaPrincipal;

/**
 *
 * @author vesprada
 */
public class Controlador {

    // Atributos
    private JFrame ventanaActual;
    private final Conexion conexion;
    private final ArrayList<Dependiente> listaDependientes;
    private final ArrayList<Asistencia> listaAsistencias;
    private final ArrayList<TareasPendientes> listaTareasPendientes;
    private final JFramePantallaPrincipal pantallaPrincipal;

    // Constructor
    public Controlador() {
        this.conexion = new Conexion(this);
        this.listaDependientes = this.conexion.getDependientes();
        this.listaAsistencias = this.conexion.getAsistencias();
        this.listaTareasPendientes = this.conexion.getTareasPendientes();
        pantallaPrincipal = new JFramePantallaPrincipal(this, conexion);
        cambiaVentana(pantallaPrincipal);
    }

    // Getters y Setters
    public ArrayList<Dependiente> getListaDependientes() {
        return listaDependientes;
    }

    public Conexion getConexion() {
        return conexion;
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

    public void rellenaTablaAgenda(JTable tablaAgenda) {
        DefaultTableModel model = new DefaultTableModel();
        TareasPendientes.setColumns(model);

        for (TareasPendientes tareasPendiente : this.conexion.getTareasPendientes()) {
            model.addRow(tareasPendiente.getTareaPendienteForTable());
        }

        tablaAgenda.setModel(model);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tablaAgenda.getColumnModel().getColumnCount(); i++) {
            tablaAgenda.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }

    }

    public void rellenaTablaHistorialLlamadas(JTable jTableHistorialLlamadas) {
        DefaultTableModel model = new DefaultTableModel();
        Asistencia.setColumns(model);

        for (Asistencia asistencia : this.conexion.getAsistencias()) {
            model.addRow(asistencia.getAsistenciaForTable());
        }

        jTableHistorialLlamadas.setModel(model);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < jTableHistorialLlamadas.getColumnModel().getColumnCount(); i++) {
            jTableHistorialLlamadas.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public void rellenaTablaListaDependiente(JTable jTableListaDependientes) {
        DefaultTableModel model = new DefaultTableModel();
        Dependiente.setColumns(model);

        for (Dependiente dependiente : this.listaDependientes) {
            model.addRow(dependiente.getDependienteForTable());
        }

        jTableListaDependientes.setModel(model);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < jTableListaDependientes.getColumnModel().getColumnCount(); i++) {
            jTableListaDependientes.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public void lanzaAlerta(String id) {
        pantallaPrincipal.abreDialogAlerta(id);
    }

    public void creaTarea(Dependiente dependiente, Calendar fecha, String hora, String encabezado, String descripcion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void crearContacto(String dni, String nombre, String apellidos, Date time, String genero, Dependiente dependienteSeleccionado, ArrayList<String[]> telefonos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void crearMedicina(String nombre, String toma, String cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void crearDependiente(String dni, String nombre, String apellidos, Calendar fechaNac, String genero, String tipo, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
