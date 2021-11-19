package comunicador;

import Model.Avaliacao;
import Model.Usuario;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import util.RandomString;

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
    private String nomeUsuario;
    // Gerador de numeros aleatorios
    private Random rand = new Random();

    // Gerador de strings aleatorias
    private RandomString randomString;
    
    public Comunicador(String nomeComunicador, String nomeUsuario) {
        // Verificando se as séries já foram inicializadas
        BdAvaliacoes.inicializarSeries();

        // Nome do usuário
        this.nomeUsuario = nomeUsuario;

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

    public Avaliacao recebe() {
        byte[] buffer = new byte[tamanho];

        Avaliacao avaliacao = new Avaliacao();

        try {
            // recebe uma solicitação/resposta:
            soc.receive(new DatagramPacket(buffer, tamanho, enderecoGrupo, porta));

            // Deserialização do Objeto:
            ByteArrayInputStream byteArrayEntrada = new ByteArrayInputStream(buffer);
            entrada = new ObjectInputStream(byteArrayEntrada);

            Object objLido = entrada.readObject();

            if (objLido instanceof Avaliacao) {
                avaliacao = (Avaliacao) objLido;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avaliacao;
    }

    public void envia(Avaliacao avaliacao) {
        try {
            ByteArrayOutputStream byteArraySaida = new ByteArrayOutputStream();
            saida = new ObjectOutputStream(byteArraySaida);

            saida.writeObject(avaliacao);
            ////
            byte[] data = byteArraySaida.toByteArray();
            soc.send(new DatagramPacket(data, data.length, enderecoGrupo, porta));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000); // espera 2 segundos
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                for (String serie : BdAvaliacoes.series) {
                    int notaGerada = rand.nextInt(4);
                    //System.out.println("[Robozinho de notas =:-| ] Nota da série " + serie + ": " + notaGerada);
                    int nota = notaGerada;
                    Usuario usuarioAvaliacao = new Usuario(this.nomeUsuario, nota);
                    Avaliacao avaliacao = new Avaliacao(usuarioAvaliacao, serie, nota);
                    // Enviando avaliação
                    envia(avaliacao);
                    Thread.sleep(1000); // espera 1 segundo
                    // Recebendo avaliação
                    avaliacao = recebe();
                    BdAvaliacoes.avaliarSerie(avaliacao);
                }
                int op = rand.nextInt(2);
                //System.out.println("[Robozinho de recomendação :-| ] Gostaria de recomendar uma série ou encerrar a sessão? [0] NAO - [1] SIM: " + op);
                
                switch (op) {
                    case 0:
                        break;
                    case 1:
                        String serie = "indicacao" + rand.nextInt(1000);
                        //System.out.print("[Robozinho de recomendação :-| ] Nome da recomendação: " + serie);
                        Usuario usuarioAvaliacao = new Usuario(this.nomeUsuario, 0);
                        Avaliacao avaliacao = new Avaliacao(usuarioAvaliacao, serie, 0);
                        // Enviando avaliação
                        envia(avaliacao);
                        // Recebendo avaliação
                        avaliacao = recebe();
                        BdAvaliacoes.avaliarSerie(avaliacao);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println("Avaliações: ");;
            //BdAvaliacoes.avaliacoes.forEach(System.out::println);
        }
    }
}
