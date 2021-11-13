
package aplicacoes;

import comunicador.Comunicador;


public class AplicaçãoJoao {


    public static void main(String[] args) {
       Comunicador joao = new Comunicador("Joao-Comunicador", "Joao");
       
       joao.start();
    }
    
}
