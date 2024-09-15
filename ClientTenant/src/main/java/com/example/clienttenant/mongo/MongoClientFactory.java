package com.example.clienttenant.mongo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoClientFactory {

    public MongoClient getMongoClient() {
        return MongoClients.create("mongodb://localhost:27017/spring");
    }
}
