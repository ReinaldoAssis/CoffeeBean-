package src;
import java.sql.Date;

import service.interfaces.iProdutoVisitor;

public class Consumivel extends Produto {
    
    private String porcao;
    private String validade;

    public Consumivel(String _codigo, String _nome, int _quantidade, double _valorDeCompra, double _valorDeVenda) {
        this.codigo = _codigo;
        this.nome = _nome;
        this.quantidade = _quantidade;
        this.valorDeCompra = _valorDeCompra;
        this.valorDeVenda = _valorDeVenda;
    }

    @Override
    public void accept(iProdutoVisitor visitor){
        visitor.visit(this);
    }

    public String getPorcao() {
        return porcao;
    }

    public void setPorcao(String porcao) {
        this.porcao = porcao;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    @Override
    public double calcularDesconto(int fidelidade, int quantidade) {
        return this.valorDeVenda * (1 - quantidade/100);
    }
    

    @Override
    public String toString() {
        String str = "";
        str += "Quantidade: " + this.quantidade + "\n";
        str += "Validade: " + this.validade + "\n";
        str += "Porção: " + this.porcao + "\n";
        str += "Custo: " + this.valorDeCompra + "\n";
        str += "Venda: " + this.valorDeVenda + "\n";
        str += "Código: " + this.codigo + "\n";

        return str; 
    }

}