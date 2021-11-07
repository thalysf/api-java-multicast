/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observador;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Observador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

      int porta = 6869;
        InetAddress ipGrupo = null;
        MulticastSocket s = null;

        try{
            ipGrupo = InetAddress.getLocalHost();
            s = new MulticastSocket(porta);
            s.joinGroup(ipGrupo);
        } catch (Exception e){
            System.out.println(e);
        }

        byte[] buf = new byte[10000];
        while (true){

            DatagramPacket recebido = new DatagramPacket(buf, buf.length);
            assert s != null;
            s.setSoTimeout(120000);
            s.receive(recebido);

            String str = new String(recebido.getData());
            System.out.println("Recebido: " + str.trim());
        }
    }
    
}
