package com.bottomline.service.schedule;

import java.math.BigDecimal;
import java.util.List;

public interface SchedulePrintService {


    void printDetailedSchedule(List<BigDecimal> paymentSchedule);

    void printSimpleSchedule(List<BigDecimal> paymentSchedule);

}
