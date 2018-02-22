package hibernate;
// Generated 12-feb-2018 18:52:59 by Hibernate Tools 4.3.1

/**
 * ContactoHasDependiente generated by hbm2java
 */
public class ContactoHasDependiente implements java.io.Serializable {

    private ContactoHasDependienteId id;
    private Contacto contacto;
    private Dependiente dependiente;
    private String relacion;
    private Boolean llave;

    public ContactoHasDependiente() {
    }

    public ContactoHasDependiente(ContactoHasDependienteId id, Contacto contacto, Dependiente dependiente) {
        this.id = id;
        this.contacto = contacto;
        this.dependiente = dependiente;
    }

    public ContactoHasDependiente(ContactoHasDependienteId id, Contacto contacto, Dependiente dependiente, String relacion, Boolean llave) {
        this.id = id;
        this.contacto = contacto;
        this.dependiente = dependiente;
        this.relacion = relacion;
        this.llave = llave;
    }

    public ContactoHasDependienteId getId() {
        return this.id;
    }

    public void setId(ContactoHasDependienteId id) {
        this.id = id;
    }

    public Contacto getContacto() {
        return this.contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Dependiente getDependiente() {
        return this.dependiente;
    }

    public void setDependiente(Dependiente dependiente) {
        this.dependiente = dependiente;
    }

    public String getRelacion() {
        return this.relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public Boolean getLlave() {
        return this.llave;
    }

    public void setLlave(Boolean llave) {
        this.llave = llave;
    }

    public Object[] getContactoForTable() {
        return new Object[]{ this, this.getContacto().getPersonas().getDni(), this.relacion, this.llave ? "Si" : "No"};
    }

    public String toString() {
        return this.getContacto().getPersonas().toString();
    }

}
