/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.home;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import red.model.colaborador.Colaborador;
import util.ColaboradorLogado;
import red.dao.parametrizacao.ParametrizacaoDAO;
import red.model.parametrizacao.Parametrizacao;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class PrincipalController implements Initializable {

    @FXML
    private AnchorPane painelPrincipal;
    @FXML
    private JFXButton doc;
    @FXML
    private JFXButton direction;
    @FXML
    private JFXButton resourse;
    @FXML
    private JFXButton production;
    @FXML
    private JFXButton measurement;
    @FXML
    private JFXButton parametrizacao;
    


     @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Colaborador c = ColaboradorLogado.col;
//        ParametrizacaoDAO daop = new ParametrizacaoDAO();
//        Parametrizacao p = daop.busca();
//        
//        if(p.getCnpj().equals("00.000.000/0000-00"))
//        {
//               AnchorPane a;
//            try {
////                a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/parametrizacao/PrincipalParametrizacao.fxml"));
////                            painelPrincipal.getChildren().setAll(a);
//       
//                    
//         
////           a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/parametrizacao/PrincipalParametrizacao.fxml"));
////                painelPrincipal.getChildren().setAll(a);
////                
//           a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/parametrizacao/PrincipalParametrizacao.fxml"));
//                painelPrincipal.getChildren().setAll(a);
//        
//                                
//            } catch (IOException ex) {
//                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//        switch(c.getCodigo())
//        {
//            case 1: Nivel1(); break;
//                
//            case 2: Nivel2(); break;
//                
//            default: Nivel3(); break;
//                   
//        }
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
    
    private void Nivel1()
    {
        doc.setDisable(false);
        direction.setDisable(false);
        resourse.setDisable(false);
        production.setDisable(false);
        measurement.setDisable(false);
        parametrizacao.setDisable(false);
    }

    private void Nivel2()
    {
        doc.setDisable(false);
        direction.setDisable(false);
        resourse.setDisable(false);
        production.setDisable(false);
        measurement.setDisable(false);
        parametrizacao.setDisable(false);
    }
    
    private void Nivel3()
    {
        doc.setDisable(false);
        direction.setDisable(false);
        resourse.setDisable(false);
        production.setDisable(false);
        measurement.setDisable(false);
        parametrizacao.setDisable(false);
    }
}
