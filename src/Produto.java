package src;
public class Produto {

    public String codigo;
    public String nome;
    public int quantidade;
    public double valorDeCompra;
    public double valorDeVenda;


    public Produto(){}

    public Produto(String _codigo, String _nome, int _quantidade, double _valorDeCompra, double _valorDeVenda) {
        this.codigo = _codigo;
        this.nome = _nome;
        this.quantidade = _quantidade;
        //this.tipo = _tipo;
        this.valorDeCompra = _valorDeCompra;
        this.valorDeVenda = _valorDeVenda;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorDeCompra() {
        return valorDeCompra;
    }

    public void setValorDeCompra(double valorDeCompra) {
        this.valorDeCompra = valorDeCompra;
    }

    public double getValorDeVenda() {
        return valorDeVenda;
    }

    public void setValorDeVenda(double valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }

    public static Consumivel toConsumivel(Produto p){
        Consumivel c = new Consumivel(p.codigo, p.nome, p.quantidade, p.valorDeCompra, p.valorDeVenda);
        return c;
    }

    public static Livro toLivro(Produto p){
        Livro l = new Livro(p.codigo, p.nome, p.quantidade, p.valorDeCompra, p.valorDeVenda);
        return l;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}