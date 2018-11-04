package red.controller.producao.lote;


import com.jfoenix.controls.JFXCheckBox;



/**
 * FXML Controller class
 *
 * @author Daniel
 */

import com.jfoenix.controls.JFXComboBox;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        estadoOriginal();
        
        tableViewNomeProduto.setCellValueFactory(new PropertyValueFactory("nome"));
    }  
    
    @FXML
    private void evtLote(ActionEvent event) throws IOException {
        
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/lote/MontagemLote.fxml"));
        painelTotal.getChildren().setAll(a);
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
        
        if(txtPesquisa.getText()==""){
            carregaTabela("");
        }
        else{
            ProdutoDAO dal = new ProdutoDAO();
            List<Produto> res = dal.pesquisa(txtPesquisa.getText().toUpperCase());
            ObservableList<Produto> produto;
            produto = FXCollections.observableArrayList(res);
            tabela.setItems(produto);   
        }   
        txtPesquisa.requestFocus();
   } 

    @FXML
    private void btnAlterar(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(tabela.getSelectionModel().getSelectedIndex() < 0){
            cbbMateriaPrima.requestFocus();
            a.setContentText("Por favor, seleciona um produto na tabela");
            a.showAndWait();
        }
        else{
            listaItensComposicao = new ArrayList<Composicao>();
            Produto p = (Produto)tabela.getSelectionModel().getSelectedItem();
            txtCodigo.setText(""+p.getCodigo());
            txtNome.setText(""+p.getNome());
            txtDescricao.setText(""+p.getDescricao());;
            cbStatus.setSelected(p.isStatus());   

            estadoEdicao();//ok carr txt e chebo, e cbbmp
            ComposicaoDAO dao = new ComposicaoDAO();
            listaItensComposicao = dao.get(p.getCodigo());
            carregaComposicao();//listaItensComposicao 
            tableColumnItemDeProduto.setCellValueFactory(new PropertyValueFactory("materia_prima"));
            tableColumnItemQuantidade.setCellValueFactory(new PropertyValueFactory("qtde"));   
        } 
    }
    
    @FXML
    private void btnApagar(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        if(tabela.getSelectionModel().getSelectedIndex() < 0){
            cbbMateriaPrima.requestFocus();
            a.setContentText("Por favor, seleciona um produto na tabela");
            a.showAndWait();
        }
        else{
            a.setContentText("Confirma a exclusão");
            if (a.showAndWait().get() == ButtonType.OK) {
               ProdutoDAO dal = new ProdutoDAO();
               Produto p = tabela.getSelectionModel().getSelectedItem();
               dal.exclui(p);
               //APAGA A LISTA DE COMPOSICAO DO PRODUTO NO BANCO
               ComposicaoDAO daoComp = new ComposicaoDAO();
               List<Composicao> listaComp = daoComp.get(p.getCodigo());
               for (Composicao composicao : listaComp) {
                        daoComp.exclui(composicao);
               } 
               carregaTabela("");
            }
        }          
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        listaItensComposicao = new ArrayList<Composicao>();
        txtCodigo.setDisable(true);
        carregaComposicao();
        tableColumnItemDeProduto.setCellValueFactory(new PropertyValueFactory("materia_prima"));
        tableColumnItemQuantidade.setCellValueFactory(new PropertyValueFactory("qtde"));
        estadoEdicao();
    }


    @FXML
    private void btnConfirma(ActionEvent event) {
         
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert d = new Alert(Alert.AlertType.CONFIRMATION);
        String msg ="";
        if(txtNome.getText().length()==0){
             msg+="Por favor, informe o Nome do produto\n";
             txtNome.requestFocus();
        }
        if(txtDescricao.getText().length()==0){
            msg+="Por favor, informe a Descrição do produto\n";
            txtDescricao.requestFocus();
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
            Produto p = new Produto(cod,txtNome.getText(),txtDescricao.getText(),cbStatus.isSelected());
            ProdutoDAO dal = new ProdutoDAO();
            
            Alert j = new Alert(Alert.AlertType.CONFIRMATION);
            if(!cbStatus.isSelected() || cbStatus.equals(null)){
                cbStatus.requestFocus();
                j.setContentText("Tem certeza que deseje que o produto fique inativo. Verifique o Status");
            }
            else
                 j.setContentText("Confirma a Gravação ?");
                
            
            if (j.showAndWait().get() != ButtonType.OK)
                    
                estadoEdicao();
            else
            {

                if (p.getCodigo() == 0) // novo cadastro
                {
                    int codUltimoIdMaisUm=dal.ultimoId();
                    if (dal.insere(p)) {
                       Produto auxPro = dal.busca(codUltimoIdMaisUm);

                          //altero a lista com o novo produto REAL
                       List<Composicao> listaRealItensProduto = new ArrayList<Composicao>();
                       for (Composicao comp2 : listaItensComposicao) {
                           Composicao cb = new Composicao(comp2.getMateria_prima(),auxPro,comp2.getQtde());
                           listaRealItensProduto.add(cb);
                       }                  
                        ComposicaoDAO daoComp = new ComposicaoDAO();
                           for (Composicao composicao : listaRealItensProduto) {
                            daoComp.insere(composicao);
                        } 
                        listaRealItensProduto=null;
                        listaItensComposicao=null;
                        a.setContentText("Gravado com Sucesso");
                    } else {
                        a.setContentText("Problemas ao Gravar");
                    }
                } 
                else{
                    if (dal.altera(p)) {

                        ComposicaoDAO daoComp = new ComposicaoDAO();
                        List<Composicao> listaexclusao= daoComp.get(cod);
                        for (Composicao composicao1 : listaexclusao) {
                            daoComp.exclui(composicao1);
                        }   
                        for (Composicao composicao2 : listaItensComposicao) {
                            daoComp.insere(composicao2);
                        } 
                        listaItensComposicao=null;

                        a.setContentText("Alterado com Sucesso");
                    } 
                    else {
                        a.setContentText("Problemas ao Alterar");
                    }
                }
                a.showAndWait();
                estadoOriginal();   
            }  
        } 
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        listaItensComposicao=null;
        estadoOriginal();
    }
    
    private void carregaTabela(String filtro) {
        ProdutoDAO dal = new ProdutoDAO();
        List<Produto> res = dal.lista();
        ObservableList<Produto> produto;
        produto = FXCollections.observableArrayList(res);
        tabela.setItems(produto);
    }
    
    private void carregaComposicao() {
        List<Composicao> res = listaItensComposicao;//dal.get(codigo);
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
        txtQtde.setText("");
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
        txtCodigo.setDisable(true);
        txtNome.requestFocus();
        carregaMateriaPrima();
        txtQtde.setText("");
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
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        String msg ="";
         int qtde;
            try {
                qtde =Integer.parseInt(txtQtde.getText());// qtde da materia prima(composicao)informada
            } catch (Exception e) {
                qtde = 0;
            }
        if(qtde<=0){
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
        else
        {        
            Produto pro =null;
            Composicao comp=null;
            int cod;
            try
            {
                cod = Integer.parseInt(txtCodigo.getText());//codigo do produto caso não seja novo
            } 
            catch (Exception e) 
            {
                cod = 0;//produto novo
            }
            MateriaPrima mp = (MateriaPrima)cbbMateriaPrima.getSelectionModel().getSelectedItem();//busca a materia prima
            ProdutoDAO daoPro = new ProdutoDAO();          
            if(cod==0)
            {
                int auxcod= daoPro.ultimoId();// busca o ultimo ID no banco para poder ter um produto para instanciar o produto
                pro = new Produto(auxcod+1);
                comp = new Composicao(mp,pro,qtde);   
            }
            else
            {
                pro = daoPro.busca(cod); // acha o produto
                comp = new Composicao(mp,pro,qtde);
            }
            a.setContentText("Confirma a inserção da matéria prima");

            if (a.showAndWait().get() == ButtonType.OK){   
//
              if (listaItensComposicao.add(comp)){
                   b.setContentText("Inserido com Sucesso");
                   cbbMateriaPrima.requestFocus();
                }
            } 
            else{
                b.setContentText("Problemas ao Inserir");
            }  
            b.showAndWait();
         //   a.showAndWait();
            carregaComposicao();
        } 
    }

    @FXML
    private void btnMenosItens(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        if(tableViewItensDeProduto.getSelectionModel().getSelectedIndex() < 0){
            cbbMateriaPrima.requestFocus();
            a.setContentText("Por favor, seleciona um item na tabela");
            a.showAndWait();
        }
        else{
            a.setContentText("Confirma a exclusão");
            if (a.showAndWait().get() == ButtonType.OK){   
                Composicao p = tableViewItensDeProduto.getSelectionModel().getSelectedItem();
                listaItensComposicao.remove(p);
                carregaComposicao();
            }   
        }
    }
    
    @FXML
    private void evtProduto(ActionEvent event) {
    }

    @FXML
    private void evtRelatorio(ActionEvent event) {
    }
            

    
}
