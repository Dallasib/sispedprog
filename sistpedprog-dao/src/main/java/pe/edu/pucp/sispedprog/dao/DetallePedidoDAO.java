package pe.edu.pucp.sispedprog.dao;

import pe.edu.pucp.sispedprog.dao.base.BaseDAO;
import pe.edu.pucp.sispedprog.dao.dbmanager.TransactionContext;
import pe.edu.pucp.sispedprog.model.DetallePedido;
import pe.edu.pucp.sispedprog.model.Pedido;

public interface DetallePedidoDAO extends BaseDAO<DetallePedido,Integer> {
    DetallePedido save(DetallePedido detallePedido);
    DetallePedido save(DetallePedido detallePedido, Pedido pedido);
    DetallePedido load(Integer id);
    DetallePedido save(DetallePedido detallePedido, Pedido pedido, TransactionContext transactionContext);
}
