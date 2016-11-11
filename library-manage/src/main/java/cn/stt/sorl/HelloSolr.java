package cn.stt.sorl;

/**
 * Created by Administrator on 2016-11-07.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class HelloSolr {

    private static final String URL="http://10.41.2.82:8080/solr";

    private static final String zkHost="10.41.2.82:2181,10.41.2.83:2181,10.41.2.84:2181,10.41.2.85:2181,10.41.2.86:2181";




    public static void main(String[] args) {
        SolrServer server= getCloudSolrServer();
        try {
            deleteAllIndex(server);
            createIndex(server);
            searchIndex(server);
            System.out.println("-------------------------------------------------");
            batchCreateIndex(server);
            searchIndex(server);
            System.out.println("-------------------------------------------------");
            createIndexWithAutoCommit(server);
            searchIndex(server);
            System.out.println("-------------------------------------------------");
            createIndexByBean(server);
            searchIndex2(server);
            System.out.println("-------------------------------------------------");
            batchCreateIndexByBean(server);
            searchIndex2(server);
            System.out.println("-------------------------------------------------");
            updateIndexByBean(server);
            searchIndex2(server);
            System.out.println("-------------------------------------------------");
            updateIndex(server);
            searchIndex(server);
            System.out.println("-------------------------------------------------");
            searchIndex2Bean(server);
            System.out.println("-------------------------------------------------");
            searchIndexByHighlight(server);
            System.out.println("-------------------------------------------------");
            searchIndexWithPage(server,0,3);
            System.out.println("-------------------------------------------------");
            searchIndexWithPage(server,3,3);
            System.out.println("-------------------------------------------------");
            searchIndex2WithPage(server,0,3);
            System.out.println("-------------------------------------------------");
            searchIndex2WithPage(server,3,3);
            System.out.println("-------------------------------------------------");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 构造HttpSolrServer
     * @return
     */
    public static HttpSolrServer getHttpSolrServer(){
        HttpSolrServer server =new HttpSolrServer(URL);
        server.setMaxRetries(1);
        server.setConnectionTimeout(60000);
        server.setParser(new XMLResponseParser());
        server.setSoTimeout(10000);
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false);
        server.setAllowCompression(true);
        return server;
    }


    /**
     * 构造CloudSolrServer
     * @return
     */
    public static CloudSolrServer getCloudSolrServer(){
        CloudSolrServer csserver=new CloudSolrServer(zkHost);
        csserver.setDefaultCollection("inspur");
        return csserver;
    }


    /**
     * 删除所有的索引
     * @throws SolrServerException
     * @throws IOException
     */
    public static void deleteAllIndex( SolrServer server) throws SolrServerException, IOException{
        server.deleteByQuery("*:*");
        server.commit();
    }

    /*
    * 创建索引
    */
    public static void createIndex( SolrServer server) throws SolrServerException, IOException {
        SolrInputDocument doc=new SolrInputDocument();
        doc.addField("id", "id1", 1.0f);
        doc.addField("name", "doc 1", 1.0f);
        doc.addField("price", 10);
        server.add(doc);
        server.commit();
    }


    /*
    * 通过javabean来创建索引
    */
    public static void createIndexByBean( SolrServer server) throws SolrServerException, IOException {
        Item item=new Item();
        item.setId("item1");
        item.setCategories(new String[]{"aaa","bbb","ccc"});
        List<String> list=new ArrayList<String>();
        list.add("qqqq");
        list.add("mmmm");
        list.add("nnnnn");
        item.setFeatures(list);
        server.addBean(item);
        server.commit();
    }

    /*
    * 通过javabean来批量创建索引
    */
    public static void batchCreateIndexByBean(SolrServer server) throws SolrServerException, IOException {
        List<Item> items=new ArrayList<Item>();
        for(int i=0;i<10;i++){
            Item item=new Item();
            item.setId("item "+i);
            item.setCategories(new String[]{"aaa","bbb "+i,"ccc "+i});
            List<String> list=new ArrayList<String>();
            list.add("qqqq "+i);
            list.add("mmmm "+i);
            list.add("nnnnn "+i);
            item.setFeatures(list);
            items.add(item);
        }
        server.addBeans(items);
        server.commit();
    }

    /*
    * 批量创建索引
    */
    public static void batchCreateIndex(SolrServer server) throws SolrServerException, IOException {
        List<SolrInputDocument> docs=new ArrayList<SolrInputDocument>();
        for(int i=10;i<20;i++){
            SolrInputDocument doc=new SolrInputDocument();
            doc.addField("id", "id"+i, 1.0f);
            doc.addField("name", "doc "+i, 1.0f);
            doc.addField("price", 2.0f*i);
            docs.add(doc);
        }
        server.add(docs);
        server.commit();
    }

    /*
    * 创建索引,自动提交
    */
    public static void createIndexWithAutoCommit( SolrServer server) throws SolrServerException, IOException {
        SolrInputDocument doc=new SolrInputDocument();
        doc.addField("id", "id100", 1.0f);
        doc.addField("name", "doc 100", 1.0f);
        doc.addField("price", 10);
        UpdateRequest req=new UpdateRequest();
        req.setAction(UpdateRequest.ACTION.COMMIT, false, false);
        req.add(doc);
        UpdateResponse resp=req.process(server);

    }



    /*
    * 通过javabean的方式来修改索引
    * id相同，即为修改
    */
    public static void updateIndexByBean( SolrServer server) throws SolrServerException, IOException {
        Item item=new Item();
        item.setId("item1");
        item.setCategories(new String[]{"aaa","bbb update","ccc update"});
        List<String> list=new ArrayList<String>();
        list.add("qqqq update");
        list.add("mmmm update");
        list.add("nnnnn update");
        item.setFeatures(list);
        server.addBean(item);
        server.commit();
    }


    /*
    * 修改索引
    * id相同，即为修改
    */
    public static void updateIndex( SolrServer server) throws SolrServerException, IOException {
        SolrInputDocument doc=new SolrInputDocument();
        doc.addField("id", "id1", 1.0f);
        doc.addField("name", "doc1 update doc1 update doc1 update haha", 1.0f);
        doc.addField("price", 100);
        server.add(doc);
        server.commit();
    }



    /**
     * 搜索
     * @param server
     * @throws SolrServerException
     */
    public static void searchIndex(SolrServer server) throws SolrServerException{
        SolrQuery query=new SolrQuery();
        query.setQuery("name:doc");
        SortClause sortClause=new SortClause("name",SolrQuery.ORDER.desc);
        query.addSort(sortClause);
        QueryResponse resp=server.query(query);
        SolrDocumentList docs=resp.getResults();
        for(SolrDocument doc:docs){
            String id=(String)doc.get("id");
            String name=(String)doc.get("name");
            Float price=(Float)doc.get("price");
            System.out.println("id="+id+"\t name="+name+"\t price="+price);
        }

    }


    /**
     * 搜索
     * @param server
     * @throws SolrServerException
     */
    public static void searchIndexWithPage(SolrServer server,int start,int rows) throws SolrServerException{
        SolrQuery query=new SolrQuery();
        query.setQuery("name:doc");
        SortClause sortClause=new SortClause("name",SolrQuery.ORDER.desc);
        query.addSort(sortClause);
        query.setStart(start);//分页的起始位置
        query.setRows(rows);//一共搜索多少页
        QueryResponse resp=server.query(query);
        SolrDocumentList docs=resp.getResults();
        for(SolrDocument doc:docs){
            String id=(String)doc.get("id");
            String name=(String)doc.get("name");
            Float price=(Float)doc.get("price");
            System.out.println("id="+id+"\t name="+name+"\t price="+price);
        }

    }



    /**
     * 搜索
     * @param server
     * @throws SolrServerException
     */
    public static void searchIndex2(SolrServer server) throws SolrServerException{
        SolrQuery query=new SolrQuery();
        query.setQuery("cat:aaa");
        SortClause sortClause=new SortClause("id",SolrQuery.ORDER.desc);
        query.addSort(sortClause);
        QueryResponse resp=server.query(query);
        SolrDocumentList docs=resp.getResults();
        for(SolrDocument doc:docs){
            String id=(String)doc.get("id");
            List<String> cats=(List<String>)doc.get("cat");
            List<String> features=(List<String>)doc.get("features");
            System.out.println("id="+id+"\t cats="+cats+"\t features="+features);
        }

    }



    /**
     * 分页搜索
     * @param server
     * @throws SolrServerException
     */
    public static void searchIndex2WithPage(SolrServer server,int start,int rows) throws SolrServerException{
        SolrQuery query=new SolrQuery();
        query.setQuery("cat:aaa");
        SortClause sortClause=new SortClause("id",SolrQuery.ORDER.desc);
        query.addSort(sortClause);
        query.setStart(start);//分页的起始位置
        query.setRows(rows);//一共搜索多少页
        QueryResponse resp=server.query(query);
        SolrDocumentList docs=resp.getResults();
        for(SolrDocument doc:docs){
            String id=(String)doc.get("id");
            List<String> cats=(List<String>)doc.get("cat");
            List<String> features=(List<String>)doc.get("features");
            System.out.println("id="+id+"\t cats="+cats+"\t features="+features);
        }

    }


    /**
     * 搜索
     * @param server
     * @throws SolrServerException
     */
    public static void searchIndex2Bean(SolrServer server) throws SolrServerException{
        SolrQuery query=new SolrQuery();
        query.setQuery("*:*");
        SortClause sortClause=new SortClause("name",SolrQuery.ORDER.desc);
        query.addSort(sortClause);
        QueryResponse resp=server.query(query);
        List<Product> pbeans = resp.getBeans(Product.class);
        for(Product pd:pbeans){
            String id=pd.getId();
            String name=pd.getProductname();
            Float price=pd.getProductprice();
            System.out.println("Product:id="+id+"\t name="+name+"\t price="+price);
        }

    }

    /**
     * 高亮
     * 高亮时不能将查询结果转换为bean的方式
     * @param server
     * @throws SolrServerException
     */
    public static void searchIndexByHighlight(SolrServer server) throws SolrServerException{
        SolrQuery query=new SolrQuery();
        query.setQuery("name:update");
        query.setHighlight(true).setHighlightSimplePre("<span style='color:red'>").setHighlightSimplePost("</span>");
        query.setParam("hl.fl", "name");
        QueryResponse resp=server.query(query);
        SolrDocumentList docs= resp.getResults();
        for(SolrDocument doc:docs){
//String name =(String)doc.getFieldValue("name");
            float price=(Float)doc.getFieldValue("price");
            String id=(String)doc.getFieldValue("id");
            System.out.print("id="+id+"\t price="+price);
            Map<String,List<String>> hlmap= resp.getHighlighting().get(id);
            if(hlmap!=null){
                List<String> hls = hlmap.get("name");
                for(String hl:hls){
                    System.out.println("\t name="+hl);
                }
            }
        }

    }

}
