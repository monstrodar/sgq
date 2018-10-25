/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.fornecedor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class AvaliacaFornecedorController implements Initializable {

    @FXML
    private AnchorPane painelTotal;
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
    private void evtCadFornecedor(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/fornecedor/CadastroFornecedor.fxml"));
        painelTotal.getChildren().setAll(a);
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

    @FXML
    private void evtAvalFornecedor(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/fornecedor/AvaliacaoFornecedor.fxml"));
        painelTotal.getChildren().setAll(a);
    }

    @FXML
    private void evtPedidoFor(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/fornecedor/PedidoFornecedor.fxml"));
        painelTotal.getChildren().setAll(a);
    }
    
}
