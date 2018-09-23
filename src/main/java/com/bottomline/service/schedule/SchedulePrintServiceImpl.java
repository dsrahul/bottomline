package com.bottomline.service.schedule;

import com.bottomline.service.print.Printer;

import java.math.BigDecimal;
import java.util.List;

/**
 * This Service is responsible to decide what needs to be printed in a detail and a simple schedule print
 */
public class SchedulePrintServiceImpl implements SchedulePrintService {


    public static final String REGULAR_AMOUNT_£_2F = "Regular Amount\t\t\t£%,.2f\n";
    public static final String LAST_AMOUNT_£_2F = "Last Amount\t\t\t£%,.2f\n";
    private Printer printer;

    public SchedulePrintServiceImpl(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void printDetailedSchedule(List<BigDecimal> paymentSchedule) {
        paymentSchedule.forEach(System.out::println);
    }

    @Override
    public void printSimpleSchedule(List<BigDecimal> paymentSchedule) {
        final BigDecimal firstPayment = paymentSchedule.get(0);
        final BigDecimal lastPayment = paymentSchedule.stream().reduce((first, second) -> second).orElse(firstPayment);

        printer.print(REGULAR_AMOUNT_£_2F, firstPayment);

        if (firstPayment.compareTo(lastPayment) != 0) {
            printer.print(LAST_AMOUNT_£_2F, lastPayment);
        }
    }
}
