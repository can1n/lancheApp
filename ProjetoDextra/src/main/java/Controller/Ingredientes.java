/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

public class Ingredientes {

    private Integer id;
    private String descritivo;
    private Double valor;
    private Integer qtde;
    
    public Ingredientes(){
        
    }

    public Ingredientes(Integer id, String descritivo, Double valor, Integer qtde) {
        this.id = id;
        this.descritivo = descritivo;
        this.valor = valor;
        this.qtde = qtde;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescritivo() {
        return descritivo;
    }

    public void setDescritivo(String descritivo) {
        this.descritivo = descritivo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }
    
    
}
