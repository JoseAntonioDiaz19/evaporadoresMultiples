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
public class capacidadSistema {
    
    public double  algoritmoCapacidadSistema(HashMap < Integer, Double > flujosVapor){
        System.out.println("-------------Capacidad del sistema --------------");
        int iteraciones = flujosVapor.size()-1;
        double calculo = 0;
        for (int i = 1; i <= iteraciones; i++) {
            calculo = calculo + flujosVapor.get(i);            
        }
        System.out.println("Capacidad del sistema = " + calculo);
        return calculo;
    }
    
    public static void main(String[] args) {
        capacidadSistema capacidadSistema = new capacidadSistema();
        
        HashMap < Integer, Double > flujosVapor = new HashMap();
        flujosVapor.put(0, 3846.19591076989917);
        flujosVapor.put(1, 3026.6166817936737);
        flujosVapor.put(2, 2081.1680620999537);
        flujosVapor.put(3, 2392.2152561063726);
        
       capacidadSistema.algoritmoCapacidadSistema(flujosVapor);
    }
}
