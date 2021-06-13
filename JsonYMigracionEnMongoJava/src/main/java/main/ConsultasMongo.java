package main;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import consultas.ConsultaPaisesDB;
import mongoConnect.MongoConnectClient;
import org.bson.Document;

public class ConsultasMongo {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoConnectClient.createConnection();
        if (mongoClient != null) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("paises_db");
            MongoCollection<Document> paises = mongoDatabase.getCollection("paises");
            ConsultaPaisesDB.buscarPorRegion(paises);
            //ConsultaPaisesDB.buscarPorRegionYPoblacionMayorA(paises);
            //ConsultaPaisesDB.buscarPorRegionDistintaAfrica(paises);
            //ConsultaPaisesDB.actualizarEgipt(paises);
            //ConsultaPaisesDB.eliminarDoscientosCincuentaYOcho(paises);
            //ConsultaPaisesDB.eliminarColeccion(paises);
            //ConsultaPaisesDB.buscarPoblacionMayorAYMenorA(paises);
            //ConsultaPaisesDB.buscarPorNombreOrdenadosDeFormaAscendente(paises);
            //ConsultaPaisesDB.metodoSkipMongo(paises);
            //ConsultaPaisesDB.metodoLikeMongo(paises);
            //ConsultaPaisesDB.crearIndice(paises);
            mongoClient.close();
        } else {
            System.out.println("Error: Conexión no establecida");
        }
    }
}
