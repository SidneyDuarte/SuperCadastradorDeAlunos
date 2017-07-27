package br.com.caelum.supercadastradordealunos;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by android6920 on 26/07/17.
 */

public class ProvasActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (isTablet()){
            transaction.replace(R.id.prova_lista, new ListaProvaFragment());
            transaction.replace(R.id.prova_detalhes, new DetalhesProvaFragment());
        }else{
            transaction.replace(R.id.prova_view, new ListaProvaFragment());
        }

        transaction.commit();
    }

    public boolean isTablet(){
        return getResources().getBoolean(R.bool.isTablet);
    }
}

