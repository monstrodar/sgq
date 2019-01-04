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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import red.dao.producao.aquisicao.EstoqueMpDAO;
import red.dao.producao.aquisicao.MateriaPrimaDAO;
import red.dao.producao.lote.ItensSaidaDAO;
import red.dao.producao.lote.MontagemLoteDAO;
import red.dao.producao.lote.ProdutoDAO;
import red.model.producao.aquisicao.MateriaPrima;
import red.model.producao.lote.Produto;
import red.model.colaborador.Colaborador;
import red.model.producao.aquisicao.EstoqueMP;
import red.model.producao.aquisicao.ItensMP;
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
    private TableColumn<String, String> colLoteLT;
    @FXML
    private TableColumn<Timestamp, Timestamp> colDataInicioLT;
    @FXML
    private TableColumn<Timestamp, Timestamp> colDataFimLT;
    @FXML
    private DatePicker dpTermino;
    @FXML
    private DatePicker dpInicio;
    @FXML
    private JFXComboBox<Colaborador> cbbColaborador;
    @FXML
    private JFXComboBox<MateriaPrima> cbbMateriaPrima;
    @FXML
    private TableView<ItensSaida> tabelaMP;
    @FXML
    private TableColumn<?, ?> colQtdeMp;
    @FXML
    private TableColumn<String, String> colMatPrimaMp;
    @FXML
    private TableColumn<String, String> colLoteMp;
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
    @FXML
    private JFXTextField txtNumLotePro;
    @FXML
    private JFXComboBox<EstoqueMP> cbbLtMp;
   
    private List<ItensSaida> listaItensSaida=null;
    private List<EstoqueMP> listaEstoqueAprovado=null;
  
    @FXML
    private DatePicker dpValidade;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        estadoOriginal(); 
    }    
    @FXML
    private void btnAlterar(ActionEvent event) {//ok

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(tabelaLote.getSelectionModel().getSelectedIndex() < 0){
                  a.setContentText("vivian teacher");
                  a.showAndWait();
        }
        else{
            MontagemLote  lote = (MontagemLote)tabelaLote.getSelectionModel().getSelectedItem();//cria objeto lote seelecionado do click

            txtNumLotePro.setText(""+lote.getNumero());
            txtQtdePro.setText(""+lote.getQtde_montada());
            txtNumLotePro.setDisable(true); 
            dpInicio.setValue(lote.getData_inicio());
            dpTermino.setValue(lote.getData_fim());   
                     
            ItensSaidaDAO daoIS = new ItensSaidaDAO(); 
            listaItensSaida = new ArrayList<ItensSaida>();
            listaItensSaida = daoIS.buscaItensDoProduto(lote.getNumero());
            List<ItensSaida> res = listaItensSaida; 
                    
            EstoqueMpDAO daoEmp = new EstoqueMpDAO();
            listaEstoqueAprovado = new ArrayList<EstoqueMP>();
            listaEstoqueAprovado = daoEmp.lista();

            carregaItensSaida();
            carregaTabelasEdicaoCbb();
            cbbProduto.setValue(lote.getProduto());
            cbbColaborador.setValue(lote.getColaborador());
            PainelCentral.setDisable(false);
            PainelLateral.setDisable(true);    
        }  
        
    }
    @FXML
    private void cbbEVTMateriaPrima(ActionEvent event) {//ok
        MateriaPrima mp =(MateriaPrima) cbbMateriaPrima.getSelectionModel().getSelectedItem();      
        carregaLotesMP( mp);  
          
    }
    @FXML
    private void btnNovo(ActionEvent event) {//OK
        listaItensSaida = new ArrayList<ItensSaida>();
        
        EstoqueMpDAO daoEmp = new EstoqueMpDAO();
        listaEstoqueAprovado = new ArrayList<EstoqueMP>();
        listaEstoqueAprovado = daoEmp.lista();
        
        
        txtNumLotePro.setDisable(true);
        dpInicio.setValue(null);
        dpTermino.setValue(null);
        estadoEdicao();
        carregaItensSaida();
    }

    @FXML
    private void btnCancelar(ActionEvent event) {//OK
        listaItensSaida=null;
       
        listaEstoqueAprovado = null;
        
        estadoOriginal();
    }
    @FXML
    private void btnMaisItens(ActionEvent event) {
     
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
        if(cbbLtMp.getSelectionModel().getSelectedIndex() < 0){
             msg+="Por favor, informe o numero do lote da Materia Prima\n";
             cbbMateriaPrima.requestFocus();
        }
        if(cbbMateriaPrima.getSelectionModel().getSelectedIndex() < 0)//verifica se o cbb esta marcado com a mp
        {
            msg+="Por favor, informe a matéria prima\n";
            cbbMateriaPrima.requestFocus();
        }
        MateriaPrima mp = (MateriaPrima)cbbMateriaPrima.getSelectionModel().getSelectedItem();
        EstoqueMP emp= (EstoqueMP)cbbLtMp.getSelectionModel().getSelectedItem();
        EstoqueMP auxEmp =null;
        ItensSaidaDAO daoIS = new ItensSaidaDAO(); 
        int saldoAprovado = 0;
        for(int i=0;i<listaEstoqueAprovado.size();i++){
            
            auxEmp = listaEstoqueAprovado.get(i);
            if(emp.getLote_mp().equals(auxEmp.getLote_mp()) && emp.getMateria_prima().equals(auxEmp.getMateria_prima()))
            saldoAprovado+=auxEmp.getQtde_aprovada();
        }
        if(qtde > saldoAprovado){
            
            msg+="Saldo Insuficente deste lote. Saldo atual do lote= "
                    + " "+emp.getLote_mp()+" Saldo= "+saldoAprovado+" unidades\n"; 
        }
        if (msg!=""){
           b.setContentText(msg); 
           b.showAndWait();
        }
        else{        
            
            MontagemLoteDAO daoLote = new MontagemLoteDAO();
            MontagemLote lote =null;
            ItensSaida itens=null;
            int lote_pro;
            try {                   
                     lote_pro =Integer.parseInt(txtNumLotePro.getText()); //o lote jáexiste

            } catch (Exception e) {             
                      lote_pro = 0;                
            }
            if(lote_pro==0){
                int auxnumero= daoLote.ultimoNumero();// busca o ultimo numero no banco para poder ter um lote para instanciar o lote
                lote = new MontagemLote(auxnumero);// tenho um lote fictcio
                itens = new ItensSaida(emp,lote,mp,qtde);    // criou um objeto itens para inserir na lista de itens 
            }
            else{
                lote = daoLote.busca(lote_pro); // acha o lote , significa que vou alterar
                itens = new ItensSaida(emp,lote,mp,qtde); // criou um objeto itens para inserir na lista de itens
            }
            a.setContentText("Confirma a inserção da matéria prima");
            if (a.showAndWait().get() == ButtonType.OK){   

               for(int i=0;i<listaEstoqueAprovado.size();i++){
                    auxEmp = listaEstoqueAprovado.get(i);
                    if(emp.getLote_mp().equals(auxEmp.getLote_mp()) && emp.getMateria_prima().equals(auxEmp.getMateria_prima())){ 
                        int x = listaEstoqueAprovado.get(i).getQtde_aprovada()-qtde;
                        listaEstoqueAprovado.get(i).setQtde_aprovada(x);
                        listaEstoqueAprovado.get(i).setQtde_montada(qtde);
                    }
                } 
                if (listaItensSaida.add(itens)){
                   b.setContentText("Inserido com Sucesso");
                   cbbMateriaPrima.requestFocus();
                }
            } 
            else{
                b.setContentText("Problemas ao Inserir");
            }  
            b.showAndWait();
             carregaItensSaida();
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
             
         
        EstoqueMP emp= IS.getLt_materia_prima();
        EstoqueMP auxEmp =null;
   
            
        for(int i=0;i<listaEstoqueAprovado.size();i++){
                    auxEmp = listaEstoqueAprovado.get(i);
                    if(emp.getLote_mp().equals(auxEmp.getLote_mp()) && emp.getMateria_prima().equals(auxEmp.getMateria_prima())){ 
                        int x = listaEstoqueAprovado.get(i).getQtde_aprovada()+IS.getQtde();
                        listaEstoqueAprovado.get(i).setQtde_aprovada(x);
                        listaEstoqueAprovado.get(i).setQtde_montada(IS.getQtde());
                    }
            
                }

            }   
        }
    }

    @FXML
    private void btnConfirma(ActionEvent event) {

        
        LocalDate inicio=dpInicio.getValue();//mudar para recuper certo a data
        LocalDate fim = dpTermino.getValue();
        LocalDate validade = null;//mudar depois no fxml
       
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert d = new Alert(Alert.AlertType.CONFIRMATION);
        String msg ="";
        int qtdeMP, qtdePro;
        try {
                qtdeMP =Integer.parseInt(txtQtdeMP.getText());// qtde da materia prima(composicao)informada
        } 
        catch (Exception e) {
                qtdeMP = 0;
        }
            
        try {
                qtdePro =Integer.parseInt(txtQtdePro.getText());// qtde da materia prima(composicao)informada
        } 
        catch (Exception e) {
                qtdePro = 0;
        }
        if(qtdePro<=0){
            msg+="Por favor, informe uma quantidade  de Produtos maior que zero\n";
            txtQtdePro.requestFocus();
        }
//        if(qtdeMP<=0){
//            msg+="Por favor, informe uma quantidade  de Materia Prima maior que zero\n";
//            txtQtdePro.requestFocus();
//        }
        if(cbbProduto.getSelectionModel().getSelectedIndex() < 0)//verifica se o cbb esta marcado com a mp
        {
            msg+="Por favor, Selecione um  Produto\n";
            cbbMateriaPrima.requestFocus();
        }
         if(inicio==null)//verifica se o cbb esta marcado com a mp
        {
            msg+="Por favor, Selecione uma Data Inicio da montagem\n";
      //     dpInicio.requestFocus();
        }
         if(inicio!=null && inicio.isAfter(fim))//verifica se o cbb esta marcado com a mp
        {
            msg+="ERRO!! Data de Inicio é depois da data término.\n";
        }
        if(fim==null)//verifica se o cbb esta marcado com a mp
        {
            msg+="Por favor, Selecione uma Data Final da montagem\n";
     //      dpTermino.requestFocus();
        }
         if(cbbColaborador.getSelectionModel().getSelectedIndex() < 0)//verifica se o cbb esta marcado com a mp
        {
            msg+="Por favor, Selecione um Colaborador\n";
            cbbColaborador.requestFocus();
        }
        if (msg!=""){
           b.setContentText(msg); 
           b.showAndWait();
        }
        else{

            Produto produto=null;
            MontagemLote lote =null;
            MontagemLoteDAO daoLote = new MontagemLoteDAO();
            Colaborador colaborador =null; 
            EstoqueMP emp=null;
            int estoque=0 ;

            int lote_pro;
            try {                   
                     lote_pro =Integer.parseInt(txtNumLotePro.getText()); //o lote jáexiste

            } catch (Exception e) {             
                      lote_pro = 0;                
            }
                    
            if ( lote_pro== 0) // novo cadastro lote  -----------OK
            {
                colaborador =cbbColaborador.getSelectionModel().getSelectedItem();
                produto = cbbProduto.getSelectionModel().getSelectedItem(); // pega no comb0obox o selecionadp
                inicio=dpInicio.getValue();
                fim = dpTermino.getValue();
      
                lote = new MontagemLote(lote_pro,produto,colaborador,inicio,fim,qtdePro,null); // cria novo lote
                            
                if (daoLote.insere(lote)) {  //grava banco o lote
                   
                    
                    ///itens
                    int ultimoNumero=daoLote.ultimoNumero(); // pega o ultimo numero de lote e coloca mais um    
                    MontagemLote auxLote = daoLote.busca(ultimoNumero);
                    List<ItensSaida> listaRealItensLote = new ArrayList<ItensSaida>();
                    for (ItensSaida is2 : listaItensSaida) {
                        ItensSaida is = new ItensSaida(is2.getLt_materia_prima(),auxLote,is2.getMp(),is2.getQtde());
                        listaRealItensLote.add(is);
                    }   //public ItensSaida(EstoqueMP lt_materia_prima, MontagemLote lote, MateriaPrima mp, int qtde)         
                    ItensSaidaDAO daoIS = new ItensSaidaDAO();
                    for (ItensSaida itens : listaRealItensLote) {
                             daoIS.insere(itens);
                    } 

                    ///estoque_mp
//                    EstoqueMpDAO daoEmp = new EstoqueMpDAO();
//                    List<EstoqueMP> listaexclusaoEMP= daoEmp.lista();
//                    for (EstoqueMP comp1 : listaexclusaoEMP) {
//                       
//                        daoEmp.exclui(comp1.getLote_mp(), comp1.getMateria_prima());
//                    }                   
//                    for (EstoqueMP comp2 : listaEstoqueAprovado) {
//                        
//                        daoEmp.insere(comp2);
//                     
//                    } 
                    listaEstoqueAprovado=null;
                    listaRealItensLote=null;
                    listaItensSaida=null;
                    a.setContentText("Gravado com Sucesso");
                } 
                else{
                    a.setContentText("Problemas ao Gravar");
                }
            } 
            else{//ok
                lote_pro = Integer.parseInt(txtNumLotePro.getText());
                colaborador =cbbColaborador.getSelectionModel().getSelectedItem();
                produto = cbbProduto.getSelectionModel().getSelectedItem();
                inicio=dpInicio.getValue();//mudar para recuper certo a data
                fim = dpTermino.getValue();
                lote = new MontagemLote(lote_pro,produto,colaborador,inicio,fim,qtdePro,null); // cria novo lote
                if (daoLote.altera(lote)) {

                    ItensSaidaDAO daoIS = new ItensSaidaDAO();
                    List<ItensSaida> listaexclusao= daoIS.buscaItensDoProduto(lote_pro);
                    for (ItensSaida composicao1 : listaexclusao) {
                        daoIS.excluiNoAlteraListaItens(composicao1);
                    }                   
                    for (ItensSaida composicao2 : listaItensSaida) {
                        daoIS.insere(composicao2);
                    }
                    
                    
                    
                    ///estoque_mp
                    EstoqueMpDAO daoEmp = new EstoqueMpDAO();
                    List<EstoqueMP> listaexclusaoEMP= daoEmp.lista();
                    for (EstoqueMP comp1 : listaexclusaoEMP) {
                        daoEmp.exclui(comp1.getLote_mp(), comp1.getMateria_prima());
                    }                   
                    for (EstoqueMP comp2 : listaEstoqueAprovado) { 
                        daoEmp.insere(comp2);
                    } 
             


                    listaEstoqueAprovado=null;
                    listaItensSaida=null;
                    listaexclusao=null;
                    a.setContentText("Alterado com Sucesso");
                } 
                else{  
                    a.setContentText("Problemas ao Alterar");
                }
            }
            a.showAndWait();
            estadoOriginal();   
        }  
    }

   @FXML
    private void btnApagar(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        if(tabelaLote.getSelectionModel().getSelectedIndex() < 0){
            a.setContentText("Por favor, seleciona um lote na tabela");
            a.showAndWait();
        }
        else{
            a.setContentText("Confirma a exclusão");
            if (a.showAndWait().get() == ButtonType.OK) {
               MontagemLoteDAO dal = new MontagemLoteDAO();
               MontagemLote lote = tabelaLote.getSelectionModel().getSelectedItem();
               dal.exclui(lote);
               //APAGA O LOTE, E TEM QUE APAGAR O ITENSSAIDA NO  BANCO E ATUALIZAR ESTOQUE
               ItensSaidaDAO daoIS = new ItensSaidaDAO();
               List<ItensSaida> listaitens = daoIS.buscaItensDoProduto(lote.getNumero());
               for (ItensSaida itens : listaitens) {
                        daoIS.exclui(itens);
               } 
               estadoOriginal();
                carregaLote("");
            }
        }          
        estadoOriginal();
    }
   
    
    @FXML
    private void btnBusca(ActionEvent event) {
     
        LocalDate inicio=dpBuscaInicio.getValue();//mudar para recuper certo a data
        LocalDate fim = dpBuscaFim.getValue();
            
        if(!txtBuscaLote.getText().isEmpty() && inicio==null && fim==null){//busca por palavras/codigo
                
                MontagemLoteDAO dal = new MontagemLoteDAO();
                List<MontagemLote> res = dal.pesquisa2( Integer.parseInt(txtBuscaLote.getText()) );
                ObservableList<MontagemLote> lote;
                lote = FXCollections.observableArrayList(res);
                tabelaLote.setItems(lote);
        }
        if(txtBuscaLote.getText().isEmpty() && inicio!=null && fim!=null){//busca pro data

                MontagemLoteDAO dal = new MontagemLoteDAO();
                List<MontagemLote> res = dal.pesquisaData( inicio,fim );
                ObservableList<MontagemLote> lote;
                lote = FXCollections.observableArrayList(res);
                tabelaLote.setItems(lote);

        }
        if(txtBuscaLote.getText().isEmpty() && inicio==null && fim==null){//mostra tudo
            
            MontagemLoteDAO dal = new MontagemLoteDAO();
            List<MontagemLote> res = dal.lista();
            ObservableList<MontagemLote> lote;
            lote = FXCollections.observableArrayList(res);
            tabelaLote.setItems(lote);
           // estadoOriginal();
        }   
        colLoteLT.setCellValueFactory(new PropertyValueFactory("numero"));
        colDataInicioLT.setCellValueFactory(new PropertyValueFactory("data_inicio"));
        colDataFimLT.setCellValueFactory(new PropertyValueFactory("data_fim")); 
        txtBuscaLote.setText("");
        dpBuscaFim.setValue(null);
        dpBuscaInicio.setValue(null);
        txtBuscaLote.requestFocus();
    } 
    private void carregaLotesMP(MateriaPrima mp) {//OK
        EstoqueMpDAO dal = new EstoqueMpDAO();
        List<EstoqueMP> res = dal.lista2(mp);   //arrumar
        ObservableList<EstoqueMP> lotes;
        lotes = FXCollections.observableArrayList(res);
        cbbLtMp.setItems(lotes);
    } 
    private void carregaMateriaPrima() {//OK
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
    private void carregaLote(String filtro) {//OK
        MontagemLoteDAO daoML = new MontagemLoteDAO();
        List<MontagemLote> res = daoML.lista();
        ObservableList<MontagemLote> lotes;
        lotes = FXCollections.observableArrayList(res);
        tabelaLote.setItems(lotes);
    }
    private void carregaItensSaida() { //ok
        List<ItensSaida> res = listaItensSaida;
        ObservableList<ItensSaida> is;
        is= FXCollections.observableArrayList(res);
        tabelaMP.setItems(is);
        colQtdeMp.setCellValueFactory(new PropertyValueFactory("qtde"));
        colMatPrimaMp.setCellValueFactory(new PropertyValueFactory("mp"));
        colLoteMp.setCellValueFactory(new PropertyValueFactory("lt_materia_prima"));//no mais itens aqui da certo
      
    }
    private void estadoOriginal() {
        PainelCentral.setDisable(true);
        PainelLateral.setDisable(false);
        carregaLote("");
        cbbMateriaPrima.getSelectionModel().select(null);
        cbbLtMp.getSelectionModel().select(null);
        cbbProduto.getSelectionModel().select(null);
        cbbColaborador.getSelectionModel().select(null);
        tabelaMP.getItems().clear();
        txtNumLotePro.setText("");
        txtQtdeMP.setText("");
        txtQtdePro.setText("");
        dpInicio.setValue(null);
        dpTermino.setValue(null);
        colLoteLT.setCellValueFactory(new PropertyValueFactory("numero"));
        colDataInicioLT.setCellValueFactory(new PropertyValueFactory("data_inicio"));
        colDataFimLT.setCellValueFactory(new PropertyValueFactory("data_fim"));
    }
    private void estadoEdicao() {     
        PainelCentral.setDisable(false);
        PainelLateral.setDisable(true);
        txtQtdeMP.setText("");
        carregaTabelasEdicaoCbb();
    }
    private void carregaTabelasEdicaoCbb(){
        carregaProduto("");
        carregaMateriaPrima();
        carregaColaborador();
    }
     
    @FXML
    private void evtProduto(ActionEvent event) throws IOException {
        
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/lote/Produto.fxml"));
        painelTotal.getChildren().setAll(a);  
        
    }

    @FXML
    private void evtPedido(ActionEvent event) {
        
      
    }

    @FXML
    private void evtRelatorio(ActionEvent event) {
          WebView webView = new WebView();
       webView.setPrefSize(750, 580);
       webView.getEngine().load("http://www.unoeste.br");
        
       PainelCentral.getChildren().add(webView);
    }

    
}
