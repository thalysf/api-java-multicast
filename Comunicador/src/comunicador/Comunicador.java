package comunicador;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class Comunicador extends Thread {

    private String nome;
    private String multiCastAdress = "224.0.0.1";
    private static int porta;
    private static InetAddress enderecoGrupo = null;
    private static MulticastSocket soc = null;
    private static ObjectOutputStream saida = null;
    private static ObjectInputStream entrada = null;
    private final int tamanho = 1024 * 4; // tamananho máximo do objeto

    public Comunicador(String nome) {
        System.out.println("Iniciando comunicador " + nome);
        this.nome = nome;
        try {
            porta = 52684;
            enderecoGrupo = InetAddress.getByName(multiCastAdress);
            soc = new MulticastSocket(porta);
            soc.joinGroup(enderecoGrupo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario recebe() {
        byte[] buffer = new byte[tamanho];

        Usuario usuario = new Usuario();

        try {
            // recebe uma solicitação/resposta:
            soc.receive(new DatagramPacket(buffer, tamanho, enderecoGrupo, porta));

            // Deserialização do Objeto:
            ByteArrayInputStream byteArrayEntrada = new ByteArrayInputStream(buffer);
            entrada = new ObjectInputStream(byteArrayEntrada);

            Object objLido = entrada.readObject();

            if (objLido instanceof Usuario) {
                usuario = (Usuario) objLido;
                System.out.println(this.nome + " - Usuário lido  " + usuario.getNome());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void envia(Usuario usuario) {
        try {
            ByteArrayOutputStream byteArraySaida = new ByteArrayOutputStream();
            saida = new ObjectOutputStream(byteArraySaida);

            saida.writeObject(usuario);
            ////
            byte[] data = byteArraySaida.toByteArray();
            soc.send(new DatagramPacket(data, data.length, enderecoGrupo, porta));
            
            System.out.println(this.nome + " - Usuário enviado  " + usuario.getNome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int contador = 0;
        Usuario usuario = null;

        try {
            Thread.sleep(5000); // espera 5 segundos
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (contador < 3) {
            try {
                switch(contador)
                {
                    case 0: usuario = new Usuario("Joao"); break;
                    case 1: usuario = new Usuario("Ana"); break;
                    case 2: usuario = new Usuario("Eduarda"); break;
                }
                if(usuario != null)
                {
                     System.out.println(this.nome + " - Enviando usuário: " + usuario.getNome());
                }
                
                // Enviando dados para o grupo:
                envia(usuario);
               // Thread.sleep(1000); // espera 1 segundo
                
                usuario = recebe();
                
                System.out.println(this.nome + " - Usuário recebido: " + usuario.getNome());
            } catch (Exception e) {
                e.printStackTrace();
            }
            contador++;
        }
    }
}
