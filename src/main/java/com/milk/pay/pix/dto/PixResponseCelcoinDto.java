package com.milk.pay.pix.dto;

public class PixResponseCelcoinDto {
  
  private Long transactionId;

  private String emvqrcps;

  private String transactionIdentification;

  public Long getTransactionId() {
    return this.transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  public String getEmvqrcps() {
    return this.emvqrcps;
  }

  public void setEmvqrcps(String emvqrcps) {
    this.emvqrcps = emvqrcps;
  }

  public String getTransactionIdentification() {
    return this.transactionIdentification;
  }

  public void setTransactionIdentification(String transactionIdentification) {
    this.transactionIdentification = transactionIdentification;
  }

}
