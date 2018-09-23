package com.bottomline;

import com.bottomline.service.payment.PaymentScheduleService;
import com.bottomline.service.print.ConsolePrinter;
import com.bottomline.service.schedule.SchedulePrintServiceImpl;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.print("Proper Usage is (e.g.): java -cp target/cc-1.0-SNAPSHOT.jar com.bottomline.App 10.00 3.00\n");
        } else {
            try {
                final BigDecimal amount = new BigDecimal(args[0]);
                final BigDecimal numberOfPayments = new BigDecimal(args[1]);
                new PaymentScheduleService(new SchedulePrintServiceImpl(new ConsolePrinter())).calculatePaymentPlan(amount,
                        numberOfPayments);
            } catch (NumberFormatException e) {
                System.out.print("Proper Usage is (e.g.): java -cp target/cc-1.0-SNAPSHOT.jar com.bottomline.App 10.00 3.00\n");
            }
        }
    }

}
