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
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import red.dao.producao.lote.ProdutoDAO;
import red.model.producao.lote.Produto;

/**
 *
 * @author Daniel
 */
public class ProdutoController implements Initializable{

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private AnchorPane PainelLateral;
    @FXML
    private TextField txtPesquisa;
    
    @FXML
    private AnchorPane PainelCentral;
    @FXML
    private TableView<?> tableViewItensDeProduto;
    @FXML
    private TableColumn<?, ?> tableColumnItemDeProduto;
    @FXML
    private TableColumn<?, ?> tableColumnItemQuantidade;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtQtde;
    @FXML
    private JFXComboBox<?> cbbMateriaPrima;
    @FXML
    private JFXRadioButton rbStatus;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<String,?> tableViewNomeProduto;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        estadoOriginal();
      //  carregaTabela();
        tableViewNomeProduto.setCellValueFactory(new PropertyValueFactory("nome"));
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
    private void evtRelatorio(ActionEvent event) {
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
    }

    @FXML
    private void btnAlterar(ActionEvent event) {
        estadoEdicao();
    }

    @FXML
    private void btnApagar(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão");
        if (a.showAndWait().get() == ButtonType.OK) {
           ProdutoDAO dal = new ProdutoDAO();
           Produto p = tabela.getSelectionModel().getSelectedItem();
            dal.exclui(p);
            carregaTabela();
        }
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        estadoEdicao();
    }

    @FXML
    private void btnItem(ActionEvent event) {
    }

    @FXML
    private void btnConfirma(ActionEvent event) {
         
        int cod;
        try {
            cod = Integer.parseInt(txtCodigo.getText());
        } catch (Exception e) {
            cod = 0;
        }
        Produto p = new Produto(cod,txtNome.getText(),txtDescricao.getText(),true);
        ProdutoDAO dal = new ProdutoDAO();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if (p.getCodigo() == 0) // novo cadastro
        {
            if (dal.insere(p)) {
                a.setContentText("Gravado com Sucesso");
            } else {
                a.setContentText("Problemas ao Gravar");
            }
        } else //alteração de cadastro
        {
            if (dal.altera(p)) {
                a.setContentText("Alterado com Sucesso");
            } else {
                a.setContentText("Problemas ao Alterar");
            }
        }
        a.showAndWait();
        estadoOriginal();
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        estadoOriginal();
    }
    
     private void carregaTabela() {
        ProdutoDAO dal = new ProdutoDAO();
        List<Produto> res = dal.lista();
        ObservableList<Produto> produto;
        produto = FXCollections.observableArrayList(res);
        tabela.setItems(produto);
    }
     private void estadoOriginal() {
        PainelCentral.setDisable(true);
        PainelLateral.setDisable(false);

        ObservableList<Node> componentes = PainelLateral.getChildren(); //”limpa” os componentes
        for (Node n : componentes) {
            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
            {
                ((TextInputControl) n).setText("");
            }
        }

        carregaTabela();
    }
      private void estadoEdicao() {     
        
        PainelCentral.setDisable(false);
        PainelLateral.setDisable(true);
        txtNome.requestFocus();
        txtDescricao.requestFocus(); 
    }
}
