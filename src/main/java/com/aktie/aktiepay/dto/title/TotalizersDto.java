package com.aktie.aktiepay.dto.title;

import java.math.BigDecimal;

/**
 *
 * @author SRamos
 */
public class TotalizersDto {
    
    private BigDecimal amountReceived;

    private BigDecimal amountToReceive;

    private Integer titlesReceived;

    private Integer titlesToReceive;

    public BigDecimal getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(BigDecimal amountReceived) {
        this.amountReceived = amountReceived;
    }

    public BigDecimal getAmountToReceive() {
        return amountToReceive;
    }

    public void setAmountToReceive(BigDecimal amountToReceive) {
        this.amountToReceive = amountToReceive;
    }

    public Integer getTitlesReceived() {
        return titlesReceived;
    }

    public void setTitlesReceived(Integer titlesReceived) {
        this.titlesReceived = titlesReceived;
    }

    public Integer getTitlesToReceive() {
        return titlesToReceive;
    }

    public void setTitlesToReceive(Integer titlesToReceive) {
        this.titlesToReceive = titlesToReceive;
    }

}
