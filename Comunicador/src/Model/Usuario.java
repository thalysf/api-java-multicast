package Model;

import java.io.Serializable;
import java.util.Objects;


public class Usuario implements Serializable{
    private String nome;
    private int notaSerie;
    
    public Usuario() {
    }

    public Usuario(String nome, int notaSerie) {
        this.nome = nome;
        this.notaSerie = notaSerie;
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

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", notaSerie=" + notaSerie + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
 
    
}
