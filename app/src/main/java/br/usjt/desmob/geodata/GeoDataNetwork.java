package br.usjt.desmob.geodata;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by asbonato on 06/10/17.
 */

public class GeoDataNetwork {

    public static Pais[] buscarPaises(String url, String regiao) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ArrayList<Pais> paises = new ArrayList<>();

        Request request = new Request.Builder()
                .url(url+regiao)
                .build();

        Response response = client.newCall(request).execute();

        String resultado = response.body().string();

        try {
            JSONArray vetor = new JSONArray(resultado);
            for(int i = 0; i < vetor.length(); i++){
                JSONObject item = (JSONObject) vetor.get(i);
                Pais pais = new Pais();
                try {
                    pais.setArea(item.getInt("area"));
                } catch (Exception e){
                    pais.setArea(0);
                }
                pais.setBandeira(item.getString("flag"));
                pais.setCapital(item.getString("capital"));
                pais.setNome(item.getString("name"));
                pais.setRegiao(item.getString("region"));
                pais.setCodigo3(item.getString("alpha3Code"));
                //completar os campos em casa

                try {
                    pais.setGini(item.getDouble("gini"));
                } catch (Exception e) {
                    pais.setGini(0.0);
                }
                try {
                    pais.setPopulacao(item.getInt("population"));
                } catch (Exception e) {
                    pais.setPopulacao(0);
                }
                pais.setDemonimo(item.getString("demonym"));
                pais.setSubRegiao(item.getString("subregion"));

                JSONArray latlng = item.getJSONArray("latlng");

                try {
                    pais.setLatitude(latlng.getDouble(0));
                } catch (Exception e) {
                    pais.setLatitude(0);
                }
                try {
                    pais.setLongitude(latlng.getDouble(1));
                } catch (Exception e) {
                    pais.setLongitude(0);
                }

                JSONArray idiomas = item.getJSONArray("languages");
                ArrayList<String> idioma = new ArrayList<>();
                for (int j = 0; j < idiomas.length(); j++){
                    String stg = idiomas.getString(j);
                    idioma.add(stg);
                }
                pais.setIdiomas(idioma);

                JSONArray moedas = item.getJSONArray("currencies");
                ArrayList<String> moeda = new ArrayList<>();
                for (int j = 0; j < moedas.length(); j++){
                    String stg = moedas.getString(j);
                    moeda.add(stg);
                }
                pais.setMoedas(moeda);

                JSONArray fusos = item.getJSONArray("timezones");
                ArrayList<String> fuso = new ArrayList<>();
                for (int j = 0; j < fusos.length(); j++){
                    String stg = fusos.getString(j);
                    fuso.add(stg);
                }
                pais.setFusos(fuso);

                JSONArray fronteiras = item.getJSONArray("topLevelDomain");
                ArrayList<String> fronteira = new ArrayList<>();
                for (int j = 0; j < fronteiras.length(); j++){
                    String stg = fronteiras.getString(j);
                    fronteira.add(stg);
                }
                pais.setFronteiras(fronteira);

                JSONArray dominios = item.getJSONArray("topLevelDomain");
                ArrayList<String> dominio = new ArrayList<>();
                for (int j = 0; j < dominios.length(); j++){
                    String stg = dominios.getString(j);
                    dominio.add(stg);
                }
                pais.setDominios(dominio);

                Log.i("PAIS: ",pais.toString());
                paises.add(pais);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        return paises.toArray(new Pais[0]);
    }

    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
