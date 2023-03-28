package telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import service.Controlador;

public class MainView {

    @FXML
    private VBox side_pane;

    @FXML
    private Button btn_estoque;

    @FXML
    private Button btn_caixa;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_usuarios;

    @FXML
    private BorderPane main_pane;

    @FXML
    private Button btn_venda;
    

    @FXML
    void click_estoque(ActionEvent event) throws Exception{
        Controlador ctrl = Controlador.getInstance();
        ctrl.telaEstoque();
    }

    @FXML
    void click_caixa(ActionEvent event) throws Exception {
        Controlador ctrl = Controlador.getInstance();
        ctrl.telaCaixa();
    }

    @FXML
    void click_venda(ActionEvent event) throws Exception {
        Controlador ctrl = Controlador.getInstance();
        ctrl.tela1_Carrinho();
    }

    @FXML
    void click_usuarios(ActionEvent event) throws Exception {
        Controlador ctrl = Controlador.getInstance();
        ctrl.userArea();
    }
}
