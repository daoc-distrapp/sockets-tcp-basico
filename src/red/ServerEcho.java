package red;

import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * https://github.com/dordonez-ute-apdist/sockets-tcp-basico
 * @author dordonez@ute.edu.ec
 */
public class ServerEcho {
    private static final int PUERTO = 8888;

    public static void main(String[] args) throws Exception {
        ServerEcho servidor = new ServerEcho();
        servidor.escuchar();
    }    
    
    public void escuchar() {
        try {
            // Crea el ServerSocket y espera conexiones
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("Server iniciado (cierre con Ctrl+C)");

            // Maneja las conexiones
            while (true) {
                System.out.println("Server esperando conexión");
                Socket socket = serverSocket.accept();
                System.out.println("Conexión aceptada desde: " + socket.getRemoteSocketAddress());

                // Crea el Scanner para recibir mensajes del cliente
                Scanner scan = new Scanner(socket.getInputStream());

                // Crea el PrintWriter para enviar mensajes al cliente
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

                boolean done = false;
                while (!done) {
                	//Lee mensaje del socket y responde un "echo"
                    String line = scan.nextLine();
                	System.out.println("Recibido:" + line + "; desde: " + socket.getRemoteSocketAddress());
                    pw.println("Echo: " + line.toUpperCase());                    
                    
                    if (line.trim().equalsIgnoreCase("EXIT")) {
                        System.out.println("Server cerrando conexión con: " + socket.getRemoteSocketAddress());
                        done = true;
                    } 
                }

                // Cierra el socket
                socket.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } 
    }
    
}
