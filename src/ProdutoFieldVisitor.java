package src;

import javafx.scene.control.TextField;
import service.interfaces.iProdutoVisitor;

// This class is used to set the fields of the GUI
// It uses the visitor pattern to solve bad code smell
public class ProdutoFieldVisitor implements iProdutoVisitor {
    
    private TextField arg1;
    private TextField arg2;

    public ProdutoFieldVisitor(TextField arg1, TextField arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public void visit(Livro produto)
    {
        arg1.setText(produto.getEditora());
        arg2.setText(produto.getIsbn());
    }

    public void visit(Consumivel produto)
    {
        arg1.setText(produto.getValidade());
        arg2.setText(produto.getPorcao());
    }

}
