package telas;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.Controlador;
//import src.Usuario;

public class Carrinho {

    @FXML
    private Button openCartButton;

    @FXML
    private TextField cpfField;

    public int u;

    //tela de identificação
    @FXML
    private void abrircarr(ActionEvent event) throws Exception {
        String cpf = cpfField.getText();
        u = Controlador.getInstance().database.userDB.getItemIndex(cpf);
        if(u == -1){
            JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
        }
        else{
            //Usuario User = Controlador.getInstance().database.userList.get(u);
            Controlador ctrl = Controlador.getInstance();
            Venda.pegarDados(this.u);
            ctrl.tela2_Carrinho();
            Stage stage = (Stage) cpfField.getScene().getWindow();
            stage.close();
        }
    
    }

}