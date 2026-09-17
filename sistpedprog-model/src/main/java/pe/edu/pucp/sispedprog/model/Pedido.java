package pe.edu.pucp.sispedprog.model;
import pe.edu.pucp.sispedprog.model.interfaces.Imprimible;

import java.util.Date;
import java.util.List;

public class Pedido implements Imprimible {

    private int numero;
    private Date fecha;
    private Cliente cliente;
    private List<DetallePedido> detalles;
    private String estado;

    public Pedido(int numero,
                  Date fecha,
                  Cliente cliente,
                  List<DetallePedido> detalles,
                  String estado) {
        this.numero = numero;
        this.fecha = fecha;
        this.cliente = cliente;
        this.detalles = detalles;
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public double calcularTotal() {
        double total = 0;

        for (DetallePedido detalle : detalles) {
            total += detalle.calcularSubtotal();
        }

        return total;
    }

    @Override
    public String devolverDatos() {
        String datos = "";
        datos += "PEDIDO N° " + numero + "\n";
        datos += "Cliente: " + cliente.getNombreCompleto() + "\n";
        datos += "Fecha: " + fecha + "\n";
        datos += "--------------------------------\n";
        for (DetallePedido detalle : detalles) {
            datos += detalle.getProducto().devolverDatos() + "\n";
            datos += "Cantidad: " + detalle.getCantidad() + "\n";
            datos += "Subtotal: S/ " + detalle.calcularSubtotal() + "\n";
            datos += "--------------------------------\n";
        }
        datos += "TOTAL: S/ " + calcularTotal();
        return datos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
}
