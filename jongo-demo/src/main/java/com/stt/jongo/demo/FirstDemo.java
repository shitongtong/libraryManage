package com.stt.jongo.demo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.stt.jongo.demo.po.PersonInfo;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import java.util.Iterator;

/**
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
public class FirstDemo {

    public static void main(String[] args) {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient("localhost",27017);
            DB db = mongoClient.getDB("jongo");
            Jongo jongo = new Jongo(db);
            MongoCollection person_info = jongo.getCollection("person_info");
            MongoCursor<PersonInfo> personInfos = person_info.find().as(PersonInfo.class);
            Iterator<PersonInfo> iterator = personInfos.iterator();
            System.out.println("==================all========================");
            while (iterator.hasNext()){
                PersonInfo personInfo = iterator.next();
                System.out.println("personInfo:"+personInfo);
            }

            System.out.println("==================one========================");
            PersonInfo personInfo = person_info.findOne("{id:1}").as(PersonInfo.class);
            System.out.println("personInfo:"+personInfo);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }
}
