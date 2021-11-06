/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastpublisher;

import commons.Avaliacao;
import commons.SeriesNotas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alexandre
 */
public class Main {
    
    public static void main(String[] args) throws IOException {
        
       MulticastPublisher m = new MulticastPublisher();
       
       SeriesNotas av = new SeriesNotas();
       Scanner resposta = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            
            System.out.println("Nota do filme: " + av.getAvaliacoes().get(i).getSerie());    
            String nota = resposta.next();
            
            try{
                
                int notaNum = Integer.parseInt(nota);
                av.getAvaliacoes().get(i).setAvaliacao(notaNum);
                
                System.out.println(av.getAvaliacoes().get(i).toString());
                
            } catch(Exception e){
                
            }
        } // Fim for
        
      m.multicast(av);
    }
}
