/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import red.model.colaborador.Colaborador;

/**
 *
 * @author Bruno Yoshino
 */
public class ColaboradorLogado {
    public static Colaborador col;
    public static boolean primeiroAcesso = false;


    
    public Colaborador getCol() {
        return col;
    }

    public void setCol(Colaborador col) {
        ColaboradorLogado.col = col;
    }
    
    
    
    
}
