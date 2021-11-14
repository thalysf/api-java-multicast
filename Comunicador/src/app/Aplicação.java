
package app;

import comunicador.*;
import ringo.Ringo;


public class Aplicação {


    public static void main(String[] args) throws InterruptedException {
       Comunicador ana = new Comunicador("Ana-Comunicador", "Ana");
       Comunicador claudio = new Comunicador("Claudio-Comunicador", "Claudio");
       Comunicador joao = new Comunicador("Joao-Comunicador", "Joao");
       
       
       Ringo ringo = new Ringo();
       ana.start();
       Thread.sleep(5000);
       claudio.start();
       Thread.sleep(8000);
       joao.start();
       Thread.sleep(2000);
       ringo.start();
       
    }
    
}
