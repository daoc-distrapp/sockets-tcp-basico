package red;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * @author dordonez@ute.edu.ec
 */
public class Client1 {

    private static String servidor = "localhost";
    private static int puerto = 8888;

    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Cliente iniciado. Buscando " + servidor + ":" + puerto);
            
            Socket socket = new Socket(servidor, puerto);
            System.out.println("Cliente conectado");

            // Crea el PrintWriter para enviar un mensaje al servidor
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("Hola desde el Cliente !!!");
            pw.flush();
            System.out.println("Mensaje enviado");
            
            socket.close();
            System.out.println("Cliente cerrado");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
