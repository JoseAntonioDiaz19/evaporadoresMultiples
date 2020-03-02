/*
 *Esta clase tiene los metodos necesarios para calcular la temperatura en 
  funcion de la presion segun el intervalo requerido. Cada metodo recibe 
  como parametro el valor de la presion y retorna la temperatura calculada.
 */
package algoritmos;
/**
 * @author Dizan
 */
public class temperaturaEnfuncionPresion {
    
    private double temperaturaCero;
    
    public double algoritmoTemperaturaEnFuncionPresion(double presion){
        System.out.println("Cálculo de temperatura en función de la presión.");
        if (presion >= 1 && presion <= 10) {
            temperaturaCero = intervalo_1_10(presion);
        }
        if (presion > 10 && presion <= 50) {
            temperaturaCero = intervalo_10_50(presion);
        }
        if (presion > 50 && presion <= 150) {
            temperaturaCero = intervalo_50_150(presion);
        }
        if (presion > 150 && presion <= 250) {
            temperaturaCero = intervalo_120_250(presion);
        }
        if (presion > 250 && presion <= 350) {
            temperaturaCero = intervalo_250_350(presion);
        }
        if (presion > 350 && presion <= 500) {
            temperaturaCero = intervalo_350_500(presion);
        }
        
        return temperaturaCero;
    }
    
    public double intervalo_1_10(double Presion){
        return temperaturaCero =  ((16.889) * (Math.log(Presion))+ 6.0535);
    }
    
    public double intervalo_10_50(double Presion){
        return temperaturaCero = ((22.072) * (Math.log(Presion))- 5.6489);
    }
 
    public double intervalo_50_150(double Presion){
        return temperaturaCero =  ((27.275) * (Math.log(Presion))- 25.736);
    }
    
    public double intervalo_120_250(double Presion){
        return temperaturaCero =  ((31.429) * (Math.log(Presion))- 46.221);
    }
    
    public double intervalo_250_350(double Presion){
        return temperaturaCero =  ((34.019) * (Math.log(Presion))- 60.471);
    }
    
    public double intervalo_350_500(double Presion){
        return temperaturaCero =  ((36.371) * (Math.log(Presion))- 74.256);
    }
    
    public static void main(String[] args) {
        
        temperaturaEnfuncionPresion prueba = new temperaturaEnfuncionPresion();
        double valorCalculado = prueba.algoritmoTemperaturaEnFuncionPresion(455.92);
        System.out.println("valorCalculado = " + valorCalculado);
        
    } 
}
