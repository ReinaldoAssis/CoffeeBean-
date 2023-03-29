package telas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import service.Controlador;
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

    @FXML
    private Button btn_cadastrar;

    @FXML
    private ListView<String> listview;

    @FXML
    private Tab tab_consultar;

    @FXML
    private TabPane tabview;

    @FXML
    private Label info;

    @FXML
    private Tab tab_cadastrar;

    @FXML
    private Button btn_deletar;

    private String codigoLinhaSelecionada = "";

    private String codigoOriginal = "";


    @Override
    public void initialize(java.net.URL arg0, java.util.ResourceBundle arg1) {

        
        nome.setText(u.getNome());
        cpf.setText(u.getCpf());
        email.setText("");
        numero.setText("");
        CarregarLista();

        Controlador ctrl = Controlador.getInstance();
        
        listview.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                try{
                    String v = listview.getItems().get((Integer) number2);
                    
                    String cpf = v.substring(v.indexOf("[") + 1, v.indexOf("]"));

                    codigoLinhaSelecionada = cpf;
                    
                    Usuario _u = ctrl.database.getUser(cpf);
                    info.setText(_u.nome);

                    if(_u instanceof Usuario){
                        Usuario u = (Usuario) _u;
                        String info_text = "Nome: "+u.getNome()+"\nCpf: "+u.getCpf()+"\nNumero: "+u.getNumero();
                        info_text += "\nEmail: "+u.getEmail()+"\nFidelidade: " +u.getFidelidade();
                        info.setText(info_text);
                    }

                } catch(Exception e)
                {
                    System.out.println("Erro: "+e.getMessage());
                }

            }
        });

        cpf.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //System.out.println(event.toString());
                if(event.getCode().toString().equalsIgnoreCase("ENTER")){
                    try{
                        Controlador ctrl = Controlador.getInstance();

                        //pega o produto do banco de dados
                        Usuario u = ctrl.database.getUser(cpf.getText());

                        //se o produto existir, preenche os campos com os dados do produto
                        if(u != null){
                            nome.setText(u.getNome());
                            cpf.setText(u.getCpf()+"");
                            email.setText(u.getEmail()+"");
                            numero.setText(u.getNumero()+"");

                            codigoOriginal = cpf.getText();

                            //muda o botão para atualizar
                            btn_cadastrar.setText("Atualizar");
                            btn_cadastrar.setStyle("-fx-background-color: #ffb86c; ");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
                        }

                    } catch(Exception e)
                    {
                        System.out.println("Erro: "+e.getMessage());
                    }
                }
            }
        });

      CarregarLista();
}


    private void atualizar_usuario(Controlador ctrl, Usuario u){
    String ctxt = cpf.getText();
            if(ctxt.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null, "O cpf do usuário não pode ser vazio!");
            }
            else if (codigoOriginal.equalsIgnoreCase(u.getCpf())){
                ctrl.database.removerUsuario(ctxt);
                ctrl.database.criarUsuario(u);
                JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
                System.out.println(u.nome+" "+u.cpf+" "+u.numero+" "+u.email);

                codigoOriginal = "";

                btn_cadastrar.setText("Cadastrar");
                btn_cadastrar.setStyle("-fx-background-color: #50fa7b; ");
                nome.setText("");
                cpf.setText("");
                numero.setText("");
                email.setText("");
                info.setText("");
                CarregarLista(); 

            }
            else{
                JOptionPane.showMessageDialog(null, "O cpf do usuário não pode ser alterado!");
            }
}

    @FXML

    void click_cadastrar(ActionEvent e){

        u.setNome(nome.getText());
        u.setCpf(cpf.getText());
        u.setEmail(email.getText());
        u.setNumero(numero.getText());
        u.setFidelidade(0);

        Controlador ctrl = Controlador.getInstance();

        boolean cadastro = btn_cadastrar.getText().equalsIgnoreCase("Cadastrar");

        if(!cadastro){
            atualizar_usuario(ctrl, u);
            return;
        }

        System.out.println(this.cpf.getText());

        if(cadastro && ctrl.database.getUserIndex(this.cpf.getText()) != -1)
        {
            JOptionPane.showMessageDialog(null, "Usuário do cpf " + this.cpf.getText() + " já foi cadastrado!");
            return;
        }
        else
        {
            if (cadastro) ctrl.database.criarUsuario(u);
            JOptionPane.showMessageDialog(null, "Usuário do cpf " + this.cpf.getText() + " foi cadastrado com sucesso!");
            
        }

        nome.setText("");
        cpf.setText("");
        numero.setText("");
        email.setText("");
        CarregarLista();   
        
    }

    @FXML
    void click_deletar(ActionEvent event) {
        Controlador ctrl = Controlador.getInstance();
        String cpf = codigoLinhaSelecionada; //codigo da linha selecionada
        System.out.println("Codigo ----- "+ cpf);
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o usuário ?", "Remover Usuário", JOptionPane.YES_NO_OPTION);
        if(opcao == JOptionPane.YES_OPTION){
            ctrl.database.removerUsuario(cpf);
            JOptionPane.showMessageDialog(null, "Usuário apagado!");
        }
        else{
            JOptionPane.showMessageDialog(null, "Operação cancelada!");
        }
        CarregarLista();  
        info.setText("");
    }   

    private void CarregarLista(){
        Controlador ctrl = Controlador.getInstance();
        listview.getItems().clear();
        List<String> lista_nomes = new ArrayList<String>();

        for(Usuario u : ctrl.database.userList){
            lista_nomes.add(u.displayName());
            System.out.println("Usuário: "+u.displayName());
        }

        listview.getItems().addAll(lista_nomes);

    }


}
