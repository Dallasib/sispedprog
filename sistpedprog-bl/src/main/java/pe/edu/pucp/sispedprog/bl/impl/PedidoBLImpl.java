package pe.edu.pucp.sispedprog.bl.impl;

import pe.edu.pucp.sispedprog.bl.PedidoBL;
import pe.edu.pucp.sispedprog.bl.exception.BusinessLogicException;
import pe.edu.pucp.sispedprog.dao.DetallePedidoDAO;
import pe.edu.pucp.sispedprog.dao.PedidoDAO;
import pe.edu.pucp.sispedprog.dao.dbmanager.TransactionContext;
import pe.edu.pucp.sispedprog.dao.impl.DetallePedidoDAOImpl;
import pe.edu.pucp.sispedprog.dao.impl.PedidoDAOImpl;
import pe.edu.pucp.sispedprog.model.DetallePedido;
import pe.edu.pucp.sispedprog.model.Pedido;

import java.sql.SQLException;

public class PedidoBLImpl implements PedidoBL {

    private final PedidoDAO pedidoDAO = new PedidoDAOImpl();
    private final DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAOImpl();

    @Override
    public Pedido registrarPedido(Pedido pedido) throws BusinessLogicException{
        /*Primero deberian realizar las validaciones de negocio*/
        if (pedido.getDetalles().size()==0){
            throw new BusinessLogicException("El pedido no cuenta con detalles");
        }
        if (pedido == null){
            throw new BusinessLogicException("El pedido ha registrar no existe");
        }
        try {
            TransactionContext transactionContext= new TransactionContext();
            try{
                pedido = pedidoDAO.save(pedido,transactionContext);
                for (DetallePedido detallePedido: pedido.getDetalles()){
                    detallePedidoDAO.save(detallePedido,pedido,transactionContext);
                }
                transactionContext.commit();
            }
            catch (Exception e){
                transactionContext.rollback();
                throw new BusinessLogicException(e);
            }
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
        return pedido;
    };
}
