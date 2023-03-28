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
    private TableView<?> table_all;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Controlador ctrl = Controlador.getInstance();
        System.out.println("vendas :" + ctrl.database.venda + "\n" + "compras :" + ctrl.database.compra);
        System.out.println("situação de caixa :" + (ctrl.database.compra-ctrl.database.venda));
     
        
    }

}