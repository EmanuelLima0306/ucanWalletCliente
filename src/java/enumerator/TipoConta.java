/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enumerator;

/**
 *
 * @author emanuellima
 */
public enum TipoConta {
    KWANZA("Kwanza"),
    DOLAR("Dolar"),
    EURO("Euro");
    
    private String descricao;
    
    private TipoConta(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
    
}
