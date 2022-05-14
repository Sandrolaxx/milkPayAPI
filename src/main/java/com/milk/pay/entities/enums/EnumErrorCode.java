package com.milk.pay.entities.enums;

import com.milk.pay.utils.EnumUtil;

import org.apache.http.HttpStatus;

/**
 *
 * @author SRamos
 */
public enum EnumErrorCode implements IEnum {

    // Erros API
    JWT_NAO_INFORMADO("001", "Token não informado!", HttpStatus.SC_UNAUTHORIZED),
    USUARIO_JWT_NAO_ENCONTRADO("002", "Usuário informado no Token não encontrado!", HttpStatus.SC_UNAUTHORIZED),
    JWT_EXPIRADO("003", "Token expirado!", HttpStatus.SC_UNAUTHORIZED),
    REQUISICAO_NAO_TRATADA("004", "Não foi possível tratar sua requisição, verifique os campos enviados.", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    ERRO_DECRYPT("005", "Erro ao decriptar as informações da conta.", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    VALOR_PAGAMENTO_INVALIDO("006", "Valor de pagamento informado: {0} inválido. O Valor deve ser maior do que zero e menor do que 9999999,99.", HttpStatus.SC_BAD_REQUEST),
    INFORMACAO_PAGAMENTO_INVALIDA("007", "Não foram encontradas as informações para realização do pagamento.", HttpStatus.SC_BAD_REQUEST),
    PARAMETROS_INVALIDOS("008", "Um ou mais parâmetros obrigatórios não foram informados! Parâmetros: {0}", HttpStatus.SC_BAD_REQUEST),
    CAMPO_PAGAMENTO_PIX_INVALIDO("009", "{0} informado no Pagamento Pix é inválido!", HttpStatus.SC_BAD_REQUEST),
    CHAVE_PIX_NAO_INFORMADA("011", "Chave Pix não informada!", HttpStatus.SC_BAD_REQUEST),
    ERRO_SALVAR_PAGAMENTO_PIX("012", "Erro persistir pagamento Pix!", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    ERRO_SALVAR_COMPROVANTE("013", "Erro ao salvar comprovante!", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    ERRO_AO_CADASTRAR_USUARIO("014", "Ocorreu um erro interno e não foi possível cadastrar o usuário!", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    USUARIO_SEM_CREDENCIAIS("015", "Rota não disponível para as credenciais informadas.", HttpStatus.SC_UNAUTHORIZED),
    PAGAMENTO_PIX_JA_REALIZADO("016", "Já realizado Pagamento Pix para o end-to-end informado! Consulte novamente a Chave Pix.", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    ERRO_SALVAR_TIT_USUARIO_INVALIDO("017", "Não foi possível salvar o título! usuário não encontrado para o documento informado.", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    ERRO_AO_ENCONTRAR_TITULO("018", "Não foi possível encontrar o título informado.", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    CAMPO_OBRIGATORIO("019", "Campo {0} é obrigatório, favor informar.", HttpStatus.SC_BAD_REQUEST),
    DATA_VENCIMENTO_INVALIDA("020", "A data de vencimento informada não pode ser menor que a data do dia de amanhã.", HttpStatus.SC_BAD_REQUEST),
    BARCODE_DIGITABLE_NAO_INFORMADOS("021", "É necessário informar o código de barras ou linha digitável!", HttpStatus.SC_BAD_REQUEST),
    TIT_NAO_ENCONTRADO("022", "Título não encontrado para o identificador informado!", HttpStatus.SC_BAD_REQUEST),
    TIT_POSSUI_LIQUIDACAO("023", "Título informado já foi liquidado!", HttpStatus.SC_BAD_REQUEST),
    BOLETO_VENCIDO("024", "Boleto de pagamento vencido! Impossível realizar o pagamento após seu vencimento.", HttpStatus.SC_BAD_REQUEST),
    //Erros externos
    ERRO_COMUNICACAO("050", "A requisição enviada ao parceiro retornou com erro!", HttpStatus.SC_BAD_GATEWAY),
    ERRO_AUTENTICACAO("051", "Erro ao se autenticar no parceiro!", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    ERRO_TRASNF_CHAVE_PIX("052", "Erro ao tentar realizar a transferência via Chave Pix, falha na comunicação com o parceiro!", HttpStatus.SC_BAD_REQUEST),
    ERRO_PAGAMENTO_PIX("053", "Erro ao tentar realizar a pagamento Pix, falha na comunicação com o parceiro!", HttpStatus.SC_BAD_REQUEST),
    CHAVE_CONSULTADA_INEXISTENTE("054", "Erro ao consultar Chave Pix, nennhum dado encontrado para a chave informada!", HttpStatus.SC_NOT_FOUND),
    ERRO_CONSULTAR_CHAVE_PIX("055", "Erro ao tentar realizar a consulta da Chave Pix, falha na comunicação com o parceiro!", HttpStatus.SC_BAD_REQUEST),
    ERRO_CONSULTAR_BOLETO("056", "Erro ao tentar realizar a consulta do boleto, falha na comunicação com o parceiro!", HttpStatus.SC_BAD_REQUEST),
    ERRO_PAGAMENTO_BOLETO("057", "Erro ao tentar realizar o pagamento do boleto, falha na comunicação com o parceiro!", HttpStatus.SC_BAD_REQUEST),
    ERRO_CONFIRMAR_PAGAMENTO_BOLETO("058", "Erro ao tentar realizar confirmação do pagamento do boleto, falha na comunicação com o parceiro!", HttpStatus.SC_BAD_REQUEST),
    ERRO_AO_BUSCAR_DADOS_USUARIO("059", "Erro ao buscar informações do usuário em nossoo parceiro!", HttpStatus.SC_BAD_GATEWAY);

    private final String key, erro;
    private final int httpStatus;

    private EnumErrorCode(String key, String error, int httpStatus) {
        this.key = key;
        this.erro = error;
        this.httpStatus = httpStatus;
    }
    
    @Override
    public String getKey() {
        return key;
    }

    public String getErro() {
        return erro;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    @Override
    public boolean containsInEnum(String key) {
        return parseByKey(key) != null;
    }

    public static EnumErrorCode parseByKey(String key) {
        return (EnumErrorCode) EnumUtil.parseByKey(EnumErrorCode.class, key);
    }
    
}
