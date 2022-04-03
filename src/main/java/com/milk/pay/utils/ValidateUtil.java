package com.milk.pay.utils;

import com.milk.pay.dto.pix.PixPaymentDto;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.entities.enums.EnumInitiationType;

/**
 *
 * @author SRamos
 */
public class ValidateUtil {

    public static void validatePixPaymentDto(PixPaymentDto dto) {

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
