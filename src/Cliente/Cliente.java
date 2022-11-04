package Cliente;

import java.net.*;
import java.io.*;

public class Cliente {

    // Atributos
    private Socket Cliente;
    private PrintWriter out;
    private BufferedReader in;
    private String Respuesta;

    /**
     * Conectarse:
     * Crea una nueva conexión con el servidor en la dirección ip y puerto especificados.
     * @param ip    Dirección ip del servidor
     * @param puerto    Puerto del servidor
     * @return  0 si se conectó correctamente, -1 si hubo un error.
     */
    public int Conectarse(String ip, int puerto) {
        try {
            Cliente = new Socket(ip, puerto);
            out = new PrintWriter(Cliente.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(Cliente.getInputStream()));
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * EnviarMensaje:
     * Envía un mensaje al servidor y guarda la respuesta en la variable respuesta.
     * @param mensaje   Mensaje a enviar
     * @return  0 si se envió correctamente, -1 si hubo un error.
     */
    public int EnviarMensaje(String mensaje) {
        try {
            out.println(mensaje);
            Respuesta = in.readLine();
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Desconectar:
     * Cierra la conexión con el servidor y regresa 0 si se pudo cerrar la conexión
     * @return  0 si se cerró correctamente, -1 si hubo un error.
     */
    public int Desconectar() {
        try {
            Cliente.close();
            in.close();
            out.close();
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * getRespuesta:
     * Regresa la respuesta del servidor.
     * @return  Respuesta del servidor.
     */
    public String getRespuesta() {
        return Respuesta;
    }

}
