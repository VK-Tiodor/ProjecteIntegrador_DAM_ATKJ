package modelo.hibernate;
// Generated 08-feb-2018 19:11:36 by Hibernate Tools 4.3.1



/**
 * Aviso generated by hbm2java
 */
public class Aviso  implements java.io.Serializable {


     private AvisoId id;
     private Dependiente dependiente;
     private String hora;

    public Aviso() {
    }

	
    public Aviso(AvisoId id, Dependiente dependiente) {
        this.id = id;
        this.dependiente = dependiente;
    }
    public Aviso(AvisoId id, Dependiente dependiente, String hora) {
       this.id = id;
       this.dependiente = dependiente;
       this.hora = hora;
    }
   
    public AvisoId getId() {
        return this.id;
    }
    
    public void setId(AvisoId id) {
        this.id = id;
    }
    public Dependiente getDependiente() {
        return this.dependiente;
    }
    
    public void setDependiente(Dependiente dependiente) {
        this.dependiente = dependiente;
    }
    public String getHora() {
        return this.hora;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }




}

