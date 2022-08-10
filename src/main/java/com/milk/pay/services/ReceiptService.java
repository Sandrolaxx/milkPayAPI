package com.milk.pay.services;

import javax.enterprise.context.ApplicationScoped;

import com.milk.pay.dto.ReceiptDto;
import com.milk.pay.entities.ReceiptInfo;
import com.milk.pay.utils.ReceiptUtil;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class ReceiptService {

    public ReceiptDto findAndGenerate(Integer txId) {

        var receiptInfo = ReceiptInfo.findByTxId(txId);
        var receiptImage = ReceiptUtil.handleCreate(receiptInfo);

        return new ReceiptDto(txId, receiptImage);

    }

}
