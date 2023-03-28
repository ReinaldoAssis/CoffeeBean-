package telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.Controlador;

public class VisCarrinho {

    @FXML
    private Button btn_del;

    @FXML
    private Button btn_fin;

    @FXML
    private Button btn_voltar;
 
    @FXML
    void click_voltar(ActionEvent event) {
        Stage stage = (Stage) btn_voltar.getScene().getWindow();
        stage.close();
    }

}
