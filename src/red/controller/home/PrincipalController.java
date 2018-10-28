/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class PrincipalController implements Initializable {

    @FXML
    private AnchorPane painelPrincipal;

     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 


    @FXML
    private void acaoProducao(ActionEvent event) throws IOException {
        
          AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/PrincipalProducao.fxml"));
        painelPrincipal.getChildren().setAll(a);
        
        
    }

    @FXML
    private void clickDoc(ActionEvent event) throws IOException {
        
        
    }

    @FXML
    private void acaoColaborador(ActionEvent event) throws IOException {
        
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/colaborador/PrincipalColaborador.fxml"));
        painelPrincipal.getChildren().setAll(a);
        
        
    }

    @FXML
    private void acaoParametrizacao(ActionEvent event) throws IOException {
       AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/parametrizacao/PrincipalParametrizacao.fxml"));
        painelPrincipal.getChildren().setAll(a); 
        
     

    }


    
    
}
