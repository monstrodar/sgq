/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.fornecedor;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import red.model.colaborador.Colaborador;
import red.model.producao.aquisicao.Fornecedor;
import red.model.producao.aquisicao.ItensMP;
import red.model.producao.aquisicao.MateriaPrima;
import red.model.producao.aquisicao.PedidoMP;
import util.ColaboradorLogado;

/**
 * FXML Controller class
 *
 * @author 羽根川　翼
 */
public class PedidoFornecedorController implements Initializable {

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private VBox painelEsquerda;
    @FXML
    private Label labelClienteNome11;
    @FXML
    private Label labelClienteNome1;
    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteTelefone;
    @FXML
    private TableColumn<String, String> tableColumnItemDeVendaProduto;
    @FXML
    private TableColumn<Integer, Integer> tableColumnItemDeVendaQuantidade;
    @FXML
    private Button buttonAdicionar;
    @FXML
    private Label labelClienteNome111;
    @FXML
    private Label labelClienteNome12;
    @FXML
    private Label labelClienteNome2;
    @FXML
    private TextField txtPNumero;
    @FXML
    private TextField txtPFornecedor;
    @FXML
    private DatePicker datePickerPInicio;
    @FXML
    private DatePicker datePickerPFim;
    @FXML
    private TableView<PedidoMP> tbvPedido;
    @FXML
    private TableColumn<Integer, Integer> tbcNumero;
    @FXML
    private TableColumn<LocalDate, LocalDate> tbcDataP;
    @FXML
    private TableColumn<LocalDate, LocalDate> tbcDataPv;
    @FXML
    private DatePicker datePickerPrevisao;
    @FXML
    private ComboBox<MateriaPrima> comboBoxMP;
    @FXML
    private ComboBox<Fornecedor> comboBoxFornecedor;
    @FXML
    private DatePicker datePickerPedido;
    @FXML
    private Button btRemover;
    @FXML
    private Button btRemoveAll;
    @FXML
    private TextField txtNumero;
    @FXML
    private TableView<ItensMP> tbViewIItens;

    private ArrayList<ItensMP> listaI;
    private ObservableList<ItensMP> listaR;
    private ArrayList<ItensMP> listaA;
    private boolean isAlterar;
    @FXML
    private Button btBuscar;
    @FXML
    private Button btAlterar;
    @FXML
    private Button btApagar;
    @FXML
    private Button btNovo;
    @FXML
    private AnchorPane paneCadastro;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btGravar;
    @FXML
    private TextField txQtd;
    /**
     * Initializes the controller class.
     */
    
