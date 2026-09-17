package pe.edu.pucp.sispedprog.dao.impl;

import pe.edu.pucp.sispedprog.dao.PedidoDAO;
import pe.edu.pucp.sispedprog.dao.dbmanager.DBManager;
import pe.edu.pucp.sispedprog.dao.dbmanager.TransactionContext;
import pe.edu.pucp.sispedprog.model.Pedido;
import pe.edu.pucp.sispedprog.model.Plato;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoDAOImpl implements PedidoDAO {
    @Override
    public Pedido save(Pedido pedido){
        Connection connection = DBManager.getInstance().getConnection();
        return saveConConnection(pedido,connection);
    }

    @Override
    public Pedido load(Integer id){
        return null;
    }

    @Override
    public Pedido save(Pedido pedido, TransactionContext transactionContext){
        Connection connection = transactionContext.getConnection();
        return saveConConnection(pedido,connection);
    }

    private Pedido saveConConnection(Pedido pedido,Connection connection){
        String sql = "{CALL sp_insertar_pedido(?,?,?)}";
        int codigoGenerado = 0;
        try{
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setDate(1,pedido.getFecha());
            stmt.setString(2,pedido.getEstado());
            stmt.setInt(3,pedido.getCliente().getCodigo());
            boolean exitoso = stmt.execute();
            if (exitoso){
                ResultSet rs = stmt.getResultSet();
                if (rs.next()){
                    codigoGenerado = rs.getInt("codigo");
                    pedido.setNumero(codigoGenerado);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener lista de platos: " + e.getMessage());
        }
        return pedido;
    }
}
