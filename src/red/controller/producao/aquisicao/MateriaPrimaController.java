/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.aquisicao;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class MateriaPrimaController implements Initializable {

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
    private AnchorPane PainelCentral;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXComboBox<?> cbbFornecedor;
    @FXML
    private TextArea tesxtArea;
    @FXML
    private JFXCheckBox cbStatus;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private AnchorPane painelTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtPedido(ActionEvent event) throws IOException {
             AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/aquisicao/Pedido.fxml"));        painelTotal.getChildren().setAll(a);
  
    }

    @FXML
    private void evtMatPrima(ActionEvent event) throws IOException {
          AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/aquisicao/MateriaPrima.fxml"));
        painelTotal.getChildren().setAll(a);
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
    private void evtRelatorio(ActionEvent event) throws IOException {
           AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/aquisicao/RelPedido.fxml"));        painelTotal.getChildren().setAll(a);
   
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
    private void btnConfirma(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }
    
}
