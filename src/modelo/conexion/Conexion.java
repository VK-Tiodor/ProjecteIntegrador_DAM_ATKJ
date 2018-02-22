/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.conexion;

import controlador.Controlador;
import hibernate.Asistencia;
import hibernate.Contacto;
import hibernate.ContactoHasDependiente;
import hibernate.Dependiente;
import hibernate.DependienteHasMedicacion;
import hibernate.HibernateUtil;
import hibernate.Medicacion;
import hibernate.Personas;
import hibernate.Poblacion;
import hibernate.TareasPendientes;
import hibernate.Telefonos;
import hibernate.Vivienda;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Juan Carlos Blanco
 */
public class Conexion {

    private final Controlador controlador;
    private final Session sessionHibernate;

    public Conexion(Controlador controlador) {
        this.controlador = controlador;
        this.sessionHibernate = HibernateUtil.getSessionFactory().openSession();
    }

    public void cierraConexion() {
        this.sessionHibernate.close();
    }

    /**
     * Metodo para conseguir el Result set de las tareas pendientes de un
     * asistente. Tiene que devolver: Dependiente, fecha, hora, encabezado y
     * descripcion
     *
     * @param clase_Asistente tiene que ser Asistente en vez de string pero aun
     * no está
     * @return
     *
     */
    public ResultSet getResultSetAgenda(String clase_Asistente) {

        return null;
    }

    public ArrayList<TareasPendientes> getTareasPendientes() {
        this.sessionHibernate.beginTransaction();
        ArrayList<TareasPendientes> tareasPendientes = (ArrayList<TareasPendientes>) this.sessionHibernate.createQuery("from TareasPendientes").list();
        this.sessionHibernate.getTransaction().commit();
        return tareasPendientes;
    }

    public ArrayList<Asistencia> getAsistencias() {
        this.sessionHibernate.beginTransaction();
        ArrayList<Asistencia> asistencia = (ArrayList<Asistencia>) this.sessionHibernate.createQuery("from Asistencia").list();
        this.sessionHibernate.getTransaction().commit();
        return asistencia;
    }

    public ArrayList<Dependiente> getDependientes() {
        this.sessionHibernate.beginTransaction();
        ArrayList<Dependiente> dependiente = (ArrayList<Dependiente>) this.sessionHibernate.createQuery("from Dependiente").list();
        this.sessionHibernate.getTransaction().commit();
        return dependiente;
    }

    public Dependiente getDependienteById(String id) {
        this.sessionHibernate.beginTransaction();
        List<Dependiente> dependiente = (List<Dependiente>) this.sessionHibernate.createQuery("FROM Dependiente d WHERE idDependiente=" + id).list();
        this.sessionHibernate.getTransaction().commit();
        return dependiente.get(0);
    }
    public ArrayList<Medicacion> getMedicinas() {
        this.sessionHibernate.beginTransaction();
        ArrayList<Medicacion> medicaciones = (ArrayList<Medicacion>) this.sessionHibernate.createQuery("from Medicacion").list();
        this.sessionHibernate.getTransaction().commit();
        return medicaciones;
    }
    public ArrayList<Poblacion> getPoblaciones() {
        this.sessionHibernate.beginTransaction();
        ArrayList<Poblacion> poblaciones = (ArrayList<Poblacion>) this.sessionHibernate.createQuery("from Poblacion").list();
        this.sessionHibernate.getTransaction().commit();
        return poblaciones;
    }

    public void guardaDependiente(Dependiente dependiente) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.saveOrUpdate(dependiente);
        this.sessionHibernate.getTransaction().commit();
    }

    public void guardaAsistencia(Asistencia asistencia) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.saveOrUpdate(asistencia);
        this.sessionHibernate.getTransaction().commit();
    }

    public void guardaTareaPendiente(TareasPendientes tareaPendiente) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.saveOrUpdate(tareaPendiente);
        this.sessionHibernate.getTransaction().commit();
    }
    
    public void guardaContacto(Contacto contacto) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.saveOrUpdate(contacto);
        this.sessionHibernate.getTransaction().commit();
    }
    
    public void guardaContactoHasDependiente(ContactoHasDependiente chd) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.saveOrUpdate(chd);
        this.sessionHibernate.getTransaction().commit();
    }
    public void guardaPersona(Personas p) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.saveOrUpdate(p);
        this.sessionHibernate.getTransaction().commit();
    }
    
    
    public void guardaTelefono(Telefonos telefono) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.saveOrUpdate(telefono);
        this.sessionHibernate.getTransaction().commit();
    }
    public void guardaMedicina(Medicacion medicacion) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.saveOrUpdate(medicacion);
        this.sessionHibernate.getTransaction().commit();
    }
    
    public void guardaDependienteHasMedicacion(DependienteHasMedicacion dhm) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.saveOrUpdate(dhm);
        this.sessionHibernate.getTransaction().commit();
    }
    public void guardaVivienda(Vivienda vivienda) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.saveOrUpdate(vivienda);
        this.sessionHibernate.getTransaction().commit();
    }
    public void eliminaTareaPendiente(TareasPendientes tareaPendiente){
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.delete(tareaPendiente);
        this.sessionHibernate.getTransaction().commit();
    }

    public void eliminaDependiente(Dependiente dependiente) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.delete(dependiente);
        this.sessionHibernate.getTransaction().commit();
    }

    public void eliminaMedicacion(DependienteHasMedicacion medicacion) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.delete(medicacion);
        this.sessionHibernate.getTransaction().commit();
    }

    public void eliminaContactoHasDependiente(ContactoHasDependiente contactoHasDependiente) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.delete(contactoHasDependiente);
        this.sessionHibernate.getTransaction().commit();
    }

    public void eliminaPersona(Personas persona){
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.delete(persona);
        this.sessionHibernate.getTransaction().commit();
    }

    public void eliminaVivienda(Vivienda vivienda) {
        this.sessionHibernate.beginTransaction();
        this.sessionHibernate.delete(vivienda);
        this.sessionHibernate.getTransaction().commit();
    }

    

    

    


    

    





}
