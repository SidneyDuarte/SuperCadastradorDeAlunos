package br.com.caelum.supercadastradordealunos;

import java.util.List;

/**
 * Created by android6920 on 26/07/17.
 */

public class Prova {
    private String materia;
    private String data;
    private List<String> topicos;

    public Prova(String materia, String data) {
        this.materia = materia;
        this.data = data;
    }

    public String getMateria() {
        return materia;
    }

    public String getData() {
        return data;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    @Override
    public String toString(){
        return this.materia;
    }
}
