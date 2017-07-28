package br.com.caelum.supercadastradordealunos;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by android6920 on 27/07/17.
 */

public class Localizador {

    private Geocoder geocoder;

    public Localizador(Context context) {
        geocoder = new Geocoder(context);
    }

    public LatLng getCoordenadas(String endereco){
        try {
            List<Address> enderecos = geocoder.getFromLocationName(endereco, 1);

            if (!enderecos.isEmpty()){
                Address end = enderecos.get(0);
                LatLng latitude = new LatLng(end.getLatitude(), end.getLongitude());

                return latitude;
            }

            return null;


        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
