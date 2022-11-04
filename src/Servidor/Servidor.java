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
    public int Servicio(){
        try{
            while ((Mensaje = in.readLine()) != null) {
                out.println(ProcesarComando.EjecutarComando(Mensaje));
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

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
