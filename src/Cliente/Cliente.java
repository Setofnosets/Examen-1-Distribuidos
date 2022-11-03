package Cliente;

import java.net.*;
import java.io.*;

public class Cliente {
    private Socket Cliente;
    private PrintWriter out;
    private BufferedReader in;
    private String Respuesta;

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

    public int EnviarMensaje(String mensaje) {
        try {
            out.println(mensaje);
            Respuesta = in.readLine();
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

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

    public String getRespuesta() {
        return Respuesta;
    }

}
