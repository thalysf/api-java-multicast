
package aplicacoes;

import comunicador.*;


public class AplicaçãoClaudio {


    public static void main(String[] args) {
       Comunicador claudio = new Comunicador("Claudio-Comunicador", "Claudio");
       claudio.start();
    }
    
}
