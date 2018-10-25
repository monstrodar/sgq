/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import red.controller.home.Modulo;


/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class PrincipalProducaoController implements Initializable {

    private AnchorPane painelPrincipal;
    @FXML
    private AnchorPane painelCentral;
    @FXML
    private JFXComboBox<Modulo> cbbModulos;
    @FXML
    private AnchorPane painelTotal;
    @FXML
    private AnchorPane painelCentralB;
    @FXML
    private AnchorPane menuLateral;
    @FXML
    private AnchorPane painelModulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //  estadoOriginal();
//        AnchorPane a=null;
//        try {
//            a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/InicioProducao.fxml"));
//        } catch (IOException ex) {
//            Logger.getLogger(ProducaoController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        painelCentral.getChildren().setAll(a);
    }    

    @FXML
    private void clickInicio(ActionEvent event) throws IOException {
        
          AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/controller/home/Principal.fxml"));
        painelTotal.getChildren().setAll(a);
    }
    
    private void estadoOriginal() {
        
        
         
        ObservableList<Node> componentes = painelPrincipal.getChildren(); //â€limpaâ€ os componentes
        for (Node n : componentes) {
            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
            {
                ((TextInputControl) n).setText("");
            }
            if (n instanceof ComboBox) {
                ((ComboBox) n).getItems().clear();
            }
        }

        carregaModulos();
    }

    private void carregaModulos() {
        //ProprietarioDAL dal = new ProprietarioDAL();
        Modulo md = new Modulo(1,"PRODUCTION"); 
        List<Modulo> res = new ArrayList<>();//= dal.get("");
       res.add(md);
        ObservableList<Modulo> modulo;
        modulo = FXCollections.observableArrayList(res);
        cbbModulos.setItems(modulo);
    }

    @FXML
    private void acaoModulo(ActionEvent event) throws IOException {
        
//         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/InicioProducao.fxml"));
//        painelCentral.getChildren().setAll(a);
        
    }

  

    @FXML
    private void evtAquisicao(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/aquisicao/PrincipalAquisicao.fxml"));
        painelModulo.getChildren().setAll(a); 
    }


    @FXML
    private void evtLote(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/lote/PrincipalLote.fxml"));
        painelModulo.getChildren().setAll(a); 
    }

    @FXML
    private void evtRastreabilidade(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/rastreabilidade/PrincipalRastreabilidade.fxml"));
        painelModulo.getChildren().setAll(a); 
    }

    @FXML
    private void evtFornecedorPrincipal(ActionEvent event) throws IOException {
        
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/fornecedor/PrincipalFornecedor.fxml"));
        painelModulo.getChildren().setAll(a);
        
        
        
    }

    
}
