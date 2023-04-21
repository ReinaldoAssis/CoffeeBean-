package src;
import java.util.List;

import service.Utils;
import service.interfaces.iProdutoVisitor;

public class Livro extends Produto {
    

    public int paginas;
    private String autor;
    private String editora;
    private String isbn;
    private String sinopse;

    Livro(){}

    public Livro(String _codigo, String _nome, int _quantidade, double _valorDeCompra, double _valorDeVenda) {
        this.codigo = _codigo;
        this.nome = _nome;
        this.quantidade = _quantidade;
        //this.tipo = _tipo;
        this.valorDeCompra = _valorDeCompra;
        this.valorDeVenda = _valorDeVenda;
    }

    @Override
    public void accept(iProdutoVisitor visitor){
        visitor.visit(this);
        Utils.debugBox("Livro.accept() called", "true");
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPaginas() {
        return paginas;
    }

    public String getSinopse() {
        return sinopse;
    }

    @Override
    public double calcularDesconto(int fidelidade, int quantidade) {
        double desconto = 0;
        if(fidelidade == 1){
            desconto = this.valorDeVenda * 0.05;
        }else if(fidelidade == 2){
            desconto = this.valorDeVenda * 0.1;
        }else if(fidelidade == 3){
            desconto = this.valorDeVenda * 0.15;
        }else if(fidelidade == 4){
            desconto = this.valorDeVenda * 0.2;
        }else if(fidelidade == 5){
            desconto = this.valorDeVenda * 0.25;
        }
        return desconto;
    }
    

    @Override
    public String toString() {
        String str = "";
        str += "Quantidade: " + this.quantidade + "\n";
        str += "Editora: " + this.editora + "\n";
        str += "ISBN: " + this.isbn + "\n";
        str += "Custo: " + this.valorDeCompra + "\n";
        str += "Venda: " + this.valorDeVenda + "\n";
        str += "Código: " + this.codigo + "\n";

        return str; 
    }

}