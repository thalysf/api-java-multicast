
package comunicador;


public class Aplicação {


    public static void main(String[] args) {
       Comunicador comunicador1 = new Comunicador("Comunicador-01");
       Comunicador comunicador2 = new Comunicador("Comunicador-02");
       Comunicador comunicador3 = new Comunicador("Comunicador-03");
       
       comunicador1.start();
       comunicador2.start();
       comunicador3.start();
    }
    
}
