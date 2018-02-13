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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.conexion.Conexion;
import vista.JFramePantallaPrincipal;


/**
 *
 * @author vesprada
 */
public class Controlador {

    private final Conexion conexion;

    private JFrame ventanaActual;
    private final JFramePantallaPrincipal pantallaPrincipal;
    private ArrayList<Dependiente> listaDependientes;

    public Controlador() {
        this.conexion = new Conexion(this);
        this.listaDependientes = this.conexion.getDependientes();
         pantallaPrincipal = new JFramePantallaPrincipal(this, conexion);
            cambiaVentana(pantallaPrincipal);
    }

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

        for (Dependiente dependiente : this.listaDependientes) {
            model.addRow(dependiente.getDependienteForTable());
        }

        jTableListaDependientes.setModel(model);
    }

    public void lanzaAlerta(int id) {
        //TODO
        System.out.println("Llamada recibida del dependiente " + id);
        pantallaPrincipal.abreDialogAlerta(id);

    }

}
