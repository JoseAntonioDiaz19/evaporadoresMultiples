/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dizan
 */
public class flujoDeCalor {
    
    HashMap < Integer, Double > flujosCalor = new HashMap();
    
    public HashMap < Integer, Double > algoritmo (HashMap < Integer, Double > entalpias,
                                                  HashMap < Integer, Double > flujosVapor ){
        
        System.out.println("------------- Cálculo de los flujos de calor mediante la fórmula 2 -------------");
        
        int iteraciones = entalpias.size();
        System.out.println("iteraciones = " + iteraciones);
        for (int i = 0; i < iteraciones; i++) {
            double calculo = (flujosVapor.get(i))*(entalpias.get(i));
            flujosCalor.put(i, calculo );
          
        }
        imprimirFlujoCalor();
        return flujosCalor;
    }
    
    public void imprimirFlujoCalor(){
        System.out.println("Impresion: ");
            for (Map.Entry <Integer, Double> entry : flujosCalor.entrySet()) {
                System.out.println("Flujo = "+ entry.getKey());
                System.out.println("Valor = " + entry.getValue());
                
            }
    }
    
    public static void main(String[] args) {
        flujoDeCalor calculoFlujoDeCalor = new flujoDeCalor();
        HashMap < Integer, Double > entalpias = new HashMap();
        entalpias.put(0, 2192.0598026300004);
        entalpias.put(1, 2257.79101);
        entalpias.put(2, 2327.176625);
        entalpias.put(3, 2429.8);
        
        HashMap < Integer, Double > flujosVapor = new HashMap();
        flujosVapor.put(0, 5530.0);
        flujosVapor.put(1, 3294.9881);
        flujosVapor.put(2, 3767.47191);
        flujosVapor.put(3, 4187.53999);
        
        calculoFlujoDeCalor.algoritmo(entalpias, flujosVapor);
    }
}
