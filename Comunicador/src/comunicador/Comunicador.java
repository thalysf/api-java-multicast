package comunicador;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;


public class Comunicador extends Thread {

    private String nome;
    private String multiCastAdress = "224.0.0.1";
    private static int porta;
    private static InetAddress enderecoGrupo = null;
    private static MulticastSocket soc = null;
    private static ObjectOutputStream saida = null;
    private static ObjectInputStream entrada = null;
    private final int tamanho = 1024 * 4; // tamananho máximo do objeto

    // Para realizar comunicação via prompt
    private Scanner scan = new Scanner(System.in);
    
    
    // Usuário avaliador
    private Usuario usuario = new Usuario();
    
    public Comunicador(String nomeComunicador, String nomeUsuario) {
        // Nome do usuário
        this.usuario.setNome(nomeUsuario);
        
        
        // Nome do comunicador
        System.out.println("Iniciando comunicador " + nomeComunicador);
        this.nome = nomeComunicador;
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
        while (true) {
            try {
                // instancia avaliação usuario
                
                scan
                
                
                if(usuario != null)
                {
                     System.out.println(this.nome + " - Enviando usuário: " + usuario.getNome());
                }
                
                
                // Enviando dados para o grupo:
                envia(usuario);
               // Thread.sleep(1000); // espera 1 segundo
                
                usuario = recebe();
                
                // registra avaliação no bd...
            } catch (Exception e) {
                e.printStackTrace();
            }
            contador++;
        }
    }
}
