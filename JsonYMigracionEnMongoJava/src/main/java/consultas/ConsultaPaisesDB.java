package consultas;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import org.bson.Document;

import java.util.function.Consumer;

public class ConsultaPaisesDB {

    /**
     * 5.1) Codifique un método que seleccione los documentos de la colección países donde la región sea
     * Americas. Muestre el resultado por pantalla o consola.
     *
     * @param collection MongoCollection<Document>
     */
    // db.paises.find({"region": "Americas"})
    public static void buscarPorRegion(MongoCollection<Document> collection) {
        FindIterable<Document> documents = collection.find(Filters.eq("region", "Americas"));
        System.out.println("Paises de América: ");
        documents.forEach((Consumer<Document>) document -> {
            System.out.println(document.getString("nombrePais") + " - " + document.getString("capitalPais"));
        });
    }

    /**
     * 5.2) Codifique un método que seleccione los documentos de la colección países donde la región sea
     * Americas y la población sea mayor a 100000000. Muestre el resultado por pantalla o consola.
     *
     * @param collection MongoCollection<Document>
     */
    // db.paises.find({"region": "Americas", "poblacion": {$gt:100000000}})
    public static void buscarPorRegionYPoblacionMayorA(MongoCollection<Document> collection) {
        FindIterable<Document> documents = collection.find(Filters.and(Filters.eq("region", "Americas"),
                Filters.gt("poblacion", 100000000)));
        System.out.println("Paises de América con población mayor a 100000000: ");
        documents.forEach((Consumer<Document>) document -> {
            System.out.println(document.getString("nombrePais") + " - " + document.getString("capitalPais") + " población: " + document.getLong("poblacion"));
        });
    }

    /**
     * 5.3) Codifique un método que seleccione los documentos de la colección países donde la región sea
     * distinto de Africa. (investigue $ne). Muestre el resultado por pantalla o consola.
     *
     * @param collection MongoCollection<Document>
     */
    // db.paises.find({"region": {$ne: "Africa"}})
    public static void buscarPorRegionDistintaAfrica(MongoCollection<Document> collection) {
        FindIterable<Document> documents = collection.find(Filters.ne("region", "Africa"));
        System.out.println("Paises distintos de África: ");
        documents.forEach((Consumer<Document>) document -> {
            System.out.println(document.getString("nombrePais") + " - " + document.getString("capitalPais"));
        });
    }

    /**
     * 5.4) Codifique un método que actualice el documento de la colección países donde el name sea Egypt,
     * cambiando el name a “Egipto” y la población a 95000000
     *
     * @param collection MongoCollection<Document>
     */
    // db.paises.update({"nombrePais": "Egypt"}, {$set: {"nombrePais": "Egipto", "poblacion": 95000000}})
    public static void actualizarEgipt(MongoCollection<Document> collection) {
        collection.findOneAndUpdate(Filters.eq("nombrePais", "Egypt"), new Document("$set", new Document("nombrePais", "Egipto").append("poblacion", 95000000)));
        FindIterable<Document> documents = collection.find(Filters.eq("nombrePais", "Egipto"));
        documents.forEach((Consumer<Document>) document -> {
            System.out.println(document.getString("nombrePais") + " - " + document.getString("capitalPais") + " población: " + document.getInteger("poblacion"));
        });
    }

    /**
     * 5.5) Codifique un método que elimine el documento de la colección países donde el código del país sea 258
     *
     * @param collection MongoCollection<Document>
     */
    // db.paises.remove({"codigoPais": 258})
    public static void eliminarDoscientosCincuentaYOcho(MongoCollection<Document> collection) {
        collection.findOneAndDelete(Filters.eq("codigoPais", 258));
    }

    /**
     * 5.6) Describa que sucede al ejecutar el método drop() sobre una colección y sobre una base de datos.
     * Al ejecutar el método drop eliminaremos la colección completa
     * Es similar al drop table en SQL
     *
     * @param collection MongoCollection<Document>
     */
    // paises.drop()
    public static void eliminarColeccion(MongoCollection<Document> collection) {
        collection.drop();
    }

    /**
     * 5.7) Codifique un método que seleccione los documentos de la colección países cuya población sea
     * mayor a 50000000 y menor a 150000000. Muestre el resultado por pantalla o consola.
     *
     * @param collection MongoCollection<Document>
     */
    // db.paises.find({"poblacion": {$gt: 50000000}, "poblacion": {$lt:150000000}})
    public static void buscarPoblacionMayorAYMenorA(MongoCollection<Document> collection) {
        FindIterable<Document> documents = collection.find(Filters.and(Filters.gt("poblacion", 50000000),
                Filters.lt("poblacion", 150000000)));
        System.out.println("Paises cuya población es mayor a 50000000 y menor a 150000000: ");
        documents.forEach((Consumer<Document>) document -> {
            System.out.println(document.getString("nombrePais") + " - " + document.getString("capitalPais") + " población: " + document.get("poblacion"));
        });
    }

