package service;
import src.Produto;
import src.Usuario;

public interface iDB {
    
    public int getProdutoIndex(String nome);
    public int getProdutoIndexUser(String nome, Usuario user);
    public int getUserIndex(String cpf);
    public void criarUsuario();
    public void modificarUsuario(String cpf);
    public void modificarUsuario();
    public void ConsultaFidelidade();
    public void removerUsuario(String cpf);
    public void removerUsuario();
    public void cadastrarProduto(Produto p);
    public boolean removerProduto(String codigo, boolean force) ;
    public void verificarEstoque();
    public boolean adicionarAoCarrinho(Usuario user, String codigo, int quantidade);
    public void printarEstoque();
    public void fluxoDeCaixa();
    public void menuCarrinho();
    public boolean promptAlugarLivro(Usuario user, String codigo);
    public void finalizarCompra(Usuario user);
    public void printarCarrinho(Usuario user);
    public void promptAdicionarAoCarrinho(Usuario user);
    public boolean promptRemoverDoCarrinho(Usuario user, String codigo);

}