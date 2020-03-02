/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaporadores;

import algoritmos.crearMatrizCaracteristica;
import controladores.controladorVistaEvaporadores;
import vistas.vistaEvaporadores;

/**
 *
 * @author Dizan
 */
public class Evaporadores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        vistaEvaporadores vistaEvaporadores = new vistaEvaporadores();
        controladorVistaEvaporadores controlVistaEvaporadores = new controladorVistaEvaporadores(vistaEvaporadores);
        vistaEvaporadores.setVisible(true);
        
    } 
}
