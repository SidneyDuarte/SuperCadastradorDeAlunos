package br.com.caelum.supercadastradordealunos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
}
