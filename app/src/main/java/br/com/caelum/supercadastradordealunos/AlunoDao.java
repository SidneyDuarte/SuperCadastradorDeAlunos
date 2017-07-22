package br.com.caelum.supercadastradordealunos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android6920 on 20/07/17.
 */

public class AlunoDao extends SQLiteOpenHelper{
    private final String TABELA = "Aluno";

    public AlunoDao(Context contexto){
        super(contexto, "CadastroAluno",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE " + TABELA +
                "(ID INTEGER NOT NULL PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "telefone TEXT," +
                "endereco TEXT," +
                "site TEXT," +
                "nota REAL" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        String sql = "DROP TABLE " + TABELA + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Aluno> getLista(){
        List<Aluno> alunos = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA, null);

        while(cursor.moveToNext()){
            Aluno aluno = new Aluno();

            aluno.setId(cursor.getLong(cursor.getColumnIndex("ID")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));

            alunos.add(aluno);
        }

        cursor.close();
        return alunos;

    }

    public void alterar(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());

        String[] args = {aluno.getId().toString()};

        getWritableDatabase().update(TABELA, values, "ID = ?", args);
    }

    public void deletar(Aluno aluno){
        getWritableDatabase().delete(TABELA, "ID = ?", new String[] {aluno.getId().toString()});
    }
}
