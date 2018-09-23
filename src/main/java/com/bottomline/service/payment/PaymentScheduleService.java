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

    public void calculatePaymentPlan(final BigDecimal amount, final BigDecimal numberOfPayments) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0 ||
                numberOfPayments.compareTo(BigDecimal.ZERO) <= 0) {
            // TODO : Could have handled this more gracefully either through exceptions or some other business valid scenario
            return;
        }

        final BigDecimal regularAmount = amount.divide(numberOfPayments, 2, RoundingMode.HALF_UP);
        List<BigDecimal> paymentSchedule = new ArrayList<>();

        if (regularAmount.compareTo(BigDecimal.ZERO) == 0) {
            // TODO : Could do with something more gracefull
            return;
        }

        paymentSchedule(amount, numberOfPayments, regularAmount, paymentSchedule);
        schedulePrintService.printSimpleSchedule(paymentSchedule);

    }

    /*
    This method is recursively called to build the payment schedule
     */
    private void paymentSchedule(final BigDecimal amount, final BigDecimal numberOfPayments, final BigDecimal regularAmount,
                                 final List<BigDecimal> paymentSchedule) {
        if (numberOfPayments.compareTo(BigDecimal.ONE) == 0) {
            paymentSchedule.add(amount);
            return;
        }
        paymentSchedule.add(regularAmount);
        BigDecimal newAmount = amount.subtract(regularAmount);
        BigDecimal newNumberOfPayments = numberOfPayments.subtract(BigDecimal.ONE);
        paymentSchedule(newAmount, newNumberOfPayments, regularAmount, paymentSchedule);

    }
}