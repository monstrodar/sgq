/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.parametrizacao;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import red.dao.parametrizacao.ParametrizacaoDAO;
import red.dao.producao.aquisicao.MateriaPrimaDAO;
import red.dao.producao.lote.ComposicaoDAO;
import red.dao.producao.lote.ProdutoDAO;
import red.dao.util.CidadeDAO;
import red.dao.util.UFDAO;
import red.model.parametrizacao.Parametrizacao;
import red.model.producao.aquisicao.MateriaPrima;
import red.model.producao.lote.Composicao;
import red.model.producao.lote.Produto;
import red.model.util.Cidade;
import red.model.util.UF;

/**
 *
 * @author Daniel
 */
public class ParametrizacaoController implements Initializable{

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private TextField txtRazaoSocial;
    @FXML
    private TextField txtCnpj;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtRua;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtCep;
    @FXML
    private ComboBox<UF> cbbEstado;
    @FXML
    private ComboBox<Cidade> cbbCidade;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//       listaItensComposicao = new ArrayList<Composicao>();
//       listaItensComposicaoRetirado = new ArrayList<Composicao>();
//        estadoOriginal();
//        tableViewNomeProduto.setCellValueFactory(new PropertyValueFactory("nome"));
//     //   tableColumnItemDeProduto.setCellValueFactory(new PropertyValueFactory("nome"));
        estadoOriginal();
    }  
    @FXML
    private void evtCadColaborador(ActionEvent event) {
    }

    @FXML
    private void btnConfirmar(ActionEvent event) {
        
           Alert b = new Alert(Alert.AlertType.INFORMATION);
        String msg ="";
//        if(txtCodigo.getText().length()==0){
//             msg+="Por favor, informe o Código do produto\n";
//            txtCodigo.requestFocus();
//        }
        if(txtRazaoSocial.getText().length()==0){
             msg+="Por favor, informe a Razão Social da Empresa\n";
             txtRazaoSocial.requestFocus();
        }
         if(txtCnpj.getText().length()==0){
             msg+="Por favor, informe o CNPJ da Empresa\n";
             txtCnpj.requestFocus();
        }
        if(txtRua.getText().length()==0){
             msg+="Por favor, informe a Rua da Empresa\n";
             txtRua.requestFocus();
        }
        if(txtNumero.getText().length()==0){
             msg+="Por favor, informe o numero da empresa da Empresa na rua\n";
             txtNumero.requestFocus();
        }
         if(txtBairro.getText().length()==0){
             msg+="Por favor, informe o Bairro Empresa\n";
             txtBairro.requestFocus();
        }
          if(txtCep.getText().length()==0){
             msg+="Por favor, informe o CEP da Empresa\n";
             txtCep.requestFocus();
        }
         if(txtTelefone.getText().length()==0){
             msg+="Por favor, informe o telefone da Empresa\n";
             txtRazaoSocial.requestFocus();
        }
        if(txtEmail.getText().length()==0){
            msg+="Por favor, informe o Email da Empresa\n";
            txtEmail.requestFocus();
        }
        if(cbbCidade.getSelectionModel().getSelectedItem()==null){
            msg+="Por favor, informe a Cidade\n";
            cbbEstado.requestFocus();
        }
         if(cbbEstado.getSelectionModel().getSelectedItem()==null){
            msg+="Por favor, informe o Estado\n";
            cbbEstado.requestFocus();
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
            
            Parametrizacao p = new Parametrizacao(cod,txtRazaoSocial.getText(),
            txtCnpj.getText(),txtRua.getText(),Integer.parseInt(txtNumero.getText()),
            txtBairro.getText(),txtCep.getText(),txtEmail.getText(),txtTelefone.getText(),
            cbbCidade.getSelectionModel().getSelectedItem());
            
            ParametrizacaoDAO dal = new ParametrizacaoDAO();
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
    }

    @FXML
    private void btnAlterar(ActionEvent event) {
        
        ParametrizacaoDAO dao = new ParametrizacaoDAO();
        Parametrizacao p= dao.busca(1);
        
        txtCodigo.setText(""+p.getCodigo());
        txtBairro.setText(""+p.getBairro());
        txtCep.setText(""+p.getCep());
        txtCnpj.setText(""+p.getCnpj());
        txtEmail.setText(""+p.getEmail());
        txtNumero.setText(""+p.getNumero());
        txtRazaoSocial.setText(""+p.getRazao_social());
        txtRua.setText(""+p.getRua());
        txtTelefone.setText(""+p.getTelefone());
        
        CidadeDAO dal = new CidadeDAO();
        Cidade cidade= dal.busca(p.getCidade().getCodigo());
        List<Cidade> res = dal.get(p.getCidade().getCodigo());
        ObservableList<Cidade> mp;
        mp = FXCollections.observableArrayList(res);
        cbbCidade.setItems(mp);
        
    }

    @FXML
    private void btnNovo(ActionEvent event) {
    }
    private void estadoOriginal() {

        cbbCidade.getSelectionModel().select(0);
        cbbEstado.getSelectionModel().select(0);
        carregaEstado();
    }
    @FXML
    private void btnApagar(ActionEvent event) {
        
          Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão");
        if (a.showAndWait().get() == ButtonType.OK) {
           ParametrizacaoDAO dal = new ParametrizacaoDAO();
           Parametrizacao  p = dal.busca(1);
            dal.exclui(p);
        }
        
    }
    private void carregaCidade(int codigo) {
        CidadeDAO dal = new CidadeDAO();
        List<Cidade> res = dal.get(codigo);
        ObservableList<Cidade> mp;
        mp = FXCollections.observableArrayList(res);
        cbbCidade.setItems(mp);
    }
    private void carregaEstado() {
        UFDAO dal = new UFDAO();
        List<UF> res = dal.lista();
        ObservableList<UF> mp;
        mp = FXCollections.observableArrayList(res);
        cbbEstado.setItems(mp);
    }

    @FXML
    private void evtEstado(ActionEvent event) {
        if(cbbEstado.getSelectionModel().getSelectedItem()!=null){
            UF estado= cbbEstado.getSelectionModel().getSelectedItem();
            carregaCidade(estado.getCodigo());
        }       
    }
}
