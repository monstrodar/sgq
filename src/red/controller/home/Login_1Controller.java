
 
package red.controller.home;

import com.jfoenix.controls.JFXPasswordField;
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
public class Login_1Controller implements Initializable {

    @FXML
    private JFXTextField txtUser;
    @FXML
    private JFXPasswordField txtSenha;
    private AnchorPane pane;
    @FXML
    private AnchorPane PainelTotal;
    @FXML
    private AnchorPane painelCentral;

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
                PainelTotal.getChildren().setAll(a);
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
                    PainelTotal.getChildren().setAll(a);
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
           // AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/controller/home/Principal.fxml"));
            AnchorPane a= (AnchorPane) FXMLLoader.load(getClass().getResource("/red/controller/home/Principal.fxml"));
           
           PainelTotal.getChildren().setAll(a);
        }
    }

    
}
