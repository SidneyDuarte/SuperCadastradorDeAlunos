package br.com.caelum.supercadastradordealunos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by android6920 on 26/07/17.
 */

public class DetalhesProvaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle saveInstanceState){
        View layout = inflater.inflate(R.layout.fragment_detalhe_prova, viewGroup, false);

        return layout;
    }
}
