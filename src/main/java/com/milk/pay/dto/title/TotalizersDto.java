package com.milk.pay.dto.title;

public class TotalizersDto {
    
    private String amountReceived;

    private String amountToReceive;

    private Integer titlesReceived;

    private Integer titlesToReceive;

    public String getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(String amountReceived) {
        this.amountReceived = amountReceived;
    }

    public String getAmountToReceive() {
        return amountToReceive;
    }

    public void setAmountToReceive(String amountToReceive) {
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
