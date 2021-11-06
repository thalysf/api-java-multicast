/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import java.io.Serializable;

public class Avaliacao implements Serializable {
    
    String serie;

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "serie=" + serie + ", avaliacao=" + avaliacao + '}';
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
    int avaliacao;
    
    public Avaliacao(String serie, int avaliacao){
        
        this.serie = serie;
        this.avaliacao = avaliacao;
    }
    
    public Avaliacao(){}
}
