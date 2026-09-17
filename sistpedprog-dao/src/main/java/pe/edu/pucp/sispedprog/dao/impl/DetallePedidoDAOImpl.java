package pe.edu.pucp.sispedprog.dao.impl;

import pe.edu.pucp.sispedprog.dao.DetallePedidoDAO;
import pe.edu.pucp.sispedprog.dao.dbmanager.DBManager;
import pe.edu.pucp.sispedprog.dao.dbmanager.TransactionContext;
import pe.edu.pucp.sispedprog.model.DetallePedido;
import pe.edu.pucp.sispedprog.model.Pedido;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetallePedidoDAOImpl implements DetallePedidoDAO {

    @Override
    public DetallePedido save(DetallePedido detallePedido){
        return detallePedido;
    }

    public DetallePedido save(DetallePedido detallePedido, Pedido pedido){
        Connection connection = DBManager.getInstance().getConnection();
        return saveConConnection(detallePedido,pedido,connection);
    }

    @Override
    public DetallePedido load(Integer id){
        return null;
    }

    @Override
    public DetallePedido save(DetallePedido detallePedido, Pedido pedido, TransactionContext transactionContext){
        Connection connection = transactionContext.getConnection();
        return saveConConnection(detallePedido,pedido,connection);
    }

    private DetallePedido saveConConnection(DetallePedido detallePedido,Pedido pedido, Connection connection){
        String sql = "{CALL sp_insertar_detallepedido(?,?,?)}";
        int codigoGenerado = 0;
        try{
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setInt(1,detallePedido.getCantidad());
            stmt.setInt(2,pedido.getNumero());
            stmt.setInt(3,detallePedido.getProducto().getCodigo());
            boolean exitoso = stmt.execute();
            if (exitoso){
                ResultSet rs = stmt.getResultSet();
                if (rs.next()){
                    codigoGenerado = rs.getInt("codigo");
                    detallePedido.setCodigo(codigoGenerado);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener lista de platos: " + e.getMessage());
        }
        return detallePedido;
    }
}
