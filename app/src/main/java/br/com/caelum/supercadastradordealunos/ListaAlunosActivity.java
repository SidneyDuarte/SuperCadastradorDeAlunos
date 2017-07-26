package br.com.caelum.supercadastradordealunos;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.jar.Manifest;

public class ListaAlunosActivity extends AppCompatActivity {
    private ListView lista;
    private Button botaoNovo;
    private final int REQUEST_LIGACAO = 123;
    private Aluno aluno;
    private List<Aluno> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        this.lista = (ListView)findViewById(R.id.lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent editar = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                aluno = (Aluno) parent.getItemAtPosition(i);
                editar.putExtra("aluno", aluno);
                startActivity(editar);
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
        aluno = (Aluno) lista.getItemAtPosition(info.position);

        MenuItem excluir = menu.add("excluir");
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

        MenuItem ligar = menu.add("ligar");
        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String permissaoLigar = android.Manifest.permission.CALL_PHONE;

                if (ActivityCompat.checkSelfPermission(ListaAlunosActivity.this, permissaoLigar) == PackageManager.PERMISSION_GRANTED) {
                    fazerLigacao();
                }else{
                    ActivityCompat.requestPermissions(ListaAlunosActivity.this, new String[]{permissaoLigar}, REQUEST_LIGACAO);
                }
                return false;
            }
        });

        MenuItem sms = menu.add("SMS");
        Intent enviarSMS = new Intent(Intent.ACTION_VIEW);
        enviarSMS.setData(Uri.parse("sms:" + aluno.getTelefone()));
        enviarSMS.putExtra("sms_body", "Sua nota é: " + aluno.getNota());
        sms.setIntent(enviarSMS);

        MenuItem mapa = menu.add("achar no mapa");
        Intent abrirMapa = new Intent(Intent.ACTION_VIEW);
        abrirMapa.setData(Uri.parse("geo:0,0?z=16&q="+Uri.encode(aluno.getEndereco())));
        mapa.setIntent(abrirMapa);

        MenuItem site = menu.add("abrir site");
        Intent abrirSite = new Intent(Intent.ACTION_VIEW);
        abrirSite.setData(Uri.parse("http://" + aluno.getSite()));
        site.setIntent(abrirSite);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] resutados) {
        if (requestCode == REQUEST_LIGACAO){
            if (resutados[0] == PackageManager.PERMISSION_GRANTED) {
                fazerLigacao();
            }else{
                Toast.makeText(this, "Erro ao fazer ligação", Toast.LENGTH_SHORT);
            }
        }
    }

    @SuppressWarnings({"Missing Permissions"})
    private void fazerLigacao(){
        Intent ligar = new Intent(Intent.ACTION_CALL);
        ligar.setData(Uri.parse("tel:" + aluno.getTelefone()));
        startActivity(ligar);
    }

    private void carregaLista(){
        AlunoDao dao = new AlunoDao(this);
        this.alunos = dao.getLista();
        dao.close();

        ListaAlunosAdapter adapter = new ListaAlunosAdapter(alunos, this);
        this.lista.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.botao_enviar) {
            new EnviaAlunosTask(this).execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
