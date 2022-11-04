package Servidor;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class EjecucionComandos {

    private Character CMD;

    /**
     * EjecutarComando:
     * Ejecuta el comando recibido como cadena de texto y devuelve la respuesta correspondiente.
     * El comando debe tener el siguiente formato: CMD,N1,N2,N3,N4,N5 donde CMD es el
     * comando a ejecutar y N es la lista de numeros a utilizar por el comando.
     * Todos los números deben ser enteros.
     * Los comandos disponibles son:
     *    - 'A': Ordenar de manera ascendiente
     *    - 'B': Ordenar de manera descendiente
     *    - 'C': Calcular la suma de los 5 números
     *    - 'D': Esperar 3 segundos e imprimir "Retardo de mensaje"
     * @param comando   Cadena de texto correspondiente al comando a ejecutar
     * @return          Respuesta al comando
     */
    public String EjecutarComando(String comando) {
        LinkedList<Integer> lista = ProcesarMensaje(comando);
        if (lista == null) {
            return "\"Error\","+comando;
        }
        switch (CMD) {
            case 'A':
                Collections.sort(lista);
                return "("+lista.toString()+")"+CMD+",DAT";
            case 'B':
                Collections.sort(lista, Collections.reverseOrder());
                return "("+lista.toString()+")"+CMD+",DAT";
            case 'C':
                Integer suma = 0;
                for (Integer integer : lista) {
                    suma += integer;
                }
                return "("+suma.toString()+")"+CMD+",DAT";
            case 'D':
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "\"Retardo de mensaje\",D,DAT";
            default:
                return "\"Comando no encontrado\","+CMD+",DAT";
        }
    }

    /**
     * ProcesarMensaje:
     * Recibe el mensaje y lo procesa para obtener el comando y los datos,
     * regresa la lista correspondiente a los datos y guarda el comando en CMD
     * @param mensaje   Cadena de texto correspondiente al comando a ejecutar
     * @return          Lista de enteros correspondiente a los datos
     */
    private LinkedList<Integer> ProcesarMensaje(String mensaje) {
        try{
            LinkedList<Integer> numeros = new LinkedList<Integer>();
            CMD = mensaje.charAt(0);
            if (Character.isDigit(CMD)){
                throw new FormatoIncorrectoException("El primer caracter debe ser una letra");
            }
            String[] partes = mensaje.split(",");
            partes = Arrays.copyOfRange(partes, 1, partes.length);
            for (String parte : partes) {
                numeros.add(Integer.parseInt(parte));
            }
            if (numeros.size() != 5) {
                throw new FormatoIncorrectoException("El mensaje debe contener 5 numeros");
            }
            return numeros;
        } catch (FormatoIncorrectoException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        } catch (NumberFormatException e) {
            System.out.println("El mensaje debe contener solo numeros");
            e.printStackTrace();
            return null;
        }
    }
}
