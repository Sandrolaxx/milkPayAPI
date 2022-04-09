package com.milk.pay.entities.enums;

import com.milk.pay.utils.Utils;

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
    CAMPO_PAGAMENTO_PIX_INVALIDO("0009", "{0} Pagamento Pix inválido!", HttpStatus.SC_BAD_REQUEST),
    CHAVE_PIX_NAO_INFORMADA("011", "Chave Pix não informada!", HttpStatus.SC_BAD_REQUEST),
    ERRO_PERSISTIR_PAGAMENTO_PIX("012", "Erro persistir pagamento Pix!", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    ERRO_SALVAR_COMPROVANTE("013", "Erro ao salvar comprovante!", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    ERRO_AO_CADASTRAR_USUARIO("014", "Ocorreu um erro interno e não foi possível cadastrar o usuário!", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    USUARIO_SEM_CREDENCIAIS("015", "Rota não disponível para as credenciais informadas.", HttpStatus.SC_UNAUTHORIZED),
    //Erros externos
    ERRO_COMUNICACAO_CELCOIN("050", "A requisição enviada a Celcoin retornou com erro!", HttpStatus.SC_BAD_GATEWAY),
    ERRO_PARSE_JSON_CELCOIN("052", "Erro ao realizar o parse do Json de resposta da Celcoin!", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    ERRO_AUTENTICACAO_CELCOIN("053", "Erro ao se autenticar na celcoin!", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    ERRO_TRASNF_CHAVE_PIX_CELCOIN("054", "Erro ao tentar realizar a transferência via Chave Pix, falha na comunicação com a Celcoin!", HttpStatus.SC_BAD_REQUEST),
    ERRO_PAGAMENTO_PIX_CELCOIN("055", "Erro ao tentar realizar a pagamento Pix, falha na comunicação com a Celcoin!", HttpStatus.SC_BAD_REQUEST),
    CHAVE_CONSULTADA_INEXISTENTE("056", "Erro ao consultar Chave Pix, nennhum dado encontrado para a chave informada!", HttpStatus.SC_NOT_FOUND),
    ERRO_CONSULTAR_CHAVE_PIX_CELCOIN("057", "Erro ao tentar realizar a consulta da Chave Pix, falha na comunicação com a Celcoin!", HttpStatus.SC_BAD_REQUEST);

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
        return (EnumErrorCode) Utils.parseByKey(EnumErrorCode.class, key);
    }
    
}
