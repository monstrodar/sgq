/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.aquisicao;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.JOptionPane;
import red.model.colaborador.Colaborador;
import red.model.producao.aquisicao.MateriaPrima;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author Daniel
 * @author 羽根川　翼
 */
public class MateriaPrimaController implements Initializable {

    @FXML
    private AnchorPane PainelLateral;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private JFXCheckBox cbStatusBuscaAtivo;
    @FXML
    private JFXCheckBox cbStatusBuscaInativo;
    @FXML
    private TableView<MateriaPrima> tabela;
    @FXML
    private TableColumn<String, String> tableViewNomeMP;
    @FXML
    private AnchorPane PainelCentral;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXCheckBox cbStatus;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private AnchorPane painelTotal;
    
    private int novo;
    @FXML
    private Button btAlterar;
    @FXML
    private Button btExcluir;
    @FXML
    private Button btNovo;
    @FXML
    private Button btGravar;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewNomeMP.setCellValueFactory(new PropertyValueFactory("nome"));
        Original();
    }    

    @FXML
    private void evtPedido(ActionEvent event) throws IOException {
             AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/aquisicao/Pedido.fxml"));        painelTotal.getChildren().setAll(a);
  
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
    private void evtRelatorio(ActionEvent event) throws IOException {
           AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/aquisicao/RelPedido.fxml"));        painelTotal.getChildren().setAll(a);
   
    }

    @FXML
    private void btnBuscar(ActionEvent event) { //check PLS
        ArrayList<MateriaPrima> lista;
        if(txtPesquisa.getText().equals(""))
         lista = new MateriaPrima().serch();
        else
         lista = new MateriaPrima().Busca(txtPesquisa.getText());
        
        if(lista != null)
        {
            ObservableList<MateriaPrima> modelo;
            modelo = FXCollections.observableArrayList(lista);    
            tabela.setItems(modelo);
            Alterar();
        }
    }

    @FXML
    private void clkAtivoBusca(ActionEvent event) {//check PLS
        if(cbStatusBuscaInativo.isSelected())
        {
            cbStatusBuscaInativo.setSelected(false);
        }
        ArrayList<MateriaPrima> lista;
        if(txtPesquisa.getText().equals(""))
         lista = new MateriaPrima().BuscaComStatus(true, "");
        else
         lista = new MateriaPrima().BuscaComStatus(true, "and mp.mp_nome = "+txtPesquisa.getText());
        
        if(lista != null)
        {
            ObservableList<MateriaPrima> modelo;
            modelo = FXCollections.observableArrayList(lista);    
            tabela.setItems(modelo);
            Alterar();
        }
    }

    @FXML
    private void clkInativoBusca(ActionEvent event) {//check PLS
        if(cbStatusBuscaAtivo.isSelected())
        {
            cbStatusBuscaAtivo.setSelected(false);
        }
        ArrayList<MateriaPrima> lista;
        if(txtPesquisa.getText().equals(""))
         lista = new MateriaPrima().BuscaComStatus(false, "");
        else
         lista = new MateriaPrima().BuscaComStatus(false, "and mp.mp_nome = "+txtPesquisa.getText());
        
        if(lista != null)
        {
            ObservableList<MateriaPrima> modelo;
            modelo = FXCollections.observableArrayList(lista);    
            tabela.setItems(modelo);
            Alterar();
        }
    }

    @FXML
    private void btnAlterar(ActionEvent event) {//novo = 1～…　で確認。  //check PLS
        MateriaPrima mp = (MateriaPrima) tabela.getSelectionModel().getSelectedItem();
        txtNome.setText(mp.getNome());
        if(mp.isStatus())
        {
            cbStatus.setSelected(true);
        }
        else
        {
            cbStatus.setSelected(false);
        }
        novo = mp.getCodigo();
        txtCodigo.setText(""+novo);
        Editando();
    }

    @FXML
    private void btnApagar(ActionEvent event) { //OK
        MateriaPrima mp = (MateriaPrima) tabela.getSelectionModel().getSelectedItem();
        if(JOptionPane.showConfirmDialog(null, "Confirma a Exclusao da materia prima: "+ mp.getNome(), "Warming", ConfirmationCallback.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
        {
            if(!mp.excluir(mp.getCodigo()))
            {
                JOptionPane.showMessageDialog(null, "Erro ao excluir!!", "ERRO CRITICO", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Exclusão concluida!", "Informação", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    private void btnNovo(ActionEvent event) {  ///OK
        novo = 0;
        txtCodigo.setText("0");
        Editando();
    }

    @FXML
    private void btnConfirma(ActionEvent event) {
        ///保存     OK
        if(!txtNome.getText().trim().equals(""))
        {
            MateriaPrima mp = new MateriaPrima(novo, txtNome.getText(), cbStatus.isSelected());
            if(mp.getCodigo() == 0)
            {
                if(!mp.gravar(mp))
                {
                    JOptionPane.showMessageDialog(null, "Erro ao gravar!!", "ERRO CRITICO", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    limpar();
                }
            }
            else
            {
                if(!mp.alterar(mp))
                {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar!!", "ERRO CRITICO", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    limpar();
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Informe o nome da materia prima!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) { //OK
        limpar();
    }
    
    private void limpar()
    {
        txtCodigo.setText("");
        txtNome.setText("");
        cbStatus.setSelected(false);
        novo = 0;
        Original();
    }
    

    private void Original()
    {
        btAlterar.setDisable(true);
        btExcluir.setDisable(true);
        btBuscar.setDisable(false);
        btCancelar.setDisable(true);
        btGravar.setDisable(true);
        btNovo.setDisable(false);
    }
    
    private void Alterar()
    {
        btAlterar.setDisable(false);
        btExcluir.setDisable(false);
        btBuscar.setDisable(false);
        btCancelar.setDisable(true);
        btGravar.setDisable(true);
        btNovo.setDisable(false);
    }
    
    private void Editando()
    {
        btAlterar.setDisable(true);
        btExcluir.setDisable(true);
        btBuscar.setDisable(true);
        btCancelar.setDisable(false);
        btGravar.setDisable(false);
        btNovo.setDisable(true);
    }    
    
}
