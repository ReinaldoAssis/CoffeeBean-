package service;

import java.util.List;

import src.Produto;
import src.Usuario;

import java.util.ArrayList;

public class DB {

    public List<Usuario> userList;
    public List<Produto> productList;

    public UserDB userDB;
    public ProductDB productDB;

    // public Dictionary<Usuario, List<Produto>> vendas;
    public double venda = 0;
    public double compra = 0;

    public DB()
    {
        userList = new ArrayList<Usuario>();
        productList = new ArrayList<Produto>();
        userDB = new UserDB(this);
        productDB = new ProductDB(this);
    }

}
