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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class CadastroFornecedorController implements Initializable {
    @FXML
    private AnchorPane painelTotal;
    @FXML
    private AnchorPane painelTotal1;
    @FXML
    private VBox painelEsquerda;
    @FXML
    private TextField txpesquisa;
    @FXML
    private ListView<?> lvcolaboradores;
    @FXML
    private VBox pnDireita;
    @FXML
    private RadioButton rbbasico;
    @FXML
    private ToggleGroup gruporadio;
    @FXML
    private RadioButton rbauditor;
    @FXML
    private RadioButton rbadministrador;
    @FXML
    private TextField txnome;
    @FXML
    private TextField txcargo;
    @FXML
    private TextField txcpf;
    @FXML
    private TextField txobs;
    @FXML
    private DatePicker dpdata;

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
    private void clkConfirmar(ActionEvent event) {
    }

    @FXML
    private void clCancelar(ActionEvent event) {
    }
    
}
