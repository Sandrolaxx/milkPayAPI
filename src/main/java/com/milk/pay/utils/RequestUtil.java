package com.milk.pay.utils;

import java.util.Base64;

import javax.enterprise.context.ApplicationScoped;

import com.milk.pay.dto.pix.PixPaymentDebitPartyCelcoinDto;
import com.milk.pay.entities.enums.EnumAccountType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class RequestUtil {

    @ConfigProperty(name = "dafe.account")
    String milkPayAccount;

    @ConfigProperty(name = "dafe.branch")
    String milkPayBranch;

    @ConfigProperty(name = "dafe.taxId")
    String milkPayTaxId;

    @ConfigProperty(name = "dafe.name")
    String milkPayName;

    @ConfigProperty(name = "dafe.bankId")
    String milkPayBankISPB;

    public static String bearerToken(String token) {
        var tokenBearer = "Bearer " + token;
        return tokenBearer;
    }

    public static String basicToken(String username, String password) {
        var basic = username + ":" + password;
        var base64encoder = Base64.getEncoder();
        var basicToken = "Basic " + base64encoder.encodeToString(basic.getBytes());
        return basicToken;
    }

    public static String onlyBasic(String token) {
        var tokenBearer = "Basic " + token;

        return tokenBearer;
    }

    public PixPaymentDebitPartyCelcoinDto getMilkPayDebitParty() {

        var milkPayDebitParty = new PixPaymentDebitPartyCelcoinDto();

        milkPayDebitParty.setAccount(milkPayAccount);
        milkPayDebitParty.setAccountType(EnumAccountType.CACC);
        milkPayDebitParty.setBranch(milkPayBranch);
        milkPayDebitParty.setName(milkPayName);
        milkPayDebitParty.setTaxId(milkPayTaxId);
        milkPayDebitParty.setBankISPB(milkPayBankISPB);

        return milkPayDebitParty;

    }

}