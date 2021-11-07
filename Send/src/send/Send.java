/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Send {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){

        String multiCastAddress = "244.0.0.1";
        final int multicastPort = 52684;
        try {

            InetAddress group = InetAddress.getByName(multiCastAddress);
            MulticastSocket s = new MulticastSocket(multicastPort);
            s.joinGroup(group);

            String message = "MyBalsInYourMouth";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(message);

            byte data[] = baos.toByteArray();

            s.send(new DatagramPacket(data, data.length, group, multicastPort));

            

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
