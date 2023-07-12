/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enumerator;

/**
 *
 * @author emanuellima
 */
public enum TipoCliente {
    PARTICULAR("Particular"),
    EMPRESA("Empresa");
    
    private String descricao;
    
    private TipoCliente(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
    
}
