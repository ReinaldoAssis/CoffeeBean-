package service;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import src.Consumivel;
import src.Livro;
import src.Produto;
import src.Usuario;

import java.util.ArrayList;


public class DB implements iDB {
    public List<Usuario> userList;
    public List<Produto> produtoList;

    //Dictionary<Usuario, List<Produto>> vendas;
    public double venda = 0;
    public double compra = 0;

    public DB()
    {
        userList = new ArrayList<Usuario>();
        produtoList = new ArrayList<Produto>();
    }

    public int getProdutoIndex(String nome)
    {
        for(int i=0; i<produtoList.size(); i++)
        {
            if(produtoList.get(i).nome.equalsIgnoreCase(nome))
            {
                return i;
            }
        }
        return -1;
    }

    public int getProdutoIndexWithCode(String codigo)
    {
        for(int i=0; i<produtoList.size(); i++)
        {
            if(produtoList.get(i).codigo.equals(codigo))
            {
                return i;
            }
        }
        return -1;
    }

    public Produto getProduto(String codigo)
    {
        for(int i=0; i<produtoList.size(); i++)
        {
            if(produtoList.get(i).codigo.equals(codigo))
            {
                return produtoList.get(i);
            }
        }
        return null;
    }

    public int getProdutoIndexUser(String nome, Usuario user)
    {
        for(int i=0; i<user.carrinho.size(); i++)
        {
            if(user.carrinho.get(i).nome.equalsIgnoreCase(nome))
            {
                return i;
            }
        }
        return -1;
    }

    public int getUserIndex(String cpf)
    {
        for(int i=0; i<userList.size(); i++)
        {
            if(userList.get(i).cpf.equals(cpf))
            {
                return i;
            }
        }
        return -1;
    } 

    public Usuario getUser(String cpf)
    {
        for(int i=0; i<userList.size(); i++)
        {
            if(userList.get(i).cpf.equals(cpf))
            {
                return userList.get(i);
            }
        }
        return null;
    } 

    public void criarUsuario(Usuario User1)
    {
        //Utils.clearScreen();

        userList.add(User1);                

    }

    public void modificarUsuario(String cpf)
    {
        int i = getUserIndex(cpf);
        userList.get(i).modificarDados();
        userList.get(i).estadoAtual();
        
    }

    public void modificarUsuario()
    {
        Scanner leitor = new Scanner(System.in);
        System.out.println("Digite o CPF do usuario a ser modificado: ");
        String cpf = leitor.nextLine();

        int i = getUserIndex(cpf);
        if(i == -1)
        {
            System.out.println("Usuario não existe");
            Utils.awaitInput();
            return;
        }

        userList.get(i).modificarDados();
        Utils.clearScreen();
        userList.get(i).estadoAtual();
        
    }
    
  public void ConsultaFidelidade()
    {
        Scanner leitor = new Scanner(System.in);
        System.out.println("Digite o CPF do usuario que deseja fazer a consulta: ");
        String cpf = leitor.nextLine();

        int i = getUserIndex(cpf);
        if(i == -1)
        {
            System.out.println("Usuario não existe");
            Utils.awaitInput();
            return;
        }
        System.out.println("Pontos de fidelidade:" + this.userList.get(i).fidelidade);

        Utils.awaitInput();
    }

    public void removerUsuario(String cpf)
    {
        int i = getUserIndex(cpf);
        if(i == -1) System.out.println("Usuario não existe");
        else userList.remove(i);
    }

    public void removerUsuario()
    {
        System.out.println("Digite o CPF do usuario a ser removido: ");

        Scanner leitor = new Scanner(System.in);
        String cpf = leitor.nextLine();
        int i = getUserIndex(cpf);
        if(i == -1) System.out.println("Usuario não existe");
        else 
        {
            System.out.println("Deseja remover usuario " + userList.get(i).nome + "? [sim/nao]");
            String opcao = leitor.nextLine();
            if(opcao.equals("sim"))
            {
                userList.remove(i);
                System.out.println("Usuario removido :'(");
    
            }
        }


        Utils.awaitInput();

    }

