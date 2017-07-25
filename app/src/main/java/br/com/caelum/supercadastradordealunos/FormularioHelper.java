package br.com.caelum.supercadastradordealunos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private Button botaoFoto;
    private ImageView imagem;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.nome);
        campoTelefone = (EditText) activity.findViewById(R.id.telefone);
        campoEndereco = (EditText) activity.findViewById(R.id.endereco);
        campoSite = (EditText) activity.findViewById(R.id.site);
        campoNota = (RatingBar) activity.findViewById(R.id.nota);
        botaoFoto = (Button) activity.findViewById(R.id.botao_foto);
        imagem = (ImageView) activity.findViewById(R.id.foto);

        aluno = new Aluno();
    }

    public Aluno getAluno(){
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String endereco = campoEndereco.getText().toString();
        String site = campoSite.getText().toString();
        double nota = campoNota.getRating();
        String caminhoFoto = (String) imagem.getTag();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEndereco(endereco);
        aluno.setSite(site);
        aluno.setNota(nota);
        aluno.setCaminhoFoto(caminhoFoto);

        return aluno;
    }

    public void colocaAlunoNoFormulario(Aluno aluno){
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEndereco.setText(aluno.getEndereco());
        campoSite.setText(aluno.getSite());
        campoNota.setRating(aluno.getNota().floatValue());

        if (aluno.getCaminhoFoto() != null) {
            atualizaImagem(aluno.getCaminhoFoto());
        }

        this.aluno = aluno;
    }

    public Button getBotaoFoto(){
        return botaoFoto;
    }

    public void atualizaImagem(String caminhoFoto){
        Bitmap btmFoto = BitmapFactory.decodeFile(caminhoFoto);
        Bitmap btmFotoReduzida = Bitmap.createScaledBitmap(btmFoto, 400, 300, true);

        imagem.setScaleType(ImageView.ScaleType.FIT_XY);
        imagem.setTag(caminhoFoto);
        imagem.setImageBitmap(btmFotoReduzida);
    }
}
