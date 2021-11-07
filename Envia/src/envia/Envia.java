/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envia;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class Envia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

       int porta = 6868;
        InetAddress ipGrupo = null;
        MulticastSocket s = null;

        String msg = "Mensagem Default ";

        try{
            ipGrupo = InetAddress.getLocalHost();
            s = new MulticastSocket(porta);

            s.joinGroup(ipGrupo);
        } catch (SocketException e){
            System.out.println(e);
        }

        DatagramPacket ftgrm = new DatagramPacket(msg.getBytes(), msg.length(), ipGrupo, porta);

        assert s != null;
        s.send(ftgrm);

        s.leaveGroup(ipGrupo);

    }
}