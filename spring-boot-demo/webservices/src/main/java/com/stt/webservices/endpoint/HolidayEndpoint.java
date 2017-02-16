package com.stt.webservices.endpoint;

import com.stt.webservices.service.HumanResourceService;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Administrator on 2017-02-15.
 *
 * @author shitongtong
 */
@Endpoint
public class HolidayEndpoint {

    private static final String NAMESPACE_URI = "http://mycompany.com/hr/schemas";

    private XPathExpression<Element> startDateExpression;

    private XPathExpression<Element> endDateExpression;

    private XPathExpression<String> nameExpression;

    private HumanResourceService humanResourceService;

    public HolidayEndpoint(HumanResourceService humanResourceService){
        this.humanResourceService = humanResourceService;
        Namespace namespace = Namespace.getNamespace("hr", NAMESPACE_URI);
        XPathFactory xPathFactory = XPathFactory.instance();
        this.startDateExpression = xPathFactory.compile("//hr:StratDate", Filters.element(),null,namespace);
        this.endDateExpression = xPathFactory.compile("//hr:EndDate",Filters.element(),null,namespace);
        this.nameExpression = xPathFactory.compile("concat(//hr:FirstName,'',//hr:LastName)",Filters.fstring(),null,namespace);

    }

    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "HolidayRequest")
    public void handleHolidayRequest(@RequestPayload Element holidayRequest) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(this.startDateExpression.evaluateFirst(holidayRequest).getText());
        Date endDate = dateFormat.parse(this.endDateExpression.evaluateFirst(holidayRequest).getText());
        String name = this.nameExpression.evaluateFirst(holidayRequest);
        this.humanResourceService.bookHoliday(startDate,endDate,name);
    }

}
