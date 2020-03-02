/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;
/**
 * @author Dizan
 */
public class conversiones {
    
    public double precionVaporAlimentacionKPa(double KPa){
        return KPa;
    }
    
    public double precionVaporAlimentacionAtm(double atm){
        double convercion = atm * ( 101.328 / 1);
        return convercion;
    }
    
    public double flujoAlimentacion(double lb)
    {
        double kg = lb * 0.45359237;
        return kg;
    }
    
    public double temperaturas(String unidad, double valor)
    {
        switch(unidad)
                {
            case "F":
                return (valor-32)/1.8;
            case "C":
                return valor;
                }
        return 0;
    }
    public double Presion(String unidad, double valor)
    { 
        switch(unidad)
        {
            case "atm":
                return valor*101.3171;
            case "lb/in²":
                return valor*6.894745;
            case "lb/ft²":
                return valor*0.0478802;
            case "mmHg":
                return valor*0.1333223;
            case "Kpa":
                return valor;
        }
        return 0;
    }
    
    public double capacidadCalorifica(String unidad, double valor)
    {
        switch(unidad)
        {
            case "Cal/g°C":
                return valor*4.184;
            case "Btu/lb°F":
                return valor*1.292227289;
            case "KJ/Kg°C":
                return valor;
        }
        return 0;
    }
    
    public double coeficientesGlobales(String unidad, double valor)
    {
        switch(unidad)
        {
            case "KCal/m²h°C":
                return valor*4.184;
            case "Btu/ft²h°F":
                return valor*6.309206695;
            case "W/m²°C":
                return valor*3.6;
            case "KJ/m²h°C":
                return valor;
        }
        return 0;
    }
    
    public static void main(String[] args) {
        conversiones conversiones= new conversiones();
        conversiones.precionVaporAlimentacionAtm(2.2);
        System.out.println(conversiones.temperaturas("C", 2));
    }
}
