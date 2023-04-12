package service;

import javax.swing.JOptionPane;

import service.interfaces.iDB;
import src.Produto;
import src.exceptions.ProductDoesNotExistException;

public class ProductDB implements iDB {

    public DB db;

    public ProductDB(DB dbService)
    {
        this.db = dbService;
    }

    public int getItemIndex(String codigo)
    {
        for(int i=0; i<db.productList.size(); i++)
        {
            if(db.productList.get(i).codigo.equals(codigo))
            {
                return i;
            }
        }
        return -1;
    }

    public Produto getProduct(String codigo)
    {
        for(int i=0; i<db.productList.size(); i++)
        {
            if(db.productList.get(i).codigo.equals(codigo))
            {
                return db.productList.get(i);
            }
        }
        return null;
    }

    public boolean deleteItem(String codigo, boolean force)
    {
        //temos que considerar um caso especial: pode existir um carrinho com o produto que será removido
        //neste caso, solicita-se a confirmação

        int i = getItemIndex(codigo);

        if(force){  
            System.out.println("O produto "+ db.productList.get(i).nome+ " foi removido");
            db.productList.remove(i);
            return true;
        }

        //verificar se o produto está em algum carrinho
        for(int j=0; j<db.userList.size(); j++){
            if(db.userList.get(j).carrinho.contains(db.productList.get(i)))
            {

                int opcao = JOptionPane.showConfirmDialog(null, "O produto "+ db.productList.get(i).nome+ " está contido em ao menos um carrinho (usuario:" + db.userList.get(j).nome+ " ). Deseja remover mesmo assim?", "Remover produto", JOptionPane.YES_NO_OPTION);
                if(opcao == JOptionPane.YES_OPTION)
                {
                    JOptionPane.showMessageDialog(null, "O produto "+ db.productList.get(i).nome+ " foi removido");
                    System.out.println("O produto "+ db.productList.get(i).nome+ " foi removido");
                    db.productList.remove(i);
                    return true;
                }
            } 
        }
            
        //caso não esteja em nenhum carrinho, remover normalmente
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o produto "+ db.productList.get(i).nome+ "?", "Remover produto", JOptionPane.YES_NO_OPTION);
        if(opcao == JOptionPane.YES_OPTION)
        {
            JOptionPane.showMessageDialog(null, "O produto "+ db.productList.get(i).nome+ " foi removido");
            System.out.println("O produto "+ db.productList.get(i).nome+ " foi removido");
            db.productList.remove(i);
            return true;
        }

        return false;
    }

    public void addItem(Object product)
    {
        db.productList.add((Produto) product);
    }

    public void modifyItem(String code, Object newItem)
    {
        int i = getItemIndex(code);
        if(i == -1) 
        {
            throw new ProductDoesNotExistException("O produto de código "+code+" não existe.", null);
        }
        else db.productList.set(i, (Produto) newItem);
    }

    public boolean checkExistence(String code)
    {
        return getItemIndex(code) != -1; //Se retornar algo diferente de -1, o usuário existe
    }
 
}
