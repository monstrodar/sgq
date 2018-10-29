/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.lote;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import red.dao.producao.aquisicao.MateriaPrimaDAO;
import red.dao.producao.lote.ComposicaoDAO;
import red.dao.producao.lote.ProdutoDAO;
import red.model.producao.aquisicao.MateriaPrima;
import red.model.producao.lote.Composicao;
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
    private TableView<Composicao> tableViewItensDeProduto;
    @FXML
    private TableColumn<?, ?> tableColumnItemDeProduto;
    @FXML
    private TableColumn<?, ?> tableColumnItemQuantidade;
    
    
    @FXML
    private JFXTextField txtDescricao;
    
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<String,String> tableViewNomeProduto;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtQtde;
    @FXML
    private JFXComboBox<MateriaPrima> cbbMateriaPrima;
    @FXML
    private JFXCheckBox cbStatus;
    
    private List<Composicao> listaItensComposicao=null;
    private List<Composicao> listaItensComposicaoRetirado = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       listaItensComposicao = new ArrayList<Composicao>();
       listaItensComposicaoRetirado = new ArrayList<Composicao>();
        estadoOriginal();
        tableViewNomeProduto.setCellValueFactory(new PropertyValueFactory("nome"));
     //   tableColumnItemDeProduto.setCellValueFactory(new PropertyValueFactory("nome"));
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
          carregaTabela("upper(mp_nome) like '%"+txtPesquisa.getText().toUpperCase()+"%'");
    } //"upper(mp_nome) like '\%FIL%\'" como tirar as barras ERRO

    @FXML
    private void btnAlterar(ActionEvent event) {
        Produto p = (Produto)tabela.getSelectionModel().getSelectedItem();
        txtCodigo.setText(""+p.getCodigo());
        txtNome.setText(""+p.getNome());
        txtDescricao.setText(""+p.getDescricao());
        cbStatus.setSelected(p.isStatus());   
        estadoEdicao();
        carregaComposicao(p.getCodigo());
       tableColumnItemDeProduto.setCellValueFactory(new PropertyValueFactory("materia_prima"));
        tableColumnItemQuantidade.setCellValueFactory(new PropertyValueFactory("qtde"));
       
        ComposicaoDAO dal = new ComposicaoDAO();
        listaItensComposicao = dal.get(p.getCodigo());
       
    }
    
    
   
    @FXML
    private void btnApagar(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão");
        if (a.showAndWait().get() == ButtonType.OK) {
           ProdutoDAO dal = new ProdutoDAO();
           Produto p = tabela.getSelectionModel().getSelectedItem();
            dal.exclui(p);
            carregaTabela("");
        }
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        
        ProdutoDAO dao = new ProdutoDAO();
       // txtCodigo.setText(Integer.toString(dao.ultimoId())); 
        estadoEdicao();
    }


    @FXML
    private void btnConfirma(ActionEvent event) {
         
//         cbMarcas.getSelectionModel().select(0);
//        cbMarcas.getSelectionModel().select(m.getMarca());
//        
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        String msg ="";
//        if(txtCodigo.getText().length()==0){
//             msg+="Por favor, informe o Código do produto\n";
//            txtCodigo.requestFocus();
//        }
        if(txtNome.getText().length()==0){
             msg+="Por favor, informe o Nome do produto\n";
             txtNome.requestFocus();
        }
        if(txtDescricao.getText().length()==0){
            msg+="Por favor, informe a Descrição do produto\n";
            txtDescricao.requestFocus();
        }
        if(!cbStatus.isSelected()){
            msg+="Por favor, informe se o produto está ativo\n";
            cbStatus.requestFocus();
        }
        if (msg!=""){
           b.setContentText(msg); 
           b.showAndWait();
        }
        else{
            
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
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        
//        ComposicaoDAO daoComp = new ComposicaoDAO();
//        Composicao comp=null;
//        for(int i=0;i<listaItensComposicao.size();i++){
//            comp=listaItensComposicao.remove(i);
//            daoComp.exclui(comp);
//        }
//        for(int i=0;i<listaItensComposicaoRetirado.size();i++){
//            comp=listaItensComposicao.remove(i);
//            daoComp.insere(comp);
//        }
        estadoOriginal();
    }
    
     private void carregaTabela(String filtro) {
        ProdutoDAO dal = new ProdutoDAO();
        List<Produto> res = dal.get(filtro);
        ObservableList<Produto> produto;
        produto = FXCollections.observableArrayList(res);
        tabela.setItems(produto);
    }
     private void carregaComposicao(int codigo) {
        ComposicaoDAO dal = new ComposicaoDAO();
        List<Composicao> res = dal.get(codigo);
        ObservableList<Composicao> mp;
        mp = FXCollections.observableArrayList(res);
        tableViewItensDeProduto.setItems(mp);
    }
    
     private void carregaMateriaPrima() {
        MateriaPrimaDAO dal = new MateriaPrimaDAO();
        List<MateriaPrima> res = dal.get("");
        ObservableList<MateriaPrima> mp;
        mp = FXCollections.observableArrayList(res);
        cbbMateriaPrima.setItems(mp);
    }
     
     private void estadoOriginal() {
        PainelCentral.setDisable(true);
        PainelLateral.setDisable(false);
        txtCodigo.setText("");
        txtNome.setText("");
        txtDescricao.setText("");
        cbStatus.setSelected(false);
        cbbMateriaPrima.getSelectionModel().select(0);
        

        ObservableList<Node> componentes = PainelLateral.getChildren(); //”limpa” os componentes
        for (Node n : componentes) {
            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
            {
                ((TextInputControl) n).setText("");
            }
        }

        carregaTabela("");
    }
      private void estadoEdicao() {     
        
        
        PainelCentral.setDisable(false);
        PainelLateral.setDisable(true);
        txtNome.requestFocus();
        cbStatus.setSelected(true);
        carregaMateriaPrima();
        
        
        
         ObservableList<Node> componentes = PainelCentral.getChildren(); //”limpa” os componentes
        for (Node n : componentes) {
            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
            {
                ((TextInputControl) n).setText("");
            }
             if (n instanceof ComboBox) {
                ((ComboBox) n).getItems().clear();
            }
        }
        
    }

    @FXML
    private void btnMaisItens(ActionEvent event) {
        
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        String msg ="";
        
       
         int qtde =0;
            try {
                qtde =Integer.parseInt(txtQtde.getText());// qtde da materia prima(composicao)informada
            } catch (Exception e) {
                qtde = 0;
            }
        
        if(qtde==0){
            msg+="Por favor, informe uma maior que zero\n";
            txtQtde.requestFocus();
        }
        if(cbbMateriaPrima.getSelectionModel().getSelectedIndex() < 0)//verifica se o cbb esta marcado com a mp
        {
            msg+="Por favor, informe a matéria prima\n";
            cbbMateriaPrima.requestFocus();
        }
        if (msg!=""){
           b.setContentText(msg); 
           b.showAndWait();
        }
        else{
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            int cod;
            try {
                cod = Integer.parseInt(txtCodigo.getText());//codigo do produto
            } catch (Exception e) {
                cod = 0;
            }
            
            try {
                qtde =Integer.parseInt(txtQtde.getText());// qtde da materia prima(composicao)informada
            } catch (Exception e) {
                qtde = 0;
            }
            MateriaPrima mp = (MateriaPrima)cbbMateriaPrima.getSelectionModel().getSelectedItem();//busca a materia prima
            ProdutoDAO daoPro = new ProdutoDAO();
            Produto pro = daoPro.busca(cod); // acha o produto
            ComposicaoDAO daoComp = new ComposicaoDAO();
            Composicao comp = new Composicao(mp,pro,qtde);
            
                if (daoComp.insere(comp)) {
                    a.setContentText("Gravado com Sucesso");
                    cbbMateriaPrima.requestFocus();
                } else {
                    a.setContentText("Problemas ao Gravar");
                }

            a.showAndWait();
            carregaComposicao(comp.getProduto().getCodigo());
        
        }
    }

    @FXML
    private void btnMenosItens(ActionEvent event) {
        
         Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão");
        if (a.showAndWait().get() == ButtonType.OK) {
           ComposicaoDAO dal = new ComposicaoDAO();
           Composicao p = tableViewItensDeProduto.getSelectionModel().getSelectedItem();
           listaItensComposicaoRetirado.add(p);
           dal.exclui(p);
            carregaComposicao(p.getProduto().getCodigo());
        }
    }


    
}
