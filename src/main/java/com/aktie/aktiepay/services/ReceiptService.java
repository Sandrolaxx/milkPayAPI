package com.aktie.aktiepay.services;

import javax.enterprise.context.ApplicationScoped;

import com.aktie.aktiepay.dto.ReceiptDto;
import com.aktie.aktiepay.entities.ReceiptInfo;
import com.aktie.aktiepay.utils.ReceiptUtil;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class ReceiptService {

    public ReceiptDto findAndGenerate(Integer txId) {

        var receiptInfo = ReceiptInfo.findByTxId(txId);
        var receiptImage = ReceiptUtil.handleCreate(receiptInfo);

        return new ReceiptDto(receiptImage);

    }

}
