package com.bottomline.service.print;

import java.math.BigDecimal;

public interface PrintService {
    void print(final String message, final BigDecimal anAmount);
}
