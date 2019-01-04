/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.producao.rastreabilidade;

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
public class PrincipalRastreabilidadeController implements Initializable {

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
    private void evtCarga(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/rastreabilidade/MontagemCarga.fxml"));
        painelTotal.getChildren().setAll(a);
    }


    @FXML
    private void evtRegiao(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/rastreabilidade/Regiao.fxml"));
        painelTotal.getChildren().setAll(a);
    }


    
}
