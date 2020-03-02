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
public class nuevasTemperaturas {
    
    HashMap < Integer, Double > nuevasTemperaturas = new HashMap();
    
    public HashMap < Integer, Double >  algoritmoNuevasTemperaturas(HashMap < Integer, Double > areas,
                                                                    double TN,
                                                                    double temperaturaCero,
                                                                    double areaPromedio,
                                                                    HashMap < Integer, Double > primerasTemperaturas){
        
        System.out.println("---------------- Calculo nuevas temperaturas --------------------");
        int iteraciones = areas.size();
       
        for (int i = 1; i <= iteraciones; i++) {
            
            if (i == 1) {
                double numerador = (temperaturaCero - primerasTemperaturas.get(i)) * areas.get(i);
                nuevasTemperaturas.put(i, (temperaturaCero - (numerador / areaPromedio)));
            
            }
            
            if (i >= 2 && i < iteraciones) {
                int i2 = i-1;
                double numerador = (primerasTemperaturas.get(i2) - primerasTemperaturas.get(i)) * areas.get(i);
                nuevasTemperaturas.put(i, (nuevasTemperaturas.get(i2) - (numerador / areaPromedio)));
            }
            if (i == iteraciones) {
                 nuevasTemperaturas.put(i, TN);
            }
//          else{
//                int i2 = i-1;
//                double numerador = (primerasTemperaturas.get(i2) - primerasTemperaturas.get(i)) * areas.get(i);
//                nuevasTemperaturas.put(i, (nuevasTemperaturas.get(i2) - (numerador / areaPromedio)));
//            }
        }
        nuevasTemperaturas.put(0, temperaturaCero); 
        imprimirTemperaturas();
        return nuevasTemperaturas;
    }
    
    public void imprimirTemperaturas(){
            for (Map.Entry<Integer, Double> entry : nuevasTemperaturas.entrySet()) {
                System.out.println("Nueva Temperatura = "+ entry.getKey());
                System.out.println("Valor = " + entry.getValue());
                
            }
    }
    
    public static void main(String[] args) {
        HashMap < Integer, Double > areas = new HashMap();
        areas.put(1, 32.7646973519445);
        areas.put(2, 34.36619299785212);
        areas.put(3, 33.1057449515841);
        
        double TN = 30.0;
        double temperaturaCero = 123.70656913941528;
        double areaPromedio = 33.412211767126905;
        
        HashMap < Integer, Double > primerasTemperaturas = new HashMap();
        primerasTemperaturas.put(1, 99.49298538246043);
        primerasTemperaturas.put(2, 72.373771574671);
        primerasTemperaturas.put(3, 30.0);
        
        nuevasTemperaturas nuevasTemperaturas = new nuevasTemperaturas();
        HashMap < Integer, Double > nTemperaturas = new HashMap();
        nTemperaturas = nuevasTemperaturas.algoritmoNuevasTemperaturas(areas, TN, temperaturaCero, areaPromedio, primerasTemperaturas);
    }
}
