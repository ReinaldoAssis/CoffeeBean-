package telas;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import service.Controlador;
import src.Consumivel;
import src.Livro;
import src.Produto;

public class Estoque implements Initializable{

    private Produto p = new Produto();

    @FXML
    private ChoiceBox<String> tipo;

    @FXML
    private TextField codigo;

    @FXML
    private ListView<String> listview;

    @FXML
    private TextField nome;

    @FXML
    private TabPane tabview;

    @FXML
    private Tab tab_consultar;

    @FXML
    private Label lbl_arg2;

    @FXML
    private Label lbl_arg1;

    @FXML
    private TextField arg2; //no caso do livro: isbn, no caso do consumivel: porção	

    @FXML
    private TextField venda;

    @FXML
    private Button btn_cadastrar;

    @FXML
    private Label produto;

    @FXML
    private TextField custo;

    @FXML
    private TextField arg1; //no caso do livro: editora, no caso do consumivel: validade

    @FXML
    private Tab tab_cadastrar;

    @FXML
    private Label info;

    @FXML
    private TextField quantidade;

    @FXML
    private Button btn_deletar;

    private String codigoOriginal = "";
    private String codigoLinhaSelecionada = "";

    @FXML
    void click_menu_tipo(MouseEvent event) {

    }

    @FXML
    void mouseEnter_menuTipo(MouseEvent event) {

    }

    @FXML
    void click_deletar(ActionEvent event) {
        Controlador ctrl = Controlador.getInstance();
        String ctxt = codigoLinhaSelecionada; //codigo da linha selecionada
        System.out.println("Codigo ----- "+ ctxt);
        boolean resultado = ctrl.database.removerProduto(ctxt, false);
        if(!resultado) JOptionPane.showMessageDialog(null, "Operação cancelada!");
        
    }
   

