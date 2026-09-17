package pe.edu.pucp.sispedprog.app;

import pe.edu.pucp.sispedprog.bl.ClienteBL;
import pe.edu.pucp.sispedprog.bl.exception.BusinessLogicException;
import pe.edu.pucp.sispedprog.bl.impl.ClienteBLImpl;
import pe.edu.pucp.sispedprog.dao.PlatoDAO;
import pe.edu.pucp.sispedprog.dao.impl.PlatoDAOImpl;
import pe.edu.pucp.sispedprog.model.Cliente;
import pe.edu.pucp.sispedprog.model.Plato;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BusinessLogicException {
        boolean centinela=true;
        int opcion;
        Scanner scanner = new Scanner(System.in);
        ClienteBL clienteBL = new ClienteBLImpl();
        PlatoDAO platoDAO = new PlatoDAOImpl();

        while (centinela){
            System.out.println("=======================");
            System.out.println("  GESTIÓN DE CLIENTES");
            System.out.println("=======================");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Buscar cliente");
            System.out.println("3. Registrar plato");
            System.out.println("4. Listar plato");
            System.out.println("6. Salir");
            System.out.println("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion){
                case 1:{
                    registrarCliente(scanner,clienteBL);
                    break;
                }
                case 2:{
                    buscarCliente(scanner,clienteBL);
                    break;
                }
                case 3:{
                    registrarPlato(scanner,platoDAO);
                    break;
                }
                case 4:{
                    listarPlatos(platoDAO);
                    break;
                }
            }
        }
    }

    public static void listarPlatos(PlatoDAO platoDAO){
        List<Plato> listaPlatos = platoDAO.listAll();
        System.out.println();
        System.out.println("======LISTA DE PLATOS======");
        for (int i=0; i<listaPlatos.size(); i++){
            System.out.println(listaPlatos.get(i).devolverDatos());
        }
    }

    public static void registrarPlato(Scanner scanner,PlatoDAO platoDAO){
        String nombre, categoria;
        double precioBase;
        System.out.println();
        System.out.println("======REGISTRAR PLATO======");
        System.out.println("Nombre: ");
        nombre = scanner.nextLine();
        System.out.println("Categoría: ");
        categoria = scanner.nextLine();
        System.out.println("Precio Base: ");
        precioBase = Double.parseDouble(scanner.nextLine());
        Plato plato = new Plato(nombre,precioBase,categoria);
        plato = platoDAO.save(plato);
        System.out.println("El plato se ha registrado con éxito. Id: " + plato.getCodigo());
        System.out.println();
    }

    public static void registrarCliente(Scanner scanner,ClienteBL clienteBL) throws BusinessLogicException {
        String dni, nombres, apellidoPaterno;
        System.out.println();
        System.out.println("======REGISTRAR CLIENTE======");
        System.out.println("DNI: ");
        dni = scanner.nextLine();
        System.out.println("Nombres: ");
        nombres = scanner.nextLine();
        System.out.println("Apellido Paterno: ");
        apellidoPaterno = scanner.nextLine();
        Cliente cliente = new Cliente(dni,nombres,apellidoPaterno);
        cliente = clienteBL.registrar(cliente);
        System.out.println("El cliente se ha registrado con éxito");
        System.out.println();
    }

    public static void buscarCliente(Scanner scanner,ClienteBL clienteBL) throws BusinessLogicException {
        int id;
        System.out.println();
        System.out.println("======REGISTRAR CLIENTE======");
        System.out.println("ID a buscar: ");
        id = Integer.parseInt(scanner.nextLine());
        Cliente cliente = clienteBL.buscar(id);
        System.out.println("Codigo: " + cliente.getCodigo());
        System.out.println("DNI: " + cliente.getDni());
        System.out.println("Nombres: " + cliente.getNombres());
        System.out.println("Apellido Paterno: " + cliente.getApellidoPaterno());
    }

}
