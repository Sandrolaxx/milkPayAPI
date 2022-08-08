package com.milk.pay.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.milk.pay.entities.ReceiptInfo;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.entities.enums.EnumPaymentType;

/**
 *
 * @author SRamos
 */
public class ReceiptUtil {

    private static int marginTopSize = 25;

    public static String findAndCreate(Integer txId) {
        var receipt = ReceiptInfo.findById(txId);
        
        return handleCreate(receipt);
    }

    public static String handleCreate(ReceiptInfo receipt) {

        try {
            var newImg = getBlankImage(260, 720);
            var graphics2D = (Graphics2D) newImg.getGraphics();

            graphics2D.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
            graphics2D.setColor(Color.BLACK);
            
            addReceiptParams(graphics2D, receipt, newImg.getWidth());

            var btArrayOutputStram = new ByteArrayOutputStream();
            ImageIO.write(newImg, "png", btArrayOutputStram);

            return new String(Base64.getEncoder().encodeToString(btArrayOutputStram.toByteArray()));
        } catch (Exception e) {
            throw new MilkPayException(EnumErrorCode.ERRO_SALVAR_COMPROVANTE);
        }

    }

    private static Graphics2D addReceiptParams(Graphics2D graphics2DImage, ReceiptInfo info, int bgImageWidth) {
        return addBankSlipParams(graphics2DImage, info, bgImageWidth);
    }

    private static BufferedImage getBlankImage(int width, int height) {
        var newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        var graphics2D = (Graphics2D) newImg.getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, width, height);

