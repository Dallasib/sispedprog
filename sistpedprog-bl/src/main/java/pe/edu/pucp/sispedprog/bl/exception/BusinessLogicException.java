package pe.edu.pucp.sispedprog.bl.exception;

public class BusinessLogicException extends Exception{
    public BusinessLogicException(){
        super();
    }

    public BusinessLogicException(String mensaje){
        super(mensaje);
    }

    public BusinessLogicException(Exception ex){
        super(ex);
    }
}
