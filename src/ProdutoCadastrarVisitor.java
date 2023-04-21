package src;

import service.Controlador;
import service.Utils;
import service.interfaces.iProdutoVisitor;

public class ProdutoCadastrarVisitor implements iProdutoVisitor {
    
    private String arg1;
    private String arg2;
    private boolean cadastro;
    private Controlador ctrl;

    public ProdutoCadastrarVisitor(String arg1, String arg2, boolean cadastro) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.cadastro = cadastro;
        this.ctrl = Controlador.getInstance();
    }

    public void visit(Livro produto)
    {
        Utils.debugBox("Livro visit called", "true", "Cadastro", cadastro+"");

        produto.setEditora(arg1);
        produto.setIsbn(arg2);

        if(cadastro) ctrl.database.productDB.addItem(produto);
    }

    public void visit(Consumivel produto)
    {
        produto.setValidade(arg1);
        produto.setPorcao(arg2);
        
        if(cadastro) ctrl.database.productDB.addItem(produto);
        
    }
}
