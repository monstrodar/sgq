/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.lote;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import red.dao.producao.aquisicao.MateriaPrimaDAO;
import red.dao.producao.lote.ComposicaoDAO;
import red.dao.producao.lote.ItensSaidaDAO;
import red.dao.producao.lote.MontagemLoteDAO;
import red.dao.producao.lote.ProdutoDAO;
import red.model.producao.aquisicao.MateriaPrima;
import red.model.producao.lote.Produto;
import red.model.colaborador.Colaborador;
import red.model.producao.lote.Composicao;
import red.model.producao.lote.ItensSaida;
import red.model.producao.lote.MontagemLote;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class MontagemLoteController implements Initializable {

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private TextField txtBuscaLote;
    @FXML
    private TableView<MontagemLote> tabelaLote;
    @FXML
    private TableColumn<String, ?> colLoteLT;
    @FXML
    private TableColumn<Timestamp, Timestamp> colDataInicioLT;
    @FXML
    private TableColumn<Timestamp, Timestamp> colDataFimLT;
    @FXML
    private DatePicker dpTermino;
    @FXML
    private DatePicker dpInicio;
    @FXML
    private JFXTextField txtNumLote;
    @FXML
    private JFXComboBox<Colaborador> cbbColaborador;
    @FXML
    private JFXComboBox<MateriaPrima> cbbMateriaPrima;
    @FXML
    private TableView<ItensSaida> tabelaMP;
    @FXML
    private TableColumn<?, ?> colQtdeMp;
    @FXML
    private TableColumn<String, ?> colMatPrimaMp;
    @FXML
    private TableColumn<String, ?> colLoteMp;
    @FXML
    private JFXComboBox<Produto> cbbProduto;
    @FXML
    private JFXTextField txtQtdePro;
    @FXML
    private DatePicker dpBuscaInicio;
    @FXML
    private DatePicker dpBuscaFim;
    @FXML
    private AnchorPane PainelLateral;
    @FXML
    private AnchorPane PainelCentral;
    @FXML
    private JFXTextField txtQtdeMP;

    private List<ItensSaida> listaItensSaida=null;
    @FXML
    private JFXTextField txtLoteMP;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
        estadoOriginal();
       
        
    }    
    private void carregaTabelasEdicaoCbb(){
     
       carregaProduto("");
       carregaMateriaPrima();
         carregaColaborador();
    }

    private void estadoOriginal() {
        PainelCentral.setDisable(true);
        PainelLateral.setDisable(false);
       carregaLote("");
    //    cbbMateriaPrima.getSelectionModel().select(0);
        ObservableList<Node> componentes = PainelLateral.getChildren(); //”limpa” os componentes
        for (Node n : componentes) {
            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
            {
                ((TextInputControl) n).setText("");
            }
        }
         colLoteLT.setCellValueFactory(new PropertyValueFactory("numero"));
        colDataInicioLT.setCellValueFactory(new PropertyValueFactory("data_inicio"));
        colDataFimLT.setCellValueFactory(new PropertyValueFactory("data_fim"));
     
         
    }
    private void estadoEdicao() {     
        PainelCentral.setDisable(false);
        PainelLateral.setDisable(true);
        
//        txtQtdePro.setText("");
//        txtNumLote.setText("");
//        txtQtdeMP.setText("");
//        
        
        
       
//        ObservableList<Node> componentes = PainelCentral.getChildren(); //”limpa” os componentes
//        for (Node n : componentes) {
//            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
//            {
//                ((TextInputControl) n).setText("");
//            }
//             if (n instanceof ComboBox) {
//                ((ComboBox) n).getItems().clear();
//            }
//        } 
        
        carregaTabelasEdicaoCbb();
    }

    

    @FXML
    private void btnBusca(ActionEvent event) {
        
         if(txtBuscaLote.getText().isEmpty()){
            estadoOriginal();
        }
        else{
            MontagemLoteDAO dal = new MontagemLoteDAO();
            List<MontagemLote> res = dal.pesquisa2( Integer.parseInt(txtBuscaLote.getText()) );
            ObservableList<MontagemLote> lote;
            lote = FXCollections.observableArrayList(res);
            tabelaLote.setItems(lote);   
        }   
        txtBuscaLote.requestFocus();
    }


    @FXML
    private void btnAlterar(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(tabelaLote.getSelectionModel().getSelectedIndex() < 0){
    //      cbbMateriaPrima.requestFocus();
            a.setContentText("Por favor, seleciona um lote na tabela");
            a.showAndWait();
        }
        else{
            txtNumLote.setDisable(true);
           listaItensSaida = new ArrayList<ItensSaida>();//inicializa uma lista dos itens de MP do lote    
            estadoEdicao();//carrega cbb(produto, mp, colaborador)    
            MontagemLote  lote = (MontagemLote)tabelaLote.getSelectionModel().getSelectedItem();//cria objeto lote seelecionado do click
            txtNumLote.setText(""+lote.getNumero());
             txtQtdePro.setText("");// criar este atributo na classe monatgem getQtde Produto
             dpInicio.setValue(lote.getData_inicio().toLocalDateTime().toLocalDate());
            dpTermino.setValue(lote.getData_fim().toLocalDateTime().toLocalDate());     //Tudo certo   
         
           cbbColaborador.setValue(lote.getColaborador());
           cbbProduto.setValue(lote.getProduto());


                ItensSaidaDAO daoIS = new ItensSaidaDAO(); //classe dao 
              listaItensSaida = new ArrayList<ItensSaida>();//inicializa uma lista dos itens de MP do lote  
              listaItensSaida = daoIS.buscaItensDoLote(lote.getNumero());
              txtQtdeMP.setText("");
              
              
              carregaItensSaida();//listaItensComposicao 
    
      
           
      //     estadoEdicao();
        } 
       
    }

    @FXML
    private void btnApagar(ActionEvent event) {
        estadoOriginal();
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        
        
        listaItensSaida = new ArrayList<ItensSaida>();
        txtNumLote.setDisable(true);
        carregaItensSaida();
        estadoEdicao();
        
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
         listaItensSaida=null;
        estadoOriginal();
    }

    @FXML
    private void btnConfirma(ActionEvent event) {
         estadoOriginal();
    }

    @FXML
    private void btnMaisItens(ActionEvent event) {
      //   estadoEdicao();
          Alert b = new Alert(Alert.AlertType.INFORMATION);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        String msg ="";
         int qtde;
            try {
                qtde =Integer.parseInt(txtQtdeMP.getText());// qtde da materia prima(composicao)informada
            } catch (Exception e) {
                qtde = 0;
            }
        if(qtde<=0){
            msg+="Por favor, informe uma maior que zero\n";
            txtQtdeMP.requestFocus();
        }
        if(txtLoteMP.getText().length()==0){
             msg+="Por favor, informe o numero do lote da Materia Prima\n";
             txtLoteMP.requestFocus();
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
        else
        {        
            MontagemLote lote =null;
            ItensSaida itens=null;
            int numero;
            try
            {
                numero = Integer.parseInt(txtNumLote.getText());//codigo do produto caso não seja novo
            } 
            catch (Exception e) 
            {
                numero = 0;//produto novo
            }
            MateriaPrima mp = (MateriaPrima) cbbMateriaPrima.getSelectionModel().getSelectedItem();//busca a materia prima
            mp.setNum_lote(txtLoteMP.getText());
            MontagemLoteDAO daoLote = new MontagemLoteDAO();          
            if(numero==0)
            {
                int auxnumero= daoLote.ultimoNumero();// busca o ultimo ID no banco para poder ter um produto para instanciar o produto
                lote = new MontagemLote(auxnumero+1);
                itens = new ItensSaida(mp,lote,qtde);   
            }
            else
            {
                lote = daoLote.busca(numero); // acha o produto
                itens = new ItensSaida(mp,lote,qtde);
            }
            a.setContentText("Confirma a inserção da matéria prima");

            if (a.showAndWait().get() == ButtonType.OK){   
//
              if (listaItensSaida.add(itens)){
                   b.setContentText("Inserido com Sucesso");
                   cbbMateriaPrima.requestFocus();
                }
            } 
            else{
                b.setContentText("Problemas ao Inserir");
            }  
            b.showAndWait();
         //   a.showAndWait();
            carregaItensSaida();
            txtLoteMP.setText("");
            txtQtdeMP.setText("");
            carregaMateriaPrima();
        } 
         
         
         
         
    }
    
   @FXML
    private void btnMenosItens(ActionEvent event) {
         estadoEdicao();
           Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        if(tabelaMP.getSelectionModel().getSelectedIndex() < 0){
            cbbMateriaPrima.requestFocus();
            a.setContentText("Por favor, seleciona um item na tabela");
            a.showAndWait();
        }
        else{
            a.setContentText("Confirma a exclusão");
            if (a.showAndWait().get() == ButtonType.OK){   
                ItensSaida IS = tabelaMP.getSelectionModel().getSelectedItem();
               listaItensSaida.remove(IS);
             carregaItensSaida();
            }   
        }
    }
   
    
    
    
     private void carregaMateriaPrima() {
        MateriaPrimaDAO dal = new MateriaPrimaDAO();
        List<MateriaPrima> res = dal.get("");
        ObservableList<MateriaPrima> mp;
        mp = FXCollections.observableArrayList(res);
        cbbMateriaPrima.setItems(mp);
        
    }
    private void carregaProduto(String filtro) {
        ProdutoDAO dal = new ProdutoDAO();
        List<Produto> res = dal.lista();
        ObservableList<Produto> produto;
        produto = FXCollections.observableArrayList(res);
        cbbProduto.setItems(produto);
    }
    private void carregaColaborador() {
        Colaborador col = new Colaborador();
        List<Colaborador> res = col.lista();
        ObservableList<Colaborador> colaborador;
        colaborador= FXCollections.observableArrayList(res);
        cbbColaborador.setItems(colaborador);
    }
     private void carregaLote(String filtro) {
        MontagemLoteDAO dal = new MontagemLoteDAO();
        List<MontagemLote> res = dal.lista();
        ObservableList<MontagemLote> lotes;
        lotes = FXCollections.observableArrayList(res);
        tabelaLote.setItems(lotes);
    }
      private void carregaItensSaida() {
      
        List<ItensSaida> res = listaItensSaida;//dal.get(codigo);
        ObservableList<ItensSaida> mp;
        mp = FXCollections.observableArrayList(res);
        tabelaMP.setItems(mp);
           colQtdeMp.setCellValueFactory(new PropertyValueFactory("qtde"));
           colMatPrimaMp.setCellValueFactory(new PropertyValueFactory("materia_prima"));
           colLoteMp.setCellValueFactory(new PropertyValueFactory("lote"));
    }
    
     
     
    @FXML
    private void evtProduto(ActionEvent event) throws IOException {
        
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/lote/Produto.fxml"));
        painelTotal.getChildren().setAll(a);  
        
    }

    @FXML
    private void evtPedido(ActionEvent event) {
    }
}
