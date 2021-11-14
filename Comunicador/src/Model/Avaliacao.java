package Model;

import java.io.Serializable;
import java.util.Objects;

public class Avaliacao implements Serializable{
    private Usuario usuario;
    private String serie;
    private int nota;

    public Avaliacao() {
    }

    
    
    public Avaliacao(Usuario usuario, String serie, int nota) {
        this.usuario = usuario;
        this.serie = serie;
        this.nota = nota;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
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
        final Avaliacao other = (Avaliacao) obj;
        if (this.nota != other.nota) {
            return false;
        }
        if (!Objects.equals(this.serie, other.serie)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "usuario=" + usuario + ", serie=" + serie + ", nota=" + nota + '}';
    }
    
    
}
