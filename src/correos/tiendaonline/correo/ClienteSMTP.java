/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.correo;

import correos.tiendaonline.utils.Constants;
import java.io.*;
import java.net.*;

/**
 *
 * @author mauriballes
 */
public class ClienteSMTP {

    private static final int PORT = 25; // SMTP

    public static void sendMail(String toMail, String subject, String content) {
        // Estableciendo variables
        BufferedReader reader;
        DataOutputStream writer;
        String command;

        try {
            // Estableciendo Conexion Socket
            Socket socket = new Socket(Constants.MAIL_SERVER_HOST, PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new DataOutputStream(socket.getOutputStream());

            if (socket != null && reader != null && writer != null) {
                reader.readLine();
                // Saludar al servidor
                command = "EHLO " + Constants.MAIL_SERVER_HOST + "\r\n";
                writer.writeBytes(command);
                getMultiline(reader);

                command = "MAIL FROM : " + Constants.MAIL_USERMAIL + "\r\n";
                writer.writeBytes(command);
                reader.readLine();

                command = "RCPT TO : " + toMail + "\r\n";
                writer.writeBytes(command);
                reader.readLine();

                // Escribir Mensaje
                command = "DATA\n";
                writer.writeBytes(command);
                getMultiline(reader);

                command = "Subject: " + subject + "\r\n" + content + "\n.\r\n";
                writer.writeBytes(command);
                reader.readLine();

                command = "QUIT\r\n";
                writer.writeBytes(command);
                reader.readLine();
            }

            // Cerrar Conexion
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    static protected String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                // Server closed connection
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.charAt(3) == ' ') {
                lines = lines + "\n" + line;
                // No more lines in the server response
                break;
            }
            // Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return lines;
    }
}
