/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.parametrizacao;

import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import red.dao.parametrizacao.ParametrizacaoDAO;
import red.dao.util.CidadeDAO;
import red.dao.util.UFDAO;
import red.model.parametrizacao.Parametrizacao;
import red.model.util.Cidade;
import red.model.util.MaskFieldUtil;
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
    @FXML
    private Label menssagemInicial;
    @FXML
    private Button txtBtnCadastrar;
    @FXML
    private Button txtBtnAlterar;
    @FXML
    private Button txtBtnConfirma;
    @FXML
    private Button txtBtnCancelar;
    @FXML
    private Label menssagemFinal;
    @FXML
    private JFXCheckBox cbStatus;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        menssagemFinal.setText(""); 
        MaskFieldUtil.foneField(this.txtTelefone);
        MaskFieldUtil.cepField(this.txtCep);
        MaskFieldUtil.cnpjField(this.txtCnpj);
        
        estadoOriginal();
    }  
    @FXML
    private void evtCadColaborador(ActionEvent event) {
    }

    @FXML
    private void btnConfirmar(ActionEvent event) {
        
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        String msg ="";
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
         
          if(!MaskFieldUtil.isCNPJ(txtCnpj.getText()) &&  !cbStatus.isSelected()){
            msg+="O CNPJ não é Válido!\n";
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

            if (dal.altera(p)) {
                a.setContentText("Alterado com Sucesso");
            } else {
                a.setContentText("Problemas ao Alterar");
            }
            a.showAndWait();
            menssagemFinal.setText("Atualizado com sucesso! Sistema pronto para ser utilizar"); 
            menssagemInicial.setText("");
            estadoOriginal();
            
        } 
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        estadoOriginal();
        txtBtnCadastrar.setDisable(false);
    }
    
    @FXML
    private void btnCadastrar(ActionEvent event) {
        estadoOriginal();
        habilitaTudo();
        comboEstadoAlterado();  
        
        txtBtnCancelar.setDisable(false);
        txtBtnConfirma.setDisable(false);
        txtBtnCadastrar.setDisable(true);
    }
    @FXML
    private void btnAlterar(ActionEvent event) {
        estadoOriginal();
        habilitaTudo();
        comboEstadoAlterado();
        txtBtnCancelar.setDisable(false);
        txtBtnConfirma.setDisable(false);
        txtBtnAlterar.setDisable(true);
        
        
    }

    private void estadoOriginal() {
        
        ParametrizacaoDAO dao = new ParametrizacaoDAO();
        Parametrizacao p= dao.busca();
        
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
        UFDAO daoUf = new UFDAO();
        UF uf = daoUf.busca(p.getCidade().getUf().getCodigo());
        
        cbbCidade.setValue(cidade);
        cbbEstado.setValue(uf);  
      if(txtCnpj.getText().equalsIgnoreCase("00.000.000/0000-00")){
    //     if(txtCnpj.getText().equalsIgnoreCase("38.981.361/0001-39")){    
             menssagemInicial.setText("O Sistema ainda não foi parametrizado. Cadastre os dados da empresa.");
             txtBtnAlterar.setDisable(true);
             txtBtnConfirma.setDisable(true);
             txtBtnCancelar.setDisable(true);
        }
        else{
            menssagemInicial.setText("O Sistema já foi parametrizado. Clique em Alterar caso haja alterações."); 
            txtBtnCadastrar.setDisable(true);
            txtBtnConfirma.setDisable(true);
            txtBtnCancelar.setDisable(true);
        }
        desabilitaTudo();   
        
    }
    public void comboEstadoAlterado(){
        
        UFDAO daoUf = new UFDAO();
        CidadeDAO daoCid = new CidadeDAO();
        List<UF> res = daoUf.lista();
        List<Cidade> res2 = daoCid.get(0);
        ObservableList<UF> mp;
        mp = FXCollections.observableArrayList(res);
      
        cbbEstado.setItems(mp);
        cbbCidade.getSelectionModel().select(0); // 5  
    }
    public void desabilitaTudo(){
        txtCodigo.setDisable(true);
        txtBairro.setDisable(true);
        txtCep.setDisable(true);
        txtCnpj.setDisable(true);
        txtEmail.setDisable(true);
        txtNumero.setDisable(true);
        txtRazaoSocial.setDisable(true);
        txtRua.setDisable(true);
        txtTelefone.setDisable(true);
        cbbCidade.setDisable(true);
        cbbEstado.setDisable(true);
    }
    public void habilitaTudo(){
        txtCodigo.setDisable(false);
        txtBairro.setDisable(false);
        txtCep.setDisable(false);
        txtCnpj.setDisable(false);
        txtEmail.setDisable(false);
        txtNumero.setDisable(false);
        txtRazaoSocial.setDisable(false);
        txtRua.setDisable(false);
        txtTelefone.setDisable(false);
        cbbCidade.setDisable(false);
        cbbEstado.setDisable(false);
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

    @FXML
    private void btnCNPJ(ActionEvent event) {
        
        txtCnpj.setText("38.981.361/0001-39 ");
    }

}
