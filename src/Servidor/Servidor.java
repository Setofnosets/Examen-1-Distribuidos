package Servidor;

import java.net.*;
import java.io.*;

public class Servidor {
    private ServerSocket serverSocket;
    private Socket Cliente;
    private PrintWriter out;
    private BufferedReader in;
    private String Mensaje;
    private EjecucionComandos ProcesarComando= new EjecucionComandos();

    /**
     * IniciarServidor:
     * Inicia el servidor en el puerto especificado, regresa 0 si se pudo iniciar
     * el servidor y -1 si hubo algún error.
     * @param puerto    Puerto en el que se iniciará el servidor
     * @return          0 si se inició correctamente, -1 si hubo un error
     */
    public int IniciarServidor(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
            Cliente = serverSocket.accept();
            out = new PrintWriter(Cliente.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(Cliente.getInputStream()));
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Servicio:
     * Espera a que el cliente envíe un mensaje y envía la respuesta al cliente.
     * Regresa 0 al terminar el servicio y -1 si hubo algún error.
     * @return 0 si se ejecuta correctamente, -1 si hubo un error.
     */
    public int Servicio(){
        try{
            while ((Mensaje = in.readLine()) != null) {
                System.out.println("Mensaje recibido: " + Mensaje);
                out.println(ProcesarComando.EjecutarComando(Mensaje));
                System.out.println("Respuesta enviada");
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * DetenerServidor:
     * Detiene el servidor y regresa 0 si se pudo detener el servidor y -1 si hubo
     * algún error.
     * @return 0 si se detuvo correctamente, -1 si hubo un error.
     */
    public int DetenerServidor() {
        try {
            serverSocket.close();
            Cliente.close();
            in.close();
            out.close();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        if(servidor.IniciarServidor(5000) == 0) {
            System.out.println("Servidor iniciado");
            servidor.Servicio();
        }else{
            System.out.println("Error al iniciar el servidor");
        }
    }
}
