package Cliente;

import java.util.Scanner;

public class ClienteAplicacion {

    private Scanner scanner = new Scanner(System.in);

    /**
     * mensajes:
     * Conecta con el servidor, envía los mensajes ingresados por el usuario
     * y imprime la respuesta del servidor.
     */
    public int mensajes() {
        Cliente cliente = new Cliente();
        if (cliente.Conectarse("127.0.0.1", 5000) == 0) {
            System.out.println("Conectado al servidor");
            while (true) {
                System.out.print("Mensaje: ");
                String mensaje = scanner.nextLine();
                if (cliente.EnviarMensaje(mensaje) == 0) {
                    System.out.println(cliente.getRespuesta());
                } else {
                    System.out.println("Error al enviar el mensaje");
                }
            }
        } else {
            System.out.println("Error al conectarse al servidor");
            return -1;
        }
    }
    public static void main(String[] args) {
        ClienteAplicacion cliente = new ClienteAplicacion();
        cliente.mensajes();
    }
}
