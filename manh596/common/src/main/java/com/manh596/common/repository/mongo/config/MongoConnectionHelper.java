package com.manh596.common.repository.mongo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class MongoConnectionHelper {
    public SimpleMongoDbFactory getMongoDbFactory(MongoProperties mongo) {
        MongoCredential mongoCredential = MongoCredential.createCredential(mongo.getUsername(), mongo.getAuthenticationDatabase(), mongo.getPassword());
        List<MongoCredential> mongoCredentialList = Collections.singletonList(mongoCredential);
        ServerAddress serverAddress = new ServerAddress(mongo.getHost(), mongo.getPort());
        MongoClient mongoClient = new MongoClient(serverAddress, mongoCredentialList);
        return new SimpleMongoDbFactory(mongoClient,
                mongo.getDatabase());
    }
}