    private void atualizar_produto(Controlador ctrl, Produto k){
        String ctxt = codigo.getText();
                if(ctxt.equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null, "O código do produto não pode ser vazio!");
                }
                else if (codigoOriginal.equalsIgnoreCase(k.getCodigo())){
                    ctrl.database.removerProduto(ctxt, true);
                    ctrl.database.cadastrarProduto(k);
                    JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
                    System.out.println(k.codigo+" "+k.nome+" "+k.valorDeCompra+" "+k.valorDeVenda+" "+k.quantidade);
                }
                else{
                    JOptionPane.showMessageDialog(null, "O código do produto não pode ser alterado!");
                }
    }

    @FXML
    void click_cadastrar(ActionEvent event) {
        p.setCodigo(codigo.getText());
        p.setNome(nome.getText());
        p.setValorDeCompra(Double.parseDouble(custo.getText()));
        p.setValorDeVenda(Double.parseDouble(venda.getText()));
        p.setQuantidade(Integer.parseInt(quantidade.getText()));
        //p.set(tipo.getValue());
        
        Controlador ctrl = Controlador.getInstance();
        boolean cadastro = btn_cadastrar.getText().equalsIgnoreCase("Cadastrar");

        try{
            if(tipo.getValue().equalsIgnoreCase("livro")){
                Livro l = Produto.toLivro(p); //quase um casting
                l.setEditora(arg1.getText());
                l.setIsbn(arg2.getText());
                if (cadastro) ctrl.database.cadastrarProduto(l);
                else atualizar_produto(ctrl, l);

            }
            else if (tipo.getValue().equalsIgnoreCase("consumivel")){
                    Consumivel c = Produto.toConsumivel(p); //quase um casting
                    c.setValidade(arg1.getText());
                    c.setPorcao(arg2.getText());
                    System.out.println(c.toString());
                    if(cadastro) ctrl.database.cadastrarProduto(c);
                    else atualizar_produto(ctrl, c);
            }

            if(cadastro)
            {
                if(ctrl.database.getProdutoIndexWithCode(this.codigo.getText()) == -1)
                {
                    Produto existente = ctrl.database.getProduto(this.codigo.getText());
                    JOptionPane.showMessageDialog(null, "O produto " + existente.nome + " já existe com esse código!");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                return;
            }

            


            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Algo deu errado :(\n"+e.getMessage());

        } finally{
            codigo.setText("");
            nome.setText("");
            custo.setText("");
            venda.setText("");
            arg1.setText("");
            arg2.setText("");
            quantidade.setText("");
        }
            
        
    }

    private void CarregarLista(){
        Controlador ctrl = Controlador.getInstance();
        listview.getItems().clear();
        List<String> lista_nomes = new ArrayList<String>();

        for(Produto p : ctrl.database.produtoList){
            lista_nomes.add(p.displayName());
            System.out.println("Produto: "+p.displayName());
        }

        listview.getItems().addAll(lista_nomes);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Controlador ctrl = Controlador.getInstance();
        System.out.println("Compras " + ctrl.database.compra);

        // TODO Auto-generated method stub
        tipo.getItems().addAll("Livro", "Consumivel");
        tipo.setValue("Livro");

        //responsável por mudar as labels de acordo com o tipo de produto selecionado
        tipo.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
              String v = tipo.getItems().get((Integer) number2);
              if(v.equalsIgnoreCase("livro")){
                  lbl_arg1.setText("Editora");
                  lbl_arg2.setText("ISBN");
              }
              else if(v.equalsIgnoreCase("consumivel")){
                  lbl_arg1.setText("Validade");
                  lbl_arg2.setText("Porção");
              }
            }
          });

          //responsável por atualizar a label info de acordo com o produto selecionado
            listview.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                    try{
                        String v = listview.getItems().get((Integer) number2);
                        
                        String codigo = v.substring(v.indexOf("[") + 1, v.indexOf("]"));

                        codigoLinhaSelecionada = codigo;
                        
                        Produto _p = ctrl.database.getProduto(codigo);
                        produto.setText(_p.nome);

                        if(_p instanceof Livro){
                            Livro l = (Livro) _p;
                            String info_text = "Quantidade: "+l.getQuantidade()+"\nEditora: "+l.getEditora()+"\nISBN: "+l.getIsbn();
                            info_text += "\nCusto: "+l.getValorDeCompra()+"\nVenda: "+l.getValorDeVenda();
                            info_text += "\nCódigo: "+l.getCodigo();
                            info.setText(info_text);
                        }
                        else if(_p instanceof Consumivel){
                            Consumivel c = (Consumivel) _p;
                            String info_text = "Quantidade: "+c.getQuantidade()+"\nValidade: "+c.getValidade()+"\nPorção: "+c.getPorcao();
                            info_text += "\nCusto: "+c.getValorDeCompra()+"\nVenda: "+c.getValorDeVenda();
                            info_text += "\nCódigo: "+c.getCodigo();
                            info.setText(info_text);
                        }

                    } catch(Exception e)
                    {
                        System.out.println("Erro: "+e.getMessage());
                    }

                }
            });

            //checa se foi dado enter no campo de código
            codigo.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    //System.out.println(event.toString());
                    if(event.getCode().toString().equalsIgnoreCase("ENTER")){
                        try{
                            Controlador ctrl = Controlador.getInstance();

                            //pega o produto do banco de dados
                            Produto p = ctrl.database.getProduto(codigo.getText());

                            //se o produto existir, preenche os campos com os dados do produto
                            if(p != null){
                                nome.setText(p.getNome());
                                custo.setText(p.getValorDeCompra()+"");
                                venda.setText(p.getValorDeVenda()+"");
                                quantidade.setText(p.getQuantidade()+"");
                                if(p instanceof Livro){
                                    Livro l = (Livro) p;
                                    arg1.setText(l.getEditora());
                                    arg2.setText(l.getIsbn());
                                }
                                else if(p instanceof Consumivel){
                                    Consumivel c = (Consumivel) p;
                                    arg1.setText(c.getValidade());
                                    arg2.setText(c.getPorcao());
                                }

                                codigoOriginal = codigo.getText();

                                //muda o botão para atualizar
                                btn_cadastrar.setText("Atualizar");
                                btn_cadastrar.setStyle("-fx-background-color: #ffb86c; ");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Produto não encontrado!");
                            }

                        } catch(Exception e)
                        {
                            System.out.println("Erro: "+e.getMessage());
                        }
                    }
                }
            });

          CarregarLista();
    }

}
