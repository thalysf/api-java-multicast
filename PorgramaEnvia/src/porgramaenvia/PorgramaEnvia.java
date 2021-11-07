/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package porgramaenvia;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class PorgramaEnvia {

    public static void main(String[] args) throws IOException {

        String multicastAddress = "224.0.0.1";
        final int multicastPort = 52684;

        try{

            InetAddress group = InetAddress.getByName(multicastAddress);
            MulticastSocket s = new MulticastSocket(multicastPort);
            s.joinGroup(group);

            Pessoa pessoa = new Pessoa("PEssoa01", 'M');

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(pessoa);

            byte[] data = baos.toByteArray();
            s.send(new DatagramPacket(data, data.length, group, multicastPort));
        } catch(Exception e){
            System.out.println(e);
        }
    }

}