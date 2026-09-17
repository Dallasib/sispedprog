package pe.edu.pucp.sispedprog.dao;

import pe.edu.pucp.sispedprog.dao.base.BaseDAO;
import pe.edu.pucp.sispedprog.dao.dbmanager.TransactionContext;
import pe.edu.pucp.sispedprog.model.Pedido;

public interface PedidoDAO extends BaseDAO<Pedido,Integer> {
    Pedido save(Pedido pedido);
    Pedido load(Integer id);
    Pedido save(Pedido pedido, TransactionContext transactionContext);
}
