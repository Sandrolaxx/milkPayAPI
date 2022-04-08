package com.milk.pay.entities.enums;

import com.milk.pay.utils.Utils;

/**
 *
 * @author SRamos
 */
public enum EnumMovementCode implements IEnum {
    
    NAO_INFORMADO("000", "Não Informado", "000", "000"),
    ENCARGOS("001", "Encargos", "102", "000"),
    ESTORNOS("002", "Estornos", "103", "204"),
    TARIFAS("003", "Tarifas", "105", "000"),
    APLICACAO("004", "Aplicação", "106", "206"),
    EMPRESTIMO("005", "Empréstimo", "107", "207"),
    FINANCIAMENTO("006", "Financiamento", "107", "207"),
    IOF("007", "IOF", "110", "000"),
    IR("008", "Imposto de Renda", "111", "000"),
    PAGAMENTO_FORNECEDORES("009", "Pagamento Fornecedores", "112", "217"),
    PAGAMENTO_SALARIO("010", "Pagamento Salário", "113", "000"),
    SAQUE_ELETRONICO("011", "Saque Eletrônico", "114", "000"),
    ACOES("012", "Ações", "115", "210"),
    TRANSF_ENTRE_CONTAS("013", "Transf. Entre Contas", "117", "213"),
    TRANSF_ENTRE_CONTAS_DIGITAIS("014", "Transf. Entre Contas Digitais DafePay", "117", "213"),
    DEVOLUCAO_COMPENSACAO("015", "Devolução da Compensação", "118", "215"),
    TRANSF_INTERBANCARIA_PIX("016", "Transf. Interbancária via Pix", "120", "209"),
    TRANSF_INTERBANCARIA_TED("017", "Transf. Interbancária via TED/DOC", "120", "209"),
    ANTECIPACAO_FORNECEDORES("018", "Antecipação a Fornecedores", "121", "000"),
    SAQUE_PIX("019", "Saque Pix", "000", "000"),
    PAGAMENTOS_DIVERSOS("020", "Pagementos Diversos", "125", "218"),
    PAGAMENTO_BOLETO("021", "Pagamento de Boleto", "125", "000"),
    PAGAMENTO_PIX("022", "Pagamento via Pix", "125", "218"),
    PAGAMENTO_TRIBUTOS("023", "Pagamento de Contas Concessionarias/Tributos", "126", "000"),
    CARTAO_CREDITO_FATURA("024", "Pagamento de fatura de cartão de crédito", "127", "000"),
    RECEBIMENTO_CARTAO_CREDITO("025", "Pagamento de fatura de cartão de crédito", "000", "222"),
    RECEBIMENTO_BOLETO("026", "Recebimento de Boleto", "000", "202"),
    RESGATE_APLICACAO("027", "Resgate de Aplicação", "000", "206"),
    RECEBIMENTO_PIX_VIA_QR_CODE("028", "Recebimento Pix via QR Code", "000", "223"),
    RECEBIMENTO_PIX_VIA_CHAVE("029", "Recebimento Pix via Chave", "000", "000"),
    DEVOLUCAO_RECEBIMENTO_PIX("030", "Devolução de Recebimento PIX", "103", "204"),
    RECEBIMENTO_SALARIO("031", "Recebimento Salário", "000", "219");

    private final String key, value, codFebrabanDebito, codFebrabanCredito;

    private EnumMovementCode(String key, String value, String codFebrabanDebito, String codFebrabanCredito) {
        this.key = key;
        this.value = value;
        this.codFebrabanDebito = codFebrabanDebito;
        this.codFebrabanCredito = codFebrabanCredito;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getCodFebrabanDebito() {
        return codFebrabanDebito;
    }

    public String getCodFebrabanCredito() {
        return codFebrabanCredito;
    }

    @Override
    public boolean containsInEnum(String key) {
        return parseByKey(key) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> T parseByKey(String key) {
        return (T) Utils.parseByKey(EnumMovementCode.class, key);
    }
    
}
