/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programarecebe;


import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ProgramaRecebe {

    public static void main(String[] args) throws IOException {

        MulticastSocket s = null;
        InetAddress group = null;
        String multicastAddress = "224.0.0.1";
        final int multicastPort = 52684;
        final int bufferSize = 1024 * 4;
        InetAddress group = InetAddress.getByName(multicastAddress);
        MulticastSocket s = new MulticastSocket(multicastPort);
        s.joinGroup(group);

        while (true) {

            byte[] buffer = new byte[bufferSize];
            s.receive(new DatagramPacket(buffer, bufferSize, group, multicastPort));

            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = new ObjectInputStream(bais);

            try {

                Object readObject = ois.readObject();

                if (readObject instanceof Pessoa) {

                    Pessoa p = (Pessoa) readObject;
                    System.out.println("printa pessoa");
                }

            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}