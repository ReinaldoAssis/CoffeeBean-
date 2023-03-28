package telas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import service.Controlador;
import src.Produto;
import src.Usuario;

public class VisCarrinho {

    @FXML
    private ListView<String> listview;

    @FXML
    private Button btn_del;

    @FXML
    private Button btn_fin;

    @FXML
    private Button btn_voltar;
 
    static int IndexUser;

    private String codigoLinhaSelecionada = "";

    public static void pegarDados(int j){
         IndexUser = j; 
    }
    @FXML
    void click_voltar(ActionEvent event) {
        Stage stage = (Stage) btn_voltar.getScene().getWindow();
        stage.close();
    }

    @FXML // P/ visualizar os produtos na tela(ainda não funciona 100%)
    public void initialize(){
        Controlador ctrl = Controlador.getInstance();
        listview.getItems().clear();
        List<String> lista_nomes = new ArrayList<String>();

        for(Produto p : ctrl.database.userList.get(IndexUser).carrinho){
            lista_nomes.add(p.displayName() + " - " + p.getQuantidade());
            System.out.println("Produto: "+ p.displayName() + " - " + p.getQuantidade());
        }

        listview.getItems().addAll(lista_nomes);

        // Add event listener to update selected index
        listview.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
        if (newVal != null) {
            // Extract the index of the selected item
            int index = listview.getSelectionModel().getSelectedIndex();
            // Update the codigoLinhaSelecionada variable
            codigoLinhaSelecionada = ctrl.database.produtoList.get(index).getCodigo();
            System.out.println("Codigo da linha selecionada: " + codigoLinhaSelecionada);
        }
    });
    }

    @FXML
    void click_del(ActionEvent event) {
        Controlador ctrl = Controlador.getInstance();
        Usuario user = Controlador.getInstance().database.userList.get(IndexUser);
    
        boolean resultado = ctrl.database.promptRemoverDoCarrinho(user, codigoLinhaSelecionada.trim());
        if(!resultado) JOptionPane.showMessageDialog(null, "Operação cancelada!");
    }

    @FXML
    void click_fin(ActionEvent event) {
        Controlador ctrl = Controlador.getInstance();
        Usuario user = Controlador.getInstance().database.userList.get(IndexUser);
        ctrl.database.finalizarCompra(user);
        //fechar janelas
    }
}
