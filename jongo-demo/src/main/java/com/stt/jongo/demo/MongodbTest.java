package com.stt.jongo.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.mongodb.util.JSONSerializers;
import com.stt.jongo.demo.po.PersonInfo;
import org.bson.BSON;
import org.bson.Document;
import org.bson.json.JsonReader;
import org.bson.json.JsonWriter;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 使用mongondb驱动本身进行增删改查
 * <p>
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
public class MongodbTest {

    MongoClient mongoClient = null;
    MongoCollection mongoCollection = null;

    @Before
    public void connectMongo() {
        mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("jongo");
        mongoCollection = mongoDatabase.getCollection("person_info");

    }

    @Test
    public void testSave(){
        PersonInfo personInfo = new PersonInfo(10, "stt", "Male", "ClassMate");
        mongoCollection.insertOne(Document.parse("{ss:'ss',kf:'dsds'}"));//不能直接保存对象
//        System.out.println(personInfo.get_id());
    }

    @Test
    public void testFind() {
        FindIterable<Document> documents = mongoCollection.find();
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()) {
            Document document = iterator.next();
            System.out.println(document);
            ObjectId _id = document.getObjectId("_id");
            System.out.println("_id:" + _id);
        }

//        FindIterable findIterable = mongoCollection.find(PersonInfo.class);
    }

    @Test
    public void testUpdate(){
        long count = mongoCollection.count();
        System.out.println(count);
//        mongoCollection.updateOne("");
    }

    @Test
    public void testDelete(){
//        mongoCollection.deleteOne(BSON)
    }

    @After
    public void disConnectMongo() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
