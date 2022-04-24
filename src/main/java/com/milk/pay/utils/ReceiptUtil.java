package com.milk.pay.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.milk.pay.entities.ReceiptInfo;

/**
 *
 * @author SRamos
 */
public class ReceiptUtil {

    public static String createReceiptPix(ReceiptInfo receiptInfo) {

        var receiptLayout = replaceDefaultFields(getLayoutPixPaidReceipt(), receiptInfo);

        receiptLayout = receiptLayout.replace("{inst-recebedor}", StringUtil.addBlankLeftPad(receiptInfo.getReceiverAccountBank(), 35));
        receiptLayout = receiptLayout.replace("{agencia-recebedor}", StringUtil.addBlankLeftPad(receiptInfo.getReceiverAccountBranch(), 32));
        receiptLayout = receiptLayout.replace("{conta-recebedor}", StringUtil.addBlankLeftPad(receiptInfo.getReceiverAccount(), 34));
        receiptLayout = receiptLayout.replace("{end-to-end}", receiptInfo.getEndToEndId());
        receiptLayout = receiptLayout.replace("{valor-pgto}", StringUtil.addBlankLeftPad(receiptInfo.getAmount().toString(), 30));

        return receiptLayout;
    }

    public static String replaceDefaultFields(String receiptLayout, ReceiptInfo receiptInfo) {
        
        var dateTime = LocalDateTime.now();

        receiptLayout = receiptLayout.replace("{protocolo}", StringUtil.addBlankLeftPad(receiptInfo.getTxId().toString(), 31));
        receiptLayout = receiptLayout.replace("{data-atual}", DateUtil.formatDDMMYYYY(LocalDate.now()));
        receiptLayout = receiptLayout.replace("{hora-atual}", StringUtil.addBlankLeftPad(dateTime.getHour() + ":" + dateTime.getMinute(),30));
        receiptLayout = receiptLayout.replace("{tipo-movimento}", receiptInfo.getMovementCode().getValue().length() > 32 ? 
            receiptInfo.getMovementCode().getValue().toUpperCase().substring(0, 32) : receiptInfo.getMovementCode().getValue().toUpperCase());
        receiptLayout = receiptLayout.replace("{nome-pagador}", StringUtil.addBlankLeftPad(receiptInfo.getPayerName(), 35));
        receiptLayout = receiptLayout.replace("{doc-pagador}", StringUtil.addBlankLeftPad(receiptInfo.getPayerDocument(), 31));
        receiptLayout = receiptLayout.replace("{inst-pagador}", StringUtil.addBlankLeftPad(receiptInfo.getPayerAccountBank(), 35));
        receiptLayout = receiptLayout.replace("{agencia-pagador}", StringUtil.addBlankLeftPad(receiptInfo.getPayerAccountBranch(), 32));
        receiptLayout = receiptLayout.replace("{conta-pagador}", StringUtil.addBlankLeftPad(receiptInfo.getPayerAccount(), 34));
        receiptLayout = receiptLayout.replace("{nome-recebedor}", StringUtil.addBlankLeftPad(receiptInfo.getReceiverName(), 35));
        receiptLayout = receiptLayout.replace("{doc-recebedor}", StringUtil.addBlankLeftPad(receiptInfo.getReceiverDocument(), 31));
        receiptLayout = receiptLayout.replace("{data-pgto}", StringUtil.addBlankLeftPad(DateUtil.formatDDMMYYYY(LocalDate.now()), 22));
        receiptLayout = receiptLayout.replace("{autenticacao-anterior}", receiptInfo.getLastAuthentication());

        return receiptLayout;
    }

    public static String addReceiptAuth(String receipt, String authentication) {
        return receipt.replace("{autenticacao-atual}", authentication);
    }

    public static String getLayoutPixPaidReceipt() {

        StringBuilder sb = new StringBuilder();

        sb.append("  MILKPAY TECNOLOGIA E PAGAMENTOS LTDA.\n\n");
        sb.append("PROTOCOLO{protocolo}\n");
        sb.append("TERM 0001                       DAFE-API\n");
        sb.append("{data-atual}{hora-atual}\n");
        sb.append("----------------------------------------\n");
        sb.append("    {tipo-movimento}    \n");
        sb.append("                 ORIGEM                 \n");
        sb.append("NOME:{nome-pagador}\n");
        sb.append("CPF/CNPJ:{doc-pagador}\n");
        sb.append("INST:{inst-pagador}\n");
        sb.append("AGENCIA:{agencia-pagador}\n");
        sb.append("CONTA:{conta-pagador}\n");
        sb.append("                 DESTINO                \n");
        sb.append("NOME:{nome-recebedor}\n");
        sb.append("CPF/CNPJ:{doc-recebedor}\n");
        sb.append("INST:{inst-recebedor}\n");
        sb.append("AGENCIA:{agencia-recebedor}\n");
        sb.append("CONTA:{conta-recebedor}\n");
        sb.append("----------------------------------------\n");
        sb.append("DATA DO PAGAMENTO:{data-pgto}\n");
        sb.append("VALOR PIX:{valor-pgto}\n");
        sb.append("ID. PIX:{end-to-end}\n\n");
        sb.append("    VALIDO COMO RECIBO DE PAGAMENTO     \n");
        sb.append("----------------------------------------\n");
        sb.append("              AUTENTICACAO              \n");
        sb.append("    {autenticacao-anterior}    \n");
        sb.append("    {autenticacao-atual}    \n");
        sb.append("----------------------------------------\n");

        return sb.toString();

    }
    
}
