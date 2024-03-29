package com.aktie.aktiepay.utils;

import java.util.Base64;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.aktie.aktiepay.dto.pix.PixPaymentDebitPartyCelcoinDto;
import com.aktie.aktiepay.entities.enums.EnumAccountType;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class RequestUtil {

    @ConfigProperty(name = "milk.account")
    String milkPayAccount;

    @ConfigProperty(name = "milk.branch")
    String milkPayBranch;

    @ConfigProperty(name = "milk.taxId")
    String milkPayTaxId;

    @ConfigProperty(name = "milk.name")
    String milkPayName;

    @ConfigProperty(name = "milk.bankId")
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
        var tokenBasic = "Basic " + token;

        return tokenBasic;
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