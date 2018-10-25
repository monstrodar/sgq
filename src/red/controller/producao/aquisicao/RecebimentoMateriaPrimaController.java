/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.aquisicao;

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
public class RecebimentoMateriaPrimaController implements Initializable {

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private VBox painelEsquerda;
    @FXML
    private Label labelClienteNome112;
    @FXML
    private Label labelClienteNome11;
    @FXML
    private TextField txpesquisa;
    @FXML
    private Label labelClienteNome111;
    @FXML
    private TextField txpesquisa1;
    @FXML
    private Label labelClienteNome1;
    @FXML
    private DatePicker datePickerVendaData1;
    @FXML
    private ListView<?> lvcolaboradores;
    @FXML
    private Label labelClienteNome;
    @FXML
    private TextField txtQtde1;
    @FXML
    private Label labelClienteNome2;
    @FXML
    private DatePicker datePickerVendaData;
    @FXML
    private TextField txtQtde11;
    @FXML
    private TextField txtQtde114;
    @FXML
    private DatePicker datePickerVendaData2;
    @FXML
    private Label labelClienteNome21;
    @FXML
    private Label labelClienteNome12;
    @FXML
    private DatePicker datePickerVendaData11;
    @FXML
    private TableView<?> tableViewItensDeVenda;
    @FXML
    private TableColumn<?, ?> tableColumnItemDeVendaQuantidade;
    @FXML
    private TableColumn<?, ?> tableColumnItemDeVendaProduto11;
    @FXML
    private TableColumn<?, ?> tableColumnItemDeVendaProduto1;
    @FXML
    private Label labelClienteTelefone;
    @FXML
    private TextField txtQtde12;
    @FXML
    private ComboBox<?> comboBoxVendaCliente;
    @FXML
    private TextField txtQtde121;
    @FXML
    private Label labelClienteNome211;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtPedido(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/aquisicao/Pedido.fxml"));
        painelTotal.getChildren().setAll(a);
    }

    @FXML
    private void evtMatPrima(ActionEvent event) {
    }

    @FXML
    private void evtRecebimentoMP(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/aquisicao/RecebimentoMateriaPrima.fxml"));
        painelTotal.getChildren().setAll(a);
    }

    @FXML
    private void evtConferenciaMP(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/aquisicao/ConferenciaMateriaPrima.fxml"));
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
    private void evtRelatorio(ActionEvent event) throws IOException {
           AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/aquisicao/RelPedido.fxml"));        painelTotal.getChildren().setAll(a);
   
    }

   
    
}
