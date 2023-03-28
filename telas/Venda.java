package telas;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import service.Controlador;
import src.Produto;

public class Venda {

    @FXML
    private ListView<String> listview;

    @FXML
    private ListView<String> listview1;

    @FXML
    private Button btn_alugar;

    @FXML
    private TabPane tabview;

    @FXML
    private Tab tab_consultar;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_vis;

    @FXML
    private Label produto;

    @FXML
    private Label produto1;

    @FXML
    private Tab tab_cadastrar;

    @FXML
    private Label info1;

    @FXML
    private TextField quantidade;

    @FXML
    private Label info;

    @FXML
    void click_vis(ActionEvent event) throws Exception {
        Controlador ctrl = Controlador.getInstance();
        ctrl.tela3_Carrinho();
    }

    @FXML
    void click_add(ActionEvent event) {

    }

    @FXML
    void click_alug(ActionEvent event) {

    }

   /* @FXML P/ visualizar os produtos na tela(ainda não funciona)
    private void initialize(){
        Controlador ctrl = Controlador.getInstance();
        listview.getItems().clear();
        List<String> lista_nomes = new ArrayList<String>();

        for(Produto p : ctrl.database.produtoList){
            lista_nomes.add(p.displayName());
            System.out.println("Produto: "+p.displayName());
        }

        listview.getItems().addAll(lista_nomes);
    }*/
    
}