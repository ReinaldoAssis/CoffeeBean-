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
    public void cadastrarProduto();
    public void removerProduto();
    public void verificarEstoque();
    public void adicionarAoCarrinho(Usuario user, Produto produto, int quantidade);
    public void printarEstoque();
    public void fluxoDeCaixa();
    public void menuCarrinho();
    public void promptAlugarLivro();
    public void finalizarCompra(Usuario user);
    public void printarCarrinho(Usuario user);
    public void promptAdicionarAoCarrinho(Usuario user);
    public void promptRemoverDoCarrinho(Usuario user);

}