package telas;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.Controlador;


public class Carrinho {

    @FXML
    private Button openCartButton;

    @FXML
    private TextField cpfField;

    public int u;

    //tela de identificação
    @FXML
    private void abrircarr(ActionEvent event)  throws Exception{
        try {
            String cpf = cpfField.getText();
            u = Controlador.getInstance().database.userDB.getItemIndex(cpf);
            if (u == -1) {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado.");           
            } 
                else {
                Controlador ctrl = Controlador.getInstance();
                Venda.pegarDados(this.u);
                ctrl.tela2_Carrinho();
                Stage stage = (Stage) cpfField.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro: " + e.getMessage());
        } 
    }
}