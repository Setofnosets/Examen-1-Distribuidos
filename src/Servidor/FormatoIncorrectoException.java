package Servidor;

public class FormatoIncorrectoException extends Exception{
    /**
     * Constructor de la excepción FormatoIncorrectoException
     * @param mensaje   Mensaje que se mostrará al usuario
     */
    public FormatoIncorrectoException(String mensaje) {
        super(mensaje);
    }
}
