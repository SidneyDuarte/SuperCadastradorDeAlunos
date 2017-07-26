package br.com.caelum.supercadastradordealunos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

/**
 * Created by android6920 on 25/07/17.
 */

public class EnviaAlunosTask extends AsyncTask<Object, Void,String> {

    private ProgressDialog progressDialog;
    private Context context;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    public void onPreExecute(){
        this.progressDialog = ProgressDialog.show(context, "Aguarde", "carregando", true, false);
    }


    @Override
    public String doInBackground(Object... args){
        AlunoDao dao = new AlunoDao(context);
        List<Aluno> alunoList = dao.getLista();
        String json = new AlunoConverter().toJson(alunoList);

        return new WebClient().post(json);
    }

    @Override
    public void onPostExecute(String retorno){
        progressDialog.dismiss();
        Toast.makeText(context, retorno, Toast.LENGTH_LONG).show();

    }


}
