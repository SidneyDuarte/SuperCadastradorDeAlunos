package br.com.caelum.supercadastradordealunos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {
    private ListView lista;
    private Button botaoNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        this.lista = (ListView)findViewById(R.id.lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListaAlunosActivity.this, "Item clicado na posição " + i, Toast.LENGTH_SHORT).show();
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                return false;
            }
        });

        this.botaoNovo = (Button)findViewById(R.id.novo);

        botaoNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(lista);

    }

    @Override
    protected  void onResume(){
        super.onResume();
        this.carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        final Aluno aluno = (Aluno) lista.getItemAtPosition(info.position);
        MenuItem excluir = menu.add("Excluir");
        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AlunoDao dao = new AlunoDao(ListaAlunosActivity.this);
                dao.deletar(aluno);
                dao.close();
                carregaLista();
                return true;
            }
        });
    }

    private void carregaLista(){
        AlunoDao dao = new AlunoDao(this);
        List<Aluno> alunos = dao.getLista();
        dao.close();

        ArrayAdapter<Aluno>adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        this.lista.setAdapter(adapter);
    }


}
