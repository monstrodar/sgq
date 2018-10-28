/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.colaborador.perfil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import red.model.colaborador.Colaborador;
import util.mask;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class CadastroColaboradorController implements Initializable {

    @FXML
    private AnchorPane painelTotal;
    @FXML
    private VBox painelEsquerda;
    @FXML
    private TextField txpesquisa;
    @FXML
    private ListView<?> lvcolaboradores;
    @FXML
    private VBox pnDireita;
    @FXML
    private RadioButton rbbasico;
    @FXML
    private ToggleGroup gruporadio;
    @FXML
    private RadioButton rbauditor;
    @FXML
    private RadioButton rbadministrador;
    @FXML
    private TextField txnome;
    @FXML
    private TextField txcargo;
    @FXML
    private TextField txcpf;
    @FXML
    private TextField txpesquisa1;
    @FXML
    private TextField txcelular;
    @FXML
    private TextField txsenha;
    @FXML
    private TextField txsenha1;

    private boolean firstAc;
    @FXML
    private Button btBuscar;
    @FXML
    private Button btAlterar;
    @FXML
    private Button btApagar;
    @FXML
    private Button btNovo;
    @FXML
    private Button btConfirmar;
    @FXML
    private Button btCancelar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Colaborador c = new Colaborador();
        if(c.FirstAcssesNecessary() == 0)
        {
            firstAc = true;
        }
        else
        {
            firstAc = false;
            
        }
            
    }    


    @FXML
    private void evtCadColaborador(ActionEvent event) throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/view/colaborador/perfil/CadastroColaborador.fxml"));
        painelTotal.getChildren().setAll(a);
    }

    @FXML
    private void clkBuscar(ActionEvent event) 
    {

    }

    @FXML
    private void clkAlterar(ActionEvent event) 
    {

    }

    @FXML
    private void alkApagar(ActionEvent event) 
    {

    }

    @FXML
    private void clkNovo(ActionEvent event) 
    {

    }

    @FXML
    private void clkConfirmar(ActionEvent event) 
    {
        if(!txnome.getText().trim().equals(""))
        {
            if(!txsenha.getText().trim().equals("") && txsenha.getText().equals(txsenha1.getText()))
            {
                if(!txcargo.getText().trim().equals(""))
                {
                    if(txcelular.getText().trim().equals(""))
                    {
                        if(!txcpf.getText().trim().equals(""))
                        {
                            //fazer as varidacoes;
                            Colaborador c = new Colaborador(0, txnome.getText(), txsenha.getText(), txcargo.getText(), true, txcpf.getText(), 0, txcelular.getText());
                        }
                        else
                        {
                             JOptionPane.showMessageDialog(null, "Digite o CPF", "Informacao", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(null, "Digite o Celular", "Informacao", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else
                {
                     JOptionPane.showMessageDialog(null, "Digite o Cargo", "Informacao", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                 JOptionPane.showMessageDialog(null, "Digite a nao confirma", "Informacao", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Digite o Nome", "Informacao", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

    @FXML
    private void clCancelar(ActionEvent event) {
        
    }

    private void StatusFirstAcsses()
    {
        txnome.setText("adm");
        txnome.setEditable(false);
        btAlterar.setDisable(false);
        btApagar.setDisable(false);
        btBuscar.setDisable(false);
        btCancelar.setDisable(false);
        btNovo.setDisable(false);
        rbadministrador.setSelected(true);
        rbauditor.setSelected(false);
        rbbasico.setSelected(false);
        rbauditor.setDisable(false);
        rbbasico.setDisable(false);
        rbadministrador.setDisable(false);
    }
    
    private void Original()
    {
        
    }
    
    private void Editando()
    {
        
    }
    
    private void limpar()
    {
        
    }

    @FXML
    private void selectB(ActionEvent event) {
        rbauditor.setSelected(false);
        rbadministrador.setSelected(false);
    }

    @FXML
    private void selectA(ActionEvent event) {
        rbbasico.setSelected(false);
        rbadministrador.setSelected(false);
    }

    @FXML
    private void selectAdm(ActionEvent event) {
        rbauditor.setSelected(false);
        rbbasico.setSelected(false);
    }

    

}
