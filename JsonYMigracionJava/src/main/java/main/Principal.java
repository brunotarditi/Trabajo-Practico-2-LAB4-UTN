package main;

import entity.Pais;
import entity.PaisesJSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entityDAO.PaisDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Tarditi
 */
public class Principal {
    public static void main(String[] args) throws Exception {

        PaisDAO paisDAO = new PaisDAO();
        Pais pais;
        /*
         * Ejecute una llamada mediante a la siguiente URL tipo RESTful
         * https://restcountries.eu/rest/v2/callingcode/{callingcode}
         * Obtenga la información y migre la misma a la tabla país creada anteriormente. El proceso debe ejecutarse
         * para los códigos desde 1 hasta 300, contemplando que si alguno de los códigos no retorna datos se
         * continúe con el siguiente.
         */
        for (int codigo = 1; codigo <= 300; codigo++) {
            URL url = new URL("https://restcountries.eu/rest/v2/callingcode/" + codigo);
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
                Gson gson = new Gson();
                List<PaisesJSON> paisesJSONS = gson.fromJson(inputStreamReader, new TypeToken<List<PaisesJSON>>() {
                }.getType());
                pais = new Pais();
                for (PaisesJSON paisesJSON : paisesJSONS) {
                    pais.setCodigoPais(Integer.parseInt(paisesJSON.getCallingCodes().get(0)));
                    pais.setNombrePais(paisesJSON.getName());
                    pais.setCapitalPais(paisesJSON.getCapital());
                    pais.setRegion(paisesJSON.getRegion());
                    pais.setPoblacion(paisesJSON.getPopulation());
                    pais.setLatitud(paisesJSON.getLatlng().get(0));
                    pais.setLongitud(paisesJSON.getLatlng().get(1));
                    boolean existePais = paisDAO.buscarPorCodigo(new Pais(pais.getCodigoPais()));
                    if (existePais) {
                        paisDAO.actualizar(pais);
                    } else {
                        paisDAO.insertar(pais);
                    }
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}