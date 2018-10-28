/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Daniel
 */
public class Red extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Principal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
   //     stage.setFullScreen(true);

      //  stage.setMaximized(true);
      //  stage.setResizable(false);//Desativa o redimensionar:
       // stage.initStyle(StageStyle.UNDECORATED);//Remove os 3 botões e a borda:

        stage.setTitle("Sistema de Gestão de Qualidade");
        stage.show();
        
        
        
        

        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
