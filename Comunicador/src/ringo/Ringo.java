package ringo;

import Model.Avaliacao;
import Model.Usuario;
import comunicador.BdAvaliacoes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ringo extends Thread {

    Double menorDistancia = 0D;
    Double distancia = 0D;
    String nomeUsuarioMenorDistancia;

    List<Usuario> usuariosExistentes;

    public void recomendaParaUsuarios() {
        usuariosExistentes = recuperaUsuarios();
        for (int i = 0; i < usuariosExistentes.size(); i++) {
            try {
                System.out.println("------------------ Séries recomendadas para " + usuariosExistentes.get(i).getNome() + " ---------------");
                System.out.println(calcularRecomendacoes(usuariosExistentes.remove(i)));
                System.out.println("------------------ Séries recomendadas para " + usuariosExistentes.get(i).getNome() + " ---------------");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("=)");
            }
        }
    }

    // Calcula recomendações (função principal - chame ela)
    public List<String> calcularRecomendacoes(Usuario usuario) {
        // Usuário com menor distância ao que está sendo comparado:
        nomeUsuarioMenorDistancia = compararNotas(usuario);

        // Pegar recomendações
        return recuperaRecomendacoes(nomeUsuarioMenorDistancia, usuario.getNome());
    }

    // Recuperar recomendações
    public List<String> recuperaRecomendacoes(String usuarioMenorDistancia, String usuarioComparado) {
        List<String> seriesBemAvaliadas = new ArrayList<>();
        List<String> seriesNaoVistasERecomendadas = new ArrayList<>();
        // Recuperando séries bem avaliadas do usuário de menor distância
        for (Avaliacao avaliacao : BdAvaliacoes.avaliacoes) {
            if (avaliacao.getUsuario().getNome().equalsIgnoreCase(usuarioMenorDistancia) && avaliacao.getNota() >= 2) {
                seriesBemAvaliadas.add(avaliacao.getSerie());
            }
        }

        // Recuperando séries não vistas pelo usuário comparado
        for (Avaliacao avaliacao : BdAvaliacoes.avaliacoes) {
            if (avaliacao.getUsuario().getNome().equalsIgnoreCase(usuarioComparado)
                    && avaliacao.getNota() == 0
                    && seriesBemAvaliadas.contains(avaliacao.getSerie())) {
                seriesNaoVistasERecomendadas.add(avaliacao.getSerie());
            }
        }

        return seriesNaoVistasERecomendadas;
    }

    public String compararNotas(Usuario usuario) {
        String usuarioMenorDistancia = "";
        // Recupera os usuarios e remove o que será comparado com os outros
        List<Usuario> usuarios = recuperaUsuarios();
        usuarios.remove(usuario);
        // Recupera notas do usuário que irá comparar com os demais
        List<Integer> notasUsuarioComparado = pegaNotas(usuario);
        // Compara notas
        for (Usuario user : usuarios) {
            List<Integer> notasUsuario = pegaNotas(user);
            for (int i = 0; i < notasUsuario.size(); i++) {
                distancia += Math.pow(notasUsuario.get(0) - notasUsuarioComparado.get(0), 2);
            }
            if (distancia < menorDistancia || menorDistancia == 0) {
                menorDistancia = distancia;
                usuarioMenorDistancia = user.getNome();
            }
        }
        return usuarioMenorDistancia;
    }

    public List<Integer> pegaNotas(Usuario usuario) {
        List<Integer> notasUsuario = new ArrayList<>();
        for (Avaliacao avaliacao : BdAvaliacoes.avaliacoes) {
            for (Avaliacao av : BdAvaliacoes.avaliacoes) {
                if (av.getUsuario().getNome().equalsIgnoreCase(avaliacao.getUsuario().getNome())) {
                    notasUsuario.add(av.getNota());
                }
            }
        }
        return notasUsuario;
    }

    public List<Usuario> recuperaUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        // Recupera usuários
        for (Avaliacao avaliacao : BdAvaliacoes.avaliacoes) {
            if (!usuarios.contains(avaliacao.getUsuario())) {
                usuarios.add(avaliacao.getUsuario());
            }
        }
        return usuarios;
    }

    @Override
    public void run() {
        try {
            System.out.println("Iniciando recomendações...");
            Thread.sleep(1000);
            System.out.println("Recomendando séries:");
            while (true) {
                recomendaParaUsuarios();
                Thread.sleep(180000); // a cada 3 minutos as recomendações de séries são feitas
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Ringo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
