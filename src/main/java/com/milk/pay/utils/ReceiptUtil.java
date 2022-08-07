package com.milk.pay.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.milk.pay.entities.ReceiptInfo;
import com.milk.pay.entities.enums.EnumErrorCode;

/**
 *
 * @author SRamos
 */
public class ReceiptUtil {

    private static int marginTopSize = 25;

    public static String createReceiptPix(ReceiptInfo receiptInfo) {

        var receiptLayout = replaceDefaultFields(getLayoutPixPaidReceipt(), receiptInfo);

        receiptLayout = receiptLayout.replace("{inst-recebedor}",
                StringUtil.addBlankLeftPad(receiptInfo.getReceiverAccountBank(), 35));
        receiptLayout = receiptLayout.replace("{agencia-recebedor}",
                StringUtil.addBlankLeftPad(receiptInfo.getReceiverAccountBranch(), 32));
        receiptLayout = receiptLayout.replace("{conta-recebedor}",
                StringUtil.addBlankLeftPad(receiptInfo.getReceiverAccount(), 34));
        receiptLayout = receiptLayout.replace("{end-to-end}", receiptInfo.getEndToEndId());
        receiptLayout = receiptLayout.replace("{valor-pgto}",
                StringUtil.addBlankLeftPad(receiptInfo.getAmount().toString(), 30));

        return receiptLayout;
    }

    public static String createReceiptBankSlip(ReceiptInfo receiptInfo) {

        var receiptLayout = replaceDefaultFields(getLayoutBankSlipReceipt(), receiptInfo);

        if (receiptInfo.getDigitable().length() == 44) {
            receiptLayout = receiptLayout.replace("{linha-digitavel-1}", receiptInfo.getDigitable().subSequence(0, 31));
            receiptLayout = receiptLayout.replace("{linha-digitavel-2}",
                    receiptInfo.getDigitable().subSequence(32, 44));
        } else if (receiptInfo.getDigitable().length() == 47) {
            receiptLayout = receiptLayout.replace("{linha-digitavel-1}", receiptInfo.getDigitable().subSequence(0, 31));
            receiptLayout = receiptLayout.replace("{linha-digitavel-2}",
                    receiptInfo.getDigitable().subSequence(32, 47));
        } else {
            receiptLayout = receiptLayout.replace("{linha-digitavel-1}", receiptInfo.getDigitable().subSequence(0, 31));
            receiptLayout = receiptLayout.replace("{linha-digitavel-2}",
                    receiptInfo.getDigitable().subSequence(32, 48));
        }

        receiptLayout = receiptLayout.replace("{vcto}",
                StringUtil.addBlankLeftPad(DateUtil.formatDDMMYYYY(receiptInfo.getDueDate()), 21));
        receiptLayout = receiptLayout.replace("{valor-tit}",
                StringUtil.addBlankLeftPad(receiptInfo.getAmount().toString(), 27));
        receiptLayout = receiptLayout.replace("{valor-cob}",
                StringUtil.addBlankLeftPad(receiptInfo.getAmount().toString(), 26));

        return receiptLayout;
    }

    public static String replaceDefaultFields(String receiptLayout, ReceiptInfo receiptInfo) {

        var dateTime = LocalDateTime.now();

        receiptLayout = receiptLayout.replace("{protocolo}",
                StringUtil.addBlankLeftPad(receiptInfo.getPayment().getId().toString(), 31));
        receiptLayout = receiptLayout.replace("{data-atual}", DateUtil.formatDDMMYYYY(LocalDate.now()));
        receiptLayout = receiptLayout.replace("{hora-atual}",
                StringUtil.addBlankLeftPad(dateTime.getHour() + ":" + dateTime.getMinute(), 30));
        receiptLayout = receiptLayout.replace("{tipo-movimento}",
                receiptInfo.getMovementCode().getValue().length() > 32
                        ? receiptInfo.getMovementCode().getValue().toUpperCase().substring(0, 32)
                        : receiptInfo.getMovementCode().getValue().toUpperCase());
        receiptLayout = receiptLayout.replace("{nome-pagador}",
                StringUtil.addBlankLeftPad(receiptInfo.getPayerName(), 35));
        receiptLayout = receiptLayout.replace("{doc-pagador}",
                StringUtil.addBlankLeftPad(receiptInfo.getPayerDocument(), 31));
        receiptLayout = receiptLayout.replace("{inst-pagador}",
                StringUtil.addBlankLeftPad(receiptInfo.getPayerAccountBank(), 35));
        receiptLayout = receiptLayout.replace("{agencia-pagador}",
                StringUtil.addBlankLeftPad(receiptInfo.getPayerAccountBranch(), 32));
        receiptLayout = receiptLayout.replace("{conta-pagador}",
                StringUtil.addBlankLeftPad(receiptInfo.getPayerAccount(), 34));
        receiptLayout = receiptLayout.replace("{nome-recebedor}",
                StringUtil.addBlankLeftPad(receiptInfo.getReceiverName(), 35));
        receiptLayout = receiptLayout.replace("{inst-recebedor}",
                StringUtil.addBlankLeftPad(receiptInfo.getReceiverAccountBank(), 35));
        receiptLayout = receiptLayout.replace("{data-pgto}",
                StringUtil.addBlankLeftPad(DateUtil.formatDDMMYYYY(LocalDate.now()), 22));
        receiptLayout = receiptLayout.replace("{autenticacao-anterior}", receiptInfo.getLastAuthentication());

        return receiptLayout;
    }

    public static String addReceiptAuth(String receipt, String authentication) {
        return receipt.replace("{autenticacao-atual}", authentication);
    }

