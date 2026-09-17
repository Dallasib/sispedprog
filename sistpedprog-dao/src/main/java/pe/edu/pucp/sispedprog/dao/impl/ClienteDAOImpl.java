package pe.edu.pucp.sispedprog.dao.impl;

import pe.edu.pucp.sispedprog.dao.ClienteDAO;
import pe.edu.pucp.sispedprog.dao.dbmanager.DBManager;
import pe.edu.pucp.sispedprog.dao.dbmanager.TransactionContext;
import pe.edu.pucp.sispedprog.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAOImpl implements ClienteDAO {
    public Cliente load(Integer id){
        Cliente cliente = null;
        String query = "SELECT codigo, dni, nombres, apellido_paterno " +
                        "FROM cliente " +
                        "WHERE codigo = ?";
        try{
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                cliente = new Cliente();
                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setDni(rs.getString("dni"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setApellidoPaterno(rs.getString("apellido_paterno"));
            }
        }
        catch (SQLException e){
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
        return cliente;
    }

    public Cliente save(Cliente cliente, TransactionContext t){

    }

    public Cliente save(Cliente cliente){
        String query = "INSERT INTO cliente(dni,nombres,apellido_paterno) " +
                        "VALUES (?,?,?)";
        try{
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1,cliente.getDni());
            ps.setString(2,cliente.getNombres());
            ps.setString(3,cliente.getApellidoPaterno());
            ps.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
        return cliente;
    }
}
