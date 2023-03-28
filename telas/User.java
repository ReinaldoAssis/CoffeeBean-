package telas;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import src.Usuario;

public class User implements Initializable{
    
    private Usuario u = new Usuario();

    @FXML
    private TextField nome;

    @FXML
    private TextField cpf;

    @FXML
    private TextField email;

    @FXML 
    private TextField numero;
}
