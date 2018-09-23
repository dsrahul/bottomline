package com.bottomline.service.print;

public class ConsolePrinter extends Printer {
    @Override
    protected PrintService getPrinter() {
        return new ConsolePrintService();
    }
}
