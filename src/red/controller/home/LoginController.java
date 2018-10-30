/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.home;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;
import red.model.colaborador.Colaborador;
import util.ColaboradorLogado;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField txtUser;
    @FXML
    private JFXTextField txtSenha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Colaborador c = new Colaborador();
        if(c.FirstAcssesNecessary() == 0)
        {
           
        }
    }    

    @FXML
    private void btnEntrar(ActionEvent event) { 
        Colaborador c = new Colaborador();
        c = c.Logim(txtUser.getText(), txtSenha.getText());
        if(c.getCodigo() == 0)
        {
            JOptionPane.showMessageDialog(null, "CPF ou Senha incorreto!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            ColaboradorLogado col = new ColaboradorLogado();
            col.setCol(c);
            
        }
    }

    @FXML
    private void evtCadastrar(ActionEvent event) {
    }
    
}
