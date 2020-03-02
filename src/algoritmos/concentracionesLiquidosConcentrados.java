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
public class concentracionesLiquidosConcentrados {
    
    HashMap < Integer, Double > concentracionesLiquidosConcentrados = new HashMap();
    
    public  HashMap < Integer, Double > calculoConcentracionesLiquidosConcentrados(double F, double XF,  HashMap < Integer, Double > flujosLiquConcent){
        System.out.println("------- Cálculo de las concentraciones de los líquidos concentrados.-------");
        int iteraciones = flujosLiquConcent.size();
        for (int i = 1; i <= iteraciones; i++) {
            double calculo = (F * XF) / (flujosLiquConcent.get(i));
            concentracionesLiquidosConcentrados.put(i, calculo);
        }
        imprimir(iteraciones);
        return concentracionesLiquidosConcentrados;
    }
    
    public void imprimir(int iteraciones){
            for (int i = 1; i <= iteraciones; i++) {
                System.out.println(concentracionesLiquidosConcentrados.get(i));
        }
    }
    public static void main(String[] args) {
        concentracionesLiquidosConcentrados prueva = new concentracionesLiquidosConcentrados();
        double F = 15000.0;
        double XF = 0.1;
        HashMap < Integer, Double > flujosLiquConcent = new HashMap();
        flujosLiquConcent.put(1,11642.06736 );
        flujosLiquConcent.put(2,7888.11946 );
        flujosLiquConcent.put(3,4279.73556 );
        prueva.calculoConcentracionesLiquidosConcentrados(F, XF, flujosLiquConcent);
        
        
    }
}
