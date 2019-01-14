/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.aquisicao;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import red.dao.producao.aquisicao.ConfereMP;
import red.dao.producao.aquisicao.Conferencia;
import red.dao.producao.aquisicao.ItensEntrada;
import red.model.colaborador.Colaborador;
import red.model.producao.aquisicao.Entrada;
import red.model.producao.aquisicao.Fornecedor;
import red.model.producao.aquisicao.MateriaPrima;
import util.ColaboradorLogado;

/**
 * FXML Controller class
 *
 * 吉野　廉
 */
public class ConferenciaMateriaPrimaController implements Initializable {

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private VBox painelEsquerda;
    @FXML
    private Label labelClienteNome112;
    @FXML
    private Label labelClienteNome11;
    @FXML
    private Label labelClienteNome111;
    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteNome2;
    @FXML
    private JFXComboBox<MateriaPrima> cbpMp;
    @FXML
    private JFXTextField txPLote;
    @FXML
    private JFXButton btBusca;
    @FXML
    private Button btAlterar;
    @FXML
    private Button btApagar;
    @FXML
    private Button btNovo;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btConfirmar;
    @FXML
    private TextArea txMotivo;
    @FXML
    private JFXTextField txQtd;
    @FXML
    private JFXTextField txLote;
    @FXML
    private AnchorPane paneCadastro;
    @FXML
    private ComboBox<MateriaPrima> cbMP;
    @FXML
    private TableView<ConfereMP> tbvConferencia;
    @FXML
    private TableColumn<Integer, Integer> tbvcLote;
    @FXML
    private TableColumn<LocalDate, LocalDate> tbvcData;
    @FXML
    private JFXTextField txEntrada;
    @FXML
    private JFXTextField txDescarte;
    @FXML
    private TextField txCodigo;

    private Colaborador c = util.ColaboradorLogado.col;
    @FXML
    private TextField txCodigoConf;
    @FXML
    private Label labelClienteNome21;
    @FXML
    private DatePicker dpConferencia;
    @FXML
    private DatePicker dpValidade;
    @FXML
    private CheckBox chbStatus;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tbvcLote.setCellValueFactory(new PropertyValueFactory("lote"));
        tbvcData.setCellValueFactory(new PropertyValueFactory("data"));
        paneCadastro.setDisable(true);
        MateriaPrima mp = new MateriaPrima();
        ObservableList<MateriaPrima> modelo;
        modelo = FXCollections.observableArrayList(mp.serch());
        cbpMp.setItems(modelo);
        cbpMp.getSelectionModel().select(0);
        txCodigo.setVisible(false);
        txCodigoConf.setVisible(false);
    }    

    

   
    
