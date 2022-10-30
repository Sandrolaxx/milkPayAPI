package com.aktie.aktiepay.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.aktie.aktiepay.entities.ReceiptInfo;
import com.aktie.aktiepay.entities.enums.EnumErrorCode;
import com.aktie.aktiepay.entities.enums.EnumPaymentType;

/**
 *
 * @author SRamos
 */
public class ReceiptUtil {

    public static String handleCreate(ReceiptInfo receipt) {

        try {
            var newImg = getBlankImage(320, 700);
            var graphics2D = (Graphics2D) newImg.getGraphics();

            graphics2D.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
            graphics2D.setColor(Color.BLACK);
            
            addReceiptParams(graphics2D, receipt, newImg.getWidth());

            var btArrayOutputStram = new ByteArrayOutputStream();
            ImageIO.write(newImg, "png", btArrayOutputStram);

            return new String(Base64.getEncoder().encodeToString(btArrayOutputStram.toByteArray()));
        } catch (Exception e) {
            throw new AktiePayException(EnumErrorCode.ERRO_GERAR_COMPROVANTE);
        }

    }

    private static BufferedImage getBlankImage(int width, int height) {
        var newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        var graphics2D = (Graphics2D) newImg.getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, width, height);

        graphics2D = addCompanyLogo(graphics2D, width);

