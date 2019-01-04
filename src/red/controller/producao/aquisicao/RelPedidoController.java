/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.aquisicao;

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
import red.model.producao.aquisicao.EstoqueMP;
import red.model.producao.aquisicao.MateriaPrima;
import red.model.producao.rastreabilidade.EntradaLote;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class RelPedidoController implements Initializable {

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private Label labelClienteNome;
    @FXML
    private DatePicker dpData;
    @FXML
    private ComboBox<EstoqueMP> cbbLoteMp;
    @FXML
    private TableView<EstoqueMP> tabela;
 
    @FXML
    private TableColumn<String, String> colMp;
    @FXML
    private TableColumn<?, ?> colQtEnt;
    @FXML
    private TableColumn<String, String> loteMp;
    @FXML
    private TableColumn<?, ?> colQtAprovada;
    @FXML
    private TableColumn<?, ?> colQtDescarte;
    @FXML
    private TableColumn<?, ?> colQtMontada;
    @FXML
    private TableColumn<?, ?> colValidade;
    @FXML
    private TableColumn<?, ?> colMov;
    @FXML
    private TableColumn<?, ?> colRec;
    @FXML
    private ComboBox<MateriaPrima> cbbMP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        carregaMateriaPrima();
        
      
        
        MateriaPrima mat_prima =  cbbMP.getSelectionModel().getSelectedItem();      
        
        if(cbbMP.getSelectionModel().getSelectedIndex()>0)//verifica se o cbb esta marcado com a mp
        {
            cbbLoteMp.requestFocus();
            carregaLoteMP(mat_prima);
        }
        
        
        EstoqueMpDAO dal =new EstoqueMpDAO();
        
        
        List<EstoqueMP> res = dal.lista();
            ObservableList<EstoqueMP> mp;
            mp = FXCollections.observableArrayList(res);
            tabela.setItems(mp);
        
      
        colMp.setCellValueFactory(new PropertyValueFactory("materia_prima"));
        colMov.setCellValueFactory(new PropertyValueFactory("data_movimentacao"));
        colQtAprovada.setCellValueFactory(new PropertyValueFactory("qtde_aprovada"));
        colQtDescarte.setCellValueFactory(new PropertyValueFactory("qtde_descarte"));
        colQtEnt.setCellValueFactory(new PropertyValueFactory("qtde_rec"));
        colQtMontada.setCellValueFactory(new PropertyValueFactory("qtde_montada"));
        colRec.setCellValueFactory(new PropertyValueFactory("recebimento_mp"));
        colValidade.setCellValueFactory(new PropertyValueFactory("validade"));
        loteMp.setCellValueFactory(new PropertyValueFactory("lote_mp"));
        
        
        
    }
    private void carregaMateriaPrima() {
        MateriaPrimaDAO dal = new MateriaPrimaDAO();
        List<MateriaPrima> res = dal.get("");
        ObservableList<MateriaPrima> mp;
        mp = FXCollections.observableArrayList(res);
        cbbMP.setItems(mp);
    }
    private void carregaLoteMP(MateriaPrima matP) {
        EstoqueMpDAO dal = new EstoqueMpDAO();
        List<EstoqueMP> res = dal.lista2(matP);
        ObservableList<EstoqueMP> mp;
        mp = FXCollections.observableArrayList(res);
        cbbLoteMp.setItems(mp);
    }



    

    @FXML
    private void evtPedido(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/aquisicao/Pedido.fxml"));
        painelTotal.getChildren().setAll(a);
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
    private void alkApagar(ActionEvent event) {
    }


    @FXML
    private void evtRelatorio(MouseEvent event) throws IOException {
           AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/aquisicao/RelPedido.fxml"));        painelTotal.getChildren().setAll(a);
   
    }
    
    
}
