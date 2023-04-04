package src;
import java.util.List;

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