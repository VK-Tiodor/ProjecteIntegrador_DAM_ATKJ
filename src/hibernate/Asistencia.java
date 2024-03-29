package hibernate;
// Generated 12-feb-2018 18:52:59 by Hibernate Tools 4.3.1


import controlador.Controlador;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 * Asistencia generated by hbm2java
 */
public class Asistencia  implements java.io.Serializable {


     private Integer idAsistencia;
     private Dependiente dependiente;
     private Date fecha;
     private String motivo;
     private String datosAsistencia;

    public Asistencia() {
    }

	
    public Asistencia(Dependiente dependiente) {
        this.dependiente = dependiente;
    }
    public Asistencia(Dependiente dependiente, Date fecha, String motivo, String datosAsistencia) {
       this.dependiente = dependiente;
       this.fecha = fecha;
       this.motivo = motivo;
       this.datosAsistencia = datosAsistencia;
    }
   
    public Integer getIdAsistencia() {
        return this.idAsistencia;
    }
    
    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }
    public Dependiente getDependiente() {
        return this.dependiente;
    }
    
    public void setDependiente(Dependiente dependiente) {
        this.dependiente = dependiente;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getMotivo() {
        return this.motivo;
    }
    
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public String getDatosAsistencia() {
        return this.datosAsistencia;
    }
    
    public void setDatosAsistencia(String datosAsistencia) {
        this.datosAsistencia = datosAsistencia;
    }
    
    public Object[] getAsistenciaForTable() {
        return new Object[]{this, Controlador.formateaFecha(this.fecha), this.motivo, this.datosAsistencia};
    }

    public static void setColumns(DefaultTableModel model) {
        model.addColumn("Dependiente");
        model.addColumn("Fecha");
        model.addColumn("Motivo");
        model.addColumn("Datos Asistencia");
    }
    
    public String toString(){
        return this.getDependiente().getPersonas().getNombre() + " " + this.getDependiente().getPersonas().getApellidos();
    }
    
}


