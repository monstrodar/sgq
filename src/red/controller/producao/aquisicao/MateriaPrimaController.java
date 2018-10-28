/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.aquisicao;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class MateriaPrimaController implements Initializable {

    @FXML
    private TableView<?> tableViewClientes;
    @FXML
    private TableColumn<?, ?> tableColumnClienteNome;
    @FXML
    private TableColumn<?, ?> tableColumnClienteCPF;
    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteCPF;
    @FXML
    private Label labelClienteTelefone;
    @FXML
    private Label labelClienteCodigo;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonRemover;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAlterar(ActionEvent event) {
    }

    @FXML
    private void handleButtonInserir(ActionEvent event) {
    }

    @FXML
    private void handleButtonRemover(ActionEvent event) {
    }
    
}
