package com.bottomline.service.schedule;

import java.math.BigDecimal;
import java.util.List;

public interface SchedulePrintService {


    void printDetailedSchedule(final List<BigDecimal> paymentSchedule);

    void printSimpleSchedule(final List<BigDecimal> paymentSchedule);

}
