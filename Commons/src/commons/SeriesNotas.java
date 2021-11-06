/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SeriesNotas implements Serializable {
    
    List<Avaliacao> avaliacoes = new ArrayList<>( Arrays.asList( 
        new Avaliacao("Planeta 01", -1),
        new Avaliacao("Macacos 02", -1),
        new Avaliacao("Macacos 03", -1),
        new Avaliacao("Macacos 04", -1),
        new Avaliacao("Macacos 05", -1),
        new Avaliacao("Macacos 06", -1),
        new Avaliacao("Macacos 07", -1),
        new Avaliacao("Macacos 08", -1),
        new Avaliacao("Macacos 09", -1),
        new Avaliacao("Macacos 10", -1)
    ));

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public String toString() {
        return "SeriesNotas{" + "avaliacoes=" + avaliacoes + '}';
    }
    
    public SeriesNotas(){}
}
