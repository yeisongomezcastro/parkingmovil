package dgpragmatic.co.com.personas.domain;

public class Persona {

    private Integer id;
    private String nombre;
    private String apellido;
    private Boolean borrar;

    public Persona(Integer id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.borrar=Boolean.FALSE;
    }

    public Persona() {
        this.borrar=Boolean.FALSE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setBorrar(Boolean borrar){
        this.borrar=borrar;
    }

    public Boolean isBorrar() {
        return borrar;
    }
}
