package br.com.caelum.supercadastradordealunos;

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

    private FormularioHelper formulario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro_alunos);
        formulario = new FormularioHelper(this);

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
            Aluno aluno = formulario.getAluno();
            Log.i("Nome do aluno", aluno.getNome());

            finish();
            return true;
        }

        return false;
    }
}
