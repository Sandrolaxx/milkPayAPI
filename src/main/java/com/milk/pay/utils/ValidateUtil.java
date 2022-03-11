package com.milk.pay.utils;

import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.entities.enums.EnumInitiationType;
import com.milk.pay.pix.dto.PixPaymentDto;

/**
 *
 * @author SRamos
 */
public class ValidateUtil {

    public static void validateTxId(String txId) {

        if (txId == null) {
            throw new MilkPayException(EnumErrorCode.TX_ID_NAO_INFORMADO);
        }
        var isValidTxId = txId.matches("[0-9]+");

        if (!isValidTxId) {
            throw new MilkPayException(EnumErrorCode.TX_ID_INVALIDO);
        }
    }

    public static void validateInvoiceParameters(String cpfCnpj, Integer subscriptionId) {
        if ((cpfCnpj == null || cpfCnpj.isEmpty()) && subscriptionId == null) {
            throw new MilkPayException(EnumErrorCode.PARAMETROS_INVALIDOS, "cpfCnpj ou subscriptionId");
        }
    }
    
    public static void validatePixPaymentDto(PixPaymentDto dto, Integer accountId) {

        if (accountId == null) {
            throw new MilkPayException(EnumErrorCode.CAMPO_GERACAO_PIX_INVALIDO, "Id Recebedor");
        }

        if (dto.getInitiationType() == null) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Tipo de Iniciação(initiationType) informado no");
        }

        if (dto.getAmount() == null || dto.getAmount().equals(0.0)) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Valor(Amount) informado no");
        }

        if (dto.getCreditAccountKey() == null || dto.getCreditAccountKey().isEmpty()) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Chave(CreditAccountKey) informado no");
        }

        if (dto.getCreditAccountName() == null || dto.getCreditAccountName().isEmpty()) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO,
                    "Nome Recebedor(CreditAccountName) informado no");
        }

        if (dto.getInitiationType().equals(EnumInitiationType.STATIC_QRCODE)
                || dto.getInitiationType().equals(EnumInitiationType.DYNAMIC_QRCODE)) {

            if (dto.getTxId() == null || dto.getTxId().isEmpty()) {
                throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO,
                        "Identificador da transação(transactionIdentification) informado no");
            }

        } else {

            if (dto.getEndToEndId() == null || dto.getEndToEndId().isEmpty()) {
                throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "EndToEndId informado no");
            }

            if (dto.getCreditAccountBank() == null || dto.getCreditAccountBank().isEmpty()) {
                throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO,
                        "Banco(CreditAccountBank) informado no");
            }

            if (dto.getCreditAccountBranch() == null) {
                throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO,
                        "Agência(CreditAccountBranch) informado no");
            }

            if (dto.getCreditAccountTaxId() == null || dto.getCreditAccountTaxId().isEmpty()) {
                throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO,
                        "CPF/CNPJ(CreditAccountTaxId) informado no");
            }

        }

    }

}
