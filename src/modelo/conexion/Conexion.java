/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.conexion;

import controlador.Controlador;
import hibernate.Asistencia;
import hibernate.Dependiente;
import hibernate.HibernateUtil;
import hibernate.TareasPendientes;
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

}
