/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Igor Gayvan
 */
public class ChatClient {

    private String serverAddress;
    private int serverPort;

    public ChatClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;

    }

    public void sendMessage(String replient, String message) throws IOException {

        try (Socket socketClient = new Socket()) {

            socketClient.setSoTimeout(1000);
            System.out.println("Connecting...");

            InetSocketAddress isa = new InetSocketAddress(serverAddress, serverPort);
            socketClient.connect(new InetSocketAddress(serverAddress, serverPort));
            System.out.println("Connect establish");

            DataOutputStream dos = new DataOutputStream(socketClient.getOutputStream());
            DataInputStream dis = new DataInputStream(socketClient.getInputStream());

            dos.writeUTF("MSG");
            dos.flush();
            dos.writeUTF(replient); ///*String.valueOf(socketClient.getLocalSocketAddress()*/));
            dos.writeUTF(message);

            dos.flush();
            String response = dis.readUTF();
            if ("OK".equals(response)) {
                System.out.println("Message was send");
            } else {
                System.out.println("ERROR: While sendeing message");
            }

        }
    }
}

//    @Override
//        public void run() {
//        try {
//            dos.writeUTF("MSG");
//            dos.writeUTF("192.168.1.114"); ///*String.valueOf(socketClient.getLocalSocketAddress()*/));
//            dos.writeUTF("Hello");
//
//            dos.flush();
//
//            String response = dis.readUTF();
//            System.out.println(response);
//
//        
//
//
//
//} catch (IOException ex) {
//            Logger.getLogger(ChatClient.class
//
//
//.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}
