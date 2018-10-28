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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class PedidoFornecedorController implements Initializable {

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private VBox painelEsquerda;
    @FXML
    private Label labelClienteNome11;
    @FXML
    private TextField txpesquisa;
    @FXML
    private Label labelClienteNome1;
    @FXML
    private DatePicker datePickerVendaData1;
    @FXML
    private ListView<?> lvcolaboradores;
    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteTelefone;
    @FXML
    private DatePicker datePickerVendaData;
    @FXML
    private ComboBox<?> comboBoxVendaCliente;
    @FXML
    private TableView<?> tableViewItensDeVenda;
    @FXML
    private TableColumn<?, ?> tableColumnItemDeVendaProduto;
    @FXML
    private TableColumn<?, ?> tableColumnItemDeVendaQuantidade;
    @FXML
    private Button buttonAdicionar;
    @FXML
    private TextField txtQtde1;
    @FXML
    private TextField txtQtde;
    @FXML
    private Label labelClienteNome111;
    @FXML
    private TextField txpesquisa1;
    @FXML
    private Label labelClienteNome12;
    @FXML
    private DatePicker datePickerVendaData11;
    @FXML
    private ComboBox<?> comboBoxVendaCliente1;
    @FXML
    private Label labelClienteNome2;
    @FXML
    private DatePicker datePickerVendaData2;

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
    private void evtAvalFornecedor(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/fornecedor/AvaliacaoFornecedor.fxml"));
        painelTotal.getChildren().setAll(a);
    }

    @FXML
    private void evtPedidoFor(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/fornecedor/PedidoFornecedor.fxml"));
        painelTotal.getChildren().setAll(a);
    }

    @FXML
    private void clkBuscar(ActionEvent event) {
    }

    @FXML
    private void clkAlterar(ActionEvent event) {
    }

    @FXML
    private void alkApagar(ActionEvent event) {
    }

    @FXML
    private void clkNovo(ActionEvent event) {
    }

    @FXML
    private void handleButtonAdicionar(ActionEvent event) {
    }
    
}
