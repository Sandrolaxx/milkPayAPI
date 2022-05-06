package com.milk.pay.dto.bankslip;

import java.math.BigDecimal;

/**
 *
 * @author SRamos
 */
public class BankSlipCelcoinBillDataDto {
    
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
