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
import javafx.scene.control.Button;
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
 * 川波
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
    private DatePicker datePickerVendaData;
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
    private void evtMatPrima(ActionEvent event) {
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
        Editando();
        ConfereMP cmp = tbvConferencia.getSelectionModel().getSelectedItem();
        cmp = cmp.リカバー(cmp.getNumero());
        txCodigo.setText(cmp.getNumero()+"");
        datePickerVendaData.setValue(cmp.getData());
        Conferencia c = new Conferencia();
        c = c.buscar(cmp.getNumero());
        txQtd.setText(""+c.getQtd());
        txDescarte.setText(""+c.getDescarte());
        txLote.setText(""+c.getLote());
        txCodigoConf.setText(""+c.getCodigo());
        ItensEntrada ie = new ItensEntrada();
        ArrayList<ItensEntrada> lista;
        lista = ie.ロード(Integer.parseInt(txEntrada.getText()));
        ArrayList<MateriaPrima> mp = new ArrayList<>();
        lista.forEach((lista1) -> 
        {
            mp.add(new MateriaPrima().リカバー(lista1.getMp().getCodigo()));
        });
        ObservableList<MateriaPrima> modelo;
        modelo = FXCollections.observableArrayList(mp);
        cbMP.setItems(modelo);
        cbMP.getSelectionModel().select(0);
    }

    @FXML
    private void alkApagar(ActionEvent event) {
        if(JOptionPane.showConfirmDialog(null, "Deseja excluir essa conferencia?", "Atenção", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
        {
            ConfereMP cmp = tbvConferencia.getSelectionModel().getSelectedItem();
            cmp.excluir(cmp.getNumero());
            new Conferencia().excluir(cmp.getNumero());
            リロード();
        }
        Alterar();
    }

    @FXML
    private void clkNovo(ActionEvent event) {
        Editando();
        txCodigo.setText("0");
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
    private void actGravar(ActionEvent event) {
        if(!txLote.getText().trim().equals(""))
        {
            if(!txQtd.getText().trim().equals(""))
            {
                try 
                {
                   int qtddescarte = 0;
                   qtddescarte = Integer.parseInt(txDescarte.getText());
                   if(qtddescarte >= 0)
                   {
                       String motivo = "";
                       if(qtddescarte == 0)
                       {
                           motivo += "Nao houve defeito";
                       }
                       else
                       {
                           if(!txMotivo.getText().trim().equals(""))
                           {
                               motivo = txMotivo.getText();
                           }
                           else
                           {
                               JOptionPane.showMessageDialog(null, "Informe o motivo", "Erro", JOptionPane.ERROR_MESSAGE);
                           }
                       }
                       if(!motivo.equals(""))
                       {
                           if(datePickerVendaData.getValue() != null)
                           {
                               if(qtddescarte <= Integer.parseInt(txQtd.getText()) )//ストックに変更せよ！！　確認済み
                               {                                                //int numero, Colaborador c, Entrada e, LocalDate data
                                   ConfereMP cmp = new ConfereMP(Integer.parseInt(txCodigo.getText()), c, new Entrada(Integer.parseInt(txEntrada.getText())), datePickerVendaData.getValue());
                                   MateriaPrima mp = cbMP.getSelectionModel().getSelectedItem();
                                   Entrada e = new Entrada().busca(Integer.parseInt(txEntrada.getText()));
                                   if(datePickerVendaData.getValue().isAfter(e.getData()))
                                   {
                                       if(txCodigo.getText().equals("0"))//コード確認、0　－＞保存；　1　＞＝　変更；　
                                        {
                                            cmp.gravar(cmp);
                                            cmp.setNumero(cmp.turningNumber()); 
                                            Conferencia c = new Conferencia(0, cmp, Integer.parseInt(txQtd.getText()), Integer.parseInt(txLote.getText()), 0, qtddescarte, motivo, cbMP.getSelectionModel().getSelectedItem());
                                            c.gravar(c);
                                            //Atualizar estoque....
                                            limpar();
                                            Original();
                                        }
                                        else
                                        {
                                            cmp.alterar(cmp);
                                            Conferencia c = new Conferencia(Integer.parseInt(txCodigoConf.getText()), cmp, Integer.parseInt(txQtd.getText()), Integer.parseInt(txLote.getText()), 0, qtddescarte, motivo, cbMP.getSelectionModel().getSelectedItem());
                                            c.alterar(c);
                                            limpar();
                                            Original();
                                        }
                                   }
                                   else
                                   {
                                       JOptionPane.showMessageDialog(null, "A data inferior a data de conferencia!", "Erro", JOptionPane.ERROR_MESSAGE); 
                                   }
                                   
                               }
                               else
                               {
                                   JOptionPane.showMessageDialog(null, "A quantidade deve ser menor ou igual a estoque!", "Erro", JOptionPane.ERROR_MESSAGE); //ストック数を追加。
                               }
                           }
                           else
                           {
                               JOptionPane.showMessageDialog(null, "Informe a data", "Erro", JOptionPane.ERROR_MESSAGE);
                           }
                       }//else いらない
                   }
                   else
                   {
                        JOptionPane.showMessageDialog(null, "Informe a quantidade maior ou igual a 0", "Erro", JOptionPane.ERROR_MESSAGE);
                   }
                }
                catch (NumberFormatException e) 
                {
                    JOptionPane.showMessageDialog(null, "Informe a quantidade (Numero)", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Informe a quantidade", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Informe o numero do Lote", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @FXML
    private void evtClick(MouseEvent event) {
        
    }

    @FXML
    private void evtHidden(Event event) 
    {
        ItensEntrada ie = new ItensEntrada();
        ie = ie.serch(Integer.parseInt(txEntrada.getText()), cbMP.getValue().getCodigo());
        txQtd.setText(""+ie.getQtd());
        txDescarte.setText("0");
    }
    
    @FXML
    private void evtEnter(KeyEvent event) {
        if("Tab".equals(event.getCode().getName()))
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
            }
        }
    }
    
    private void limpar()
    {
        txLote.setText("");
        txMotivo.setText("");
        txQtd.setText("");
        datePickerVendaData.setValue(null);
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
