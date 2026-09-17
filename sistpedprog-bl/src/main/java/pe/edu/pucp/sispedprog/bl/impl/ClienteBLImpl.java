package pe.edu.pucp.sispedprog.bl.impl;

import pe.edu.pucp.sispedprog.bl.ClienteBL;
import pe.edu.pucp.sispedprog.bl.exception.BusinessLogicException;
import pe.edu.pucp.sispedprog.dao.ClienteDAO;
import pe.edu.pucp.sispedprog.dao.impl.ClienteDAOImpl;
import pe.edu.pucp.sispedprog.model.Cliente;

public class ClienteBLImpl implements ClienteBL {

    private final ClienteDAO clienteDAO = new ClienteDAOImpl();

    public Cliente registrar(Cliente cliente) throws BusinessLogicException{
        validarCliente(cliente);
        try{
            clienteDAO.save(cliente);
            return cliente;
        }
        catch(Exception ex){
            throw new BusinessLogicException(ex);
        }
    }

    public Cliente buscar(int id) throws BusinessLogicException{
        if (id>0){
            return clienteDAO.load(id);
        }
        else{
            throw new BusinessLogicException("El id enviado no es correcto");
        }
    }

    private void validarCliente(Cliente cliente) throws BusinessLogicException{
        if (cliente == null){
            throw new BusinessLogicException("El cliente no puede ser nulo");
        }
        if (cliente.getDni()==null || cliente.getDni()==""){
            throw new BusinessLogicException("El dni es un dato obligatorio");
        }
    }
}
