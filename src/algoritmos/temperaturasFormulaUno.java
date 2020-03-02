package algoritmos;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Dizan
 */
public class temperaturasFormulaUno {
    HashMap < Integer, Double > temperaturas = new HashMap();
   
    public HashMap < Integer, Double > algoritmoTemperaturaFormulaUno(double T0,
                                               double TN ,
                                               int numeroCoeficientes,
                                               HashMap < Integer, Double > Un,
                                               int numeroEvaporadores){
        
        temperaturas = new HashMap();
        System.out.println("-------------- Cálculo de temperaturas mediante la fórmula 1 ----------------");
        
        double sigma = 0;
        double numerador = T0 - TN;
      
        for (int i = 1; i <= numeroCoeficientes; i++) {
             sigma = sigma + (1/Un.get(i));
        }
        System.out.println("Sigma: "+ sigma);
        
        for (int i = 1; i <= numeroEvaporadores; i++) {
            if (i == 1) {
                temperaturas.put(i, T0 - (numerador / (Un.get(i) * sigma)));
            } 
            if (i >= 2 && i < numeroCoeficientes) {
                temperaturas.put(i, temperaturas.get(i-1)-(numerador/(Un.get(i)*sigma)));
            } 
            if (i == numeroCoeficientes) {
                 temperaturas.put(i, TN);
            }
        } 
        temperaturas.put(0, T0);
        imprimirTemperaturas();
        return temperaturas;
    }    
    
    public void imprimirTemperaturas(){
        //Imprimir valores de coeficientes globales
            for (Map.Entry<Integer, Double> entry : temperaturas.entrySet()) {
                System.out.println("Temperatura = "+ entry.getKey());
                System.out.println("Valor = " + entry.getValue());
                
            }
    }
    
    public static void main (String []args){
        
        double temperaturaCero = 148.42327166131275;//T0
        double temperaturaUltimoEfecto = 30;//TN
        int numeroDeEfectos = 3;//n
        HashMap < Integer, Double > Un = new HashMap();
        Un.put(1, 2800.0);
        Un.put(2, 2500.0);
        Un.put(3, 1600.0);
        
        temperaturasFormulaUno temperaturasFormulaUno = new temperaturasFormulaUno();
        temperaturasFormulaUno.algoritmoTemperaturaFormulaUno(temperaturaCero, 
                                                              temperaturaUltimoEfecto, 
                                                              numeroDeEfectos, 
                                                              Un, 
                                                              numeroDeEfectos);
        
    }
}
