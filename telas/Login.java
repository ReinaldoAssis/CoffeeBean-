package telas;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import service.Controlador;


public class Login implements Initializable{
    
    @FXML
    TextField login;
    @FXML
    PasswordField senha;
    @FXML
    Label mensagem;

    @FXML
    private AnchorPane panel;

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
            JOptionPane.showMessageDialog(null, "Dados incorretos");

        }

    }

    @Override
    public void initialize(java.net.URL arg0, java.util.ResourceBundle arg1) {
        panel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                case ENTER:
                    try {
                        validacao(null);
                    } catch (Exception e) {
                        //TODO: fazer tratamento de erro
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
                }
            }

              
        });

        // Senha enter
        senha.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                case ENTER:
                    try {
                        validacao(null);
                    } catch (Exception e) {
                        //TODO: fazer tratamento de erro
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
                }
            }

              
        });

        //login enter
        login.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                case ENTER:
                    try {
                        validacao(null);
                    } catch (Exception e) {
                        //TODO: fazer tratamento de erro
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
                }
            }

              
        });
        
    }
    

}
