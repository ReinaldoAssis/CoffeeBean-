package telas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Controlador;
import javafx.fxml.Initializable;


public class FluxoCaixa implements Initializable {

    @FXML
    private Label infos;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Controlador ctrl = Controlador.getInstance();
        infos.setText("Compra total: " + ctrl.database.compra + "\n" + "Vendas total: " + ctrl.database.venda + "\n" + "Caixa: " + (ctrl.database.venda-ctrl.database.compra));
        //System.out.println("vendas :" + ctrl.database.venda + "\n" + "compras :" + ctrl.database.compra);
        //System.out.println("situação de caixa :" + (ctrl.database.compra-ctrl.database.venda));
     
        
    }

}