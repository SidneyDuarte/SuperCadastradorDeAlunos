package br.com.caelum.supercadastradordealunos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by android6920 on 18/07/17.
 */

public class CadastroAluno extends AppCompatActivity{

    EditText nome;
    EditText telefone;
    EditText endereco;
    EditText site;
    RatingBar nota;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro_alunos);
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
            finish();
            return true;
        }

        return false;
    }
}
