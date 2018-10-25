/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.lote;

import red.controller.producao.fornecedor.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class PrincipalLoteController implements Initializable {

    @FXML
    private AnchorPane painelTotal;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtLote(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/lote/MontagemLote.fxml"));
        painelTotal.getChildren().setAll(a);
        
    }

    @FXML
    private void evtProduto(ActionEvent event) throws IOException {
       AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/lote/Produto.fxml"));
        painelTotal.getChildren().setAll(a);  
        
    }


    
    
}
