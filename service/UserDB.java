package service;

import javax.swing.JOptionPane;

import service.interfaces.iUserDB;
import src.Livro;
import src.Produto;
import src.Usuario;

public class UserDB implements iUserDB {

    //public List<Usuario> userList;
    //public double venda;
    public DB db;

    public UserDB(DB dbService)
    {
        this.db = dbService;
    }

    public int getItemIndex(String cpf)
    {
        for(int i=0; i<db.userList.size(); i++)
        {
            if(db.userList.get(i).cpf.equals(cpf))
            {
                return i;
            }
        }
        return -1;
    }

    public Usuario getUser(String cpf)
    {
        for(int i=0; i<db.userList.size(); i++)
        {
            if(db.userList.get(i).cpf.equals(cpf))
            {
                return db.userList.get(i);
            }
        }
        return null;
    }

    public boolean deleteItem(String cpf, boolean force)
    {
        int i = getItemIndex(cpf);
        if(i == -1) {
            System.out.println("Usuario não existe");
            return false;
        } //TODO: tratar exceção sem println
        else db.userList.remove(i);
        return true;
    }

    public void addItem(Object user)
    {
        db.userList.add((Usuario) user);
    }

    public void modifyItem(String cpf, Object newItem)
    {
        Usuario newUser = (Usuario) newItem;
        int i = getItemIndex(cpf);
        if(i == -1) System.out.println("Usuario não existe"); //TODO: tratar exceção sem println
        else db.userList.set(i, newUser);
    }

    public boolean checkExistence(String cpf)
    {
        return getItemIndex(cpf) != -1; //Se retornar algo diferente de -1, o usuário existe
    }

    //Retorna o indice do produto no carrinho do usuario
    public int getCartProductIndex(Usuario user, String productCode)
    {
        for(int i=0; i<user.carrinho.size(); i++)
        {
            if(user.carrinho.get(i).codigo.equals(productCode))
            {
                return i;
            }
        }
        return -1;
    }

    //Retorna o indice do produto no carrinho do usuario utilizando o cpf
    public int getCartProductIndex(String cpf, String productCode)
    {
        Usuario user = getUser(cpf);
        for(int i=0; i<user.carrinho.size(); i++)
        {
            if(user.carrinho.get(i).codigo.equals(productCode))
            {
                return i;
            }
        }
        return -1;
    }
    
    public void completePurchase(Usuario user){

        double total = 0;
        for(int i=0; i<user.carrinho.size(); i++)
        {
            total += user.carrinho.get(i).valorDeVenda*user.carrinho.get(i).quantidade;
        }
        JOptionPane.showMessageDialog(null, "Compra finalizada, total: " + total);
        System.out.println("Compra finalizada, total: " + total);
        
        db.venda += total;

        user.carrinho.clear();
    }
    
    //Adicionar ao carrinho
    public boolean addToCart(Usuario user, String codigoProduto, int quantidade)
    {
        int i = db.productDB.getItemIndex(codigoProduto);
        Produto produto = db.productList.get(i);
        
        if(produto.quantidade >= quantidade)
        {
            produto.quantidade -= quantidade;

            int j = getCartProductIndex(user, produto.codigo);
            if (j == -1)
            {
                Produto novo = new Produto();
                novo.quantidade = quantidade;
                novo.nome = produto.nome;
                novo.codigo = produto.codigo;
                novo.valorDeCompra = produto.valorDeCompra;
                novo.valorDeVenda = produto.valorDeVenda;

                user.carrinho.add(novo);
            }else{
                Produto p2 = user.carrinho.get(j);
                p2.quantidade += quantidade;
            }
            System.out.println(produto.nome+" adicionado ao carrinho");
            return true;
        }
        else
        {
            System.out.println("Quantidade indisponivel");
            return false;
        }
    }

    public boolean removeFromCart(Usuario user, String codigoProduto)
    {
        int i = db.productDB.getItemIndex(codigoProduto);
        if(i == -1){
            System.out.println("Produto não listado no estoque"); //TODO: tratar exceção sem println
            return false;
        }
        Produto p1 = db.productList.get(i);
        user.carrinho.remove(i);

        JOptionPane.showMessageDialog(null, "Produto foi removido do carrinho.");
        return true;
    }

    public boolean rentBook(Usuario user, String bookCode)
    {
        int i = db.productDB.getItemIndex(bookCode);
       
        Produto p = db.productList.get(i);
        //TODO: tratar caso não seja um livro!
        if(p instanceof Livro)
        {
            user.alugados.add(p);
            user.fidelidade += 1;
        }
        JOptionPane.showMessageDialog(null, "Livro alugado por "+ user.nome+", valor: "+ p.valorDeVenda*0.3);
        System.out.println("Livro alugado por "+ user.nome+", valor: "+ p.valorDeVenda*0.3);
        db.venda += p.valorDeVenda*0.3;
        return true;
    }
}
