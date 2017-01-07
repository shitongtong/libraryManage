package com.stt.jongo.demo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.stt.jongo.demo.po.PersonInfo;
import org.bson.types.ObjectId;
import org.jongo.FindOne;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * 使用Jongo对mongodb进行增删改查
 * <p>
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
public class JongoTest {

    MongoClient mongoClient = null;
    MongoCollection mongoCollection = null;

    @Before
    public void connectMongo() {
        mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("jongo");
        Jongo jongo = new Jongo(db);
        mongoCollection = jongo.getCollection("person_info");
    }

    @Test
    public void testSave() {
        PersonInfo personInfo = new PersonInfo(4, "Marry", "Male", "ClassMate");
        WriteResult result = mongoCollection.save(personInfo);
        System.out.println(result);
    }

    @Test
    public void testUpdate(){
        mongoCollection.update(new ObjectId("586de199de4055240020c527")).with("{$inc:{id:2}}");
        mongoCollection.update(new ObjectId("586de199de4055240020c527")).with("{$set:{person_name:'Dark Update'}}");
    }

    @Test
    public void testFind(){
        MongoCursor<PersonInfo> personInfos = mongoCollection.find().as(PersonInfo.class);
        System.out.println("totalCount:"+personInfos.count());
        while (personInfos.hasNext()){
            PersonInfo personInfo = personInfos.next();
            System.out.println("all:personInfo:"+personInfo);
        }
        /*Iterator<PersonInfo> iterator = personInfos.iterator();
        while (iterator.hasNext()){
            PersonInfo personInfo = iterator.next();
            System.out.println("all:personInfo:"+personInfo);
        }*/
        PersonInfo personInfo = mongoCollection.findOne(new ObjectId("586de199de4055240020c527")).as(PersonInfo.class);
        System.out.println("findOne:personInfo:"+personInfo);
    }

    @Test
    public void testDelete(){
        WriteResult result = mongoCollection.remove("{ss:'ss'}");
        System.out.println(result);
    }

    @After
    public void disConnectMongo() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    /**
     * 不查询出某字段使用：
     * Projection
     */
    @Test
    public void testProjection(){
//        PersonInfo personInfo = mongoCollection.findOne("{'person_name':'Dark Update'}").projection("{id:1}").as(PersonInfo.class);
        //只查询name和id字段，其他字段不查
        PersonInfo personInfo = mongoCollection.findOne().projection("{name:1,id:1}").as(PersonInfo.class);
        System.out.println(personInfo); //PersonInfo{id=4, name='Marry', sex='null', relationship='null'}
    }

    /**
     * 在Jongo中Sort、Skip、Limit、Hint、Count基本和Mongo Shell一致。
     * Skip、Limit、Hint貌似没有生效
     */
    @Test
    public void testOtherFind(){
//        Iterator<PersonInfo> sort = (Iterator<PersonInfo>) mongoCollection.find().sort("{id:1}").as(PersonInfo.class);//sort {field:1} 升序，{field:-1} 降序
//        Iterator<PersonInfo> skip = (Iterator<PersonInfo>) mongoCollection.find().skip(1).as(PersonInfo.class);//查询时跳过多少条记录
//        Iterator<PersonInfo> limit = (Iterator<PersonInfo>) mongoCollection.find().limit(2).as(PersonInfo.class);//查询指定数量的记录
//        Iterator<PersonInfo> hint = (Iterator<PersonInfo>) mongoCollection.find().hint("{person_name:-1}").as(PersonInfo.class);//在查询过程中强制使用hint指定的索引方式，注意必须事先建立person_name字段的倒序索引。
        System.out.println(mongoCollection.find().sort("{id:1}").as(PersonInfo.class).count());
        System.out.println(mongoCollection.find().skip(2).as(PersonInfo.class).count());
        System.out.println(mongoCollection.find().limit(1).as(PersonInfo.class).count());
        System.out.println(mongoCollection.find().hint("{name:-1}").as(PersonInfo.class).count());
        System.out.println(mongoCollection.count("{id:4}"));//查询满足条件的记录数
    }

    /**
     * Jongo的查询模板
     几乎所有查询Jongo可以模板化:添加锚#。绑定参数可以BSON原语或任何复杂类型。
     */
    @Test
    public void testTemplet(){
        //相当于findOne("{id:2,person_name:'xiaohong'}")
        PersonInfo personInfo = mongoCollection.findOne("{id:#,name:#}",6,"Marry").as(PersonInfo.class);
        System.out.println(personInfo);
        //相当于 db.person_info.findOne({'address.regionId':'0755','address.privinceName':'shenzhen'});
//        PersonInfo personInfo2 = person_info.findOne("{address:#}",new Address("0755","shenzhen")).as(PersonInfo.class);
        //相当于db.person_info.find({id:{$in:[2,5]}});
        int[] ids = {4,5,6};
        Iterator<PersonInfo> ite =  mongoCollection.find("{id:{$in:#}}",ids).as(PersonInfo.class);
        while (ite.hasNext()){
            PersonInfo info = ite.next();
            System.out.println("info:"+info);
        }
    }

    /**
     * Jongo的正则查询
     以下几种正则查询都是等价的：
     */
    @Test
    public void testRegex(){
        PersonInfo personInfo = mongoCollection.findOne("{name:{$regex:#}}", "Mar*").as(PersonInfo.class);
        System.out.println(personInfo);

        PersonInfo personInfo1 = mongoCollection.findOne("{name:{$regex:'Mar*'}}").as(PersonInfo.class);
        System.out.println(personInfo1);

        PersonInfo personInfo2 = mongoCollection.findOne("{name:#}", Pattern.compile("Mar*")).as(PersonInfo.class);
        System.out.println(personInfo2);

        Pattern pattern = Pattern.compile("Mar*");
        PersonInfo personInfo3 = mongoCollection.findOne("{name:{$regex:'"+pattern+"'}}").as(PersonInfo.class);
        System.out.println(personInfo3);
    }
}
