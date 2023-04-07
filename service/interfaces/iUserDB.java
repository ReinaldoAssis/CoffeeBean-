package service.interfaces;

import src.Usuario;

public interface iUserDB extends iDB {
    public void completePurchase(Usuario user);
    public boolean addToCart(Usuario user, String codigoProduto, int quantidade);
    public boolean removeFromCart(Usuario user, String codigoProduto);
    public boolean rentBook(Usuario user, String bookCode);
    public Usuario getUser(String cpf);
    public int getCartProductIndex(Usuario user, String productCode);
}
