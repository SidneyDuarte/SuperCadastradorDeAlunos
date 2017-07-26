package br.com.caelum.supercadastradordealunos;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by android6920 on 25/07/17.
 */

public class AlunoConverter {
    public String toJson(List<Aluno> alunos) {
        Gson gson = new Gson();
        String stringJson = "{list: [{aluno:" + gson.toJson(alunos) + "}]}";

        return stringJson;
    }
}
