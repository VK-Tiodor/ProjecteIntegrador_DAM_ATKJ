package modelo.hibernate;
// Generated 08-feb-2018 19:11:36 by Hibernate Tools 4.3.1



/**
 * TareasPendientesId generated by hbm2java
 */
public class TareasPendientesId  implements java.io.Serializable {


     private int idTarea;
     private int idAsistente;
     private int idDependiente;

    public TareasPendientesId() {
    }

    public TareasPendientesId(int idTarea, int idAsistente, int idDependiente) {
       this.idTarea = idTarea;
       this.idAsistente = idAsistente;
       this.idDependiente = idDependiente;
    }
   
    public int getIdTarea() {
        return this.idTarea;
    }
    
    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }
    public int getIdAsistente() {
        return this.idAsistente;
    }
    
    public void setIdAsistente(int idAsistente) {
        this.idAsistente = idAsistente;
    }
    public int getIdDependiente() {
        return this.idDependiente;
    }
    
    public void setIdDependiente(int idDependiente) {
        this.idDependiente = idDependiente;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TareasPendientesId) ) return false;
		 TareasPendientesId castOther = ( TareasPendientesId ) other; 
         
		 return (this.getIdTarea()==castOther.getIdTarea())
 && (this.getIdAsistente()==castOther.getIdAsistente())
 && (this.getIdDependiente()==castOther.getIdDependiente());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdTarea();
         result = 37 * result + this.getIdAsistente();
         result = 37 * result + this.getIdDependiente();
         return result;
   }   


}

