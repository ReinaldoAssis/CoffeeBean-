package telas;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import service.Controlador;


public class Login {
    
    @FXML
    TextField login;
    @FXML
    PasswordField senha;
    @FXML
    Label mensagem;

    @FXML
    private void validacao(ActionEvent Joel) throws Exception{

       
        if(login.getText().equals("admin") && senha.getText().equals("admin")){
           System.out.println("Validado!");
           mensagem.setTextFill(Color.rgb(100, 240, 40));
           mensagem.setText("Validado!");
            Controlador ctrl = Controlador.getInstance();
            ctrl.telaPrincipal();
            
        }
        else{
            mensagem.setTextFill(Color.rgb(255 , 0, 0));
            mensagem.setText("Dados incorretos");
            JOptionPane.showMessageDialog(null, "dados incorretos");

        }

    }
    

}
