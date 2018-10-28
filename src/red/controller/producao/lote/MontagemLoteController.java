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
public class MontagemLoteController implements Initializable {

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
    private Label labelClienteNome1;
    @FXML
    private DatePicker datePickerVendaData1;
    @FXML
    private ListView<?> lvcolaboradores;
    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteNome2;
    @FXML
    private DatePicker datePickerVendaData;
    @FXML
    private Label labelClienteNome21;
    @FXML
    private TableView<?> tableViewItensDeVenda;
    @FXML
    private TableColumn<?, ?> tableColumnItemDeVendaProduto;
    @FXML
    private TableColumn<?, ?> tableColumnItemDeVendaQuantidade;
    @FXML
    private TableColumn<?, ?> tableColumnItemDeVendaProduto1;
    @FXML
    private TableColumn<?, ?> tableColumnItemDeVendaProduto11;
    @FXML
    private Label labelClienteNome12;
    @FXML
    private DatePicker datePickerVendaData11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtPedido(ActionEvent event) {
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
        
      //  Lote novo = new Lote();
     //   listar(Produtos();






        
    }
     
//    private void evtPesquisar(ActionEvent event) {
//        carregaTabela("upper(vei_placa) like '%"+txPesquisar.getText().toUpperCase()+"%'");
//    }
//    private void listarProdutos() {
//        produtoDAL dal = new ModeloDAL();
//        List<Produto> res = dal.get("");
//        ObservableList<Produto> produto;
//        produto = FXCollections.observableArrayList(res);
//        cbProduto.setItems(produto);
//    }

    @FXML
    private void evtitem(ActionEvent event) {
    }

    @FXML
    private void evtProduto(ActionEvent event) throws IOException {
        
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/lote/Produto.fxml"));
        painelTotal.getChildren().setAll(a);  
        
    }
}
