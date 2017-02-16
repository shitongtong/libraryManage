package com.stt.webservices.service;

import java.util.Date;

/**
 * Created by Administrator on 2017-02-15.
 *
 * @author shitongtong
 */
public interface HumanResourceService {

    void bookHoliday(Date startDate,Date endDate, String name);
}
