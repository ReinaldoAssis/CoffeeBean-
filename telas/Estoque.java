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
import service.Utils;
import src.Consumivel;
import src.Livro;
import src.Produto;
import src.ProdutoCadastrarVisitor;
import src.ProdutoFieldVisitor;

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

    private void resetInfoLabels(){
        info.setText("");
        produto.setText("Produto");
    }

    @FXML
    void click_deletar(ActionEvent event) {
        Controlador ctrl = Controlador.getInstance();
        boolean resultado = ctrl.database.productDB.deleteItem(codigoLinhaSelecionada, false);

        if(!resultado) JOptionPane.showMessageDialog(null, "Operação cancelada!");
        
        CarregarLista();
        
        resetInfoLabels();
    }

   

    private void atualizar_produto(Controlador ctrl, Produto k){
        String ctxt = codigo.getText();
        if(ctxt.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "O código do produto não pode ser vazio!");
        }
        else if (codigoOriginal.equalsIgnoreCase(k.getCodigo())){
            ctrl.database.productDB.modifyItem(ctxt, k);
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
            
            codigoOriginal = "";

            btn_cadastrar.setText("Cadastrar");
            btn_cadastrar.setStyle("-fx-background-color: #50fa7b; ");

            codigo.setText("");
            nome.setText("");
            custo.setText("");
            venda.setText("");
            arg1.setText("");
            arg2.setText("");
            quantidade.setText("");

        }
        else{
            JOptionPane.showMessageDialog(null, "O código do produto não pode ser alterado!");
        }
    }

    private boolean cadastrarConditions(boolean cadastro, Controlador ctrl)
    {
        //verifica se o produto já existe, se sim, exibe uma mensagem de erro
        if(cadastro && ctrl.database.productDB.getItemIndex(this.codigo.getText()) != -1)
        {
            Produto existente = ctrl.database.productDB.getProduct(this.codigo.getText());
            JOptionPane.showMessageDialog(null, "O produto " + existente.nome + " já existe com esse código!");
            return false;
        }
        return true;
    }

    @FXML
    void click_cadastrar(ActionEvent event) {
        p.setCodigo(codigo.getText());
        p.setNome(nome.getText());
        p.setValorDeCompra(Double.parseDouble(custo.getText()));
        p.setValorDeVenda(Double.parseDouble(venda.getText()));
        p.setQuantidade(Integer.parseInt(quantidade.getText()));
        
        Controlador ctrl = Controlador.getInstance();
        boolean cadastro = btn_cadastrar.getText().equalsIgnoreCase("Cadastrar");
        
        if (cadastrarConditions(cadastro, ctrl) == false)
            return;

        try{
            //verifica se o produto é um livro ou um consumível 
            
            System.out.println("Tentando - Tipo: "+tipo.getValue());

            if(Utils.isStrEqual(tipo.getValue(), "Livro")) p = Produto.toLivro(p);
            else p = Produto.toConsumivel(p);

            p.accept(new ProdutoCadastrarVisitor(arg1.getText(), arg2.getText(), cadastro));
            
            if (!cadastro) {
                atualizar_produto(ctrl, p);
                return;
            }

            if(cadastro && ctrl.database.productDB.checkExistence(p.getCodigo()))
            {
                Utils.successBox("Produto cadastrado com sucesso!");
                return;
            }
            else
            {
                Utils.errorBox("Erro ao cadastrar o produto!", "Algo deu errado :(");
                Utils.debugBox("Tipo", tipo.getValue(), "Código", codigo.getText(), "Nome", nome.getText(), "Custo", custo.getText(), "Venda", venda.getText(), "Quantidade", quantidade.getText(), "Arg1", arg1.getText(), "Arg2", arg2.getText());
                return;
            }
    
        } catch(Exception e){
            Utils.errorBox(e.getMessage(),"Algo deu errado :(");
            throw new RuntimeException("Erro: "+e.getMessage());

        } finally{
            codigo.setText("");
            nome.setText("");
            custo.setText("");
            venda.setText("");
            arg1.setText("");
            arg2.setText("");
            quantidade.setText("");
            produto.setText(""); 
            info.setText("");   
            CarregarLista();
        }
            
        
    }

    private void CarregarLista(){
        Controlador ctrl = Controlador.getInstance();
        listview.getItems().clear();
        List<String> lista_nomes = new ArrayList<String>();

        for(Produto p : ctrl.database.productList){
            lista_nomes.add(p.displayName());
            System.out.println("Produto: "+p.displayName());
        }

        listview.getItems().addAll(lista_nomes);

    }

    //responsável por mudar as labels de acordo com o tipo de produto selecionado
    private void updateLabelsWithProductType()
    {
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
    }

    private void initializeTipoFieldValues()
    {
        tipo.getItems().addAll("Livro", "Consumivel");
        tipo.setValue("Livro");
    }

    private boolean isENTERKey(KeyEvent e)
    {
        return e.getCode().toString().equalsIgnoreCase("ENTER");
    }

    //checa se foi dado enter no campo de código
    private void codigoFieldEnterKeyListener(Controlador ctrl)
    {
        codigo.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(isENTERKey(event)){
                    try{
                        //pega o produto do banco de dados
                        Produto p = ctrl.database.productDB.getProduct(codigo.getText());

                        //se o produto existir, preenche os campos com os dados do produto
                        if(p != null){
                            nome.setText(p.getNome());
                            custo.setText(p.getValorDeCompra()+"");
                            venda.setText(p.getValorDeVenda()+"");
                            quantidade.setText(p.getQuantidade()+"");

                            p.accept(new ProdutoFieldVisitor(arg1, arg2));

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
                        throw new RuntimeException("Erro: "+e.getMessage());
                    }
                }
            }
        });
    }

    //responsável por atualizar a label info de acordo com o produto selecionado
    private void listViewListener(Controlador ctrl)
    {
        listview.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                try{
                    String v = listview.getItems().get((Integer) number2);
                    
                    String codigo = v.substring(v.indexOf("[") + 1, v.indexOf("]"));

                    codigoLinhaSelecionada = codigo;
                    
                    Produto _p = ctrl.database.productDB.getProduct(codigo);
                    produto.setText(_p.nome);

                    //Polimorfismo aplicado aqui
                    produto.setText(_p.nome);
                    info.setText(_p.toString());

                } catch(Exception e)
                {
                    throw new RuntimeException("Erro: "+e.getMessage());
                }

            }
        });
    }

    //Code smell: long method
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Controlador ctrl = Controlador.getInstance();
        initializeTipoFieldValues();
        updateLabelsWithProductType();
        listViewListener(ctrl);
        codigoFieldEnterKeyListener(ctrl);
        CarregarLista();
    }

}
