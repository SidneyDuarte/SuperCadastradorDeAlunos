package br.com.caelum.supercadastradordealunos;

import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by android6920 on 19/07/17.
 */

public class FormularioHelper {
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEndereco;
    private EditText campoSite;
    private RatingBar campoNota;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.nome);
        campoTelefone = (EditText) activity.findViewById(R.id.telefone);
        campoEndereco = (EditText) activity.findViewById(R.id.endereco);
        campoSite = (EditText) activity.findViewById(R.id.site);
        campoNota = (RatingBar) activity.findViewById(R.id.nota);

        aluno = new Aluno();
    }

    public Aluno getAluno(){
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String endereco = campoEndereco.getText().toString();
        String site = campoSite.getText().toString();
        double nota = campoNota.getRating();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEndereco(endereco);
        aluno.setSite(site);
        aluno.setNota(nota);

        return aluno;
    }

    public void colocaAlunoNoFormulario(Aluno aluno){
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEndereco.setText(aluno.getEndereco());
        campoSite.setText(aluno.getSite());
        campoNota.setRating(aluno.getNota().floatValue());

        this.aluno = aluno;
    }


}
