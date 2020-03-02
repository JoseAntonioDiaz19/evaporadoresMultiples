/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.HashMap;

/**
 *
 * @author Dizan
 */
public class desicionAreas {
    
    
    
    public boolean algoritmoDesicion(HashMap < Integer, Double > areas){
        System.out.println("---------------- Desicion --------------");
        int iteraciones = areas.size();
        double error;
        boolean aceptable = false;
        for (int i = 1; i <= iteraciones; i++) {
            for (int j = 1; j <= iteraciones; j++) {
                error  = errorRelativoPorcentual(areas.get(i), areas.get(j));
                //System.out.println("error = "+'('+i+","+j+')'+" " + error);
                if (error > 0.01) {
                    aceptable = false;
                    break;
                }else{
                    aceptable = true;
                }
               
            }
        }
        System.out.println("Aceptado: " + aceptable);
        return aceptable;
    }
    
    public double errorRelativoPorcentual(double xrNuevo, double xrAnterior){
        double error;
        error = ((xrNuevo - xrAnterior) / xrAnterior) * 100;
        if(error < 0){ 
            error = error * (-1);
        }
        return error;
    }
    
    public static void main(String[] args) {
        desicionAreas prueba = new desicionAreas();
        HashMap < Integer, Double > areas2 = new HashMap ();
        areas2.put(1, 141.24263611339137);
        areas2.put(2, 124.76144926683207);
        areas2.put(3, 129.26283647131083);
        boolean desicion = prueba.algoritmoDesicion(areas2);
        System.out.println("desicion = " + desicion);
        int interaciones = areas2.size();
        System.out.println("interaciones = " + interaciones);
    }
}
