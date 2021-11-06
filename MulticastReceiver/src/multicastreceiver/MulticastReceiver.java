/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastreceiver;

import commons.SeriesNotas;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Alexandre
 */
public class MulticastReceiver extends Thread {
    protected MulticastSocket socket = null;

    public void run() {
        
        System.out.println("Iniciou");
        
        try{
        socket = new MulticastSocket(4446);
        InetAddress group = InetAddress.getByName("230.0.0.0");
        socket.joinGroup(group);
        while (true) {
            
            byte[] buf = new byte[10000];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);         
           
            // Desserializar
            ByteArrayInputStream bais = new ByteArrayInputStream(buf);
            ObjectInputStream ois = new ObjectInputStream(bais);
            
            Object readObject = ois.readObject();
            
            if(readObject instanceof SeriesNotas){
                
                System.out.println("Chegou aqui buceta");
                System.out.println(((SeriesNotas)readObject).toString());
            }
        }
      
        }catch(Exception e ){
               e.printStackTrace();
        }
    } 
}
