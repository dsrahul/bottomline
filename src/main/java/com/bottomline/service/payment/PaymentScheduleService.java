package com.bottomline.service.payment;

import com.bottomline.service.schedule.SchedulePrintService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * This Service is responsible to create the payment schedule
 */
public class PaymentScheduleService implements IPaymentSchedule {
    private final SchedulePrintService schedulePrintService;

    public PaymentScheduleService(final SchedulePrintService schedulePrintService) {
        this.schedulePrintService = schedulePrintService;
    }

    public void calculatePaymentPlan(BigDecimal amount, BigDecimal numberOfPayments) {

        assert amount.compareTo(BigDecimal.ZERO) <= 0 && numberOfPayments.compareTo(BigDecimal.ZERO) <= 0;

        if (amount.compareTo(BigDecimal.ZERO) <= 0 ||
                numberOfPayments.compareTo(BigDecimal.ZERO) <= 0) {
            // TODO : Could have handled this more gracefully either through exceptions or some other business valid scenario
            return;
        }

        final BigDecimal regularAmount = amount.divide(numberOfPayments, 2, RoundingMode.HALF_UP);
        List<BigDecimal> paymentSchedule = new ArrayList<>();
        paymentSchedule(amount, numberOfPayments, regularAmount, paymentSchedule);
        schedulePrintService.printSimpleSchedule(paymentSchedule);

    }

    /*
    This method is recursively called to build the payment schedule
     */
    private void paymentSchedule(BigDecimal amount, BigDecimal numberOfPayments, BigDecimal regularAmount, List<BigDecimal> paymentSchedule) {
        if (numberOfPayments.compareTo(BigDecimal.ONE) == 0) {
            paymentSchedule.add(amount);
            return;
        }
        paymentSchedule.add(regularAmount);
        amount = amount.subtract(regularAmount);
        numberOfPayments = numberOfPayments.subtract(BigDecimal.ONE);
        paymentSchedule(amount, numberOfPayments, regularAmount, paymentSchedule);

    }
}