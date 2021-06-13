package mongoConnect;

import com.mongodb.MongoClient;

public class MongoConnectClient {

    public static MongoClient createConnection() {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient("localhost", 27017);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return mongoClient;
    }
}
