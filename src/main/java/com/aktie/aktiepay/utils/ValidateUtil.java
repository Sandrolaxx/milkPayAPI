package com.aktie.aktiepay.utils;

import java.time.LocalDate;

import com.aktie.aktiepay.dto.bankslip.BankSlipConsultDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipPaymentDto;
import com.aktie.aktiepay.dto.pix.PixPaymentDto;
import com.aktie.aktiepay.dto.title.TitleCreateDto;
import com.aktie.aktiepay.dto.user.CreateUserDto;
import com.aktie.aktiepay.entities.Title;
import com.aktie.aktiepay.entities.enums.EnumErrorCode;
import com.aktie.aktiepay.entities.enums.EnumPaymentType;

/**
 *
 * @author SRamos
 */
public class ValidateUtil {

    public static void validatePixPaymentDto(PixPaymentDto dto) {

        if (NumericUtil.isNullOrZero(dto.getTitleId())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Id do Título(titleId)");
        }

        validateTitleExistence(dto.getTitleId());

        if (StringUtil.isNullOrEmpty(dto.getEndToEndId())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "endToEndId");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverName())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Nome Recebedor(receiverName)");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverDocument())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Documento Recebedor(receiverDocument)");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverKey())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Chave(creditAccountKey)");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverBank())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Banco Recebedor(receiverBank)");
        }

        if (StringUtil.isNullOrEmpty(dto.getReceiverAccount())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Conta Recebedor(receiverAccount)");
        }

        if (dto.getReceiverAccountType() == null) {
            throw new AktiePayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Tipo Conta Recebedor(receiverAccountType)");
        }

        if (NumericUtil.isNullOrZero(dto.getReceiverBranch())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_PAGAMENTO_PIX_INVALIDO, "Agência Recebedor(receiverBranch)");
        }

    }

    public static void validateNewTitleDto(TitleCreateDto dto) {

        if (NumericUtil.isNullOrZero(dto.getAmount())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Valor(amount)");
        }

        if (dto.getExternalId() == null) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Identificador Externo(externalId)");
        }

        if (dto.getDailyInterest() == null) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Juro diário(dailyInterest)");
        }

        if (StringUtil.isNullOrEmpty(dto.getUserDocument())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Documento(userDocument)");
        }

        if (StringUtil.isNullOrEmpty(dto.getNfNumber())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Número NF(nfNumber)");
        }

        if (StringUtil.isNullOrEmpty(dto.getInclusionDate())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Data inclusão(inclusionDate)");
        }

        if (StringUtil.isNullOrEmpty(dto.getDueDate())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Data vencimento(dueDate)");
        }

        if (dto.getPaymentType() == null) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Tipo de Pagamento(paymentType)");
        }

        if (EnumUtil.isEquals(dto.getPaymentType(), EnumPaymentType.BOLETO)
                && (StringUtil.isNullOrEmpty(dto.getDigitable())
                        || StringUtil.isNullOrEmpty(dto.getDigitable()))) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Tipo de Pagamento(paymentType) Boleto"
                    + " o campo linha digitável(digitable) ou código de barras(barcode)");
        }

        var dueDate = DateUtil.DDMMYYYYToLocalDate(dto.getDueDate().substring(0, 10));
        var tomorrow = DateUtil.getTodayZeroHour().plusDays(1);

        if (dueDate.atStartOfDay().isBefore(tomorrow)) {
            throw new AktiePayException(EnumErrorCode.DATA_VENCIMENTO_INVALIDA);
        }

    }

    public static void validateNewUser(CreateUserDto dto) {

        if (StringUtil.isNullOrEmpty(dto.getDocument())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Documento");
        }

        if (StringUtil.isNullOrEmpty(dto.getPassword())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Senha");
        }

    }

    public static void validateConsultTitle(BankSlipConsultDto dto) {

        if (StringUtil.isNullOrEmpty(dto.getBarcode())
                && StringUtil.isNullOrEmpty(dto.getDigitable())) {
            throw new AktiePayException(EnumErrorCode.BARCODE_DIGITABLE_NAO_INFORMADOS);
        }

    }

    public static void validatePaymentTitle(BankSlipPaymentDto dto) {

        if (StringUtil.isNullOrEmpty(dto.getBarcode())
                && StringUtil.isNullOrEmpty(dto.getDigitable())) {
            throw new AktiePayException(EnumErrorCode.BARCODE_DIGITABLE_NAO_INFORMADOS);
        }

        if (dto.getDueDate() == null) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Data vencimento(dueDate)");
        }

        if (dto.getDueDate().isBefore(LocalDate.now())) {
            throw new AktiePayException(EnumErrorCode.BOLETO_VENCIDO);
        }

        if (NumericUtil.isNullOrZero(dto.getTitleId())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Id do Título(titleId)");
        }

        validateTitleExistence(dto.getTitleId());

        if (NumericUtil.isNullOrZero(dto.getAmount())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Valor(amount)");
        }

        if (NumericUtil.isNullOrZero(dto.getTxId())) {
            throw new AktiePayException(EnumErrorCode.CAMPO_OBRIGATORIO, "Identificador da Transação(txId)");
        }

    }

    public static void validateTxId(Integer txId) {

        if (txId == null) {
            throw new AktiePayException(EnumErrorCode.TX_ID_NAO_INFORMADO);
        }

    }

    public static void validatePixKey(String key) {

        if (key == null
                || key.isEmpty()) {
            throw new AktiePayException(EnumErrorCode.CHAVE_PIX_NAO_INFORMADA);
        }

    }

    private static void validateTitleExistence(Integer titleId) {

        var title = Title.findById(titleId);

        if (title == null) {
            throw new AktiePayException(EnumErrorCode.TIT_NAO_ENCONTRADO);
        }

        if (title.isLiquidated()) {
            throw new AktiePayException(EnumErrorCode.TIT_POSSUI_LIQUIDACAO);
        }

    }
    

}