//     private void evtPedido2(ActionEvent event) throws IOException {
//         
//        
//
//    }
//
//    @FXML
//    private void evtMatPrima(ActionEvent event) {
//    }
//
//    @FXML
//    private void evtRecebimentoMP(ActionEvent event) throws IOException {
//        
//    }
//
//    @FXML
//    private void evtConferenciaMP(ActionEvent event) throws IOException {
//         
//        
//    }

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
    private void clkAlterar(ActionEvent event) {
       
    }

    @FXML
    private void alkApagar(ActionEvent event) {
      
    }

    @FXML
    private void clkNovo(ActionEvent event) {
        Editando();
        txCodigo.setText("0");
        dpConferencia.setValue(LocalDate.now());
    }

    @FXML
    private void evtRelatorio(ActionEvent event) throws IOException {
          AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/aquisicao/RelPedido.fxml"));        painelTotal.getChildren().setAll(a);
   
    }

    @FXML
    private void actBusca(ActionEvent event) {
        リロード();
    }

    @FXML
    private void actCancelar(ActionEvent event) {
        limpar();
        Original();
    }

    @FXML
    private void actGravar(ActionEvent event)
    {
        if(!txEntrada.getText().trim().equals(""))
        {
            boolean ok = true;
            try {
                int descarte = Integer.parseInt(txDescarte.getText());
                int quantidade = Integer.parseInt(txQtd.getText());
                if(descarte >= 0 && descarte <= quantidade)
                {
                    if(descarte == 0 && txMotivo.getText().trim().equals(""))
                    {
                        if(txCodigo.getText().equals("0"))//Gravar
                        {
                            
                        }
                        else//Alterar
                        {
                            
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Precisa informar o motivo pelo fato de ter descarte!", "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "O valor do descarte é maior que a quantidde ou menor que 0!", "Erro", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) 
            {
                JOptionPane.showMessageDialog(null, "Inofrme a quantidade de descarte", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Inofrme o numero da entrada!", "Erro", JOptionPane.WARNING_MESSAGE);
        }
            
    }
    
    @FXML
    private void evtClick(MouseEvent event) {
        
        
    }

    private void evtHidden(Event event) //evento comBX da Materia prima.
    {
        ItensEntrada ie = new ItensEntrada();
        ie = ie.serch(Integer.parseInt(txEntrada.getText()), cbMP.getValue().getCodigo());
        txQtd.setText(""+ie.getQtd());
        txQtd.setDisable(true);
        txDescarte.setText("0");
    }
    
    @FXML
    private void evtEnter(KeyEvent event) {
        if("Tab".equals(event.getCode().getName()) || "Enter".equals(event.getCode().getName()))
        {
            ItensEntrada ie = new ItensEntrada();
            ArrayList<ItensEntrada> lista;
            lista = ie.ロード(Integer.parseInt(txEntrada.getText()));
            if(lista == null)
            {
                JOptionPane.showMessageDialog(null, "Nenhuma entarada encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                ArrayList<MateriaPrima> mp = new ArrayList<>();
                lista.forEach((lista1) -> 
                {
                    mp.add(new MateriaPrima().リカバー(lista1.getMp().getCodigo()));
                });
                ObservableList<MateriaPrima> modelo;
                modelo = FXCollections.observableArrayList(mp);
                cbMP.setItems(modelo);
                cbMP.getSelectionModel().select(0);
                cbMP.setFocusTraversable(true);
            }
        }
    }
    
    private void limpar()
    {
        txLote.setText("");
        txMotivo.setText("");
        txQtd.setText("");
        dpConferencia.setValue(null);
        dpValidade.setValue(null);
        cbMP.setValue(null);
    }
    
    private void Original()
    {
        btAlterar.setDisable(true);
        btApagar.setDisable(true);
        btBusca.setDisable(false);
        btCancelar.setDisable(true);
        btConfirmar.setDisable(true);
        btNovo.setDisable(false);
        paneCadastro.setDisable(true);
    }
    
    private void Alterar()
    {
        btAlterar.setDisable(false);
        btApagar.setDisable(false);
        btBusca.setDisable(false);
        btCancelar.setDisable(true);
        btConfirmar.setDisable(true);
        btNovo.setDisable(false);
        paneCadastro.setDisable(true);
    }
    
    private void Editando()
    {
        btAlterar.setDisable(true);
        btApagar.setDisable(true);
        btBusca.setDisable(true);
        btCancelar.setDisable(false);
        btConfirmar.setDisable(false);
        btNovo.setDisable(true);
        paneCadastro.setDisable(false);
    }

    private void リロード()
    {
        ConfereMP e = new ConfereMP();
        ArrayList<ConfereMP> lista;
        if(txPLote.getText().trim().equals(""))
        {
            lista = e.buscar(cbpMp.getSelectionModel().getSelectedItem());
        }
        else
        {
            int lote;
            try 
            {
                lote = Integer.parseInt(txPLote.getText());
            } catch (NumberFormatException x) 
            {
                lote = 0;
            }
            lista = e.buscar(cbpMp.getSelectionModel().getSelectedItem(), lote);
        }
        Alterar();
        ObservableList<ConfereMP> modelo;
        modelo = FXCollections.observableArrayList(lista);
        tbvConferencia.setItems(modelo);
    }        
}
