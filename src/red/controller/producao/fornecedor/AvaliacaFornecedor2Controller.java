/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.fornecedor;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class AvaliacaFornecedor2Controller implements Initializable {

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private AnchorPane PainelLateral;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private JFXCheckBox cbStatusBuscaAtivo;
    @FXML
    private JFXCheckBox cbStatusBuscaInativo;
    @FXML
    private TableView<?> tabela;
    @FXML
    private TableColumn<?, ?> tableViewNomeMP;
    @FXML
    private ScrollPane PainelCentral;
    @FXML
    private JFXComboBox<?> cbbMateriaPrima;
    @FXML
    private TableView<?> tabelaMP;
    @FXML
    private TableColumn<?, ?> colMatPrimaMp;
    @FXML
    private JFXComboBox<?> cbbMateriaPrima1;

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
    private void btnBuscar(ActionEvent event) {
    }

    @FXML
    private void clkAtivoBusca(ActionEvent event) {
    }

    @FXML
    private void clkInativoBusca(ActionEvent event) {
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
    private void cbbEVTMateriaPrima(ActionEvent event) {
    }

    @FXML
    private void btnMenosItens(ActionEvent event) {
    }

    @FXML
    private void btnMaisItens(ActionEvent event) {
    }

    
}
