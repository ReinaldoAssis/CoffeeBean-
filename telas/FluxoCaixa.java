package telas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;


public class FluxoCaixa implements Initializable {

    @FXML
    private TableView<?> table_vendas;

    @FXML
    private TableView<?> table_gasto;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        TableColumn teste = new TableColumn<>("teste");
        teste.setCellValueFactory(new PropertyValueFactory<>("name"));
        table_gasto.getColumns().addAll(teste);
    }

}