/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastpublisher;

import commons.SeriesNotas;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Alexandre
 */
public class MulticastPublisher {
    private DatagramSocket socket;
    private InetAddress group;

    public void multicast(
      SeriesNotas seriesNotas) throws IOException {
        socket = new DatagramSocket();
        group = InetAddress.getByName("230.0.0.0");
        
        ByteArrayOutputStream myBaos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(myBaos);
        oos.writeObject(seriesNotas);
        
        byte[] buf = myBaos.toByteArray();
        
        DatagramPacket packet 
          = new DatagramPacket(buf, buf.length, group, 4446);
        socket.send(packet);
        socket.close();
    }
}
