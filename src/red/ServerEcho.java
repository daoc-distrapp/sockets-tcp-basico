package red;

import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 * 
 * @author dordonez@ute.edu.ec
 */
public class ServerEcho {
    private int puerto = 8888;
    
    public ServerEcho(){
    }
    
    public ServerEcho(int puerto) {
        this.puerto = puerto;
    }

    public void escuchar() {
        try {
            // Crea el socket y escucha por conexiones
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Server iniciado (cierre con Ctrl+C)");

            // Maneja las conexiones
            while (true) {
                System.out.println("Server esperando conexion");
                Socket socket = serverSocket.accept();
                System.out.println("Conexion aceptada");

                // Crea el Scanner para recibir mensajes del cliente
                Scanner scanner = new Scanner(socket.getInputStream());

                // Crea el PrintWriter para enviar mensajes al cliente
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

                pw.println("Conectado a ServerEcho. Escriba EXIT para salir.");
                boolean done = false;
                while (!done && scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.trim().equals("EXIT")) {
                        pw.println("Close: ServerEcho cerrando conexion");
                        System.out.println("EXIT solicitado");
                        done = true;
                    } else {
                        pw.println("Echo: " + line);
                        System.out.println("Echo enviado");
                    }
                }

                // Cierra el socket
                socket.close();
                System.out.println("Conexion cerrada");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param args puerto de escucha default es 8888
     */
    public static void main(String[] args) throws Exception {
        ServerEcho servidor;
        if(args.length > 0) {
            servidor = new ServerEcho(Integer.parseInt(args[0]));
        } else {
            servidor = new ServerEcho();
        }
        servidor.escuchar();
    }
    
}
