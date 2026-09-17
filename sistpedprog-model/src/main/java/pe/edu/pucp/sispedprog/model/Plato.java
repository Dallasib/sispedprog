package pe.edu.pucp.sispedprog.model;

public class Plato extends Producto{
    private String categoria;

    public Plato(int codigo, String nombre, double precioBase, String categoria){
        super(codigo,nombre,precioBase); //Invocamos al constructor de la clase padre
        this.categoria = categoria;
    }

    public Plato(String nombre, double precioBase, String categoria){
        super(nombre,precioBase); //Invocamos al constructor de la clase padre
        this.categoria = categoria;
    }

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    @Override
    public String devolverDatos(){
        return "Plato: " + getCodigo() + "|" + getNombre() + "|" + getPrecioBase() + "|" + getCategoria();
    }
}
