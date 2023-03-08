import java.util.List;

public class Livro extends Produto {
    

    public int paginas;
    public String autor;
    public String editora;
    public String isbn;
    public String sinopse;

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
    public String toString() {
        String str = "";
        str += "ISBN: " + this.isbn + "\n";
        str += "Autor: " + this.autor + "\n";
        str += "Editora: " + this.editora + "\n";
        str += "Paginas: " + this.paginas + "\n";

        return str; 
    }

}