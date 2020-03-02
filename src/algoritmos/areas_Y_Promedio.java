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
public class areas_Y_Promedio {
    
    HashMap < Integer, Double > areas = new HashMap();
    private double areaPromedio;
    
    public HashMap < Integer, Double > algoritmoCalculoAreas(HashMap < Integer, Double >flujosCalor, 
                                                            HashMap < Integer, Double >coeficientesGlobales, 
                                                            HashMap < Integer, Double > temperaturas){
        
        System.out.println("-----------Calculo de areas formula 3 -----------");
        int iteraciones = coeficientesGlobales.size();
        double sumaAreas = 0;
        double deltaT;
        for (int i = 1; i <= iteraciones; i++) {
           deltaT = (temperaturas.get(i))- (temperaturas.get(i-1));
            if (deltaT < 0) {
                deltaT = deltaT * (-1);
            }
           areas.put(i, (flujosCalor.get(i-1)) / (coeficientesGlobales.get(i) * deltaT));
        }
        
        for (int i = 1; i <= iteraciones; i++) {
            sumaAreas = sumaAreas + areas.get(i);
        }
        areaPromedio = sumaAreas / iteraciones;
        imprimirEntalpias(iteraciones);
        return areas;
    }
    
    public void imprimirEntalpias(int cantidadCoeficientes){
            for (int i = 1; i <= cantidadCoeficientes; i++) {
                System.out.println(areas.get(i));
        }
    }
    
    /**
     * @return the areaPromedio
     */
    public double getAreaPromedio() {
        return areaPromedio;
    }
    
    public static void main(String[] args) {
        
        HashMap < Integer, Double >flujosCalor = new HashMap();
        flujosCalor.put(0,1.2122090708543902E7 );
        flujosCalor.put(1,7439394.51023698 );
        flujosCalor.put(2,8767572.564296104 );
        flujosCalor.put(3,1.0174884667702E7 );
        
        HashMap < Integer, Double > coeficientesGlobales = new HashMap();
        coeficientesGlobales.put(1, 2800.0);
        coeficientesGlobales.put(2, 2500.0);
        coeficientesGlobales.put(3, 1600.0);
        
        HashMap < Integer, Double > temperaturas = new HashMap();
        temperaturas.put(0, 123.70995);
        temperaturas.put(1, 99.4954926);
        temperaturas.put(2, 72.3753003);
        temperaturas.put(3, 30.0);
        
        areas_Y_Promedio calculoAreas_Y_Promedio = new areas_Y_Promedio();
        calculoAreas_Y_Promedio.algoritmoCalculoAreas(flujosCalor, coeficientesGlobales, temperaturas);
        
    }
  
}
