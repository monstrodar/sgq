/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.lote;

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
public class ProdutoController implements Initializable {

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private VBox painelEsquerda;
    @FXML
    private Label labelClienteNome11;
    @FXML
    private TextField txpesquisa;
    @FXML
    private ListView<?> lvcolaboradores;
    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteTelefone;
    @FXML
    private TableView<?> tableViewItensDeVenda;
    @FXML
    private TableColumn<?, ?> tableColumnItemDeVendaProduto;
    @FXML
    private TableColumn<?, ?> tableColumnItemDeVendaQuantidade;
    @FXML
    private Label labelClienteNome1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void evtRelatorio(ActionEvent event) {
    }



    @FXML
    private void evtLote(ActionEvent event) throws IOException {
          AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/lote/MontagemLote.fxml"));
        painelTotal.getChildren().setAll(a);
    }

    @FXML
    private void evtProduto(ActionEvent event) {
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
    }

    @FXML
    private void btnAlterar(ActionEvent event) {
    }

    @FXML
    private void btnApagar(ActionEvent event) {
    }

    @FXML
    private void btnNovo(ActionEvent event) {
    }

    @FXML
    private void btnItem(ActionEvent event) {
    }

    @FXML
    private void btnConfirma(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }
    
}
