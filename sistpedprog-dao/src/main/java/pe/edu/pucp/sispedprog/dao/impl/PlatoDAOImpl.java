package pe.edu.pucp.sispedprog.dao.impl;

import pe.edu.pucp.sispedprog.dao.PlatoDAO;
import pe.edu.pucp.sispedprog.dao.dbmanager.DBManager;
import pe.edu.pucp.sispedprog.model.Plato;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatoDAOImpl implements PlatoDAO {

    public Plato load(Integer id){
        Plato plato = null;
        String sql = "{CALL sp_obtener_plato(?)}";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            CallableStatement stmt = conexion.prepareCall(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                double precioBase = rs.getDouble("precioBase");
                String categoria = rs.getString("categoria");
                plato = new Plato(codigo,nombre,precioBase,categoria);
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener plato: " + e.getMessage());
        }
        return plato;
    }

    public Plato save(Plato plato){
        int codigoGenerado = 0;
        String sql = "{CALL sp_insertar_plato(?,?,?)}";
        try{
            Connection conexion = DBManager.getInstance().getConnection();
            CallableStatement stmt = conexion.prepareCall(sql);
            stmt.setString(1,plato.getNombre());
            stmt.setDouble(2,plato.getPrecioBase());
            stmt.setString(3,plato.getCategoria());
            boolean exitoso = stmt.execute();
            if (exitoso){
                ResultSet rs = stmt.getResultSet();
                if (rs.next()){
                    codigoGenerado = rs.getInt("codigo");
                    plato.setCodigo(codigoGenerado);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar plato: " + e.getMessage());
        }
        return plato;
    }

    public List<Plato> listAll(){
        List<Plato> listaPlatos = new ArrayList<Plato>();
        String sql = "{CALL sp_listar_platos()}";
        try{
            Connection conexion = DBManager.getInstance().getConnection();
            CallableStatement stmt = conexion.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                double precioBase = rs.getDouble("precioBase");
                String categoria = rs.getString("categoria");
                Plato plato = new Plato(codigo,nombre,precioBase,categoria);
                listaPlatos.add(plato);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener lista de platos: " + e.getMessage());
        }
        return listaPlatos;
    }
}
