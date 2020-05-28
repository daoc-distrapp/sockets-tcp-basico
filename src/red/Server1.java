package red;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * @author dordonez@ute.edu.ec
 */
public class Server1 {
    private static int puerto = 8888;
    
    public static void main(String[] args) throws Exception {
        try {
            // Crea el ServerSocket
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Server iniciado (cierre con Ctrl+C)");

            // Maneja las conexiones
            while (true) {
                System.out.println("Server esperando conexion");
                // escucha-acepta conexiones
                Socket socket = serverSocket.accept();
                System.out.println("Conexion aceptada");

                InputStream inputStream = socket.getInputStream();
                Scanner in = new Scanner(inputStream);
                
                System.out.println("Esperando datos del Cliente");
                String line = "Recibido del Cliente: " + in.nextLine() + " (" + socket.getRemoteSocketAddress() + ")";
                System.out.println(line);
                
                // Cierra el socket
                socket.close();
                System.out.println("Conexión cerrada");
            }
            
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }
    
}
