package src;
import java.sql.Date;

public class Consumivel extends Produto {
    
    private String porcao;
    private String validade;

    public Consumivel(String _codigo, String _nome, int _quantidade, double _valorDeCompra, double _valorDeVenda) {
        this.codigo = _codigo;
        this.nome = _nome;
        this.quantidade = _quantidade;
        //this.tipo = _tipo;
        this.valorDeCompra = _valorDeCompra;
        this.valorDeVenda = _valorDeVenda;
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