package service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// singleton class
public class Controlador {
    private static Controlador instance = null;
    private Stage stage;
    private Parent root;

    public DB database = new DB(); // instanciando o banco de dados
    
    private Controlador() {
        // apenas para garantir que não seja instanciada
    }

    public static Controlador getInstance() {
        if(instance == null) {
            instance = new Controlador();
            
        }
        return instance;
    }

    public void setStage(Stage _stage) {
        this.stage = _stage;
        instance.stage = _stage;
            instance.stage.setTitle("Coffee Beans");
    }

    public void telaLogin() throws Exception{
        this.root = FXMLLoader.load(getClass().getResource("../telas/login.fxml"));
        this.stage.setScene(new Scene(root));
        this.stage.show();
    }

    public void telaPrincipal() throws Exception{
        this.root = FXMLLoader.load(getClass().getResource("../telas/MainView.fxml"));
        this.stage.setScene(new Scene(root));
        this.stage.setWidth(1280);
        this.stage.setHeight(800);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    public void telaEstoque() throws Exception{
        this.root = FXMLLoader.load(getClass().getResource("../telas/Estoque.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Estoque");
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }
}
