package br.com.caelum.supercadastradordealunos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by android6920 on 26/07/17.
 */

public class ListaProvaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle b){
        View view = inflater.inflate(R.layout.fragment_lista_prova, parent, false);
        ListView lista = (ListView) view.findViewById(R.id.lista_prova);

        List<Prova> provas = new ArrayList<>();
        Prova prova1 = new Prova("Matematica", "01/10/1999");
        prova1.setTopicos(Arrays.asList("Geometria", "Matriz"));
        provas.add(prova1);

        Prova prova2 = new Prova("Historia", "06/06/1966");
        prova1.setTopicos(Arrays.asList("Historia do Brasil", "Historia antiga"));
        provas.add(prova2);

        Prova prova3 = new Prova("Português", "01/10/1991");
        prova1.setTopicos(Arrays.asList("Artigos", "Verbos"));
        provas.add(prova3);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getActivity(), android.R.layout.simple_list_item_1, provas);
        lista.setAdapter(adapter);

        return view;


    }
}
