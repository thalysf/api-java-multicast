package comunicador;

import Model.Avaliacao;
import java.util.ArrayList;
import java.util.List;

public class BdAvaliacoes {

    public static List<String> series = new ArrayList<>();
    public static List<Avaliacao> avaliacoes = new ArrayList<>();

    public BdAvaliacoes() {

    }

    public static void avaliarSerie(Avaliacao avaliacao) {
        boolean ok = true;
        // Verifica se série da avaliação existe, senão insere
        if(!series.contains(avaliacao.getSerie()))
        {
            series.add(avaliacao.getSerie());
        }
        for (Avaliacao av : avaliacoes) {
            if (av.getSerie().equalsIgnoreCase(avaliacao.getSerie()) 
                    && av.getUsuario().getNome().equalsIgnoreCase(avaliacao.getUsuario().getNome())) {
              //  System.out.println("Avaliação atualizada!");
                av.setNota(avaliacao.getNota());
                ok = false;
            }
        }
        if(ok)
            avaliacoes.add(avaliacao);
    }

    public static void inicializarSeries() {
        if (series.isEmpty()) {
            series.add("Arcane");
            series.add("Peaky Blinders");
            series.add("Suits");
        }
    }
}
