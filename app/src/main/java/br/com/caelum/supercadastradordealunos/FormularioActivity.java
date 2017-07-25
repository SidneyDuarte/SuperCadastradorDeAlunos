package br.com.caelum.supercadastradordealunos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.File;

/**
 * Created by android6920 on 18/07/17.
 */

public class FormularioActivity extends AppCompatActivity{

    private FormularioHelper helper;
    private final int REQUEST_TIRAR_FOTO = 666;
    private String caminhoFoto;

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

        helper.getBotaoFoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                Uri uriFoto = Uri.fromFile(new File(caminhoFoto));

                Intent tirarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                tirarFoto.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
                startActivityForResult(tirarFoto, REQUEST_TIRAR_FOTO);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if (requestCode == REQUEST_TIRAR_FOTO) {
            if (resultCode == RESULT_OK) {
                helper.atualizaImagem(caminhoFoto);
            }else{
                caminhoFoto = null;
            }
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
