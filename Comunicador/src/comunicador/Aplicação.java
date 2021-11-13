
package comunicador;


public class Aplicação {


    public static void main(String[] args) {
        // inicializando banco de avaliações
        BdAvaliacoes bd = new BdAvaliacoes();
        
        
       Comunicador joao = new Comunicador("Joao-Comunicador", "Joao");
       Comunicador claudio = new Comunicador("Claudio-Comunicador", "Claudio");
       
       joao.start();
       claudio.start();
    }
    
}
