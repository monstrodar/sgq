/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.rastreabilidade;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import red.dao.producao.lote.MontagemLoteDAO;
import red.dao.producao.lote.ProdutoDAO;
import red.dao.producao.rastreabilidade.EntradaLoteDAO;
import red.dao.producao.rastreabilidade.MontagemCargaDAO;
import red.dao.util.RegiaoDAO;
import red.model.colaborador.Colaborador;
import red.model.producao.lote.MontagemLote;
import red.model.producao.lote.Produto;
import red.model.producao.rastreabilidade.EntradaLote;
import red.model.producao.rastreabilidade.MontagemCarga;
import red.model.util.Regiao;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class MontagemCargaController implements Initializable {

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private TableView<MontagemCarga> tabelaCarga;
     @FXML
    private TableColumn<String, ?> colCargaTB;
    @FXML
    private TableColumn<String, ?> colRegiaoTB;
    
    @FXML
    private DatePicker dpTermino;
    @FXML
    private DatePicker dpInicio;
    @FXML
    private JFXTextField txtNumeroCarga;
    @FXML
    private JFXComboBox<Colaborador> cbbColaborador;
    @FXML
    private JFXTextField txtQtdePro;
    @FXML
    private JFXTextField txtLotePro;
    @FXML
    private TableView<EntradaLote> tabelaItensCarga;
    @FXML
    private TableColumn<?, ?> colCodPro;
    @FXML
    private TableColumn<?, ?> colQtdePro;
    @FXML
    private TableColumn<String, ?> colNomePro;
    @FXML
    private TableColumn<String, ?> colLotePro;
    @FXML
    private JFXComboBox<Produto> cbbProduto;
    @FXML
    private AnchorPane PainelLateral;
    @FXML
    private AnchorPane PainelCentral;
    @FXML
    private JFXComboBox<Regiao> cbbRegiao;
    
    private List<EntradaLote> listaItensSaida=null;
    @FXML
    private JFXComboBox<Regiao> cbbBuscaRegiao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        estadoOriginal();
    }    

    @FXML
    private void evtMontarCarga(ActionEvent event) {
    }

    @FXML
    private void evtRegiao(ActionEvent event) {
    }


    @FXML
    private void btnBuscar(ActionEvent event) {
            if(txtPesquisa.getText().isEmpty()){
           // estadoOriginal();
            carregaCarga();
        }
        else{
            MontagemCargaDAO dal = new MontagemCargaDAO();
            List<MontagemCarga> res = dal.pesquisa2(Integer.parseInt(txtPesquisa.getText()));
            ObservableList<MontagemCarga> carga;
            carga = FXCollections.observableArrayList(res);
            tabelaCarga.setItems(carga);   
        }   
        txtPesquisa.requestFocus();
    }

    @FXML
    private void btnAlterar(ActionEvent event) {
        
          
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(tabelaCarga.getSelectionModel().getSelectedIndex() < 0){
   
            a.setContentText("Por favor, seleciona uma Carga na tabela");
            a.showAndWait();
        }
        else{
   
            MontagemCarga  carga = (MontagemCarga)tabelaCarga.getSelectionModel().getSelectedItem();//cria objeto lote seelecionado do click
            txtNumeroCarga.setText(""+carga.getCodigo());
            txtNumeroCarga.setDisable(true);
            dpInicio.setValue(carga.getData_inicio());
            dpTermino.setValue(carga.getData_fim());     //Tudo certo   
            cbbColaborador.setValue(carga.getColaborador());     
            cbbRegiao.setValue(carga.getRegiao());

            EntradaLoteDAO dao = new EntradaLoteDAO(); //classe dao 
            listaItensSaida = new ArrayList<EntradaLote>();//inicializa uma lista dos itens de MP do lote  
            listaItensSaida = dao.buscaItensDaCarga(carga.getCodigo());
        
            List<EntradaLote> res = listaItensSaida; ///dal.get(codigo);
            ObservableList<EntradaLote> produtos;
            produtos = FXCollections.observableArrayList(res);
            tabelaItensCarga.setItems(produtos);
            colCodPro.setCellValueFactory(new PropertyValueFactory("carga"));
            colNomePro.setCellValueFactory(new PropertyValueFactory("lote"));
            colQtdePro.setCellValueFactory(new PropertyValueFactory("qtde"));
            colLotePro.setCellValueFactory(new PropertyValueFactory("lote"));

           estadoEdicao();
           
        } 
       
    }

    

    @FXML
    private void btnApagar(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        if(tabelaCarga.getSelectionModel().getSelectedIndex() < 0){
    //        cbbMateriaPrima.requestFocus();
            a.setContentText("Por favor, seleciona uma carga na tabela");
            a.showAndWait();
        }
        else{
            a.setContentText("Confirma a exclusão");
            if (a.showAndWait().get() == ButtonType.OK) {
                MontagemCargaDAO dal = new MontagemCargaDAO();
                MontagemCarga carga = tabelaCarga.getSelectionModel().getSelectedItem();
                dal.exclui(carga);
                //APAGA a CARGA, E TEM QUE APAGAR O entradalote NO  BANCO E ATUALIZAR ESTOQUE
                EntradaLoteDAO dao = new EntradaLoteDAO();
                List<EntradaLote> listaitens = dao.buscaItensDaCarga(carga.getCodigo());
                for (EntradaLote itens : listaitens) {
                    dao.exclui(itens);
                } 
                carregaCarga();
            }
        }          
        estadoOriginal();
    }

    @FXML
    private void btnNovo(ActionEvent event) {
         
        listaItensSaida = new ArrayList<EntradaLote>();
        txtNumeroCarga.setDisable(true);
        estadoEdicao();
        carregaItensCarga();
        colCodPro.setCellValueFactory(new PropertyValueFactory("carga"));
        colNomePro.setCellValueFactory(new PropertyValueFactory("lote"));
        colQtdePro.setCellValueFactory(new PropertyValueFactory("qtde"));
        colLotePro.setCellValueFactory(new PropertyValueFactory("lote"));
        estadoEdicao();
        
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        listaItensSaida=null;
              
        estadoOriginal();
    }

    @FXML
    private void btnConfirma(ActionEvent event) {
        
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        String msg ="";
        if(cbbRegiao.getSelectionModel().getSelectedIndex() < 0)//verifica se o cbb esta marcado com a mp
        {
            msg+="Por favor, Selecione uma Região\n";
            cbbRegiao.requestFocus();
        }
        if(cbbColaborador.getSelectionModel().getSelectedIndex() < 0)//verifica se o cbb esta marcado com a mp
        {
            msg+="Por favor, Selecione um Colaborador\n";
            cbbColaborador.requestFocus();
        }
        if(dpInicio.getValue()==null)//verifica se o cbb esta marcado com a mp
        {
            msg+="Por favor, Selecione uma Data Inicio da montagem\n";
            dpInicio.requestFocus();
        }
        if(dpTermino.getValue()==null)//verifica se o cbb esta marcado com a mp
        {
            msg+="Por favor, Selecione uma Data Final da montagem\n";
            dpTermino.requestFocus();
        }
        
        if (msg!=""){
           b.setContentText(msg); 
           b.showAndWait();
        }
        else
        {
            MontagemCargaDAO daoCarga = new MontagemCargaDAO();
            int numero;
            try {                   
                numero = Integer.parseInt(txtNumeroCarga.getText()); //o lote jáexiste

            } catch (Exception e) {             
                numero = 0;                
            }
            if (numero == 0) // novo cadastro carga
            {
                
                int ultimoNumeroMaisUm=daoCarga.ultimoNumero(); // pega o ultimo numero de lote e coloca mais um
                Colaborador colaborador =cbbColaborador.getSelectionModel().getSelectedItem();
                LocalDate inicio=dpInicio.getValue();//mudar para recuper certo a data
                LocalDate fim = dpTermino.getValue();
                Regiao regiao = cbbRegiao.getSelectionModel().getSelectedItem();
                boolean status = true;
                MontagemCarga carga = new MontagemCarga(numero,regiao,colaborador,inicio,fim,status); // cria novo lote
                    
                if (daoCarga.insere(carga)) {  //grava banco a carga

                    MontagemCarga codAuxCarga = daoCarga.busca(ultimoNumeroMaisUm);
                    //altero a lista com o novo lote REAL
                    List<EntradaLote> listaRealItensLote = new ArrayList<EntradaLote>();
                    //EntradaLote(MontagemCarga carga, MontagemLote lote, int qtde)
                    for (EntradaLote item : listaItensSaida) {
                       EntradaLote is = new EntradaLote(codAuxCarga,item.getLote(),item.getQtde());
                       listaRealItensLote.add(is);
                    }                  
                    EntradaLoteDAO daoIS = new EntradaLoteDAO();
                    for (EntradaLote itens : listaRealItensLote) {
                             daoIS.insere(itens);
                    } 
                    listaRealItensLote=null;
                    listaItensSaida=null;
                    a.setContentText("Gravado com Sucesso");
                    } 
                    else 
                    {
                        a.setContentText("Problemas ao Gravar");
                    }
            } 
            else{
              
                int codCarga = Integer.parseInt(txtNumeroCarga.getText());
                Colaborador colaborador =cbbColaborador.getSelectionModel().getSelectedItem();
                LocalDate inicio=dpInicio.getValue();//mudar para recuper certo a data
                LocalDate fim = dpTermino.getValue();
                Regiao regiao = cbbRegiao.getSelectionModel().getSelectedItem();
                boolean status = true;
                
                MontagemCarga carga = new MontagemCarga(codCarga,regiao,colaborador,inicio,fim,status); // cria novo lote

                if (daoCarga.altera(carga)) {

                    EntradaLoteDAO daoIS = new EntradaLoteDAO();
                    List<EntradaLote> listaexclusao= daoIS.buscaItensDaCarga(numero);
                    for (EntradaLote composicao1 : listaexclusao) {
                        daoIS.exclui(composicao1);
                    }   
                    for (EntradaLote composicao2 : listaItensSaida) {
                        daoIS.insere(composicao2);
                    } 
                    
                    a.setContentText("Alterado com Sucesso");
                    listaItensSaida=null;

                    
                } 
                else
                {
                    a.setContentText("Problemas ao Alterar");
                }
            }
            a.showAndWait();
            estadoOriginal();   
        }   
    }

    @FXML
    private void btnMenosItens(ActionEvent event) {
        estadoEdicao();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        if(tabelaItensCarga.getSelectionModel().getSelectedIndex() < 0){
            cbbProduto.requestFocus();
            a.setContentText("Por favor, seleciona um item na tabela");
            a.showAndWait();
        }
        else{
            a.setContentText("Confirma a exclusão");
            if (a.showAndWait().get() == ButtonType.OK){   
                EntradaLote IS = tabelaItensCarga.getSelectionModel().getSelectedItem();
                listaItensSaida.remove(IS);
                carregaItensCarga();
            }   
        }
        
        
    }
    private MontagemLote verificaQualLoteDisponivel(int numeroLote, Produto produto){
         MontagemLote lote = null;
         MontagemLoteDAO  daoLote = new MontagemLoteDAO();
          lote =daoLote.buscaDisponivel(numeroLote,produto);
        return lote;
    }
    private boolean verificaSaldo(MontagemLote lote,int qtde){
       
        MontagemLoteDAO  daoLote = new MontagemLoteDAO();
        lote =daoLote.getSaldo(lote.getNumero());//erro
        int saldo =lote.getEstoque();
          if(saldo>=qtde){
              return true; 
          }else
          {
             return false;  
          }
    }

    @FXML
    private void btnMaisItens(ActionEvent event) {
        
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        MontagemCargaDAO daoCarga = new MontagemCargaDAO();          
        String msg ="";
        int qtdePro;
        try {
            qtdePro =Integer.parseInt(txtQtdePro.getText());// qtde da materia prima(composicao)informada
        } catch (Exception e) {
            qtdePro = 0;
        }
        if(qtdePro<=0){
            msg+="Por favor, informe uma maior que zero\n";
            txtQtdePro.requestFocus();
        }
        if(txtLotePro.getText().length()==0){
             msg+="Por favor, informe o numero do lote do Produto\n";
             txtLotePro.requestFocus();
        }
         int loteNumeroPro;
            try {
                loteNumeroPro =Integer.parseInt(txtLotePro.getText());// qtde da materia prima(composicao)informada
            } catch (Exception e) {
               
                loteNumeroPro = 0;
            }
        if(loteNumeroPro==0){
            msg+="Por favor, infomar um lote válido (inteiro)\n";
            txtQtdePro.requestFocus();
        }
        if(cbbProduto.getSelectionModel().getSelectedIndex() < 0)//verifica se o cbb esta marcado com a mp
        {
            msg+="Por favor, informe um Produto\n";
            cbbProduto.requestFocus();
        }
        
        if (msg!=""){
           b.setContentText(msg); 
           b.showAndWait();
        }
        else{        
            MontagemCarga carga =null;
            EntradaLote itens=null;
            MontagemLote lote =null;
            MontagemLoteDAO daoLote = new MontagemLoteDAO();
            Produto produto = (Produto) cbbProduto.getSelectionModel().getSelectedItem();//busca a materia prima
            int codigoCarga; //numero do lote carga
            try{
                codigoCarga = Integer.parseInt(txtNumeroCarga.getText());//verifica se o lote existe
            } 
            catch (Exception e){ 
                codigoCarga = 0;//é um lote novo 
            }
            lote = verificaQualLoteDisponivel(loteNumeroPro,produto);
            

            if( lote==null || verificaSaldo(lote,qtdePro)==false){
             
                if(lote==null)
                    a.setContentText("Esse lote não pertence a este produto");
                else
                    a.setContentText("Não tem em estoque esse produto procure outro lote.\n"
                        + "Saldo Atual = "+lote.getEstoque());
                
                a.showAndWait(); 
            }
            else{
           
                int s = lote.getEstoque()-qtdePro;
                a.setContentText("Seu Saldo Anterior era de  "+lote.getEstoque() +" unidades. \n"
                        + "Saldo atual = "+s+" unidades");
                a.showAndWait();  
               
                if(codigoCarga==0){//novo cert  ok
                    a.setContentText("Confirma a inserção de produto");
                    if (a.showAndWait().get() == ButtonType.OK){   

                        int auxnumero= daoCarga.ultimoNumero();// busca o ultimo numero no banco para poder ter um carga para instanciar a carga
                        carga = new MontagemCarga(auxnumero+1);// tenho uma carga fictcio
                        itens = new EntradaLote(carga,lote,qtdePro);    // criou um objeto itens para inserir na lista de prutoos entradalote
                        if (listaItensSaida.add(itens)){
                            b.setContentText("Inserido com Sucesso");
                            cbbProduto.requestFocus();
                            b.showAndWait();
                        }
                    } 
                    else{
                        b.setContentText("Problemas ao Inserir");
                        b.showAndWait();
                    }  
                    

                }
                else{        //ok                 
                    a.setContentText("Confirma a inserção de produto");
                    if (a.showAndWait().get() == ButtonType.OK){   

                         
                        carga = daoCarga.busca(codigoCarga); // acha o lote , significa que vou alterar
                        itens = new EntradaLote(carga,lote,qtdePro); // criou um objeto itens para inserir na lista de itens

                        if (listaItensSaida.add(itens)){
                            b.setContentText("Inserido com Sucesso");
                            cbbProduto.requestFocus();
                        }
                    } 
                    else{
                        b.setContentText("Problemas ao Inserir");
                    }  
                    b.showAndWait();  
                }
                carregaItensCarga();
                carregaProduto();
                estadoEdicao();//duvida
            }
        }    
    }
    
    private void estadoOriginal() {
        PainelCentral.setDisable(true);
        PainelLateral.setDisable(false);
        cbbProduto.getSelectionModel().select(null);
        cbbRegiao.getSelectionModel().select(null);
        cbbColaborador.getSelectionModel().select(null);
        dpInicio.setValue(null);
        dpTermino.setValue(null);
        txtLotePro.setText("");
        txtQtdePro.setText("");
        txtNumeroCarga.setText("");
        txtNumeroCarga.setDisable(true);
        tabelaItensCarga.getItems().clear();
        carregaCarga();
        carregaRegiao();
        colCargaTB.setCellValueFactory(new PropertyValueFactory("codigo"));
        colRegiaoTB.setCellValueFactory(new PropertyValueFactory("regiao"));
    }
    private void estadoEdicao() {     
        PainelCentral.setDisable(false);
        PainelLateral.setDisable(true);
        carregaTabelasEdicaoCbb();
    }
    private void carregaTabelasEdicaoCbb(){
        carregaProduto();
        carregaRegiao();
        carregaColaborador();
    }
    private void carregaRegiao() {
        RegiaoDAO dal = new RegiaoDAO();
        List<Regiao> res = dal.lista();
        ObservableList<Regiao> mp;
        mp = FXCollections.observableArrayList(res);
        cbbRegiao.setItems(mp);
        cbbBuscaRegiao.setItems(mp);
    }
    private void carregaProduto() {
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
     private void carregaCarga() {
        MontagemCargaDAO dal = new MontagemCargaDAO();
        List<MontagemCarga> res = dal.lista();
        ObservableList<MontagemCarga> carga;
        carga = FXCollections.observableArrayList(res);
        tabelaCarga.setItems(carga);
        
    }
      private void carregaItensCarga() {
      
        List<EntradaLote> res = listaItensSaida; ///dal.get(codigo);
        ObservableList<EntradaLote> produtos;
        produtos = FXCollections.observableArrayList(res);
        tabelaItensCarga.setItems(produtos);
        colCodPro.setCellValueFactory(new PropertyValueFactory("carga"));
        colNomePro.setCellValueFactory(new PropertyValueFactory("lote"));
        colQtdePro.setCellValueFactory(new PropertyValueFactory("qtde"));
        colLotePro.setCellValueFactory(new PropertyValueFactory("lote"));
    }

    @FXML
    private void evtCbbBuscaRegiao(ActionEvent event) {
        
        Regiao regiao = (Regiao)cbbBuscaRegiao.getSelectionModel().getSelectedItem();
        MontagemCargaDAO dal = new MontagemCargaDAO();

        if(regiao.getCodigo()==5)
             carregaCarga();
         //estadoOriginal();
        else
        {
            List<MontagemCarga> lista = dal.lista();
            List<MontagemCarga> lista2 = new ArrayList<MontagemCarga>();
            for (MontagemCarga carga : lista) {
                if(regiao.getCodigo()==carga.getRegiao().getCodigo())
                    lista2.add(carga);    
            }
            List<MontagemCarga> res = dal.pesquisa3(regiao.getCodigo());
            ObservableList<MontagemCarga> carga;
            carga = FXCollections.observableArrayList(res);
            tabelaCarga.setItems(carga);     
        }    
    }
      
}
