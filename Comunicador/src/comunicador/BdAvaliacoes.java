package comunicador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BdAvaliacoes {
    private List<Usuario> avaliacoesUsuario = new ArrayList<>();

    public static Map<String, List<Usuario>> seriesAvaliadas = new HashMap<>();

    public BdAvaliacoes() {
        
    }

    public static void avaliarSerie(String serie, Usuario usuario) {
        List<Usuario> avaliacoesUsuario = BdAvaliacoes.seriesAvaliadas.get(serie);
        if (avaliacoesUsuario == null) {
            avaliacoesUsuario = new ArrayList<>();
        }
        if (avaliacoesUsuario.contains(usuario)) {
            System.out.println("usuario já avaliou essa série!");
        } else {
            avaliacoesUsuario.add(usuario);
            BdAvaliacoes.seriesAvaliadas.put(serie, avaliacoesUsuario);
        }

    }
    
    public static void inicializarSeries()
    {
        if (seriesAvaliadas.size() == 0) {
            seriesAvaliadas.put("Arcane", new ArrayList<>());
            seriesAvaliadas.put("Peaky Blinders", new ArrayList<>());
            seriesAvaliadas.put("Suits", new ArrayList<>());
        }
    }
}
