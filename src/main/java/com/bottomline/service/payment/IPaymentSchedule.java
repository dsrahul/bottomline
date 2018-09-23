package com.bottomline.service.payment;

import java.math.BigDecimal;

public interface IPaymentSchedule {
    void calculatePaymentPlan(BigDecimal amount, BigDecimal numberOfPayments);
}
