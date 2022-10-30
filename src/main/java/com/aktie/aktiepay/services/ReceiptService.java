package com.aktie.aktiepay.services;

import javax.enterprise.context.ApplicationScoped;

import com.aktie.aktiepay.dto.ReceiptDto;
import com.aktie.aktiepay.entities.ReceiptInfo;
import com.aktie.aktiepay.entities.enums.EnumErrorCode;
import com.aktie.aktiepay.utils.AktiePayException;
import com.aktie.aktiepay.utils.ReceiptUtil;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class ReceiptService {

    public ReceiptDto findAndGenerate(Integer txId) {

        var receiptInfo = ReceiptInfo.findByTxId(txId);

        if (receiptInfo == null) {
            throw new AktiePayException(EnumErrorCode.TX_ID_NAO_ENCONTRADO);
        }

        var receiptImage = ReceiptUtil.handleCreate(receiptInfo);

        return new ReceiptDto(receiptImage);

    }

}
