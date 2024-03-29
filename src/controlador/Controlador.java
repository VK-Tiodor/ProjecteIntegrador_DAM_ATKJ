/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import hibernate.Asistencia;
import hibernate.Contacto;
import hibernate.ContactoHasDependiente;
import hibernate.ContactoHasDependienteId;
import hibernate.Dependiente;
import hibernate.DependienteHasMedicacion;
import hibernate.DependienteHasMedicacionId;
import hibernate.Medicacion;
import hibernate.Personas;
import hibernate.Poblacion;
import hibernate.RecursosLocalidad;
import hibernate.TareasPendientes;
import hibernate.Telefonos;
import hibernate.Vivienda;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
    public final double alturaDePantalla;
    public final double anchuraDePantalla;

    // Constructor
    public Controlador() {
        this.conexion = new Conexion(this);
        this.listaDependientes = this.conexion.getDependientes();
        this.listaAsistencias = this.conexion.getAsistencias();
        this.listaTareasPendientes = this.conexion.getTareasPendientes();
        pantallaPrincipal = new JFramePantallaPrincipal(this, conexion);
        abreFrame(pantallaPrincipal);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        Dimension tamanyo = gd.getDefaultConfiguration().getBounds().getSize();
        alturaDePantalla = tamanyo.getHeight();
        anchuraDePantalla = tamanyo.getWidth();

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
            nueva.setExtendedState(JFrame.MAXIMIZED_BOTH);
            nueva.setVisible(true);
        }
    }

    public void abreDialog(JDialog dialog, boolean modal) {
        dialog.pack();
        dialog.setVisible(true);
        dialog.setModal(modal);
        dialog.setLocationRelativeTo(null);
    }

    public void actualizaTablaAgenda() {
        rellenaTablaAgenda(pantallaPrincipal.getjTableAgenda());
    }

    /**
     * Modelo de tabla para evitar que se puedan modificar las celdas
     */
    public class MiModelo extends DefaultTableModel {

        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    public void rellenaTablaAgenda(JTable tablaAgenda) {

        DefaultTableModel model = new MiModelo();
        TareasPendientes.setColumns(model);

        for (TareasPendientes tareasPendiente : this.conexion.getTareasPendientes()) {
            if (!tareasPendiente.getRealizada()) {
                model.addRow(tareasPendiente.getTareaPendienteForTable());
            }
        }

        tablaAgenda.setModel(model);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        tablaAgenda.setRowSorter(sorter);
        tablaAgenda.getRowSorter().toggleSortOrder(1);

        centraTabla(tablaAgenda);

    }

    public void rellenaTablaAgendaHistorial(JTable tablaHistorial) {

        DefaultTableModel model = new MiModelo();
        TareasPendientes.setColumns(model);

        for (TareasPendientes tareasPendiente : this.conexion.getTareasPendientes()) {
            if (tareasPendiente.getRealizada()) {
                model.addRow(tareasPendiente.getTareaPendienteForTable());
            }
        }

        tablaHistorial.setModel(model);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        tablaHistorial.setRowSorter(sorter);
        tablaHistorial.getRowSorter().toggleSortOrder(1);

        centraTabla(tablaHistorial);

    }

    public void rellenaTablaHistorialLlamadas(JTable jTableHistorialLlamadas) {
        DefaultTableModel model = new MiModelo();
        Asistencia.setColumns(model);

        for (Asistencia asistencia : this.conexion.getAsistencias()) {
            model.addRow(asistencia.getAsistenciaForTable());
        }

        jTableHistorialLlamadas.setModel(model);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        jTableHistorialLlamadas.setRowSorter(sorter);
        jTableHistorialLlamadas.getRowSorter().toggleSortOrder(1);

        centraTabla(jTableHistorialLlamadas);
    }

    public void rellenaTablaListaDependiente(JTable jTableListaDependientes) {
        DefaultTableModel model = new MiModelo();
        Dependiente.setColumns(model);

        for (Dependiente dependiente : this.listaDependientes) {
            model.addRow(dependiente.getDependienteForTable());
        }

        jTableListaDependientes.setModel(model);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        jTableListaDependientes.setRowSorter(sorter);
        jTableListaDependientes.getRowSorter().toggleSortOrder(0);

        centraTabla(jTableListaDependientes);
    }

    public void rellenaTablaContactosDependiente(JTable jTableContactosDependiente, Dependiente dependiente) {
        DefaultTableModel model = new MiModelo();
        Contacto.setColumns(model);

        if (dependiente.getContactoHasDependientes() != null) {
            //DefaultTableModel tablaContactos = (DefaultTableModel)jTableContactosDependiente.getModel();
            for (Object object : dependiente.getContactoHasDependientes().toArray()) {
                ContactoHasDependiente contactoHasDependiente = (ContactoHasDependiente) object;
                model.addRow(contactoHasDependiente.getContactoForTable());
            }
        }

        jTableContactosDependiente.setModel(model);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        jTableContactosDependiente.setRowSorter(sorter);
        jTableContactosDependiente.getRowSorter().toggleSortOrder(0);

        centraTabla(jTableContactosDependiente);
    }

    public void rellenaTablaMedicacionDependiente(JTable jTableMedicacionDependiente, Dependiente dependiente) {
        DefaultTableModel model = new MiModelo();
        Medicacion.setColumns(model);

        if (dependiente.getDependienteHasMedicacions() != null) {
            //DefaultTableModel tablaContactos = (DefaultTableModel)jTableContactosDependiente.getModel();
            for (Object object : dependiente.getDependienteHasMedicacions().toArray()) {
                DependienteHasMedicacion dependienteHasMedicacion = (DependienteHasMedicacion) object;
                model.addRow(dependienteHasMedicacion.getMedicacionForTable());
            }
        }
        jTableMedicacionDependiente.setModel(model);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        jTableMedicacionDependiente.setRowSorter(sorter);
        jTableMedicacionDependiente.getRowSorter().toggleSortOrder(0);

        centraTabla(jTableMedicacionDependiente);
    }

    public void rellenaTablaViviendaDependiente(JTable jTableViviendasDependiente, Dependiente dependiente) {
        DefaultTableModel model = new MiModelo();
        Vivienda.setColumns(model);

        if (dependiente.getPersonas().getViviendas() != null) {
            //DefaultTableModel tablaContactos = (DefaultTableModel)jTableContactosDependiente.getModel();
            for (Object object : dependiente.getPersonas().getViviendas().toArray()) {
                Vivienda vivienda = (Vivienda) object;
                model.addRow(vivienda.getViviendaForTable());
            }
        }

        jTableViviendasDependiente.setModel(model);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        jTableViviendasDependiente.setRowSorter(sorter);
        jTableViviendasDependiente.getRowSorter().toggleSortOrder(0);

        centraTabla(jTableViviendasDependiente);
    }

    public void rellenaListRecursos(JList listaRecursos, Vivienda vivienda) {

        listaRecursos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = vivienda.getPoblacion().getRecursosLocalidad().returnRecursosForList();

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
    }

    public void rellenaTablaMedicacionDependiente(JTable jTableAddMedicinas) {
        DefaultTableModel model = new MiModelo();
        Medicacion.setSimpleColumns(model);
        ArrayList<Medicacion> medicinas = this.conexion.getMedicinas();
        for (Medicacion medicina : medicinas) {
            model.addRow(medicina.getMedicinaForSimpleTable());
        }

        jTableAddMedicinas.setModel(model);

        centraTabla(jTableAddMedicinas);

    }

    public void rellenaTablaPoblaciones(JTable jTablePoblacionesCrearVivienda) {
        DefaultTableModel model = new MiModelo();
        Poblacion.setColumns(model);
        ArrayList<Poblacion> poblaciones = this.conexion.getPoblaciones();
        for (Poblacion poblacion : poblaciones) {
            model.addRow(poblacion.getPoblacionForTable());
        }

        jTablePoblacionesCrearVivienda.setModel(model);

        centraTabla(jTablePoblacionesCrearVivienda);
    }

    public void centraTabla(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tabla.getColumnModel().getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public void lanzaAlerta(String id, String longitud, String latitud) {
        pantallaPrincipal.abreDialogAlerta(id, longitud, latitud);
    }

    public void creaTarea(Dependiente dependiente, Date fecha, String hora, String encabezado, String descripcion, DefaultTableModel model) {
        TareasPendientes nuevaTarea = new TareasPendientes(dependiente, fecha, encabezado, descripcion);
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

    public void crearMedicina(Medicacion medicacion) {
        this.conexion.guardaMedicina(medicacion);
    }

    public void crearAsistencia(Asistencia asistencia) {
        this.conexion.guardaAsistencia(asistencia);
    }

    public void creaVivienda(Poblacion poblacion, String direccion, Dependiente dependiente, JTable tablaViviendas) {
        Vivienda vivienda = new Vivienda(poblacion);
        vivienda.setDireccion(direccion);
        vivienda.getPersonases().add(dependiente.getPersonas());
        dependiente.getPersonas().getViviendas().add(vivienda);

        DefaultTableModel dtm = (DefaultTableModel) tablaViviendas.getModel();
        dtm.addRow(vivienda.getViviendaForTable());
        this.conexion.guardaVivienda(vivienda);

    }

    public void borraTarea(TareasPendientes tarea) {
        this.listaTareasPendientes.remove(tarea);
        this.conexion.eliminaTareaPendiente(tarea);
    }

    public void borraTareaMedicacion(DependienteHasMedicacion medicacion, Dependiente dependiente) {
        for (int i = 0; i < this.listaTareasPendientes.size(); i++) {
            TareasPendientes tarea = this.listaTareasPendientes.get(i);
            if (tarea.getEncabezado().contains(medicacion.getMedicacion().toString()) && tarea.getDependiente() == dependiente && !tarea.getRealizada()) {
                this.listaTareasPendientes.remove(tarea);
                this.conexion.eliminaTareaPendiente(tarea);
            }
        }

        dependiente.getDependienteHasMedicacions().remove(medicacion);
        this.conexion.eliminaMedicacion(medicacion);

        actualizaTablaAgenda();
    }

    public void modificaTareaMedicacion(DependienteHasMedicacion medicacion) {
        for (int i = 0; i < this.listaTareasPendientes.size(); i++) {
            TareasPendientes tarea = this.listaTareasPendientes.get(i);
            if (tarea.getEncabezado().contains(medicacion.getMedicacion().toString()) && tarea.getDependiente() == medicacion.getDependiente() && !tarea.getRealizada()) {
                tarea.setHorasRepeticion(Double.parseDouble(medicacion.getToma()));
                tarea.setEncabezado(medicacion.getMedicacion().getNombre() + " " + medicacion.getCantidad());
                this.conexion.guardaTareaPendiente(tarea);
            }
        }

        actualizaTablaAgenda();
    }

    public void borraContactoHasDependiente(ContactoHasDependiente contactoHasDependiente, Dependiente dependiente) {
        dependiente.getContactoHasDependientes().remove(contactoHasDependiente.getContacto());
        dependiente.getContactoHasDependientes().remove(contactoHasDependiente);
        this.conexion.eliminaContactoHasDependiente(contactoHasDependiente);

    }

    public void borraVivienda(Vivienda vivienda, Dependiente dependiente) {
        dependiente.getPersonas().getViviendas().remove(vivienda);
        this.conexion.eliminaVivienda(vivienda);
    }

    public static Date parseaFecha(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("y-MM-d HH:mm");
        return dateFormat.parse(date);
    }

    public static String formateaFecha(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("y-MM-d HH:mm");
        return dateFormat.format(date);
    }

    public void guardaMedicinaEditada(DependienteHasMedicacion medicinaEditandonse) {
        this.conexion.guardaDependienteHasMedicacion(medicinaEditandonse);
    }

    public void crearTareaMedicina(Dependiente dependienteSeleccionado, Date time, String encabezado, String descripcion, Double horasRepeticion, Boolean tareaAsistente, Boolean realizada) {
        this.conexion.guardaTareaPendiente(new TareasPendientes(dependienteSeleccionado, time, encabezado, descripcion, horasRepeticion, tareaAsistente, realizada));
    }

}
