package algoritmos;
/**
 * @author Dizan
 */
public class economiaSistema {
    
    public double algoritmoEconomiaSistema(double capacidadSistema, double V0){
        
        System.out.println("------------------- Economia del sistema --------------------");
        double calculo = capacidadSistema / V0;
        System.out.println("Economia del sistema: " + calculo);
        return calculo;
    } 
    
    public static void main(String[] args) {
        economiaSistema economiaSistema = new economiaSistema();
        economiaSistema.algoritmoEconomiaSistema(7500, 3846.1959107698917);
        
    }
}

