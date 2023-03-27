package telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.Controlador;

public class Carrinho {

    @FXML
    private Button openCartButton;

    @FXML
    private TextField cpfField;

    @FXML
    private void abrircarr(ActionEvent event) throws Exception {
        Controlador ctrl = Controlador.getInstance();
        ctrl.tela2_Carrinho();
    }

}