    private static String getLayoutPixPaidReceipt() {

        StringBuilder sb = new StringBuilder();

        sb.append("   AKTIE TECNOLOGIA E PAGAMENTOS LTDA.\n\n");
        sb.append("PROTOCOLO{protocolo}\n");
        sb.append("TERM 0001                       DAFE-API\n");
        sb.append("{data-atual}{hora-atual}\n");
        sb.append("----------------------------------------\n");
        sb.append("    {tipo-movimento}    \n");
        sb.append("                 ORIGEM                 \n");
        sb.append("NOME:{nome-pagador}\n");
        sb.append("CPF/CNPJ:{doc-pagador}\n");
        sb.append("INST:{inst-pagador}\n");
        sb.append("AGENCIA:{agencia-pagador}\n");
        sb.append("CONTA:{conta-pagador}\n");
        sb.append("                 DESTINO                \n");
        sb.append("NOME:{nome-recebedor}\n");
        sb.append("CPF/CNPJ:{doc-recebedor}\n");
        sb.append("INST:{inst-recebedor}\n");
        sb.append("AGENCIA:{agencia-recebedor}\n");
        sb.append("CONTA:{conta-recebedor}\n");
        sb.append("----------------------------------------\n");
        sb.append("DATA DO PAGAMENTO:{data-pgto}\n");
        sb.append("VALOR PIX:{valor-pgto}\n");
        sb.append("ID. PIX:{end-to-end}\n\n");
        sb.append("    VALIDO COMO RECIBO DE PAGAMENTO     \n");
        sb.append("----------------------------------------\n");
        sb.append("              AUTENTICACAO              \n");
        sb.append("    {autenticacao-anterior}    \n");
        sb.append("    {autenticacao-atual}    \n");
        sb.append("----------------------------------------\n");

        return sb.toString();

    }

    private static String getLayoutBankSlipReceipt() {

        StringBuilder sb = new StringBuilder();

        sb.append("   AKTIE TECNOLOGIA E PAGAMENTOS LTDA.\n\n");
        sb.append("PROTOCOLO{protocolo}\n");
        sb.append("TERM 0001                       DAFE-API\n");
        sb.append("{data-atual}{hora-atual}\n");
        sb.append("----------------------------------------\n");
        sb.append("    {tipo-movimento}    \n");
        sb.append("                 ORIGEM                 \n");
        sb.append("NOME:{nome-pagador}\n");
        sb.append("CPF/CNPJ:{doc-pagador}\n");
        sb.append("INST:{inst-pagador}\n");
        sb.append("AGENCIA:{agencia-pagador}\n");
        sb.append("CONTA:{conta-pagador}\n");
        sb.append("                 DESTINO                \n");
        sb.append("NOME:{nome-recebedor}\n");
        sb.append("CPF/CNPJ:{doc-recebedor}\n");
        sb.append("----------------------------------------\n");
        sb.append("DATA DE VENCIMENTO:{vcto}\n");
        sb.append("DATA DO PAGAMENTO:{data-pgto}\n");
        sb.append("VALOR TITULO:{valor-tit}\n");
        sb.append("VALOR COBRADO:{valor-cob}\n");
        sb.append("             LINHA DIGITAVEL            \n");
        sb.append("    {linha-digitavel-1}    \n");
        sb.append("       {linha-digitavel-2}       \n");
        sb.append("    VALIDO COMO RECIBO DE PAGAMENTO     \n");
        sb.append("----------------------------------------\n");
        sb.append("              AUTENTICACAO              \n");
        sb.append("    {autenticacao-anterior}    \n");
        sb.append("    {autenticacao-atual}    \n");
        sb.append("----------------------------------------\n");

        return sb.toString();

    }

    public static String handleCreate(String receipt) {

        try {
            var newImg = getBlankImage(260, 720);
            var graphics2D = (Graphics2D) newImg.getGraphics();

            graphics2D.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
            graphics2D.setColor(Color.BLACK);
            
            setTextCenter(graphics2D, "Teste:Sandrolax", newImg, 10);

            var btArrayOutputStram = new ByteArrayOutputStream();
            ImageIO.write(newImg, "png", btArrayOutputStram);

            return new String(Base64.getEncoder().encodeToString(btArrayOutputStram.toByteArray()));
        } catch (Exception e) {
            throw new MilkPayException(EnumErrorCode.ERRO_SALVAR_COMPROVANTE);
        }

    }

    private static Graphics2D setTextCenter(Graphics2D graphics2DImage, String string,
            BufferedImage bgImage, int textVerticalLocation) {
        graphics2DImage = addText(graphics2DImage, bgImage.getWidth());

        return graphics2DImage;
    }

    private static BufferedImage getBlankImage(int width, int height) {
        var newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        var graphics2D = (Graphics2D) newImg.getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, width, height);

        return newImg;
    }

    private static Graphics2D addText(Graphics2D graphics2DImage, int bgImageWidth) {
        var separator = "---------------------------------------------------------";

        graphics2DImage.drawString("AKTIE TECNOLOGIA", getHorizontalSize(graphics2DImage, bgImageWidth, "AKTIE TECNOLOGIA"), marginTopSize);
        graphics2DImage.drawString(" PROTOCOLO:", 0, getNextMarginTop());
        graphics2DImage.drawString("XX123123", getLeftTextSize(graphics2DImage, bgImageWidth, "XX123123"), marginTopSize);
        graphics2DImage.drawString(" TERMINAL:", 0, getNextMarginTop());
        graphics2DImage.drawString("AKTIE-API", getLeftTextSize(graphics2DImage, bgImageWidth, "AKTIE-API"), marginTopSize);
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
