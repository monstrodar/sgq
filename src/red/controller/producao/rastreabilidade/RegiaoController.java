/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.rastreabilidade;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class RegiaoController implements Initializable {

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
    private AnchorPane PainelCentral;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXCheckBox cbStatus;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private TableColumn<?, ?> colCidade;
    @FXML
    private TableColumn<?, ?> colEstado;
    @FXML
    private JFXComboBox<?> cbbCidade;
    @FXML
    private TableColumn<?, ?> colBuscaCod;
    @FXML
    private TableColumn<?, ?> colBuscaRegiao;
    @FXML
    private JFXComboBox<?> cbbEstado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void btnMaisItens(ActionEvent event) {
    }

    @FXML
    private void btnMenosItens(ActionEvent event) {
    }

    @FXML
    private void btnConfirma(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }

    @FXML
    private void evtMontarCarga(ActionEvent event) throws IOException {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/rastreabilidade/MontagemCarga.fxml"));
        painelTotal.getChildren().setAll(a);
  
    }

    @FXML
    private void evtRegiao(ActionEvent event) throws IOException {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/rastreabilidade/Regiao.fxml"));
        painelTotal.getChildren().setAll(a);
  
    }
    
}