    /*
        モニカメモ：
        SQLはまだ追加していません。使用する大体のメッソドは追加しています。足りなかったら、ごめんなさい。SQL ほとんど追加しました。
        
        ブルーノメモ：
        各、リカバーの対象を確認せよ。
    
    Verificar o dizable do combobox fornecedor （チェック） e a lista(チェック)
        羽根川メモ：
        変更・消去 OK・１列消し・全消しの確認をお願いいたします。 
        コンボボックス（Fornecedor） 変更済み。リストの変更を確認しました。
    
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtNumero.setEditable(false);
        Original();
        tableColumnItemDeVendaProduto.setCellValueFactory(new PropertyValueFactory("nomeMP"));
        tableColumnItemDeVendaQuantidade.setCellValueFactory(new PropertyValueFactory("qtd"));
        
        tbcNumero.setCellValueFactory(new PropertyValueFactory("numero"));
        tbcDataP.setCellValueFactory(new PropertyValueFactory("dataPedido"));
        tbcDataPv.setCellValueFactory(new PropertyValueFactory("dataPrevisao"));
    }    

    @FXML
    private void evtCadFornecedor(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/fornecedor/CadastroFornecedor.fxml"));
        painelTotal.getChildren().setAll(a);
    }

    @FXML
    private void evtAvalFornecedor(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/fornecedor/AvaliacaoFornecedor2.fxml"));
        painelTotal.getChildren().setAll(a);
    }

    @FXML
    private void evtPedidoFor(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/fornecedor/PedidoFornecedor.fxml"));
        painelTotal.getChildren().setAll(a);
    }

    @FXML
    private void clkAlterar(ActionEvent event) {//多分OK <-- じゃねーよアホさっさと直せ       
        //通常の検索に加えて、マルチテーブルの検索
        ItensMP imp = new ItensMP();
//        listaA = new ArrayList<>();
        listaI = new ArrayList<>();
        isAlterar = true;
        PedidoMP pmp = tbvPedido.getSelectionModel().getSelectedItem();
        listaI = imp.carregar(pmp.getNumero());
        MateriaPrima mtp = new MateriaPrima();
        pmp = pmp.リカバー(listaI.get(0).getP().getNumero());
        for(int i = 0; i < listaI.size(); i++)
        {
            listaI.get(i).setP(pmp);
            mtp = mtp.リカバー(listaI.get(i).getMp().getCodigo());
            listaI.get(i).setMp(mtp);
        }
        ObservableList<ItensMP> modelo;
        modelo = FXCollections.observableArrayList(listaI);  
        tbViewIItens.setItems(modelo);
        txtNumero.setText(""+pmp.getNumero());
        comboBoxFornecedor.getSelectionModel().select(0);
        comboBoxFornecedor.getSelectionModel().select(pmp.getForn());
        ArrayList<MateriaPrima> mp = new MateriaPrima().serch(pmp.getForn().getCodigo());
        ObservableList<MateriaPrima> modelo2;
        modelo2 = FXCollections.observableArrayList(mp);
        comboBoxMP.setItems(modelo2);
        datePickerPedido.setValue(pmp.getDataPedido());
        datePickerPrevisao.setValue(pmp.getDataPrevisao());
        
    }

    @FXML
    private void alkApagar(ActionEvent event) {//編集・確認
        //確認２回
        //取り消しはitens_mp
        //その後pedidos_mp
        if(JOptionPane.showConfirmDialog(null, "Deseja excluir esse pedido?", "Atenção", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
        {
            if(JOptionPane.showConfirmDialog(null, "Apos a exclusao, nao ha como recuperar, deseja excluir mesmo?", "Atenção", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
            {
                PedidoMP pmp = tbvPedido.getSelectionModel().getSelectedItem();
                if(!pmp.チェック(pmp.getNumero()))
                {
                    new ItensMP().excluir(pmp.getNumero());
                    pmp.excluir(pmp.getNumero());
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "O pedido ja teve uma entrada!", "Erro", JOptionPane.ERROR);
                }
                Original();
                recarregar();
            }
        }
    }

    @FXML
    private void clkNovo(ActionEvent event) {//OK
        //通常　+　Fornecedorの追加
        listaI = new ArrayList<>();
        isAlterar = false;
        ArrayList<Fornecedor> fornL = new Fornecedor().carrega();
        ObservableList<Fornecedor> modelo;
        modelo = FXCollections.observableArrayList(fornL);  
        comboBoxFornecedor.setItems(modelo);
        comboBoxFornecedor.getSelectionModel().select(0);
        Editando();
        txQtd.setDisable(false);
        txQtd.setText("0");
        txtNumero.setText("0");
        txtNumero.setDisable(true);
        /*
         ArrayList<MateriaPrima> mp = new MateriaPrima().serch(fo.getCodigo());
        ObservableList<MateriaPrima> modelo;
        modelo = FXCollections.observableArrayList(mp);
        comboBoxMP.setItems(modelo);
        comboBoxMP.getSelectionModel().select(0);
        */
    }

    @FXML
    private void clickGravar(ActionEvent event) {
        // 3のリスト使用すること。　変更を行う場合のみ。　最初（新しく）の追加は通常。 ok. ３番目のリストはいらない。
        //1目は新しく追加したアイテム ok.
        //2目は消去（取りぬいた）を行ったアイテム。　＜－これはホールバック専用。 ok.
        //３目は変更を行ったアイテム。 <- これはリスト1の使用で可能です。ok.
        Colaborador cl = ColaboradorLogado.col;
        if(datePickerPedido.getValue() != null)
        {
            if(datePickerPrevisao.getValue() != null)
            {
                if(datePickerPedido.getValue().isBefore(datePickerPrevisao.getValue()))
                {
                    if(!isAlterar)
                    {
                        PedidoMP pmp = new PedidoMP(0, comboBoxFornecedor.getValue(), cl, datePickerPedido.getValue(), datePickerPrevisao.getValue());
                        if(pmp.gravar(pmp))
                        {
                            pmp = pmp.リカバー(pmp.Atualizar());
                            ItensMP imp = new ItensMP();
                            for (ItensMP listaI1 : listaI) {
                                imp.gravar(listaI1, pmp);
                            }
                            JOptionPane.showMessageDialog(null, "Gravado com sucesso", "Informacao", JOptionPane.INFORMATION_MESSAGE); 
                            Original();
                            Clear();  
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Erro ao Gravar!", "Erro", JOptionPane.ERROR_MESSAGE); 
                        }
                    }
                    else
                    {
                        //check Alteracao e gravar para depender do caso.
                        PedidoMP pmp = new PedidoMP(Integer.parseInt(txtNumero.getText()), comboBoxFornecedor.getValue(), cl, datePickerPedido.getValue(), datePickerPrevisao.getValue());
                        ItensMP imp = new ItensMP();
                        boolean certo = true;
                        for(int x = 0; x < listaI.size() && certo; x++)
                        {
                            if(imp.serchExist(imp, pmp)) 
                            {
                               certo = imp.alterar(imp, pmp);
                            }
                            else
                            {
                               certo = imp.gravar(imp, pmp);
                            }
                        }
                        if(certo)
                        {
                            JOptionPane.showMessageDialog(null, "Alterado com sucesso", "Informacao", JOptionPane.INFORMATION_MESSAGE);
                            Original();
                            Clear();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro imprevisto!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else
                {
                   JOptionPane.showMessageDialog(null, "A data do previsao deve ser depois de pedido", "Erro", JOptionPane.ERROR_MESSAGE); 
                   datePickerPrevisao.setFocusTraversable(true);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Informe a data de previsao", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Informe a data do pedido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    @FXML
    private void handleButtonAdicionar(ActionEvent event) {//多分OK
        //追加すること。なお、追加後はコンボボックスをロックすること。変更の場合、リストに追加。
        
        if(!txQtd.getText().trim().equals(""))
        {
            try
            {
                if(Integer.parseInt(txQtd.getText()) > 0)
                {
                    MateriaPrima mp = comboBoxMP.getValue();
                    ItensMP imp = new ItensMP(mp, new PedidoMP(0), Integer.parseInt(txQtd.getText()));
                    imp.setName();
                    boolean achou = false;
                    tbViewIItens.refresh();
                    if(!listaI.isEmpty())
                    {
                        for(int x = 0; x < listaI.size() && !achou; x++)
                        {
                            if(mp.getCodigo() == listaI.get(x).getMp().getCodigo())
                            {
                                int qtd = listaI.get(x).getQtd() + Integer.parseInt(txQtd.getText());
                                listaI.get(x).setQtd(qtd);
                                achou = true;
                            }
                        }
                    }
                    if(!achou)
                    {
                        listaI.add(imp);
                    }
                    
                    ObservableList<ItensMP> modelo;
                    modelo = FXCollections.observableArrayList(listaI);  
                    tbViewIItens.setItems(modelo);
                    comboBoxFornecedor.setDisable(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Quantidade deve ser maior que 0", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(HeadlessException | NumberFormatException i)
            {
                JOptionPane.showMessageDialog(null, "Digite numero", "Erro", JOptionPane.ERROR);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Informe a quantidade", "Informacao", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    @FXML
    private void clickCancelar(ActionEvent event) {
        Original();
        Clear();
    }
    
    @FXML
    private void clkBuscar(ActionEvent event) {
        //これだけで十分
        Alterar();
        PedidoMP pmp = new PedidoMP();
        List<PedidoMP> lista;

        if(!txtPNumero.getText().trim().equals("") && datePickerPInicio.getValue() != null && datePickerPFim.getValue() != null)
        {
            try {
                lista = pmp.serch(datePickerPInicio.getValue(), datePickerPFim.getValue(), Integer.parseInt(txtPNumero.getText()));
            } catch (NumberFormatException e) {
                lista = pmp.serch();
            }
        }
        else
        {
            if(datePickerPInicio.getValue() != null && datePickerPFim.getValue() != null)
            {
                lista = pmp.serch(datePickerPInicio.getValue(), datePickerPFim.getValue());
            }
            else
            {
                lista = pmp.serch();
            }
        }
        if(!lista.isEmpty())
        {
            ObservableList<PedidoMP> modelo;
            modelo = FXCollections.observableArrayList(lista);    
            tbvPedido.setItems(modelo);
            Alterar();
        }
    }
    

    @FXML
    private void evtClick(MouseEvent event) {
    }

    @FXML
    private void clickremove(ActionEvent event) {//多分OK
        //ライン一列消す、この時、確認すること。なお、空であればコンボボックスをアクティベート。変更の場合、リストに追加。
        if(JOptionPane.showConfirmDialog(null, "Deseja remover esse item?", "Atenção", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
        {
            if(isAlterar)
            {
                listaR.add((ItensMP) tbViewIItens.getSelectionModel().getSelectedItem());
                listaI.remove(tbViewIItens.getSelectionModel().getFocusedIndex());
                
            }
            if(tbViewIItens.getItems().isEmpty())
            {
                comboBoxFornecedor.setDisable(false);
            }
            tbViewIItens.refresh();          
            if(!listaI.isEmpty())
            {
                ObservableList<ItensMP> modelo;
                modelo = FXCollections.observableArrayList(listaI);  
                tbViewIItens.setItems(modelo);
            }
//            if(!listaA.isEmpty() && isAlterar)   
//            {
//                ObservableList<ItensMP> modelo;
//                modelo = FXCollections.observableArrayList(listaA);  
//                tbViewIItens.setItems(modelo);
//            }
        }
    }

    @FXML
    private void clickRemoveAllItens(ActionEvent event) {//多分OK
        //全部消す、この時、確認すること。なお、空であればコンボボックスをアクティベート。変更の場合、リストに追加。
        if(JOptionPane.showConfirmDialog(null, "Deseja remover todods os itens?", "Atenção", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
        {
            if(isAlterar)
            {
                listaR = tbViewIItens.getItems();
                listaI = new ArrayList<>();
            }
            tbViewIItens.refresh();
            comboBoxFornecedor.setDisable(false);
        }
    }

    private void evtHidden(Event event) {//OK
        //コンボボックスの変更を確認し、MPをコンボボックスに追加する。
        Fornecedor fo = comboBoxFornecedor.getValue();
        ArrayList<MateriaPrima> mp = new MateriaPrima().serch(fo.getCodigo());
        ObservableList<MateriaPrima> modelo;
        modelo = FXCollections.observableArrayList(mp);
        comboBoxMP.setItems(modelo);
        comboBoxMP.getSelectionModel().select(0);
    }

    private void Original()
    {
        btAlterar.setDisable(true);
        btApagar.setDisable(true);
        btBuscar.setDisable(false);
        btCancelar.setDisable(true);
        btGravar.setDisable(true);
        btNovo.setDisable(false);
        paneCadastro.setDisable(true);
        comboBoxFornecedor.setDisable(false);
    }
    
    private void Alterar()
    {
        btAlterar.setDisable(false);
        btApagar.setDisable(false);
        btBuscar.setDisable(false);
        btCancelar.setDisable(true);
        btGravar.setDisable(true);
        btNovo.setDisable(false);
        paneCadastro.setDisable(true);
    }
    
    private void Editando()
    {
        btAlterar.setDisable(true);
        btApagar.setDisable(true);
        btBuscar.setDisable(true);
        btCancelar.setDisable(false);
        btGravar.setDisable(false);
        btNovo.setDisable(true);
        paneCadastro.setDisable(false);
    }

    private void Clear()
    {
        txtNumero.setText("");
        datePickerPedido.setValue(null);
        datePickerPrevisao.setValue(null);
        comboBoxFornecedor.setItems(null);
        txQtd.setText("0");
        comboBoxMP.setItems(null);
        tbViewIItens.setItems(null);
        comboBoxFornecedor.setItems(null);
        listaI = new ArrayList<>();
        listaR = null;
        listaA = new ArrayList<>();
    }

    private void recarregar()
    {
        PedidoMP pmp = new PedidoMP();
        ArrayList<PedidoMP> lista = new ArrayList<>();
        lista = pmp.serch();
        if(!lista.isEmpty())
        {
            ObservableList<PedidoMP> modelo;
            modelo = FXCollections.observableArrayList(lista);    
            tbvPedido.setItems(modelo);
            Alterar();
        }
    }
    
}
