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
public class calculoEntalpias {
    
     HashMap < Integer, Double > entalpias = new HashMap();
             
    public HashMap < Integer, Double > algoritmoCalcularEntalpias(HashMap < Integer, Double > temperaturas){
        System.out.println("----- Cálculo de entalpías mediante las ecuaciones descritas en la tabla 2 -----");
       
        int cantidadTemperaturas = temperaturas.size();
        System.out.println("cantidadTemperaturas = " + cantidadTemperaturas);
        
        for (int i = 0; i < cantidadTemperaturas; i++) {
            System.out.println("i = " + i);
            if (temperaturas.get(i)>= 0.01 && temperaturas.get(i) <= 20 ) {
                double temperatura = temperaturas.get(i);
                double calculo = (-2.3709 * temperatura) + 2500.9;
                entalpias.put(i, calculo);
            }
            if (temperaturas.get(i)> 20 && temperaturas.get(i) <= 45 ) {
                entalpias.put(i, (-2.38)* temperaturas.get(i)+ 2501.2);
            }
            if (temperaturas.get(i)> 45 && temperaturas.get(i) <= 70 ) {
                entalpias.put(i, (-2.4394)* temperaturas.get(i)+ 2503.9);
            }
            if (temperaturas.get(i)> 70 && temperaturas.get(i) <= 95 ) {
                entalpias.put(i, (-2.5371)* temperaturas.get(i)+ 2510.8);
            }
            if (temperaturas.get(i)> 95 && temperaturas.get(i) <= 120 ) {
                double calculo = (-2.6977 * (temperaturas.get(i)) + 2526.2);
                entalpias.put(i, calculo);
            }
            if (temperaturas.get(i)> 120 && temperaturas.get(i) <= 155 ) {
                double temperatura = temperaturas.get(i);
                double calculo = (-2.9726)* temperatura + 2559.8;
                entalpias.put(i,calculo );
            }
        }
        imprimirEntalpias(cantidadTemperaturas);
        return entalpias;
    }
    
    public void imprimirEntalpias(int cantidadTemperaturas){
            for (int i = 0; i < cantidadTemperaturas; i++) {
                System.out.println("Entapia "+i+" "+entalpias.get(i));
        }
    }
    
    public static void main(String[] args) {
        calculoEntalpias calculoEntalpias = new calculoEntalpias();
        HashMap < Integer, Double > temperaturas = new HashMap();
        temperaturas.put(0, 148.42327166131275);//2149.003
        temperaturas.put(1, 132.23513876459262);
        temperaturas.put(2, 114.80176487581708);
        temperaturas.put(3, 95.9156098296436);
        calculoEntalpias.algoritmoCalcularEntalpias(temperaturas); 
    }
}