    /**
     * 5.8) Codifique un método que seleccione los documentos de la colección países ordenados por nombre
     * (name) en forma Ascendente. sort(). Muestre el resultado por pantalla o consola.
     *
     * @param collection MongoCollection<Document>
     */
    // db.paises.find().sort({"nombrePais":1})
    public static void buscarPorNombreOrdenadosDeFormaAscendente(MongoCollection<Document> collection) {
        FindIterable<Document> documents = collection.find().sort(new BasicDBObject("nombrePais", 1));
        documents.forEach((Consumer<Document>) document -> {
            System.out.println(document.getString("nombrePais") + " - " + document.getString("capitalPais"));
        });
    }

    /**
     * 5.9) Describa que sucede al ejecutar el método skip() sobre una colección. Ejemplifique con la colección
     * países.
     * Permite saltar números de elementos, por ejemplo en este método saltará 50 elementos y mostrará los que quedan
     *
     * @param collection MongoCollection<Document>
     */
    // db.paises.find().skip(50)
    public static void metodoSkipMongo(MongoCollection<Document> collection) {
        FindIterable<Document> documents = collection.find().skip(50);
        documents.forEach((Consumer<Document>) document -> {
            System.out.println(document.getString("nombrePais") + " - " + document.getString("capitalPais"));
        });
    }

    /**
     * 5.10) Describa y ejemplifique como el uso de expresiones regulares en Mongo puede reemplazar
     * el uso de la cláusula LIKE de SQL.
     * La cláusula LIKE en Mongo se reemplaza con expresiones regulares
     *
     * @param collection MongoCollection<Document>
     */
    //db.tu_coleccion.find({"campo": /.*busqueda.*/});
    //db.tu_coleccion.find({"campo": /.*busqueda.*/i}); para ignorar mayúsculas y minúsculas
    public static void metodoLikeMongo(MongoCollection<Document> collection) {
        FindIterable<Document> documents = collection.find(Filters.regex("nombrePais", ".*ge.*", "i"));
        System.out.println("Paises que tengan 'ge' ignorando mayúsculas y minúsculas en su nombre: ");
        documents.forEach((Consumer<Document>) document -> {
            System.out.println(document.getString("nombrePais"));
        });
    }

    /**
     * 5.11) Cree un nuevo índice para la colección países asignando el campo código como índice.
     * investigue createIndex())
     * La definición de índices es importante para una búsqueda más rápida y eficiente de documentos en una colección.
     * Los índices se pueden crear utilizando el método createIndex. Los índices se pueden crear en un solo campo o en
     * varios valores de campo.
     * Los índices se pueden encontrar mediante el método getIndexes.
     * Los índices se pueden eliminar utilizando dropIndex para índices individuales o dropIndexes para eliminar todos
     * los índices.
     * El método createIndex se utiliza para crear un índice basado en el "codigoPais" del documento.
     * El parámetro '1' indica que cuando el índice se crea con los valores del campo "codigoPais",
     * deben ordenarse en orden ascendente y -1 para orden descendente.
     *
     * @param collection MongoCollection<Document>
     */
    // db.paises.createIndex("codigoPais",1)
    public static void crearIndice(MongoCollection<Document> collection) {
        collection.createIndex(Indexes.ascending("codigoPais"));
    }

    /*
     * 5.12) Describa como se realiza un backup de la base de datos mongo países_db.
     * Para realizar un backup en mongo tenemos que tener la herramienta mongodump,
     * con ella al ejecutar el comando 'mongodump' desde la terminal nos guardará una copia
     * de seguridad de nuestras bases de datos.
     * Por defecto crea una carpeta llamada dump, que contiene todas nuestras bases de datos separadas por carpetas.
     * Para recuperar la copia en caso de haber eliminado la base de datos, hay que situarse en la carpeta dump desde
     * la consola de comandos y ejecutar el comando mongorestore
     * Podemos especificar qué bases de datos o qué colección en particular queremos como backup
     * mongodump --db mibasededatos (se hace backup a una base de datos en particular)
     * mongorestore --db mibasededatos dump/mibasededatos (se restaura una base de datos en particular)
     * mongodump --db mibasededatos --collection micolección (se hace backup de una colección en particula)
     * mongorestore --db mibasededatos --collection micolección dump/mibasededatos/micolección (se restaura una colección
     * en particular)
     */

}