    public void cadastrarProduto(Produto p)
    {        
        
        produtoList.add(p);

        compra += p.valorDeCompra * p.quantidade;

        System.out.println(p.nome+" cadastrado com sucesso :D");

        if(p instanceof Livro)
            System.out.println("ISBN: " + ((Livro)p).getIsbn());
        else if(p instanceof Consumivel)
            System.out.println("Data de validade: " + ((Consumivel)p).getValidade());

    }

    public boolean removerProduto(String codigo, boolean force)
    {
        //temos que considerar um caso especial: pode existir um carrinho com o produto que será removido
        //neste caso, solicita-se a confirmação

        int i = getProdutoIndexWithCode(codigo);

        if(force){  
           //JOptionPane.showMessageDialog(null, "O produto "+ produtoList.get(i).nome+ " foi removido.");
            System.out.println("O produto "+ produtoList.get(i).nome+ " foi removido");
            produtoList.remove(i);
            return true;
        }

        //verificar se o produto está em algum carrinho
        for(int j=0; j<userList.size(); j++){
            if(userList.get(j).carrinho.contains(produtoList.get(i)))
            {

                int opcao = JOptionPane.showConfirmDialog(null, "O produto "+ produtoList.get(i).nome+ " está contido em ao menos um carrinho (usuario:" + userList.get(j).nome+ " ). Deseja remover mesmo assim?", "Remover produto", JOptionPane.YES_NO_OPTION);
                if(opcao == JOptionPane.YES_OPTION)
                {
                    JOptionPane.showMessageDialog(null, "O produto "+ produtoList.get(i).nome+ " foi removido");
                    System.out.println("O produto "+ produtoList.get(i).nome+ " foi removido");
                    produtoList.remove(i);
                    return true;
                }
            } 
        }
            
        //caso não esteja em nenhum carrinho, remover normalmente
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o produto "+ produtoList.get(i).nome+ "?", "Remover produto", JOptionPane.YES_NO_OPTION);
        if(opcao == JOptionPane.YES_OPTION)
        {
            JOptionPane.showMessageDialog(null, "O produto "+ produtoList.get(i).nome+ " foi removido");
            System.out.println("O produto "+ produtoList.get(i).nome+ " foi removido");
            produtoList.remove(i);
            return true;
        }

        return false;
            
        

    }

    public void verificarEstoque()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----------------");
        System.out.println("Digite o nome do produto: "); //sugestão: utilizar o código do produto em vez do nome

        String Nome = scanner.nextLine();

        int i = getProdutoIndex(Nome);

        if(i == -1){
            System.out.println("Produto não listado no estoque");
        }else {
            Produto p1 = this.produtoList.get(i);
            if(p1.quantidade > 0){
                System.out.println("O produto " +Nome+ " (quantidade: "+ p1.quantidade +") está disponível no estoque!");
            }else{
                System.out.println("O produto " +Nome+ " não está disponível no estoque :(");
            }
        }

