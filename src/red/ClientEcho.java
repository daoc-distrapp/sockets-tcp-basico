package red;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * https://github.com/dordonez-ute-apdist/sockets-tcp-basico
 * @author dordonez@ute.edu.ec
 */
public class ClientEcho {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 8888;

    public static void main(String[] args) throws IOException {
        ClientEcho cliente = new ClientEcho();
        cliente.conectar(); 
    }
    
    public void conectar() {
        try {
            Socket socket = new Socket(SERVIDOR, PUERTO);
            System.out.println("Cliente conectado con servidor: " + socket.getRemoteSocketAddress());

            //Crea un scanner para recibir mensajes del usuario mediante el teclado
            Scanner teclado = new Scanner(System.in);            

            // Crea el scanner para leer mensajes del servidor
            Scanner scan = new Scanner(socket.getInputStream());

            // Crea el PrintWriter para enviar mensajes al cliente
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            boolean done = false;
            while (!done) {
                //Lee mensaje del teclado y lo envía al servidor
                String mensaje = teclado.nextLine();
                pw.println(mensaje);

                // Si el usuario escribió "exit", prepara el cierre
                if (mensaje.equalsIgnoreCase("EXIT")) {
                    done = true;
                    teclado.close();
                }

                //Espera respuesta del servidor y la imprime
                System.out.println(scan.nextLine());
            }

            // Cierra el socket y termina
            System.out.println("Desconectado de servidor: " + socket.getRemoteSocketAddress());  
            socket.close();  
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
}
