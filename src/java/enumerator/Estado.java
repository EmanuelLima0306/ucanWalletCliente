/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enumerator;

/**
 *
 * @author emanuellima
 */
public enum Estado {
    ACTIVO("Activo"),
    VALIDA("Válida"),
    EM_VALIDACAO("Em Validação"),
    NAO_VALIDA("Não Válida"),
    REJEITADA("Rejeitada"),
    BLOQUEADA("Bloqueada");
    
    private String descricao;
    
    private Estado(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
    
}
