package br.com.caelum.supercadastradordealunos;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by android6920 on 25/07/17.
 */

public class WebClient {

    public String post(String json){
        try {
            URL url = new URL("https://www.caelum.com.br/mobile");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoInput(true);
            con.setDoOutput(true);

            PrintStream saida = new PrintStream(con.getOutputStream());
            saida.println(json);

            con.connect();

            Scanner entrada = new Scanner(con.getInputStream());

            if (entrada.hasNext()){
                return entrada.next();
            }else{
                return "Sem resposta";
            }
        }catch(IOException e){
            String erro = e.getLocalizedMessage();
            return erro;
        }
    }
}
