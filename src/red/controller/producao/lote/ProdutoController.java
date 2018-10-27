/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.lote;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import red.dao.producao.lote.ProdutoDAO;
import red.model.producao.lote.Produto;

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
    private TableView<Produto> tableViewItensDeVenda;
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
      //  estadoOriginal();
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
    
    
     private void estadoOriginal() {
//        pnTabela.setDisable(false);
//        pnDados.setDisable(true);
//
//        btConfirmar.setDisable(true);
//        btCancelar.setDisable(false);
//        btApagar.setDisable(true);
//        btAlterar.setDisable(true);
//        btNovo.setDisable(false);
//        txCodigo.setDisable(true);//mudar nas outras desabilita o codigo
         
//        ObservableList<Node> componentes = pnDados.getChildren(); //”limpa” os componentes
//        for (Node n : componentes) {
//            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
//            {
//                ((TextInputControl) n).setText("");
//            }
//            if (n instanceof ComboBox) {
//                ((ComboBox) n).getItems().clear();
//            }
//        }

        carregaTabela("");
    }
     private void carregaTabela(String filtro) {
        ProdutoDAO dal = new ProdutoDAO();
        List<Produto> res = dal.lista();
        ObservableList<Produto> produto;
        produto = FXCollections.observableArrayList(res);
        tableViewItensDeVenda.setItems(produto);
    }
    
    
}
