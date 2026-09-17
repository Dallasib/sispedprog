package pe.edu.pucp.sispedprog.model;

public class Cliente {
    private int codigo;
    /*Atributos*/
    private String dni;
    private String nombres;
    private String apellidoPaterno;

    /*Métodos*/
    public Cliente(){

    }

    public Cliente(String dni, String nombres, String apellidoPaterno){
        this.dni = dni;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getNombreCompleto() {
        return nombres + " " + apellidoPaterno;
    }
}
