/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;
import java.util.HashMap;
/**
 * @author Dizan
 */
public class flujoDeLiquidosConcentrados {
    HashMap < Integer, Double > flujoLiqConcent = new HashMap();//L
    
    
    public  HashMap < Integer, Double > algoritmoflujoDeLiquidosConcentrados(double F, 
                                                                             HashMap < Integer, Double > flujosVapor,
                                                                             HashMap < Integer, Integer > posicion_evaporador,
                                                                             HashMap < Integer, Integer > evaporador_posicion
                                                                             ){
//      HashMap < Integer, Integer > evaporador_posicion = new HashMap();
//      evaporador_posicion.put(2, 1);//---> Cuarto argumento del metodo equivalente a variablesConocidas.Arreglo
//      evaporador_posicion.put(3, 2);
//      evaporador_posicion.put(1, 3);

//      HashMap < Integer, Integer > arreglo = new HashMap();//---> Tercer argumento del metodo
//      arreglo.put(1, 2);
//      arreglo.put(2, 3);
//      arreglo.put(3, 1);

        System.out.println("----------------- Cálculo de los flujos de líquido concentrado. ---------------");
//      HashMap < Integer, Double > flujosVapor = new HashMap();
//      flujosVapor.put(0, 3846.19591076989917);
//      flujosVapor.put(1, 3026.6166817936737);
//      flujosVapor.put(2, 2081.1680620999537);
//      flujosVapor.put(3, 2392.2152561063726);
        double acumulador = 0;
        double calculo = 0;
        int iteraciones = flujosVapor.size()- 1;
        for (int i = 1; i <= iteraciones; i++) {
           
            int iteracionesAcumular = evaporador_posicion.get(i);
            System.out.println("iteracionesAcumular = " + iteracionesAcumular);
           
            for (int j = iteracionesAcumular; j >= 1; j--) {
                acumulador = acumulador + flujosVapor.get(posicion_evaporador.get(j));
                calculo = F - acumulador;
                calculo = redondearNumero(calculo, 6);
                flujoLiqConcent.put(i, calculo);
                System.out.println("evaporador: " + posicion_evaporador.get(j));
            } 
            System.out.println("calculo = " + calculo);
            acumulador = 0;
            calculo = 0;
        }
        flujoLiquidosConcentrados(iteraciones);
        return flujoLiqConcent;
    }
    
    public void flujoLiquidosConcentrados(int iteraciones){
            for (int i = 1; i <= iteraciones; i++) {
                System.out.println("L"+i+": "+flujoLiqConcent.get(i));
        }
    }
    
    public static double redondearNumero(double numero, int digitos) {
       double resultado;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado/Math.pow(10, digitos);
        return resultado;
    }
    
    public static void main(String[] args) {
       
        HashMap < Integer, Double > flujosVapor = new HashMap();
        flujosVapor.put(0, 3846.19591076989917);
        flujosVapor.put(1, 3026.6166817936737);
        flujosVapor.put(2, 2081.1680620999537);
        flujosVapor.put(3, 2392.2152561063726);
        
        double F = 10000.0;
        
        HashMap < Integer, Integer > posicion_evaporador = new HashMap();
        posicion_evaporador.put(1, 2);
        posicion_evaporador.put(2, 3);
        posicion_evaporador.put(3, 1);
        
        HashMap < Integer, Integer > evaporador_posicion = new HashMap();
        evaporador_posicion.put(2, 1);
        evaporador_posicion.put(3, 2);
        evaporador_posicion.put(1, 3);
        
        flujoDeLiquidosConcentrados flujoDeLiquidosConcentrados = new flujoDeLiquidosConcentrados();
        flujoDeLiquidosConcentrados.algoritmoflujoDeLiquidosConcentrados(F, flujosVapor, posicion_evaporador, evaporador_posicion);
    }
}
