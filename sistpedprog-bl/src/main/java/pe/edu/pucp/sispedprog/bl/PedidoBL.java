package pe.edu.pucp.sispedprog.bl;

import pe.edu.pucp.sispedprog.bl.exception.BusinessLogicException;
import pe.edu.pucp.sispedprog.model.Pedido;

public interface PedidoBL {
    public Pedido registrarPedido(Pedido pedido) throws BusinessLogicException;
}
