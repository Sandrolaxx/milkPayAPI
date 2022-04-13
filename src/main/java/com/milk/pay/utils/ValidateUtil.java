package com.milk.pay.utils;

import com.milk.pay.dto.pix.PixPaymentDto;
import com.milk.pay.entities.enums.EnumErrorCode;

/**
 *
 * @author SRamos
 */
public class ValidateUtil {

    public static void validatePixPaymentDto(PixPaymentDto dto) {

        if (NumericUtil.isNullOrZero(dto.getAmount())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Valor(Amount)");
        }

        if (dto.getTitleId() == null) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Id Título(titleId)");
        }

        if (StringUtil.isNullOrEmpty(dto.getEndToEndId())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "EndToEndId");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverKey())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Chave(CreditAccountKey)");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverName())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Nome Recebedor(ReceiverName)");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverName())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Nome Recebedor(ReceiverName)");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverName())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Nome Recebedor(ReceiverName)");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverName())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Nome Recebedor(ReceiverName)");
        }

    }

}
