package telas;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
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
    private ListView<?> listview;

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
    void click_menu_tipo(MouseEvent event) {

    }

    @FXML
    void mouseEnter_menuTipo(MouseEvent event) {

    }
   

    @FXML
    void click_cadastrar(ActionEvent event) {
        p.setCodigo(codigo.getText());
        p.setNome(nome.getText());
        p.setValorDeCompra(Double.parseDouble(custo.getText()));
        p.setValorDeVenda(Double.parseDouble(venda.getText()));
        //p.set(tipo.getValue());
        
        Controlador ctrl = Controlador.getInstance();

        try{
        if(tipo.getValue().equalsIgnoreCase("livro")){
            Livro l = Produto.toLivro(p); //quase um casting
            l.setEditora(arg1.getText());
            l.setIsbn(arg2.getText());
            ctrl.database.cadastrarProduto(l);

        }
        else if (tipo.getValue().equalsIgnoreCase("consumivel")){
                Consumivel c = Produto.toConsumivel(p); //quase um casting
                c.setValidade(arg1.getText());
                c.setPorcao(arg2.getText());
                System.out.println(c.toString());
                ctrl.database.cadastrarProduto(c);
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

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
        }
            
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        tipo.getItems().addAll("Livro", "Consumivel");
        tipo.setValue("Livro");

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

}
