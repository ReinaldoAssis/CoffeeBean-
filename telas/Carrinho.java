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
    void get_text(ActionEvent event) {

    }

    @FXML
    void opencart(ActionEvent event) throws Exception {
       // String cpf = cpfField.getText();
        //System.out.println(cpf);
        Controlador ctrl = Controlador.getInstance();
        ctrl.tela2_Carrinho();
    }

    @FXML
    void open_cart(ActionEvent event) throws Exception {
        //String cpf = cpfField.getText();
        //System.out.println(cpf);
        Controlador ctrl = Controlador.getInstance();
        ctrl.tela2_Carrinho();
    }

}