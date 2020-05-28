package red;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

/**
 * 
 * @author dordonez@ute.edu.ec
 */
public class ClientEcho {
    private String servidor = "localhost";
    private int puerto = 8888;

    public ClientEcho() {
    }

    public ClientEcho(String servidor, int puerto) {
        this.servidor = servidor;
        this.puerto = puerto;
    }    
    
    public void conectar() {
        try {
            Socket socket = new Socket(servidor, puerto);
            System.out.println("Cliente conectado");

            // Crea el scanner para leer mensajes del servidor
            Scanner scanner = new Scanner(socket.getInputStream());

            // Crea el PrintWriter para enviar mensajes al cliente
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            //Crea un keyboard scanner para recibir datos del usuario
            Scanner keyboard = new Scanner(System.in);
            
            System.out.println("Esperando datos del Server");
            boolean done = false;
            while (!done && scanner.hasNextLine()) {
                String line = "Recibido del Server: " + scanner.nextLine();
                System.out.println(line);
                if (line.contains("Close:")) {
                    done = true;
                    break;
                }
                String instruccion = keyboard.nextLine().trim();
                printWriter.println(instruccion);
            }

            socket.close();
            System.out.println("Cliente cerrado");     
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param args servidor y puerto de conexion default "localhost" y 8888
     */
    public static void main(String[] args) throws IOException {
        ClientEcho cliente;
        if (args.length > 0) {
            cliente = new ClientEcho(args[0], Integer.parseInt(args[1]));
        } else {
            cliente = new ClientEcho();
        }
        cliente.conectar();  
    }

}
