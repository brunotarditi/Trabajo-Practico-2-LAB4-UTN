package main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import model.PaisesJSON;
import mongoConnect.MongoConnectClient;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class Principal {
    public static void main(String[] args) throws Exception {

        /*
         * Ejecute una llamada mediante a la siguiente URL tipo RESTful
         * https://restcountries.eu/rest/v2/callingcode/{callingcode}
         * Obtenga la información y migre la misma a la tabla país creada anteriormente. El proceso debe ejecutarse
         * para los códigos desde 1 hasta 300, contemplando que si alguno de los códigos no retorna datos se
         * continúe con el siguiente.
         */

        int codigoPais = 0;
        String nombrePais = "", capitalPais = "", region = "";
        long poblacion = 0;
        double latitud = 0, longitud = 0;

        MongoClient mongoClient = MongoConnectClient.createConnection();
        if (mongoClient != null) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("paises_db");
            MongoCollection<Document> paises = mongoDatabase.getCollection("paises");
            for (int codigo = 1; codigo <= 300; codigo++) {
                URL url = new URL("https://restcountries.eu/rest/v2/callingcode/" + codigo);
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
                    Gson gson = new Gson();
                    List<PaisesJSON> paisesJSONS = gson.fromJson(inputStreamReader, new TypeToken<List<PaisesJSON>>() {
                    }.getType());
                    for (PaisesJSON paisesJSON : paisesJSONS) {
                        codigoPais = Integer.parseInt(paisesJSON.getCallingCodes().get(0));
                        nombrePais = paisesJSON.getName();
                        capitalPais = paisesJSON.getCapital();
                        region = paisesJSON.getRegion();
                        poblacion = paisesJSON.getPopulation();
                        latitud = paisesJSON.getLatlng().get(0);
                        longitud = paisesJSON.getLatlng().get(1);

                        int existeCodigo = 0;
                        FindIterable<Document> documents = paises.find(new Document("codigoPais", codigoPais));
                        for (Document document : documents) {
                            existeCodigo = document.getInteger("codigoPais", codigoPais);
                        }
                        if (existeCodigo == codigoPais) {
                            Document pais = new Document("codigoPais", codigoPais)
                                    .append("nombrePais", nombrePais)
                                    .append("capitalPais", capitalPais)
                                    .append("region", region)
                                    .append("poblacion", poblacion)
                                    .append("latitud", latitud)
                                    .append("longitud", longitud);
                            paises.updateOne(Filters.eq("codigoPais", codigoPais), new Document("$set", pais));
                        } else {
                            Document pais = new Document("_id", new ObjectId())
                                    .append("codigoPais", codigoPais)
                                    .append("nombrePais", nombrePais)
                                    .append("capitalPais", capitalPais)
                                    .append("region", region)
                                    .append("poblacion", poblacion)
                                    .append("latitud", latitud)
                                    .append("longitud", longitud);
                            paises.insertOne(pais);
                        }
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            mongoClient.close();
        } else {
            System.out.println("Error: Conexión no establecida");
        }

    }
}

