package cn.stt.test;

import cn.stt.util.ListSortUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by stt on 2016/9/3.
 */
public class SimpleTest {

    @Test
    public void testMember() throws Exception {

        // 生成自定义对象，然后对它按照指定字段排序
        List<Member> listMember = new ArrayList<Member>();
        listMember.add(new Member(1, "wm123", 3, "1992-12-01"));
        listMember.add(new Member(2, "a234", 8, "1995-12-01"));
        listMember.add(new Member(3, "m456", 12, "1990-12-01"));
        System.out.println("Member当前顺序...");
        System.out.println(listMember);

        // 方式一排序输出
        System.out.println("Member默认排序（用自带的compareTo方法）后...");
        Collections.sort(listMember);
        System.out.println(listMember);
        System.out.println("Member倒序（用自带的compareTo方法）后...");
        Collections.sort(listMember, Collections.reverseOrder());
        System.out.println(listMember);

        // 方式二排序输出
        ListSortUtil<Member> msList = new ListSortUtil<Member>();
        msList.sortByMethod(listMember, "getUsername", false);
        System.out.println("Member按字段用户名排序后...");
        System.out.println(listMember);

        msList.sortByMethod(listMember, "getLevel", false);
        System.out.println("Member按字段级别排序后...");
        System.out.println(listMember);

        msList.sortByMethod(listMember, "getBirthday", true);
        System.out.println("Member按字段出生日期倒序后...");
        System.out.println(listMember);

        System.out.println("----------------------------------");
        ListSortUtil<Member> msList1 = new ListSortUtil<Member>();
        msList1.sortByField(listMember, "birthday", true);
        System.out.println(listMember);

    }

    /*@Test
    public void testSolr(){
        String url = "http://127.0.0.1:8983/solr";
        SolrServer solrServer = new SolrServer(url);
        solrServer.setMaxRetries(1);
        solrServer.setConnectionTimeout(60000);
        solrServer.setParser(new XMLResponseParser());
        solrServer.setSoTimeout(10000);
        solrServer.setDefaultMaxConnectionsPerHost(100);
        solrServer.setMaxTotalConnections(100);
        solrServer.setFollowRedirects(false);
        solrServer.setAllowCompression(true);

        SolrQuery query=new SolrQuery();
        query.setQuery("name:doc");
        SolrQuery.SortClause sortClause=new SolrQuery.SortClause("name",SolrQuery.ORDER.desc);
        query.addSort(sortClause);
        QueryResponse resp=solrServer.query(query);
        SolrDocumentList docs=resp.getResults();
        for(SolrDocument doc:docs){
            String id=(String)doc.get("id");
            String name=(String)doc.get("name");
            Float price=(Float)doc.get("price");
            System.out.println("id="+id+"\t name="+name+"\t price="+price);
        }*/
}
