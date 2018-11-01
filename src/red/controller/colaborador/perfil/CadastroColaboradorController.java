/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.controller.colaborador.perfil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import util.MaskFieldUtil;
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
    private ListView<Colaborador> lvcolaboradores;
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
    
    private Colaborador colA;
    private boolean firstAc;
    @FXML
    private TextField txcodigo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txcodigo.setVisible(false);
        Colaborador c = new Colaborador();
        firstAc = c.FirstAcssesNecessary() == 0;
        if(firstAc)
        {
            StatusFirstAcsses();
        }
        else
        {
            Original();
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
        ArrayList<Colaborador> lista = new ArrayList<>();
        if(!txpesquisa.getText().trim().equals("") && !txpesquisa1.getText().trim().equals(""))
        {
            lista = colA.serch(txpesquisa.getText(), txpesquisa1.getText());
        }
        else
        {
            lista = colA.serch(txpesquisa.getText());
        }
        if(lista != null)
        {
            ObservableList<Colaborador> modelo;
            modelo = FXCollections.observableArrayList(lista);    
            lvcolaboradores.setItems(modelo);
            Alterar();
        }
    }

    @FXML
    private void clkAlterar(ActionEvent event) 
    {
        Colaborador c = new Colaborador();
        c = (Colaborador) lvcolaboradores.getSelectionModel().getSelectedItem();
        
        txnome.setText(""+c.getNome());
        txcpf.setText(""+c.getCpf());
        txcelular.setText(""+c.getCelular());
        txcargo.setText(""+c.getCargo());
        txsenha.setText(""+c.getSenha());
        switch(c.getNivel())
        {
            case 0: rbbasico.setSelected(true);  break;
            case 1: rbauditor.setSelected(true); break;
            default: rbadministrador.setSelected(true);
        }
        txsenha1.setText("");
        txcodigo.setText(""+c.getCodigo());
        Editando();
    }

    @FXML
    private void alkApagar(ActionEvent event) 
    {
        if(JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse Colaborador?", "Confirmacao", JOptionPane.INFORMATION_MESSAGE) == 1)
        {
            Colaborador c = (Colaborador) lvcolaboradores.getSelectionModel().getSelectedItem();
            if(c.getNivel() == 2)
            {
                if(c.ReturnQtdColaboradorNivel(c.getNivel()) == 1)
                {
                    JOptionPane.showMessageDialog(null, "Nao é possivel excluir o colaborador pelo fato de existir somente 1 colaborador como administrador ", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if(c.excluir(c.getCodigo()))
                        JOptionPane.showMessageDialog(null, "Colaborador excluido com suceso", "Erro", JOptionPane.ERROR_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "Erro ao excluir", "Erro", JOptionPane.ERROR_MESSAGE);
                }    
            }
            else
            {
                if(colA.excluir(colA.getCodigo()))
                        JOptionPane.showMessageDialog(null, "Colaborador excluido com suceso", "Erro", JOptionPane.ERROR_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "Erro ao excluir", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            Original();
        }
    }

    @FXML
    private void clkNovo(ActionEvent event) 
    {
        Editando();
        txcodigo.setText("0");
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
                    if(!txcelular.getText().trim().equals(""))
                    {
                        String cpfA;
                        cpfA = txcpf.getText();
                        cpfA = cpfA.replaceAll("\\.", "");
                        cpfA = cpfA.replaceAll("/", "");
                        cpfA = cpfA.replaceAll("-", "");
                        if(!cpfA.trim().equals("")) // remover a verificacao do lenght 14 e colocar no outro if
                        {
                            //fazer as varidacoes;
//460.005.538/11
                            if(cpfA.length() == 11)
                            {
                              int dig1, dig2;
                              dig1 = PrimeiroDigito(cpfA);
                              dig2 = SegundoDigito(cpfA);  
                              String d1, d2;
                              d1 = Integer.toString(dig1);
                              d2 = Integer.toString(dig2);
                              if(cpfA.charAt(9) == d1.charAt(0) && cpfA.charAt(10) == d2.charAt(0))
                              {
                                  int nivel;
                                  if(rbbasico.isSelected())
                                      nivel = 1;
                                  else
                                      if(rbauditor.isSelected())
                                          nivel = 2;
                                      else
                                         nivel = 3; 
                                  Colaborador c = new Colaborador(0, txnome.getText(), txsenha.getText(), txcargo.getText(), true, txcpf.getText(), nivel, txcelular.getText());
                                  if(txcodigo.getText().equals("0"))
                                  {
                                      if(c.gravar(c))
                                      {
                                          JOptionPane.showMessageDialog(null, "Gravado com sucesso", "Informacao", JOptionPane.INFORMATION_MESSAGE);
                                          limpar();
                                          Original();
                                          if(firstAc)
                                          {
                                              AnchorPane a;
                                              try 
                                              {
                                                  a = (AnchorPane) FXMLLoader.load(getClass().getResource("/red/controller/homr/Login.fxml"));
                                                  painelTotal.getChildren().setAll(a);
                                              } 
                                              catch (IOException ex) 
                                              {
                                                  Logger.getLogger(CadastroColaboradorController.class.getName()).log(Level.SEVERE, null, ex);
                                              }
                                          }
                                      }
                                      else
                                      {
                                           JOptionPane.showMessageDialog(null, "Erro ao gravar", "Erro", JOptionPane.ERROR_MESSAGE);
                                      }
                                  }
                                  else
                                  {
                                      c.setCodigo(colA.getCodigo());
                                      if(c.alterar(c))
                                      {
                                          JOptionPane.showMessageDialog(null, "Alterado com sucesso", "Informacao", JOptionPane.INFORMATION_MESSAGE);
                                          limpar();
                                          Original();
                                      }
                                      else
                                          JOptionPane.showMessageDialog(null, "Erro ao gravar", "Erro", JOptionPane.ERROR_MESSAGE);
                                  }    
                              }  
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "CPF invaliddo!", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
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
        limpar();
        Original();
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

    private void StatusFirstAcsses()
    {
        txnome.setText("adm");
        txcodigo.setText("0");
        txcodigo.setVisible(false);
        txnome.setEditable(true);
        btAlterar.setDisable(true);
        btApagar.setDisable(true);
        btBuscar.setDisable(true);
        btCancelar.setDisable(true);
        btNovo.setDisable(true);
        btConfirmar.setDisable(false);
        rbadministrador.setSelected(true);
        rbauditor.setSelected(false);
        rbbasico.setSelected(false);
        rbauditor.setDisable(true);
        rbbasico.setDisable(true);
        rbadministrador.setDisable(false);
         MaskFieldUtil.cpfCnpjField(txcpf);
        MaskFieldUtil.foneField(txcelular);
    }
    
    private void Original()
    {
        btAlterar.setDisable(true);
        btApagar.setDisable(true);
        btBuscar.setDisable(false);
        btCancelar.setDisable(true);
        btConfirmar.setDisable(true);
        btNovo.setDisable(false);
        colA = new Colaborador();
        MaskFieldUtil.cpfCnpjField(txcpf);
        MaskFieldUtil.foneField(txcelular);
    }
    
    private void Alterar()
    {
        btAlterar.setDisable(false);
        btApagar.setDisable(false);
        btBuscar.setDisable(false);
        btCancelar.setDisable(true);
        btConfirmar.setDisable(true);
        btNovo.setDisable(false);
        colA = new Colaborador();
    }
    
    private void Editando()
    {
        btAlterar.setDisable(false);
        btApagar.setDisable(false);
        btBuscar.setDisable(false);
        btCancelar.setDisable(true);
        btConfirmar.setDisable(true);
        btNovo.setDisable(false);
    }
    
    private void limpar()
    {
        txnome.setText("");
        txcpf.setText("");
        txcelular.setText("");
        txcargo.setText("");
        txsenha.setText("");
        txsenha1.setText("");
        txcodigo.setText("");
    }
    
    private int PrimeiroDigito(String cpf)
    {
        int soma = 0, resultado = 0, resto = 0, num;
        for(int i = 0, x = 10; i < 9; i++, x--)
        {
            num = Character.digit(cpf.charAt(i), 10);
            soma = soma + num * x; 
        }
        resto = soma%11;
        resultado = 11 - resto;
        if(resultado == 10 || resultado == 11)
        {
            return 0;
        }
        return resultado;
    }

    private int SegundoDigito(String cpf)
    {
        int soma = 0, resultado = 0, resto = 0, num;
        for(int i = 0, x = 11; i < 10; i++, x--)
        {
            num = Character.digit(cpf.charAt(i), 10);
            soma = soma + num * x; 
        }
        resto = soma%11;
        resultado = 11 - resto;
        if(resultado == 10 || resultado == 11)
        {
            return 0;
        }
        return resultado;
    }
}
