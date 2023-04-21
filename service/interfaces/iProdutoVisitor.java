package service.interfaces;

import src.Consumivel;
import src.Livro;
import src.Produto;

public interface iProdutoVisitor {
    public void visit(Livro produto);
    public void visit(Consumivel produto);
}
