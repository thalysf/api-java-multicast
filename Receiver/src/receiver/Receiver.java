/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package receiver;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        String multicastAddress = "244.0.0.1";
        final int multicastPort = 52684;
        final int bufferSize = 1024 * 4;

        InetAddress group = InetAddress.getByName(multicastAddress);
        MulticastSocket s = new MulticastSocket(multicastPort);
        s.joinGroup(group);

        while(true){

            byte[] buffer = new byte[bufferSize];
            s.receive(new DatagramPacket(buffer, bufferSize, group, multicastPort));

            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = new ObjectInputStream(bais);

            try{

                Object readObject = ois.readObject();

                if(readObject instanceof String){

                    String message = (String) readObject;
                    System.out.println("Mnsagem: " + message);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
