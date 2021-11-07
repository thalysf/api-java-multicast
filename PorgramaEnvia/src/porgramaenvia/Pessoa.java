/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package porgramaenvia;


import java.io.Serializable;

public class Pessoa implements Serializable {
    
    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    char sexo;
    
    public Pessoa(String nome, char sexo){
        this.nome = nome;
        this.sexo = sexo;
        
    }
}
