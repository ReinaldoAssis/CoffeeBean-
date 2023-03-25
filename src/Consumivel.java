package src;
import java.sql.Date;

public class Consumivel extends Produto {
    
    public String tipo;
    public Date validade;

    public Consumivel(String _codigo, String _nome, int _quantidade, double _valorDeCompra, double _valorDeVenda) {
        this.codigo = _codigo;
        this.nome = _nome;
        this.quantidade = _quantidade;
        //this.tipo = _tipo;
        this.valorDeCompra = _valorDeCompra;
        this.valorDeVenda = _valorDeVenda;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + "\nValidade: " + validade.toString();
    }

}