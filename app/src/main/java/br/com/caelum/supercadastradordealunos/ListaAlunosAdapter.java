package br.com.caelum.supercadastradordealunos;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by android6920 on 24/07/17.
 */

public class ListaAlunosAdapter extends BaseAdapter {

    private Activity activity;
    private List<Aluno> alunos;

    public ListaAlunosAdapter(List<Aluno> alunos, Activity activity) {
        this.activity = activity;
        this.alunos= alunos;
    }

    @Override
    public View getView(int posicao, View contentView, ViewGroup parent){
        Aluno aluno = alunos.get(posicao);
        contentView = activity.getLayoutInflater().inflate(R.layout.item, parent, false);

        TextView nome = (TextView) contentView.findViewById(R.id.nome_aluno);
        nome.setText(aluno.getNome());

        Bitmap btmFoto = null;

        if (aluno.getCaminhoFoto() != null) {
            btmFoto = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        }else{
            btmFoto = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
        }

        btmFoto = Bitmap.createScaledBitmap(btmFoto, 100, 70, true);

        ImageView foto = (ImageView) contentView.findViewById(R.id.foto_aluno);
        foto.setImageBitmap(btmFoto);

        return contentView;
    }

    @Override
    public int getCount(){
        return alunos.size();
    }

    @Override
    public Object getItem(int posicao){
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao){
        return alunos.get(posicao).getId();
    }
}
