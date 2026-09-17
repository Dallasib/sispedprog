package pe.edu.pucp.sispedprog.dao;

import pe.edu.pucp.sispedprog.dao.base.BaseDAO;
import pe.edu.pucp.sispedprog.model.Cliente;

public interface ClienteDAO extends BaseDAO<Cliente,Integer> {
    Cliente load(Integer id);
    Cliente save(Cliente cliente);
}