        Utils.awaitInput();
    }

    public boolean adicionarAoCarrinho(Usuario user, String codigo, int quantidade)
    {
        int i = getProdutoIndexWithCode(codigo);
        Produto produto = produtoList.get(i);
        
        if(produto.quantidade >= quantidade)
        {
            produto.quantidade -= quantidade;

            int j = getProdutoIndexUser(produto.nome, user);
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

    public void printarEstoque()
    {
        System.out.println("Nome | Quantidade | Codigo");
        for(int i=0; i<produtoList.size(); i++)
        {
            System.out.println(produtoList.get(i).nome + " | " + produtoList.get(i).quantidade + " | " + produtoList.get(i).codigo);   
        }
        System.out.println("-----------------");
    }

    public void fluxoDeCaixa()
    {
        System.out.println("Fluxo de caixa: " + venda+" - "+compra+" = "+(venda-compra));
        Utils.awaitInput();
    }

    public void menuCarrinho()
    {
        Scanner scanner = new Scanner(System.in);
        Utils.clearScreen();

        System.out.println("Digite o CPF do usuario: ");
        
        String cpf = scanner.nextLine();
        int u = getUserIndex(cpf);
        
        if (u == -1) {
            System.out.println("Usuario não cadastrado");
            Utils.awaitInput();
            return;
        }
        
        Usuario user = userList.get(u);
        
        while(true)
        {


            Utils.clearScreen();

            System.out.println("-----------------");
            System.out.println("Carrinho de " + user.nome);
            System.out.println();
            System.out.println("1. Adicionar produto");
            System.out.println("2. Remover produto");
            System.out.println("3. Visualizar carrinho");
            System.out.println("4. Finalizar compra");
            System.out.println("5. Voltar ao menu");
            System.out.println("-----------------");

            int opcao = scanner.nextInt();

            switch(opcao)
            {
                case 1:
                    promptAdicionarAoCarrinho(user);
                    break;
               /* case 2:
                    promptRemoverDoCarrinho(user);
                    break;*/
                case 3:
                    printarCarrinho(user);
                    break;
                case 4:
                    finalizarCompra(user);
                    break;

                case 5:
                    return;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

        }


    }

    public boolean promptAlugarLivro(Usuario user,String codigo)
    {
        int i = getProdutoIndexWithCode(codigo);
       
            Produto p = produtoList.get(i);
            if(p.getClass() == Livro.class)
            {
                user.alugados.add(p);
                user.fidelidade += 1;
            }
            JOptionPane.showMessageDialog(null, "Livro alugado por "+ user.nome+", valor: "+ p.valorDeVenda*0.3);
            System.out.println("Livro alugado por "+ user.nome+", valor: "+ p.valorDeVenda*0.3);
            venda += p.valorDeVenda*0.3;
            return true;

    }

    public void finalizarCompra(Usuario user)
    {
        double total = 0;
        for(int i=0; i<user.carrinho.size(); i++)
        {
            total += user.carrinho.get(i).valorDeVenda*user.carrinho.get(i).quantidade;
        }
        JOptionPane.showMessageDialog(null, "Compra finalizada, total: " + total);
        System.out.println("Compra finalizada, total: " + total);
        
        venda += total;

        user.carrinho.clear();

    }

    public void printarCarrinho(Usuario user)
    {
        System.out.println("Nome | Quantidade | Codigo");
        for(int i=0; i<user.carrinho.size(); i++)
        {
            System.out.println(user.carrinho.get(i).nome + " | " + user.carrinho.get(i).quantidade + " | " + user.carrinho.get(i).codigo);   
        }
        System.out.println("-----------------");

        Utils.awaitInput();
    }

    //solicita input do usuario e chama adicionarAoCarrinho
    public void promptAdicionarAoCarrinho(Usuario user) {
        Scanner scanner = new Scanner(System.in);
        //verificamos a existencia do produto

        printarEstoque();

        System.out.println("Digite o nome do produto: ");
        String Nome = scanner.nextLine();
        int i = getProdutoIndex(Nome);
        if (i == -1) {
            System.out.println("Produto não listado no estoque");
        } else {

            Produto p1 = this.produtoList.get(i);
            System.out.println("Digite a quantidade que deseja adicionar: ");
            Scanner scanner2 = new Scanner(System.in);
            int quantidade = scanner2.nextInt();
           // adicionarAoCarrinho(user, p1, quantidade);//chama a função no usuário solicitad

        }

        Utils.awaitInput();
    }

    public boolean promptRemoverDoCarrinho(Usuario user,String codigo) {

        int i = getProdutoIndexWithCode(codigo);
        if(i == -1){
            System.out.println("Produto não listado no estoque");
            return false;
        }
        Produto p1 = produtoList.get(i);
        user.carrinho.remove(i);

      /* int j = getProdutoIndexUser(p1.nome, user);
        Produto p2 = user.carrinho.get(j);
                
        int quantidade = p1.quantidade;
        int q = p2.quantidade;
        p2.quantidade = q - quantidade;
        p1.quantidade += quantidade;
        System.out.println(p1.nome + "foi removido do carrinho");*/

        JOptionPane.showMessageDialog(null, p1.nome + " foi removido do carrinho slkdfjsdlkfjsdklfjsdlfkj");
        return true;
        }
}