        return newImg;
    }

    private static Graphics2D addReceiptParams(Graphics2D graphics2DImage, ReceiptInfo info, int bgImageWidth) {
        int marginTopSize = 90;

        var companyName = "MILKPAY PAGAMENTOS";
        var terminal = "AKTIE-API";
        var paymentType = info.getPayment().getTitle().getPaymentType();
        var movementType = EnumUtil.isEquals(paymentType, EnumPaymentType.PIX) ? "PAGAMENTO PIX" : "PAGAMENTO DE TÍTULO";
        var payment = info.getPayment();
        var protocol = payment.getId().toString();
        var paymentDate = DateUtil.formatDDMMYYYYHHMM(info.getPaidAt());
        var dueDate = DateUtil.formatDDMMYYYY(info.getDueDate());
        var digitable = info.getDigitable();
        var payerBank = info.getPayerAccountBank().length() > 34 ? info.getPayerAccountBank().substring(0, 34).concat("...") : info.getPayerAccountBank();
        var payerName = info.getPayerName().length() > 34 ? info.getPayerName().substring(0, 34).concat("...") : info.getPayerName();
        var receiverName = info.getReceiverName().length() > 34 ? info.getReceiverName().substring(0, 34).concat("...") : info.getReceiverName();
        var receiverBank = info.getReceiverAccountBank().length() > 34 ? info.getReceiverAccountBank().substring(0, 34).concat("...") : info.getReceiverAccountBank();
        var separator = "--------------------------------------------------------------------------";

        graphics2DImage.drawString(companyName, getHorizontalSize(graphics2DImage, bgImageWidth, companyName), marginTopSize);
        graphics2DImage.drawString("PROTOCOLO:", 4, marginTopSize += 20);
        graphics2DImage.drawString(protocol, getLeftTextSize(graphics2DImage, bgImageWidth, protocol), marginTopSize);
        graphics2DImage.drawString("TERMINAL:", 4, marginTopSize += 20);
        graphics2DImage.drawString(terminal, getLeftTextSize(graphics2DImage, bgImageWidth, terminal), marginTopSize);
        graphics2DImage.drawString(paymentDate, getHorizontalSize(graphics2DImage, bgImageWidth, paymentDate), marginTopSize += 20);
        graphics2DImage.drawString(separator, getHorizontalSize(graphics2DImage, bgImageWidth, separator), marginTopSize += 20);
        
        graphics2DImage.drawString(movementType, getHorizontalSize(graphics2DImage, bgImageWidth, movementType), marginTopSize += 20);
        graphics2DImage.drawString("ORIGEM", getHorizontalSize(graphics2DImage, bgImageWidth, "ORIGEM"), marginTopSize += 20);
        graphics2DImage.drawString("NOME:", 4, marginTopSize += 20);
        graphics2DImage.drawString(payerName, getLeftTextSize(graphics2DImage, bgImageWidth, payerName), marginTopSize);
        graphics2DImage.drawString("CPF/CNPJ:", 4, marginTopSize += 20);
        graphics2DImage.drawString(info.getPayerDocument(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getPayerDocument()), marginTopSize);
        graphics2DImage.drawString("INST:", 4, marginTopSize += 20);
        graphics2DImage.drawString(payerBank, getLeftTextSize(graphics2DImage, bgImageWidth, payerBank), marginTopSize);
        graphics2DImage.drawString("AGÊNCIA:", 4, marginTopSize += 20);
        graphics2DImage.drawString(info.getPayerAccountBranch(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getPayerAccountBranch()), marginTopSize);
        graphics2DImage.drawString("CONTA:", 4, marginTopSize += 20);
        graphics2DImage.drawString(info.getPayerAccount(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getPayerAccount()), marginTopSize);
        graphics2DImage.drawString("DESTINO", getHorizontalSize(graphics2DImage, bgImageWidth, "DESTINO"), marginTopSize += 20);
        graphics2DImage.drawString("NOME:", 4, marginTopSize += 20);
        graphics2DImage.drawString(receiverName, getLeftTextSize(graphics2DImage, bgImageWidth, receiverName), marginTopSize);
        
        if (EnumUtil.isEquals(paymentType, EnumPaymentType.PIX)) {
            graphics2DImage.drawString("CPF/CNPJ:", 4, marginTopSize += 20);
            graphics2DImage.drawString(info.getReceiverDocument(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getReceiverDocument()), marginTopSize);
        }
        
        graphics2DImage.drawString("INST:", 4, marginTopSize += 20);
        graphics2DImage.drawString(receiverBank, getLeftTextSize(graphics2DImage, bgImageWidth, receiverBank), marginTopSize);

        if (EnumUtil.isEquals(paymentType, EnumPaymentType.PIX)) {
            graphics2DImage.drawString("AGÊNCIA:", 4, marginTopSize += 20);
            graphics2DImage.drawString(info.getReceiverAccountBranch(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getReceiverAccountBranch()), marginTopSize);
            graphics2DImage.drawString("CONTA:", 4, marginTopSize += 20);
            graphics2DImage.drawString(info.getReceiverAccount(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getReceiverAccount()), marginTopSize);
        }
        
        graphics2DImage.drawString(separator, getHorizontalSize(graphics2DImage, bgImageWidth, separator), marginTopSize += 20);
        graphics2DImage.drawString("DATA DO PAGAMENTO:", 4, marginTopSize += 20);
        graphics2DImage.drawString(paymentDate.substring(0, 10), getLeftTextSize(graphics2DImage, bgImageWidth, paymentDate.substring(0, 10)), marginTopSize);
        
        if (EnumUtil.isEquals(paymentType, EnumPaymentType.BOLETO)) {
            graphics2DImage.drawString("DATA DE VENCIMENTO:", 4, marginTopSize += 20);
            graphics2DImage.drawString(dueDate, getLeftTextSize(graphics2DImage, bgImageWidth, dueDate), marginTopSize);
            graphics2DImage.drawString("VALOR TÍTULO:", 4, marginTopSize += 20);
            graphics2DImage.drawString(info.getAmount().toString(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getAmount().toString()), marginTopSize);
            graphics2DImage.drawString("VALOR PAGO:", 4, marginTopSize += 20);
            graphics2DImage.drawString(payment.getRequestedAmount().toString(), getLeftTextSize(graphics2DImage, bgImageWidth, payment.getRequestedAmount().toString()), marginTopSize);
            graphics2DImage.drawString("LINHA DIGITÁVEL", getHorizontalSize(graphics2DImage, bgImageWidth, "LINHA DIGITÁVEL"), marginTopSize += 20);
            
            if (digitable.length() == 44) {
                graphics2DImage.drawString(digitable.substring(0, 24), getHorizontalSize(graphics2DImage, bgImageWidth, digitable.substring(0, 24)), marginTopSize += 20);
                graphics2DImage.drawString(digitable.substring(25, 44), getHorizontalSize(graphics2DImage, bgImageWidth, digitable.substring(25, 44)), marginTopSize += 20);
            } else if (digitable.length() == 47) {
                graphics2DImage.drawString(digitable.substring(0, 24), getHorizontalSize(graphics2DImage, bgImageWidth, digitable.substring(0, 24)), marginTopSize += 20);
                graphics2DImage.drawString(digitable.substring(25, 47), getHorizontalSize(graphics2DImage, bgImageWidth, digitable.substring(25, 47)), marginTopSize += 20);
            } else {
                graphics2DImage.drawString(digitable.substring(0, 24), getHorizontalSize(graphics2DImage, bgImageWidth, digitable.substring(0, 24)), marginTopSize += 20);
                graphics2DImage.drawString(digitable.substring(25, 48), getHorizontalSize(graphics2DImage, bgImageWidth, digitable.substring(25, 48)), marginTopSize += 20);
            }
        }

        if (EnumUtil.isEquals(paymentType, EnumPaymentType.PIX)) {
            graphics2DImage.drawString("VALOR TÍTULO:", 4, marginTopSize += 20);
            graphics2DImage.drawString(info.getPayment().getTitle().getAmount().toString(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getPayment().getTitle().getAmount().toString()), marginTopSize);
            graphics2DImage.drawString("VALOR JURO:", 4, marginTopSize += 20);
            graphics2DImage.drawString(info.getPayment().getInterestAmount().toString(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getPayment().getInterestAmount().toString()), marginTopSize);
            graphics2DImage.drawString("VALOR PIX:", 4, marginTopSize += 20);
            graphics2DImage.drawString(info.getAmount().toString(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getAmount().toString()), marginTopSize);
            graphics2DImage.drawString("ID. PIX:", 4, marginTopSize += 20);
            graphics2DImage.drawString(info.getEndToEndId(), getLeftTextSize(graphics2DImage, bgImageWidth, info.getEndToEndId()), marginTopSize);
        }

        marginTopSize += 20;
        graphics2DImage.drawString("VÁLIDO COMO RECIBO DE PAGAMENTO", getHorizontalSize(graphics2DImage, bgImageWidth, "VÁLIDO COMO RECIBO DE PAGAMENTO"), marginTopSize += 20);

        graphics2DImage.drawString(separator, getHorizontalSize(graphics2DImage, bgImageWidth, separator), marginTopSize += 20);
        graphics2DImage.drawString("AUTENTICAÇÃO", getHorizontalSize(graphics2DImage, bgImageWidth, "AUTENTICAÇÃO"), marginTopSize += 20);
        graphics2DImage.drawString(info.getAuthentication(), getHorizontalSize(graphics2DImage, bgImageWidth, info.getAuthentication()), marginTopSize += 20);
        graphics2DImage.drawString(info.getLastAuthentication(), getHorizontalSize(graphics2DImage, bgImageWidth, info.getLastAuthentication()), marginTopSize += 20);
        graphics2DImage.drawString(separator, getHorizontalSize(graphics2DImage, bgImageWidth, separator), marginTopSize += 20);

        return graphics2DImage;

    }

    private static Graphics2D addCompanyLogo(Graphics2D graphics2DImage, int bgImageWidth) {
        try {
            var logo = ImageIO.read(new File("src/main/resources/META-INF/resources/logo.png"));

            graphics2DImage.drawImage(logo, null, ((bgImageWidth / 2) - 40), 8);
        } catch (Exception e) {
            throw new AktiePayException(EnumErrorCode.ERRO_GERAR_COMPROVANTE);
        }

        return graphics2DImage;
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
