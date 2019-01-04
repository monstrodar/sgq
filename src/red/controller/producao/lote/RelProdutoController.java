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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import red.dao.producao.aquisicao.EstoqueMpDAO;
import red.dao.producao.aquisicao.MateriaPrimaDAO;
import red.dao.producao.lote.EstoqueProdutoDAO;
import red.dao.producao.lote.ProdutoDAO;
import red.model.producao.aquisicao.EstoqueMP;
import red.model.producao.aquisicao.MateriaPrima;
import red.model.producao.lote.EstoqueProduto;
import red.model.producao.lote.Produto;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class RelProdutoController implements Initializable {

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private Label labelClienteNome;
    @FXML
    private DatePicker dpData;
    @FXML
    private TableView<EstoqueProduto> tabela;
    @FXML
    private TableColumn<?, ?> colQtEnt;
    @FXML
    private TableColumn<?, ?> colValidade;
    @FXML
    private TableColumn<?, ?> colMov;
    @FXML
    private ComboBox<EstoqueProduto> cbbLotePro;
    @FXML
    private TableColumn<?, ?> colProduto;
    @FXML
    private TableColumn<?, ?> loteProduto;
    @FXML
    private TableColumn<?, ?> colQtVendida;
    @FXML
    private ComboBox<Produto> cbbProduto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         carregaProduto();
      
        Produto produto =  cbbProduto.getSelectionModel().getSelectedItem();      
        
        if(cbbProduto.getSelectionModel().getSelectedIndex()>0)//verifica se o cbb esta marcado com a mp
        {
            cbbLotePro.requestFocus();
            carregaLoteProduto(produto);
        }
        
        
        EstoqueProdutoDAO dal =new EstoqueProdutoDAO();
        
        
        List<EstoqueProduto> res = dal.lista();
            ObservableList<EstoqueProduto> mp;
            mp = FXCollections.observableArrayList(res);
            tabela.setItems(mp);

        colProduto.setCellValueFactory(new PropertyValueFactory("produto"));
        colQtEnt.setCellValueFactory(new PropertyValueFactory("qtde"));
        loteProduto.setCellValueFactory(new PropertyValueFactory("lote"));
        colValidade.setCellValueFactory(new PropertyValueFactory("validade"));
        colQtVendida.setCellValueFactory(new PropertyValueFactory("vendida"));
        colMov.setCellValueFactory(new PropertyValueFactory("data_mov"));
        
        
        
    }    


    @FXML
    private void alkApagar(ActionEvent event) {
    }


    
        @FXML
    private void evtLote(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/lote/MontagemLote.fxml"));
        painelTotal.getChildren().setAll(a);
        
    }

    @FXML
    private void evtProduto(ActionEvent event) throws IOException {
       AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/lote/Produto.fxml"));
        painelTotal.getChildren().setAll(a);  
        
    }

    @FXML
    private void evtRelatorio(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/lote/RelProduto.fxml"));
        painelTotal.getChildren().setAll(a);  
    }

    @FXML
    private void evtRelatorio(MouseEvent event) {
    }
    private void carregaProduto() {
        ProdutoDAO dal = new ProdutoDAO();
        List<Produto> res = dal.lista();
        ObservableList<Produto> mp;
        mp = FXCollections.observableArrayList(res);
        cbbProduto.setItems(mp);
    }
    private void carregaLoteProduto(Produto p) {
        EstoqueProdutoDAO dal = new EstoqueProdutoDAO();
        List<EstoqueProduto> res = dal.lista2(p);
        ObservableList<EstoqueProduto> mp;
        mp = FXCollections.observableArrayList(res);
        cbbLotePro.setItems(mp);
    }
}
