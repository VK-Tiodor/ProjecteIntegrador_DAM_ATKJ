/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import hibernate.Asistencia;
import hibernate.Contacto;
import hibernate.ContactoHasDependiente;
import hibernate.ContactoHasDependienteId;
import hibernate.Dependiente;
import hibernate.DependienteHasMedicacion;
import hibernate.DependienteHasMedicacionId;
import hibernate.Medicacion;
import hibernate.Personas;
import hibernate.RecursosLocalidad;
import hibernate.TareasPendientes;
import hibernate.Telefonos;
import hibernate.Vivienda;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
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

    public void rellenaTablaContactosDependiente(JTable jTableContactosDependiente, Dependiente dependiente) {
        DefaultTableModel model = new DefaultTableModel();
        Contacto.setColumns(model);

        if (dependiente.getContactoHasDependientes() != null) {
            //DefaultTableModel tablaContactos = (DefaultTableModel)jTableContactosDependiente.getModel();
            for (Object object : dependiente.getContactoHasDependientes().toArray()) {
                ContactoHasDependiente contactoHasDependiente = (ContactoHasDependiente) object;
                model.addRow(contactoHasDependiente.getContactoForTable());
            }
        }

        jTableContactosDependiente.setModel(model);
    }

    public void rellenaTablaMedicacionDependiente(JTable jTableMedicacionDependiente, Dependiente dependiente) {
        DefaultTableModel model = new DefaultTableModel();
        Medicacion.setColumns(model);

        if (dependiente.getDependienteHasMedicacions() != null) {
            //DefaultTableModel tablaContactos = (DefaultTableModel)jTableContactosDependiente.getModel();
            for (Object object : dependiente.getDependienteHasMedicacions().toArray()) {
                DependienteHasMedicacion dependienteHasMedicacion = (DependienteHasMedicacion) object;
                model.addRow(dependienteHasMedicacion.getMedicacionForTable());
            }
        }
        jTableMedicacionDependiente.setModel(model);
    }

    public void rellenaTablaViviendaDependiente(JTable jTableViviendasDependiente, Dependiente dependiente) {
        DefaultTableModel model = new DefaultTableModel();
        Vivienda.setColumns(model);

        if (dependiente.getPersonas().getViviendas() != null) {
            //DefaultTableModel tablaContactos = (DefaultTableModel)jTableContactosDependiente.getModel();
            for (Object object : dependiente.getPersonas().getViviendas().toArray()) {
                Vivienda vivienda = (Vivienda) object;
                model.addRow(vivienda.getViviendaForTable());
            }
        }

        jTableViviendasDependiente.setModel(model);
    }

    public void rellenaTablaRecursos(JTable jTableRecursosLocalidadDependiente, Vivienda vivienda) {
        DefaultTableModel model = new DefaultTableModel();
        RecursosLocalidad.setColumns(model);

        if (vivienda.getPoblacion().getRecursosLocalidad() != null) {
            //DefaultTableModel tablaContactos = (DefaultTableModel)jTableContactosDependiente.getModel();
            model.addRow(vivienda.getPoblacion().getRecursosLocalidad().getRecursosForTable());
        }

        jTableRecursosLocalidadDependiente.setModel(model);
    }
    
    public void rellenaTablaMedicacionDependiente(JTable jTableAddMedicinas) {
        DefaultTableModel model = new DefaultTableModel();
        Medicacion.setSimpleColumns(model);
        ArrayList<Medicacion> medicinas = this.conexion.getMedicinas();
        for (Medicacion medicina : medicinas) {
            model.addRow(medicina.getMedicinaForSimpleTable());
        }

        jTableAddMedicinas.setModel(model);
    }

    public void lanzaAlerta(String id) {
        pantallaPrincipal.abreDialogAlerta(id);
    }

    public void creaTarea(Dependiente dependiente, Date fecha, String hora, String encabezado, String descripcion, DefaultTableModel model) {
        TareasPendientes nuevaTarea = new TareasPendientes(dependiente,fecha, hora, encabezado, descripcion);
        this.listaTareasPendientes.add(nuevaTarea);
        this.conexion.guardaTareaPendiente(nuevaTarea);
        model.addRow(this.listaTareasPendientes.get(this.listaTareasPendientes.size() - 1).getTareaPendienteForTable());
    }

    public void crearContacto(String dni, String nombre, String apellidos, Date fechaNac, String genero, String relacion, boolean llave, Dependiente dependiente, Set telefonos, JTable jTableContactosDependiente) {
        Personas p = new Personas(dni, nombre, apellidos, fechaNac, genero, null, null, (Set) telefonos, null, null);
        Contacto c = new Contacto(p);
        p.setContacto(c);
        this.conexion.guardaPersona(p);
        ArrayList<Telefonos> arrayTelefonos = new ArrayList<>(p.getTelefonoses());
        for (Telefonos telefono : arrayTelefonos) {
            telefono.setPersonas(p);
            this.conexion.guardaTelefono(telefono);
        }
        this.conexion.guardaContacto(c);
        ContactoHasDependienteId chdi = new ContactoHasDependienteId(c.getIdContacto(), dependiente.getIdDependiente());
        ContactoHasDependiente chd = new ContactoHasDependiente(chdi, c, dependiente, relacion, llave);
        c.getContactoHasDependientes().add(chd);
        dependiente.getContactoHasDependientes().add(chd);
        this.conexion.guardaContacto(c);
        this.conexion.guardaContactoHasDependiente(chd);
       
    }
    
    public void borraTarea(TareasPendientes tarea){
        this.listaTareasPendientes.remove(tarea);
        this.conexion.eliminaTareaPendiente(tarea);
    }
    
    public void borraMedicacion(DependienteHasMedicacion medicacion, Dependiente dependiente){
        dependiente.getDependienteHasMedicacions().remove(medicacion);
        this.conexion.eliminaMedicacion(medicacion);
    }
    

    public void borraContactoHasDependiente(ContactoHasDependiente contactoHasDependiente, Dependiente dependiente) {
        dependiente.getContactoHasDependientes().remove(contactoHasDependiente.getContacto());
        dependiente.getContactoHasDependientes().remove(contactoHasDependiente);
        this.conexion.eliminaContactoHasDependiente(contactoHasDependiente);
        
    }

    public void crearMedicacionDependiente(Medicacion medicina, String toma, Double cantidad, Dependiente dependiente) {
        DependienteHasMedicacionId dhmid = new DependienteHasMedicacionId(dependiente.getIdDependiente(), medicina.getIdMedicacion());
        DependienteHasMedicacion dhm = new DependienteHasMedicacion(dhmid, dependiente, medicina, toma, cantidad);
        this.conexion.guardaDependienteHasMedicacion(dhm);
        dependiente.getDependienteHasMedicacions().add(dhm);
        medicina.getDependienteHasMedicacions().add(dhm);
        
    }

    public void crearDependiente(String dni, String nombre, String apellidos, Calendar fechaNac, String genero, String tipo, String pass, DefaultTableModel tabla) {
        Personas p = new Personas(dni, nombre, apellidos, fechaNac.getTime(), genero, null, null, null, null, null);
        Dependiente d = new Dependiente(p, tipo, Calendar.getInstance().getTime(), pass, null, null, null, null, null, null);
        p.setDependiente(d);
        this.listaDependientes.add(d);
        tabla.addRow(d.getDependienteForTable());
        this.getConexion().guardaDependiente(d);
        
    }
    
       public static String formateaFecha(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("y-MM-d");
        return dateFormat.format(date);
    }

    public void crearMedicina(Medicacion medicacion) {
        this.conexion.guardaMedicina(medicacion);
    }

    

   


   
        

}
