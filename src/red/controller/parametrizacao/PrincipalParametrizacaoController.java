/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.parametrizacao;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class PrincipalParametrizacaoController implements Initializable {

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private JFXComboBox<?> cbbModulos;
    @FXML
    private AnchorPane painelCentral;
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
        // TODO
    }    

    @FXML
    private void acaoModulo(ActionEvent event) {
    }

    @FXML
    private void clickInicio(ActionEvent event) throws IOException {
        
              AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/controller/home/Principal.fxml"));
        painelTotal.getChildren().setAll(a);
    }

    @FXML
    private void evtDadosCadastrais(ActionEvent event) throws IOException {
        
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/parametrizacao/Parametrizacao.fxml"));
        painelModulo.getChildren().setAll(a); 
        
        
    }
    
}
