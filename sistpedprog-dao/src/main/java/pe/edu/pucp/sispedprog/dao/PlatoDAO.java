package pe.edu.pucp.sispedprog.dao;

import pe.edu.pucp.sispedprog.dao.base.BaseDAO;
import pe.edu.pucp.sispedprog.model.Plato;

import java.util.List;

public interface PlatoDAO extends BaseDAO<Plato,Integer> {
    Plato load(Integer id);
    Plato save(Plato plato);
    List<Plato> listAll();
}
