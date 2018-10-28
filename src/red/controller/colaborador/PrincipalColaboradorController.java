/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.colaborador;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import red.controller.home.Modulo;


/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class PrincipalColaboradorController implements Initializable {

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
  }    

    @FXML
    private void clickInicio(ActionEvent event) throws IOException {
        
           AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/controller/home/Principal.fxml"));
        painelTotal.getChildren().setAll(a);
    }


    @FXML
    private void acaoModulo(ActionEvent event) {
    }

    @FXML
    private void evtPerfil(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/colaborador/perfil/PrincipalPerfil.fxml"));
        painelModulo.getChildren().setAll(a);
    }
    
}