        return newImg;
    }

    private static Graphics2D addBankSlipParams(Graphics2D graphics2DImage, ReceiptInfo info, int bgImageWidth) {
        
        var companyName = "AKTIE TECNOLOGIA";
        var terminal = "AKTIE-API";
        var paymentType = info.getPayment().getTitle().getPaymentType();
        var movementType = EnumUtil.isEquals(paymentType, EnumPaymentType.PIX) ? "PAGAMENTO PIX" : "PAGAMENTO DE TÍTULO";
        var payment = info.getPayment();
        var protocol = payment.getId().toString();
        var paymentDate = DateUtil.formatDDMMYYYYHHMM(info.getPaidAt());
        var dueDate = DateUtil.formatDDMMYYYY(info.getDueDate());
        var digitable = info.getDigitable();
        var separator = "---------------------------------------------------------";

        graphics2DImage.drawString(companyName, getHorizontalSize(graphics2DImage, bgImageWidth, companyName), marginTopSize);
        graphics2DImage.drawString("PROTOCOLO:", 4, getNextMarginTop());
        graphics2DImage.drawString(protocol, getLeftTextSize(graphics2DImage, bgImageWidth, protocol), marginTopSize);
        graphics2DImage.drawString("TERMINAL:", 4, getNextMarginTop());
        graphics2DImage.drawString(terminal, getLeftTextSize(graphics2DImage, bgImageWidth, terminal), marginTopSize);
        graphics2DImage.drawString(paymentDate, getHorizontalSize(graphics2DImage, bgImageWidth, paymentDate), getNextMarginTop());
        graphics2DImage.drawString(separator, getHorizontalSize(graphics2DImage, bgImageWidth, separator), getNextMarginTop());
        
        graphics2DImage.drawString(movementType, getHorizontalSize(graphics2DImage, bgImageWidth, movementType), getNextMarginTop());
        graphics2DImage.drawString("ORIGEM", getHorizontalSize(graphics2DImage, bgImageWidth, "ORIGEM"), getNextMarginTop());
        graphics2DImage.drawString("NOME:", 4, getNextMarginTop());
        graphics2DImage.drawString(info.getPayerName(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getPayerName()), marginTopSize);
        graphics2DImage.drawString("CPF/CNPJ:", 4, getNextMarginTop());
        graphics2DImage.drawString(info.getPayerDocument(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getPayerDocument()), marginTopSize);
        graphics2DImage.drawString("INST:", 4, getNextMarginTop());
        graphics2DImage.drawString(info.getPayerAccountBank(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getPayerAccountBank()), marginTopSize);
        graphics2DImage.drawString("AGÊNCIA:", 4, getNextMarginTop());
        graphics2DImage.drawString(info.getPayerAccountBranch(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getPayerAccountBranch()), marginTopSize);
        graphics2DImage.drawString("CONTA:", 4, getNextMarginTop());
        graphics2DImage.drawString(info.getPayerAccount(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getPayerAccount()), marginTopSize);
        graphics2DImage.drawString("DESTINO", getHorizontalSize(graphics2DImage, bgImageWidth, "DESTINO"), getNextMarginTop());
        graphics2DImage.drawString("NOME:", 4, getNextMarginTop());
        graphics2DImage.drawString(info.getReceiverName(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getReceiverName()), marginTopSize);
        graphics2DImage.drawString("CPF/CNPJ:", 4, getNextMarginTop());
        graphics2DImage.drawString(info.getReceiverDocument(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getReceiverDocument()), marginTopSize);
        
        if (EnumUtil.isEquals(paymentType, EnumPaymentType.PIX)) {
            graphics2DImage.drawString("INST:", 4, getNextMarginTop());
            graphics2DImage.drawString(info.getReceiverAccountBank(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getReceiverAccountBank()), marginTopSize);
            graphics2DImage.drawString("AGÊNCIA:", 4, getNextMarginTop());
            graphics2DImage.drawString(info.getReceiverAccountBranch(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getReceiverAccountBranch()), marginTopSize);
            graphics2DImage.drawString("CONTA:", 4, getNextMarginTop());
            graphics2DImage.drawString(info.getReceiverAccount(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getReceiverAccount()), marginTopSize);
        }
        
        graphics2DImage.drawString(separator, getHorizontalSize(graphics2DImage, bgImageWidth, separator), getNextMarginTop());
        graphics2DImage.drawString("DATA DO PAGAMENTO:", 4, getNextMarginTop());
        graphics2DImage.drawString(paymentDate.substring(0, 10), getLeftTextSize(graphics2DImage, bgImageWidth, paymentDate.substring(0, 10)), marginTopSize);
        
        if (EnumUtil.isEquals(paymentType, EnumPaymentType.BOLETO)) {
            graphics2DImage.drawString("DATA DE VENCIMENTO:", 4, getNextMarginTop());
            graphics2DImage.drawString(dueDate, getLeftTextSize(graphics2DImage, bgImageWidth, dueDate), marginTopSize);
            graphics2DImage.drawString("VALOR TÍTULO:", 4, getNextMarginTop());
            graphics2DImage.drawString(info.getAmount().toString(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getAmount().toString()), marginTopSize);
            graphics2DImage.drawString("VALOR PAGO:", 4, getNextMarginTop());
            graphics2DImage.drawString(payment.getRequestedAmount().toString(), getLeftTextSize(graphics2DImage, bgImageWidth, payment.getRequestedAmount().toString()), marginTopSize);
            graphics2DImage.drawString("LINHA DIGITÁVEL", getHorizontalSize(graphics2DImage, bgImageWidth, "LINHA DIGITÁVEL"), getNextMarginTop());
            
            if (digitable.length() == 44) {
                graphics2DImage.drawString(digitable.substring(0, 24), getHorizontalSize(graphics2DImage, bgImageWidth, digitable.substring(0, 24)), getNextMarginTop());
                graphics2DImage.drawString(digitable.substring(25, 44), getHorizontalSize(graphics2DImage, bgImageWidth, digitable.substring(25, 44)), getNextMarginTop());
            } else if (digitable.length() == 47) {
                graphics2DImage.drawString(digitable.substring(0, 24), getHorizontalSize(graphics2DImage, bgImageWidth, digitable.substring(0, 24)), getNextMarginTop());
                graphics2DImage.drawString(digitable.substring(25, 47), getHorizontalSize(graphics2DImage, bgImageWidth, digitable.substring(25, 47)), getNextMarginTop());
            } else {
                graphics2DImage.drawString(digitable.substring(0, 24), getHorizontalSize(graphics2DImage, bgImageWidth, digitable.substring(0, 24)), getNextMarginTop());
                graphics2DImage.drawString(digitable.substring(25, 48), getHorizontalSize(graphics2DImage, bgImageWidth, digitable.substring(25, 48)), getNextMarginTop());
            }
        }

        if (EnumUtil.isEquals(paymentType, EnumPaymentType.PIX)) {
            graphics2DImage.drawString("VALOR PIX:", 4, getNextMarginTop());
            graphics2DImage.drawString(info.getAmount().toString(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getAmount().toString()), marginTopSize);
            graphics2DImage.drawString("ID. PIX:", 4, getNextMarginTop());
            graphics2DImage.drawString(info.getEndToEndId(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getEndToEndId()), marginTopSize);
        }

        graphics2DImage.drawString("VÁLIDO COMO RECIBO DE PAGAMENTO", getHorizontalSize(graphics2DImage, bgImageWidth, "VÁLIDO COMO RECIBO DE PAGAMENTO"), getNextMarginTop());

        graphics2DImage.drawString(separator, getHorizontalSize(graphics2DImage, bgImageWidth, separator), getNextMarginTop());
        graphics2DImage.drawString("AUTENTICAÇÃO", getHorizontalSize(graphics2DImage, bgImageWidth, "AUTENTICAÇÃO"), getNextMarginTop());
        graphics2DImage.drawString(info.getAuthentication(), getHorizontalSize(graphics2DImage, bgImageWidth, info.getAuthentication()), getNextMarginTop());
        graphics2DImage.drawString(info.getLastAuthentication(), getHorizontalSize(graphics2DImage, bgImageWidth, info.getLastAuthentication()), getNextMarginTop());
        graphics2DImage.drawString(separator, getHorizontalSize(graphics2DImage, bgImageWidth, separator), getNextMarginTop());

        return graphics2DImage;

    }

    private static int getNextMarginTop() {
        marginTopSize = marginTopSize + 20;

        return marginTopSize;
    }

    private static int getHorizontalSize(Graphics2D graphics2DImage, int bgImageWidth, String string) {
        int stringWidthLength = (int) graphics2DImage.getFontMetrics().getStringBounds(string, graphics2DImage).getWidth();

        return bgImageWidth / 2 - stringWidthLength / 2;
    }

    private static int getLeftTextSize(Graphics2D graphics2DImage, int bgImageWidth, String string) {
        int stringWidthLength = (int) graphics2DImage.getFontMetrics().getStringBounds(string, graphics2DImage).getWidth();

        return bgImageWidth - stringWidthLength - 4;
    }

}
