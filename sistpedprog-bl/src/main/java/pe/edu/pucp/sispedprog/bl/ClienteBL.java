package pe.edu.pucp.sispedprog.bl;

import pe.edu.pucp.sispedprog.bl.exception.BusinessLogicException;
import pe.edu.pucp.sispedprog.model.Cliente;

public interface ClienteBL {
    public Cliente registrar(Cliente cliente) throws BusinessLogicException;
    public Cliente buscar(int id) throws BusinessLogicException;
}
