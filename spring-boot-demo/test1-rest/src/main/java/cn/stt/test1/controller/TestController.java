package cn.stt.test1.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/6/7.
 */
@RestController
@RequestMapping("test1")
public class TestController {

    @RequestMapping(value = "test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test1");
    }

    @RequestMapping(value = "testdata",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<String> testData(){
        return ResponseEntity.ok("test1");
    }

    @RequestMapping(value = "test1ByJson11",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void test1ByJson11(HttpServletResponse response) throws IOException, InterruptedException {
        //媒体类型为 text/event-stream
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");
        String aa = "data:"+System.currentTimeMillis()+"\n\n";

        ResponseData responseData = new ResponseData();
        responseData.setData1("data:"+System.currentTimeMillis()+"\n\n");
        responseData.setData2("data:"+System.currentTimeMillis()+"\n\n");
        responseData.setData3("data:"+System.currentTimeMillis()+"\n\n");
        PrintWriter out = response.getWriter();

        //响应报文格式为:
        //data:Hello World
        //event:load
        //id:140312
        //换行符(/r/n)
        String data = System.currentTimeMillis()+"";
        out.println("data:"+data+"\n\n");
        out.println("data:"+data+"\n\n");
        out.println("data:"+data+"\n\n");
        out.println("data:=================\n\n");
        out.flush();
        out.close();
        /*StringBuilder sb = new StringBuilder();
        String ss = "data:";
        String data1 = "data1="+System.currentTimeMillis();
        String data2 = "data2="+System.currentTimeMillis();
        String data3 = "data3="+System.currentTimeMillis();
        sb.append(ss);
        sb.append(data1);
        sb.append(data2);
        sb.append(data3);*/
//        return sb.toString();
    }
}
