package Servidor;

import java.util.Collections;
import java.util.LinkedList;

public class ServidorUtilidad {

    private Character CMD;

    public String EjecutarComando(String comando) {
        LinkedList<Integer> lista = ProcesarMensaje(comando);
        switch (CMD) {
            case 'A':
                Collections.sort(lista);
                return lista.toString();
            case 'B':
                Collections.sort(lista, Collections.reverseOrder());
                return lista.toString();
            case 'C':
                Integer suma = 0;
                for (Integer integer : lista) {
                    suma += integer;
                }
                return suma.toString();
            case 'D':
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Retardo de mensaje";
            default:
                return null;
        }
    }

    public LinkedList<Integer> ProcesarMensaje(String mensaje) {
        LinkedList<Integer> numeros = new LinkedList();
        CMD = mensaje.charAt(0);
        if (Character.isDigit(CMD)){
            return null;
        }
        String[] partes = mensaje.split(",");
        partes[0] = partes[0].substring(1);
        for (String parte : partes) {
            numeros.add(Integer.parseInt(parte));
        }
        if (numeros.size() != 5) {
            return null;
        }
        return numeros;
    }
}
