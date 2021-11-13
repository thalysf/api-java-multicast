package comunicador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BdAvaliacoes {
    private static List<Usuario> avaliacoesUsuario = new ArrayList<>();

    public static Map<String, List<Usuario>> seriesAvaliadas() {
        Map<String, List<Usuario>> series = new HashMap<>();
        series.put("Arcane", avaliacoesUsuario);
        series.put("Peaky Blinders", avaliacoesUsuario);
        series.put("Suits", avaliacoesUsuario);
        series.put("Chuck", avaliacoesUsuario);
        series.put("Elite", avaliacoesUsuario);
        series.put("3%", avaliacoesUsuario);
        series.put("B99", avaliacoesUsuario);
        series.put("Outer Banks", avaliacoesUsuario);
        series.put("You", avaliacoesUsuario);
        series.put("Dark", avaliacoesUsuario);
        return series;
    }

    public BdAvaliacoes() {

    }

    public static void avaliarSerie(String serie, Usuario usuario) {
        List<Usuario> avaliacoesUsuario = BdAvaliacoes.seriesAvaliadas().get(serie);
        if (avaliacoesUsuario == null) {
            avaliacoesUsuario = new ArrayList<>(Arrays.asList(usuario));
        }
        avaliacoesUsuario.add(usuario);

        BdAvaliacoes.seriesAvaliadas().put(serie, avaliacoesUsuario);
    }
}
