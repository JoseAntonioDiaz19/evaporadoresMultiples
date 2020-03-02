package algoritmos;
import java.util.HashMap;
/**
 *
 * @author Dizan
 */
public class presionesEvaporadoresEnFuncionTemperaturas {
    
    HashMap < Integer, Double > presiones = new HashMap();
    
    public HashMap < Integer, Double > algoritmoPresionesEnFuncionTemperaturas(HashMap < Integer, Double > temperaturas){
        System.out.println("------------- Cálculo de las presiones en cada evaporador en función de las temperaturas, "
                             + "empleando las ecuaciones de la tabla 3 ------------------");
        
        int iteraciones = temperaturas.size();
        
        for (int i = 0; i < iteraciones; i++) {
            
            if (temperaturas.get(i) >= 0.01 && temperaturas.get(i) <= 20) {
                presiones.put(i,0.6196 * Math.pow(Math.E, 0.071*temperaturas.get(i)) ); 
            }
            if (temperaturas.get(i) > 20 && temperaturas.get(i) <= 45) {
                presiones.put(i,0.7702 * Math.pow(Math.E, 0.0564*temperaturas.get(i)) ); 
            }
            if (temperaturas.get(i) > 45 && temperaturas.get(i) <= 70) {
                presiones.put(i,1.1655 * Math.pow(Math.E, 0.0472*temperaturas.get(i)) ); 
            }
            if (temperaturas.get(i) > 70 && temperaturas.get(i) <= 95) {
                presiones.put(i,1.9329 * Math.pow(Math.E, 0.0399*temperaturas.get(i)) ); 
            }
            if (temperaturas.get(i) > 95 && temperaturas.get(i) <= 120) {
                presiones.put(i,3.333 * Math.pow(Math.E, 0.0341*temperaturas.get(i)) ); 
            }
            if (temperaturas.get(i) > 120 && temperaturas.get(i) <= 155) {
                presiones.put(i,6.407 * Math.pow(Math.E, 0.0287*temperaturas.get(i)) ); 
            }
        }
        imprimirPreisones(iteraciones);
        return presiones;
    }
    
    public void imprimirPreisones(int cantidadTemperaturas){
            for (int i = 0; i < cantidadTemperaturas; i++) {
                System.out.println(presiones.get(i));
        }
    }
    
    public static void main(String[] args) {
        
        HashMap < Integer, Double > temperaturas = new HashMap();
        temperaturas.put(0, 123.70995);
        temperaturas.put(1, 92.0);
        temperaturas.put(2, 69.0);
        temperaturas.put(3, 30.0);
        
        presionesEvaporadoresEnFuncionTemperaturas presionesEvaporadoresEnFuncionTemperaturas= new presionesEvaporadoresEnFuncionTemperaturas();
        presionesEvaporadoresEnFuncionTemperaturas.algoritmoPresionesEnFuncionTemperaturas(temperaturas);
    }
}
