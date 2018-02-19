package hibernate;
// Generated 12-feb-2018 18:52:59 by Hibernate Tools 4.3.1

import controlador.Controlador;
import javax.swing.table.DefaultTableModel;




/**
 * Telefonos generated by hbm2java
 */
public class Telefonos  implements java.io.Serializable {


     private Integer idTelefonos;
     private Personas personas;
     private String numero;
     private String tipo;

    public Telefonos() {
    }

	
    public Telefonos(Personas personas) {
        this.personas = personas;
    }
    public Telefonos(Personas personas, String numero, String tipo) {
       this.personas = personas;
       this.numero = numero;
       this.tipo = tipo;
    }
   
    public Integer getIdTelefonos() {
        return this.idTelefonos;
    }
    
    public void setIdTelefonos(Integer idTelefonos) {
        this.idTelefonos = idTelefonos;
    }
    public Personas getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Personas personas) {
        this.personas = personas;
    }
    public String getNumero() {
        return this.numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
     public Object[] getTelefonoForTable() {
        return new Object[]{this.tipo,this};
    }

    public static void setColumns(DefaultTableModel model) {
        model.addColumn("Tipo");
        model.addColumn("Numero");
    }

    @Override
    public String toString() {
        return this.numero;
    }
    



}


