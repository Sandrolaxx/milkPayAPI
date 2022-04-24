package com.milk.pay.utils;

import java.time.LocalDateTime;

import com.milk.pay.dto.bankslip.BankSlipConsultDto;
import com.milk.pay.dto.pix.PixPaymentDto;
import com.milk.pay.dto.title.TitleCreateDto;
import com.milk.pay.dto.user.CreateUserDto;
import com.milk.pay.entities.enums.EnumErrorCode;

/**
 *
 * @author SRamos
 */
public class ValidateUtil {

    public static void validatePixPaymentDto(PixPaymentDto dto) {

        if (NumericUtil.isNullOrZero(dto.getAmount())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Valor(amount)");
        }

        if (dto.getTitleId() == null) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Id Título(titleId)");
        }

        if (StringUtil.isNullOrEmpty(dto.getEndToEndId())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "endToEndId");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverKey())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Chave(creditAccountKey)");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverName())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Nome Recebedor(receiverName)");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverName())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Nome Recebedor(receiverName)");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverName())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Nome Recebedor(receiverName)");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverName())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Nome Recebedor(receiverName)");
        }

    }

    public static void validateNewTitleDto(TitleCreateDto dto) {

        if (NumericUtil.isNullOrZero(dto.getAmount())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Valor(amount)");
        }

        if (dto.getExternalId() == null) {
            throw new MilkPayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Identificador Externo(externalId)");
        }

        if (dto.getDailyInterest() == null) {
            throw new MilkPayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Juro diário(dailyInterest)");
        }

        if (StringUtil.isNullOrEmpty(dto.getUserDocument())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Documento(userDocument)");
        }

        if (StringUtil.isNullOrEmpty(dto.getNfNumber())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Número NF(nfNumber)");
        }

        if (StringUtil.isNullOrEmpty(dto.getInclusionDate())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Data inclusão(inclusionDate)");
        }

        if (StringUtil.isNullOrEmpty(dto.getDueDate())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Data vencimento(dueDate)");
        }

        var dueDate = DateUtil.DDMMYYYYHHMMSSToLocalDateTime(dto.getDueDate());
        var tomorrow = LocalDateTime.now().plusDays(1);

        if (dueDate.isBefore(tomorrow)) {
            throw new MilkPayException(EnumErrorCode.DATA_VENCIMENTO_INVALIDA);
        }

    }

    public static void validateNewUser(CreateUserDto dto) {

        if (StringUtil.isNullOrEmpty(dto.getDocument())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Documento");
        }

        if (StringUtil.isNullOrEmpty(dto.getPassword())) {
            throw new MilkPayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Senha");
        }

    }

    public static void validateConsultTitle(BankSlipConsultDto dto) {

        if (StringUtil.isNullOrEmpty(dto.getBarcode())
                && StringUtil.isNullOrEmpty(dto.getDigitable())) {
            throw new MilkPayException(EnumErrorCode.BARCODE_DIGITABLE_NAO_INFORMADOS);
        }

    }

}
