package br.com.caelum.supercadastradordealunos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by android6920 on 18/07/17.
 */

public class FormularioActivity extends AppCompatActivity{

    private FormularioHelper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro_alunos);
        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if (aluno != null) {
            helper.colocaAlunoNoFormulario(aluno);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.menu_salvar){
            AlunoDao alunoDao = new AlunoDao(this);
            Aluno aluno = helper.getAluno();

            if (aluno.getId() == null) {
                alunoDao.insere(aluno);
            }else{
                alunoDao.alterar(aluno);
            }

            alunoDao.close();

            finish();
            return true;
        }

        return false;
    }
}
