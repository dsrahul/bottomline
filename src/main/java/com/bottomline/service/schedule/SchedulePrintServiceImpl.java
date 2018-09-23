package com.bottomline.service.schedule;

import com.bottomline.service.print.Printer;

import java.math.BigDecimal;
import java.util.List;

/**
 * This Service is responsible to decide what needs to be printed in a detail and a simple schedule print
 */
public class SchedulePrintServiceImpl implements SchedulePrintService {


    private static final String REGULAR_AMOUNT = "Regular Amount\t\t\t£%,.2f\n";
    private static final String LAST_AMOUNT = "Last Amount\t\t\t£%,.2f\n";
    private Printer printer;

    public SchedulePrintServiceImpl(final Printer printer) {
        this.printer = printer;
    }

    @Override
    public void printDetailedSchedule(final List<BigDecimal> paymentSchedule) {
        paymentSchedule.forEach(System.out::println);
    }

    @Override
    public void printSimpleSchedule(final List<BigDecimal> paymentSchedule) {
        final BigDecimal firstPayment = paymentSchedule.get(0);
        final BigDecimal lastPayment = paymentSchedule.stream().reduce((first, second) -> second).orElse(firstPayment);

        printer.print(REGULAR_AMOUNT, firstPayment);

        if (firstPayment.compareTo(lastPayment) != 0) {
            printer.print(LAST_AMOUNT, lastPayment);
        }
    }
}
