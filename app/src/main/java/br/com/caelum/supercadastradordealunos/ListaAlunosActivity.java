package br.com.caelum.supercadastradordealunos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlunosActivity extends AppCompatActivity {
    private ListView lista;
    private Button botaoNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String[] alunos = {"Batman", "Mulher Maravilha", "Super Homem"};
        this.lista = (ListView)findViewById(R.id.lista);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListaAlunosActivity.this, "Item clicado na posição " + i, Toast.LENGTH_SHORT).show();
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nome = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(ListaAlunosActivity.this, nome, Toast.LENGTH_LONG).show();

                return true;
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

    }


}
