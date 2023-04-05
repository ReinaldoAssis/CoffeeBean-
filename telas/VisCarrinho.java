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

    //voltar as compras
    @FXML
    void click_voltar(ActionEvent event) throws Exception {
        Controlador ctrl = Controlador.getInstance();
        Stage stage = (Stage) btn_voltar.getScene().getWindow();
        stage.close();
        ctrl.tela2_Carrinho();
    }

    @FXML // P/ visualizar os produtos na tela
    public void initialize(){
        Controlador ctrl = Controlador.getInstance();
        listview.getItems().clear();
        List<String> lista_nomes = new ArrayList<String>();
        
        Usuario u = ctrl.database.userList.get(IndexUser);

        for(Produto p : u.carrinho){
            lista_nomes.add(p.toString());
            System.out.println(p.toString());
        }

        listview.getItems().addAll(lista_nomes);

        listview.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
        if (newVal != null) {

            int index = listview.getSelectionModel().getSelectedIndex();

            codigoLinhaSelecionada = ctrl.database.produtoList.get(index).getCodigo();
            System.out.println("Codigo da linha selecionada: " + codigoLinhaSelecionada);
        }
    });
    }
    //deletar produto do carrinho
    @FXML
    void click_del(ActionEvent event) throws Exception {
        Controlador ctrl = Controlador.getInstance();
        Usuario user = Controlador.getInstance().database.userList.get(IndexUser);
        
        if(codigoLinhaSelecionada == "")
        {
            JOptionPane.showMessageDialog(null, "Nenhum produto selecionado!");
            return;
        }
        boolean resultado = ctrl.database.promptRemoverDoCarrinho(user, codigoLinhaSelecionada.trim());
        if(!resultado) JOptionPane.showMessageDialog(null, "Operação cancelada!");
        //gambiarra para atualizar a tela
        //Controlador ctrl = Controlador.getInstance();
        Stage stage = (Stage) btn_voltar.getScene().getWindow();
        stage.close();
        ctrl.tela2_Carrinho();
    }

    //finalizar compra
    @FXML
    void click_fin(ActionEvent event) {
        Controlador ctrl = Controlador.getInstance();
        Usuario user = Controlador.getInstance().database.userList.get(IndexUser);
        ctrl.database.finalizarCompra(user);
        //fechar janelas
        Stage stage = (Stage) btn_fin.getScene().getWindow();
        stage.close();
    }
}
