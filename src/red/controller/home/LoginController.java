/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.home;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import red.model.colaborador.Colaborador;
import red.model.producao.lote.Produto;
import util.ColaboradorLogado;
import red.dao.producao.lote.ProdutoDAO;
import util.MaskFieldUtil;

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
    private AnchorPane pane;
    @FXML
    private AnchorPane painelCentral;
    @FXML
    private AnchorPane PainelTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Colaborador c = new Colaborador();
        ProdutoDAO p = new ProdutoDAO();
        if(c.FirstAcssesNecessary() == 0)
        {
            AnchorPane a;
            try 
            {
                a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/colaborador/perfil/CadastroColaborador.fxml"));
                painelCentral.getChildren().setAll(a);
              //  pane.getChildren().setAll(a);//bruno
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            // verificar o produto
            if(p.QtdProduto() == 0)
            {
                AnchorPane a;
                try 
                {
                    a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/producao/lote/Produto.fxml"));
                    pane.getChildren().setAll(a);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        MaskFieldUtil.cpfCnpjField(txtUser);
    }    

    @FXML
    private void btnEntrar(ActionEvent event) throws IOException { 
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
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/controller/home/Principal.fxml"));
           
            
              painelCentral.getChildren().setAll(a);
              //  pane.getChildren().setAll(a);//bruno
        }
    }

    @FXML
    private void evtCadastrar(ActionEvent event) {
    }
    
}
