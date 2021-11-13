package comunicador;

import java.io.Serializable;


public class Usuario implements Serializable{
    private String nome;
    private int notaSerie;
    
    public Usuario() {
    }

    
    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNotaSerie() {
        return notaSerie;
    }

    public void setNotaSerie(int notaSerie) {
        this.notaSerie = notaSerie;
    }
    
}
