package multicastpeer;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Alexandre
 */
public class MulticastPeer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {

        Scanner input = new Scanner(System.in);
        String msg;
        MulticastSocket s = null;
        try {
            System.out.println("\n\nEscreva sua mensagem agora: ");
            msg = input.next();
            InetAddress group = InetAddress.getByName("Usuario-PC");
            s = new MulticastSocket (6789);
            s.joinGroup (group);
            byte []m = msg.getBytes();
            DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6789);
            s.send(messageOut);
            byte [] buffer = new byte[1000];

            for (int i = 0; i < 1000; i++) {

                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                s.receive(messageIn);
                System.out.println("Recebido: " + new String(messageIn.getData()));

            }

            s.leaveGroup(group);

        } catch (Exception e){
            System.out.println(e);
        }
    }

}