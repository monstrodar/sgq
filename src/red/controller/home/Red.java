/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.home;


import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import red.dao.util.Conecta;
import red.model.colaborador.Colaborador;

/**
 *
 * @author Daniel
 */
public class Red extends Application {
    
    @Override
    public void start(Stage stage) throws Exception 
    {
      Parent root = FXMLLoader.load(getClass().getResource("/red/controller/home/Principal.fxml"));//banco_novo
   //   Parent root = FXMLLoader.load(getClass().getResource("/red/controller/home/Login_1.fxml"));//daniel entrega final
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
      // stage.setMaximized(true);
       
        stage.setTitle("Sistema de Gestão de Qualidade");
        stage.show();
 
    /**
     * @param args the command line arguments
     */

    }
}
