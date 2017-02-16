package com.stt.webservice;

import com.stt.webservices.WebServiceApplication;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Administrator on 2017-02-15.
 *
 * @author shitongtong
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = WebServiceApplication.class)
public class WebServiceApplicationTests {

    @Rule
    public OutputCapture output = new OutputCapture();

    private WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    @LocalServerPort
    private int serverPort;

    @Before
    public void setUp(){
        this.webServiceTemplate.setDefaultUri("http://localhost:" + this.serverPort + "/services/");
    }

    @Test
    public void testSendingHolidayRequest(){

        final String request = "<hr:HolidayRequest xmlns:hr=\"http://mycompany.com/hr/schemas\">"
                + "   <hr:Holiday>" + "      <hr:StartDate>2013-10-20</hr:StartDate>"
                + "      <hr:EndDate>2013-11-22</hr:EndDate>" + "   </hr:Holiday>"
                + "   <hr:Employee>" + "      <hr:Number>1</hr:Number>"
                + "      <hr:FirstName>John</hr:FirstName>"
                + "      <hr:LastName>Doe</hr:LastName>" + "   </hr:Employee>"
                + "</hr:HolidayRequest>";

        StreamSource source = new StreamSource(new StringReader(request));
        StreamResult result = new StreamResult(System.out);

        this.webServiceTemplate.sendSourceAndReceiveToResult(source,result);

        System.out.println("===output==="+this.output.toString()+"======");

        assertThat(this.output.toString()).contains("Booking holiday for");
    }

}
