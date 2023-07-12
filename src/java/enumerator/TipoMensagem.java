/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enumerator;

/**
 *
 * @author Celso de Sousa
 */
public enum TipoMensagem {
    
    PREENCHA_CAMPO("Preencha os Campos Obrigatórios!"),
    DADOS_EXISTENTES("Dados já Existem!"),
    DADOS_NAO_EXISTENTE("Dados Não Existente!"),
    SUCESSO("Operação Realizada com Sucesso!"),
    CREDENCIAS_NAO_CONDIZEM("As Credênciais não Condizem!"),
    ERRO("Erro ao Realizar a Operação!"),
    ERRO_CONTA_ORIGEM_IGUAL_DESTINO("Erro ao Realizar a Operação Conta de Origem é Igual ao Destino!"),
    ERRO_INESPERADO("Ocorreu um Erro Inesperado!"),
    SELECIONE("Selecione o Dado!"),
    NULL("Dados Nulo!"),
    CREDENCIAIS_INVALIDA("Credênciais Inválidas!"),
    ERRO_MOSTRAT_RELATORIO("Erro ao Mostrar o Relatório"),
    VALOR_INSUFICIENTE("Valor Insuficiente para Realizar a Operação!"),
    SALDO_INSUFICIENTE("Saldo Insuficiente para Realizar a Operação!"),
    USUARIO_OU_SENHA_INVALIDA("Usuário ou Senha Inválida!"),
    ENVIANDO("Dados a ser enviados!"),
    SEM_FOTO_SELECIONADA("Foto não Selecionada!");
    
    private String descricao;

    TipoMensagem(String descricao) {
        this.descricao = descricao;
    }
    
    
    public String getDescricao() {
        return this.descricao;
    }
}
