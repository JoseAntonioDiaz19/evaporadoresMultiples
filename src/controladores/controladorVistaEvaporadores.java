package controladores;
import algoritmos.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.modeloTablaResultados;
import modelo.variablesConocidas;
import vistas.datosCoeficientesGlobales;
import vistas.valoresArreglo;
import vistas.vistaEvaporadores;
/**
 * @author Dizan;
 */
public class controladorVistaEvaporadores {
    vistaEvaporadores vistaEvaporadores;
    datosCoeficientesGlobales  datosCoeficientesGlobales;
    valoresArreglo valoresArreglo;
    variablesConocidas variablesConocidas = new variablesConocidas();
    modeloTablaResultados modeloTablaResultados;
    ArrayList<modeloTablaResultados> datosEvaporadores = new ArrayList<>();

    public controladorVistaEvaporadores(vistaEvaporadores vistaEvaporadores) {
       this.vistaEvaporadores = vistaEvaporadores;
       eventosComponentes();
    }
    
    private void eventosComponentes(){
         vistaEvaporadores.botonIngresarValoresArreglo.addActionListener(this::botonIngresarValoresArreglo);
         vistaEvaporadores.botonIngresarValoresCoeficientesGlobales.addActionListener(this::botonIngresarValoresCoeficientesGlobales);
         vistaEvaporadores.botonVerValoresCoeficientesGlobales.addActionListener(this::botonVerValoresCoeficientesGlobales);
         vistaEvaporadores.botonVerValoresArreglo.addActionListener(this::botonVerValoresArreglo);
         vistaEvaporadores.botonCalcular.addActionListener(this::botonCalcular);
         vistaEvaporadores.botonNuevo.addActionListener(this::botonNuevo);
    }
    
    private void botonIngresarValoresArreglo(ActionEvent e){
        int cantidadMatrices = Integer.parseInt(vistaEvaporadores.fieldArreglo.getText());
        valoresArreglo = new valoresArreglo(cantidadMatrices, vistaEvaporadores, true);
        valoresArreglo.setVisible(true);
        vistaEvaporadores.botonIngresarValoresArreglo.setEnabled(false);
        vistaEvaporadores.botonVerValoresArreglo.setEnabled(true);   
    } 
    
    private void botonIngresarValoresCoeficientesGlobales(ActionEvent e){
        System.out.println("Dentro de boton valores globales");
        int cantidadCoeficientesGlobales = Integer.parseInt(vistaEvaporadores.fieldCoeficientesGlobales.getText());
        datosCoeficientesGlobales =  new datosCoeficientesGlobales(cantidadCoeficientesGlobales,vistaEvaporadores,true);
        datosCoeficientesGlobales.setVisible(true);
        vistaEvaporadores.botonIngresarValoresCoeficientesGlobales.setEnabled(false);
        vistaEvaporadores.botonVerValoresCoeficientesGlobales.setEnabled(true);
    }
    
    private void botonVerValoresCoeficientesGlobales(ActionEvent e){
        datosCoeficientesGlobales.setVisible(true);
    }
    
    private void botonVerValoresArreglo (ActionEvent e){
        valoresArreglo.setVisible(true);
        obtenerVariablesConocidas();
    }
    
    public void botonCalcular(ActionEvent e){
        obtenerVariablesConocidas();
        conversiones conversion = new conversiones();
        double TN = 0;
        double T0 = 0;
              
        //Si se proporciona el valor de la presion
        System.out.println("******************* Paso 2 **********************");
        if (vistaEvaporadores.checkPresionAlimentacion.isSelected()) {
            double presion = variablesConocidas.getP0();
            temperaturaEnfuncionPresion prueba = new temperaturaEnfuncionPresion();
            T0 = prueba.algoritmoTemperaturaEnFuncionPresion(presion);           
        }else{
            T0 = variablesConocidas.getT0();
        }
        
       //Presion del vapor proveniente del ultimo efecto
        if (vistaEvaporadores.checkPresionUltimoEfecto.isSelected()) {
           String valorUnidad = (String) vistaEvaporadores.boxUnidadPresionUltimoEfecto.getSelectedItem();
           double presion = Double.parseDouble(vistaEvaporadores.fieldPresionUltimoEfecto.getText());
            
           if (valorUnidad.equals("atm")) {
                presion = conversion.Presion("atm", presion);
                System.out.println("Convercion desde atm");
            }
            if (valorUnidad.equals("lb/in²")) {
                presion = conversion.Presion("lb/in²", presion);
                System.out.println("Convercion desde lb/in² ");
            }
            if (valorUnidad.equals("lb/ft²")) {
                presion = conversion.Presion("lb/ft²", presion);
                System.out.println("Convercion desde lb/ft²");
            }
            if (valorUnidad.equals("mmHg")) {
                presion = conversion.Presion("mmHg", presion);
                System.out.println("Convercion desde mmHg");
            }
            if (valorUnidad.equals("Kpa")) {
                presion = conversion.Presion("Kpa", presion);
                System.out.println("Convercion desde Kpa");
            }
            //Obtencion de la temperatura a partir de la presion proveniente del ultimo efecto
            temperaturaEnfuncionPresion temperaturaEnfuncionPresion = new temperaturaEnfuncionPresion();
            TN = temperaturaEnfuncionPresion.algoritmoTemperaturaEnFuncionPresion(presion);
        }else{
            TN = variablesConocidas.getTN();
        }
        
        System.out.println("Imprimir condicionantes");
        System.out.println("T0: "+T0);
        System.out.println("TN: "+TN);
        System.out.println("XF: "+variablesConocidas.getXF());
        System.out.println("XAN: "+variablesConocidas.getXAN());
        System.out.println("T0: "+T0);
        System.out.println("P0: "+variablesConocidas.getP0());
        
        
        if (T0 > TN  &&
            variablesConocidas.getXF() < variablesConocidas.getXAN() &&
            T0 < 373.95 ) {
            calculos();
        }else{
            JOptionPane.showMessageDialog(null, "Verifique los datos: ¡¡Datos invalidos!!\n"
                    + ":::::::Vericar:::::::"
                    + "T0 > TN\n"
                    + "XF < XAN\n");
                    
        }
    }
    
    public void obtenerVariablesConocidas(){
        System.out.println("------------ Obteniendo variables conocidas ---------");
        conversiones conversion = new conversiones();
        //Numero de efectos
        if (!"N".equals(vistaEvaporadores.fieldNumeroEfectos.getText())) {
            variablesConocidas.setNumeroEfectos(Integer.parseInt(vistaEvaporadores.fieldNumeroEfectos.getText()));
            System.out.println("----> Numero de efectos = " + variablesConocidas.getNumeroEfectos()); 
        }
      
        //Obtener valores de arreglo
        HashMap < Integer, Integer > arreglo = new HashMap();
        if (!"Escribir la cantidad".equals(vistaEvaporadores.fieldArreglo.getText())) {
                for (int i = 0; i < variablesConocidas.getNumeroEfectos(); i++) {
                //Las claves se forman ---> #evaporador + numero introducido por el usuario en el field
                                                                    //El valor del map es la posicion                                                        
                arreglo.put(Integer.parseInt(valoresArreglo.TextMatriz[i].getText()),i+1);
            } 
            //Imprimir valores de arreglo
            int iteraciones =  variablesConocidas.getNumeroEfectos();
            System.out.println("-----> Arreglo");
            for (int i = 1; i <= iteraciones; i++) {
                System.out.println( "Clave: " + i );
                System.out.println( "Valor: " + arreglo.get(i) );
            }
        }
        //Guardar mapa en el objeto variablesConocidas
        variablesConocidas.setArreglo(arreglo);
        
        //Flujo de alimentacion
        if (!"F".equals(vistaEvaporadores.fieldFlujoAlimentacion.getText())) {
            String valorUnidad = (String) vistaEvaporadores.boxUnidadFlujoAlientacion.getSelectedItem();
            System.out.println("valorUnidad = " + valorUnidad);
            variablesConocidas.setF(Double.parseDouble(vistaEvaporadores.fieldFlujoAlimentacion.getText()));
            if (valorUnidad.equals("lb/h")) { 
                variablesConocidas.setF(conversion.flujoAlimentacion
                        (Double.parseDouble
                            (vistaEvaporadores.fieldFlujoAlimentacion.getText())));
            }
            System.out.println("-------> Flujo de alimentacion"+ variablesConocidas.getF() );
        }
       
        //Concentracion de la alimentacion
        if (!"XF".equals(vistaEvaporadores.fieldConcentracionAlimentacion.getText())) {
            variablesConocidas.setXF(Double.parseDouble(vistaEvaporadores.fieldConcentracionAlimentacion.getText()));
            System.out.println("-------> Concentracion de la alimentacion: "+ variablesConocidas.getXF() );
        }
       
        //Presion del vapor de alimentación
        if (vistaEvaporadores.checkPresionAlimentacion.isSelected()) {
           String valorUnidad = (String) vistaEvaporadores.boxUnidadPresionAlimentacion.getSelectedItem();
           System.out.println("valorUnidad = " + valorUnidad);
           double presion = Double.parseDouble(vistaEvaporadores.fieldPresionVaporAlimentacion.getText());
            
           if (valorUnidad.equals("atm")) {
                presion = conversion.Presion("atm", presion);
                System.out.println("Conversion desde atm: " + presion);
            }
            if (valorUnidad.equals("lb/in²")) {
                presion = conversion.Presion("lb/in²", presion);
                System.out.println("Conversion desde lb/in²: " + presion);
            }
            if (valorUnidad.equals("lb/ft²")) {
                presion = conversion.Presion("lb/ft²", presion);
                System.out.println("Conversion desde lb/ft²: " + presion);
            }
            if (valorUnidad.equals("mmHg")) {
                presion = conversion.Presion("mmHg", presion);
                System.out.println("Conversion desde mmHg: " + presion);
            }
            if (valorUnidad.equals("Kpa")) {
                presion = conversion.Presion("Kpa", presion);
                System.out.println("Conversion desde Kpa: " + presion);
            }
            System.out.println("---------> Presion = " + presion+" Kpa");
            variablesConocidas.setP0(presion);
            
        }
       
        //Temperatura del vapor de alimentación
        if (vistaEvaporadores.checkTemperaturaAlimentacion.isSelected()) {
            String valorUnidad = (String) vistaEvaporadores.boxUnidadTempVaporAlimentacion.getSelectedItem();
            System.out.println("valorUnidad = " + valorUnidad);
            double temperatura = Double.parseDouble(vistaEvaporadores.fieldTemperaturaVaporAlimentacion.getText());
            if (valorUnidad.equals("°F")) {
                temperatura = conversion.temperaturas("F", temperatura);
                System.out.println("Convercion desde °F");
            }
            if (valorUnidad.equals("°C")) {
                temperatura = conversion.temperaturas("C", temperatura);
                System.out.println("Convercion desde °C");
            }
            System.out.println("Temperatura: " + temperatura+" °C");
            variablesConocidas.setT0(temperatura);
        }
        
        //Concentracion final de la solucion concentrada
        if (!"XAN".equals(vistaEvaporadores.fieldConcentracionFinalConcentrada.getText())) {
             variablesConocidas.setXAN(Double.parseDouble(vistaEvaporadores.fieldConcentracionFinalConcentrada.getText()));
             System.out.println("---------> Concentracion final de la solucion concentrada: "+variablesConocidas.getXAN());
        }
      
        //Temperatura del vapor proveniente del ultimo efecto
        if (vistaEvaporadores.checkkTemperaturaUltimoEfecto.isSelected()) {
            String valorUnidad = (String) vistaEvaporadores.boxUnidadTemperaturaUltimoEfecto.getSelectedItem();
            System.out.println("valorUnidad = " + valorUnidad);
            double temperatura = Double.parseDouble(vistaEvaporadores.fieldTemperaturaUltimoEfecto.getText());
            if (valorUnidad.equals("°F")) {
                temperatura = conversion.temperaturas("F", temperatura);
                System.out.println("Convercion desde °F");
            }
            if (valorUnidad.equals("°C")) {
                temperatura = conversion.temperaturas("C", temperatura);
                System.out.println("Convercion desde °C");
            }
            System.out.println("-------> Temperatura del vapor proveniente del ultimo efecto: "+ temperatura+" °C");
            variablesConocidas.setTN(temperatura);
        }
        
        //Presion del vapor proveniente del ultimo efecto
        if (vistaEvaporadores.checkPresionUltimoEfecto.isSelected()) {
           String valorUnidad = (String) vistaEvaporadores.boxUnidadPresionUltimoEfecto.getSelectedItem();
           double presion = Double.parseDouble(vistaEvaporadores.fieldPresionUltimoEfecto.getText());
            
           if (valorUnidad.equals("atm")) {
                presion = conversion.Presion("atm", presion);
                System.out.println("Convercion desde atm");
            }
            if (valorUnidad.equals("lb/in²")) {
                presion = conversion.Presion("lb/in²", presion);
                System.out.println("Convercion desde lb/in² ");
            }
            if (valorUnidad.equals("lb/ft²")) {
                presion = conversion.Presion("lb/ft²", presion);
                System.out.println("Convercion desde lb/ft²");
            }
            if (valorUnidad.equals("mmHg")) {
                presion = conversion.Presion("mmHg", presion);
                System.out.println("Convercion desde mmHg");
            }
            if (valorUnidad.equals("Kpa")) {
                presion = conversion.Presion("Kpa", presion);
                System.out.println("Convercion desde Kpa");
            }
            System.out.println("-------> Presion del vapor proveniente del ultimo efecto: "+variablesConocidas.getPN());
            variablesConocidas.setPN(presion);
            
            //Obtencion de la temperatura a partir de la presion proveniente del ultimo efecto
            temperaturaEnfuncionPresion temperaturaEnfuncionPresion = new temperaturaEnfuncionPresion();
            double temperaturaUltimoEfecto = temperaturaEnfuncionPresion.algoritmoTemperaturaEnFuncionPresion(presion);
            variablesConocidas.setTN(temperaturaUltimoEfecto);
            System.out.println("--------> Temperatura proveniente del ultimo efecto: " + temperaturaUltimoEfecto);
        }
        
        //Coeficientes globales de transferencia de calor
        HashMap < Integer, Double > Un = new HashMap();
        if (!"Escribir la cantidad".equals(vistaEvaporadores.fieldCoeficientesGlobales.getText())) {
            String valorUnidad = (String) vistaEvaporadores.boxUnidadCoeficientesGlobales.getSelectedItem();
            int numeroEfectos = variablesConocidas.getNumeroEfectos();
            double[] coefGlobales = new double[numeroEfectos];
            
            for (int i = 0; i < numeroEfectos; i++) {
                coefGlobales[i] = Double.parseDouble(datosCoeficientesGlobales.TextMatriz[i].getText());
            }
            
            if (valorUnidad.equals("KCal/m²h°C")) {
                for (int i = 0; i < numeroEfectos; i++) {
                    coefGlobales[i] = conversion.coeficientesGlobales(valorUnidad, coefGlobales[i]);
                }
            }
            if (valorUnidad.equals("Btu/ft²h°F")) {
                for (int i = 0; i < numeroEfectos; i++) {
                    coefGlobales[i] = conversion.coeficientesGlobales(valorUnidad, coefGlobales[i]);
                }
            }
            if (valorUnidad.equals("W/m²°C")) {
                for (int i = 0; i < numeroEfectos; i++) {
                    coefGlobales[i] = conversion.coeficientesGlobales(valorUnidad, coefGlobales[i]);
                }
            }
            if (valorUnidad.equals("KJ/m²h°C")) {
                for (int i = 0; i < numeroEfectos; i++) {
                    coefGlobales[i] = conversion.coeficientesGlobales(valorUnidad, coefGlobales[i]);
                }
            }
            for (int i = 0; i < variablesConocidas.getNumeroEfectos(); i++) {
                Un.put((i + 1), coefGlobales[i]);
            }
            
            //Imprimir valores de coeficientes globales
            System.out.println("------> Coeficientes globales");
            for (Map.Entry<Integer, Double> entry : Un.entrySet()) {
                System.out.println("Clave=" + entry.getKey());
                System.out.println("Valor=" + entry.getValue());
            }
        }
        
        //Guardar mapa en el objeto variablesConocidas
        variablesConocidas.setUn(Un);
        
        //capacidadCalorifica
        if (!"Cp".equals(vistaEvaporadores.fieldCapacidadCalorifica.getText())) {
            String valorUnidad = (String) vistaEvaporadores.boxUnidadCapacidadCalorifica.getSelectedItem();
            System.out.println("valorUnidad = " + valorUnidad);
            double cp = Double.parseDouble(vistaEvaporadores.fieldCapacidadCalorifica.getText());
            if (valorUnidad.equals("Cal/g°C")) {
                cp = conversion.capacidadCalorifica(valorUnidad, cp);
                System.out.println("Convercion desde Cal/g°C ");
            }
             if (valorUnidad.equals("Btu/lb°F")) {
                cp = conversion.capacidadCalorifica(valorUnidad, cp);
                System.out.println("Convercion desde Btu/lb°F ");
            }
              if (valorUnidad.equals("KJ/Kg°C")) {
                cp = conversion.capacidadCalorifica(valorUnidad, cp);
                System.out.println("Convercion desde KJ/Kg°C ");
                
            }
            variablesConocidas.setCP(cp);
            System.out.println("-------> Capacidad calorifica: "+variablesConocidas.getCP() );
        }
        
        //Temperatura de alimentacion
        if (!"TF".equals(vistaEvaporadores.fieldTemperaturaAlimentacion.getText())) {
            String valorUnidad = (String) vistaEvaporadores.boxUnidadTemperaturaAlimentacion.getSelectedItem();
            System.out.println("valorUnidad = " + valorUnidad);
            double temperatura = Double.parseDouble(vistaEvaporadores.fieldTemperaturaAlimentacion.getText());
            if (valorUnidad.equals("°F")) {
                temperatura = conversion.temperaturas("F", temperatura);
            }
            if (valorUnidad.equals("°C")) {
                temperatura = conversion.temperaturas("C", temperatura);
            }
            variablesConocidas.setTF(temperatura);
            System.out.println("Temperatura de alimentacion: " + temperatura);
        }   
        
        System.out.println("-------- Variables obtenidas satisfactoriamente---------");
    }
    
    public void botonNuevo(ActionEvent e){
        vistaEvaporadores.dispose();
        vistaEvaporadores vistaEvaporadores2 = new vistaEvaporadores();
        controladorVistaEvaporadores controlVistaEvaporadores2 = new controladorVistaEvaporadores(vistaEvaporadores2);
        vistaEvaporadores2.setVisible(true);
    }
    
    private void calculos(){
        boolean desicion;
        HashMap < Integer, Double > flujosCalor = new HashMap();
       
        HashMap < Integer, Double > nuevasTemperaturas = new HashMap();
        HashMap < Integer, Double > entalpias = new HashMap();
        HashMap < Integer, Double > flujosVapor = new HashMap();
        HashMap < Integer, Double > presionesEvap = new HashMap();
        HashMap < Integer, Double > flujosLiqConcent = new HashMap();
        HashMap < Integer, Double > concentracionesLiqConcent = new HashMap();
        HashMap < Integer, Double > areas = new HashMap();
        
        //Paso 1 se obtienen las varialbes conocidas y se guardan en un objeto
        System.out.println("******************* Paso 1 **********************");
        
        //Si se proporciona el valor de la presion
         System.out.println("******************* Paso 2 **********************");
        if (vistaEvaporadores.checkPresionAlimentacion.isSelected()) {
            double presion = variablesConocidas.getP0();
            temperaturaEnfuncionPresion prueba = new temperaturaEnfuncionPresion();
            double temperaturaCero = prueba.algoritmoTemperaturaEnFuncionPresion(presion);
            variablesConocidas.setT0(temperaturaCero);
            System.out.println("temperaturaCero = " + temperaturaCero);
           
        }
        //Si se proporciona el valor de la temperatura
        if (vistaEvaporadores.checkTemperaturaAlimentacion.isSelected()) {
            double temperatura = Double.parseDouble(vistaEvaporadores.fieldTemperaturaVaporAlimentacion.getText());
            System.out.println("temperatura = " + temperatura);
        }
         System.out.println("******************* Paso 3 **********************");
         System.out.println("Se realiza el Cálculo de temperaturas mediante la fórmula 1  "); 
         temperaturasFormulaUno temperaturasFormulaUno = new temperaturasFormulaUno(); 
         HashMap < Integer, Double > primerasTemperaturas = new HashMap();
         primerasTemperaturas = temperaturasFormulaUno.algoritmoTemperaturaFormulaUno(variablesConocidas.getT0(), 
                                                                                      variablesConocidas.getTN(), 
                                                                                      variablesConocidas.getNumeroEfectos(),
                                                                                      variablesConocidas.getUn(), 
                                                                                      variablesConocidas.getNumeroEfectos());
        
        do{     
        //Calculo de entalpias
        System.out.println("******************* Paso 4   **********************");
        calculoEntalpias calculoEntalpias = new calculoEntalpias();
        entalpias = calculoEntalpias.algoritmoCalcularEntalpias(primerasTemperaturas);
        
        //:::::::::::::::: Calculo de la matriz caracteristico ::::::::::::::::::::::::::::::::::
        System.out.println("******************* Paso 5 **********************");
        int filas = variablesConocidas.getNumeroEfectos() + 1;
        int columnas = variablesConocidas.getNumeroEfectos() + 2;
        HashMap < Integer, Integer > posicion_temperatura = new HashMap();
        HashMap < Integer, Integer > arreglo = new HashMap();
        arreglo = variablesConocidas.getArreglo();
        System.out.println("-----Temperatura/Posicion-----");
        //impresion
        for (int i = 1; i <= variablesConocidas.getNumeroEfectos(); i++) {
            posicion_temperatura.put(arreglo.get(i),i );
            System.out.println( "Temperat: " + i );
            System.out.println( "Posicion: " + arreglo.get(i) );
        }
        HashMap < Integer, Double > temperaturasMatriz = new HashMap();
        for (int i = 1; i <= variablesConocidas.getNumeroEfectos(); i++) {
            temperaturasMatriz.put(i, primerasTemperaturas.get(i));
        }
        
       crearMatrizCaracteristica matrizCaracteristica = new crearMatrizCaracteristica(
                filas, 
                columnas,
                entalpias,
                temperaturasMatriz,
                arreglo,
                posicion_temperatura,
                variablesConocidas.getCP(),
                variablesConocidas.getF(),
                variablesConocidas.getXF(),
                variablesConocidas.getXAN(),
                variablesConocidas.getTF()
               
        );
              
       flujosVapor = matrizCaracteristica.Resultado(); //---------->>> Resultados de la matriz flujos de vapor
       
       for (int i = 0; i < flujosVapor.size(); i++) {
            System.out.println( "V" + i +":");
            System.out.println( "Posicion: " + flujosVapor.get(i) );
        }
       
       //Cálculo de los flujos de calor mediante la fórmula 2
       System.out.println("******************* Paso 6 **********************");
       flujoDeCalor flujosDeCalor = new  flujoDeCalor();
       flujosCalor = flujosDeCalor.algoritmo(entalpias, flujosVapor);
       
       // Cálculo de áreas mediante la fórmula 3
        System.out.println("******************* Paso 7 **********************");
        areas_Y_Promedio areas_Y_Promedio = new areas_Y_Promedio();
        areas = areas_Y_Promedio.algoritmoCalculoAreas(flujosCalor, 
                                                       variablesConocidas.getUn(), 
                                                       primerasTemperaturas);
        
        //Cálculo de área promedio
        System.out.println("******************* Paso 8 **********************");
        double areaPromedio = areas_Y_Promedio.getAreaPromedio();
        System.out.println("areaPromedio = " + areaPromedio);
        
        //Decisión
         System.out.println("******************* Paso 9 **********************");
        /*Las áreas calculadas en 7) deben ser iguales, o al menos diferir entre sí en un ±1% de error. 
          Cuando lo anterior se cumpla, se puede pasar al paso 12), 
          de lo contrario se deberá seguir con el paso 10).
        */
        desicionAreas desicionAreas = new desicionAreas();
        desicion = desicionAreas.algoritmoDesicion(areas);
        System.out.println("desicionAreas = " + desicion);
        
        for (int i = 1; i <= areas.size(); i++) {
            System.out.println( "Area " + i +": " + areas.get(i));
        }
        
        //Cálculo de nuevas temperaturas 
         System.out.println("******************* Paso 10 **********************");
        nuevasTemperaturas nuevasTemp = new nuevasTemperaturas();
        nuevasTemperaturas = nuevasTemp.algoritmoNuevasTemperaturas(areas,
                                                                    variablesConocidas.getTN(),
                                                                    variablesConocidas.getT0(), 
                                                                    areaPromedio, 
                                                                    primerasTemperaturas);
        

        primerasTemperaturas = nuevasTemperaturas;
         System.out.println("******************* Paso 11 **********************");
        }while(desicion == false);
        System.out.println("Salí del ciclo");
        
        //Impresion temperaturas finales
        System.out.println("---------------------Temperaturas finales----------------------");
         for (int i = 1; i <= nuevasTemperaturas.size(); i++) {
            System.out.println( "Temp. Final" + i +":");
            System.out.println( "Valor: " + nuevasTemperaturas.get(i) );
        }
        
        //Cálculo de las presiones en cada evaporador en función de las temperaturas, 
        //empleando las ecuaciones de la tabla 3.
         System.out.println("******************* Paso 12 **********************");
        presionesEvaporadoresEnFuncionTemperaturas presionesEvapFunTemp = new presionesEvaporadoresEnFuncionTemperaturas();      
        presionesEvap = presionesEvapFunTemp.algoritmoPresionesEnFuncionTemperaturas(nuevasTemperaturas);
        
        //Cálculo de los flujos de líquido concentrado.
        HashMap < Integer, Integer > posicion_evaporador = new HashMap();
        HashMap < Integer, Integer > evaporador_posicion = new HashMap();
        evaporador_posicion  = variablesConocidas.getArreglo();
        for (int i = 1; i <= variablesConocidas.getNumeroEfectos(); i++) {
            posicion_evaporador.put(evaporador_posicion.get(i),i );
            System.out.println("Posicion: "+evaporador_posicion.get(i)+ " Evaporador: " + i );
        }
        for (int i = 1; i <= variablesConocidas.getNumeroEfectos(); i++) {
            System.out.println("Evaporador: "+evaporador_posicion.get(i)+" Posicion"+1);
        }
        
        System.out.println("******************* Paso 13 **********************");
        flujoDeLiquidosConcentrados flujoDeLiquidosConcentrados = new flujoDeLiquidosConcentrados();
        flujosLiqConcent = flujoDeLiquidosConcentrados.algoritmoflujoDeLiquidosConcentrados(variablesConocidas.getF(), 
                                                                                            flujosVapor, 
                                                                                            posicion_evaporador,
                                                                                            evaporador_posicion);
        
        //Cálculo de las concentraciones de los líquidos concentrados.
         System.out.println("******************* Paso 14 **********************");
        concentracionesLiquidosConcentrados concentracionesLiquidosConcentrados = new concentracionesLiquidosConcentrados();
        concentracionesLiqConcent = concentracionesLiquidosConcentrados.calculoConcentracionesLiquidosConcentrados(variablesConocidas.getF(), 
                                                                                                                   variablesConocidas.getXF(), 
                                                                                                                   flujosLiqConcent);
       
         //Cálculo de la capacidad del sistema
         System.out.println("******************* Paso 16 **********************");
        capacidadSistema capacidadSistema = new capacidadSistema();
        double capacidadDelSistema = capacidadSistema.algoritmoCapacidadSistema(flujosVapor);
        System.out.println("capacidadDelSistema = " + capacidadDelSistema);

        //Cálculo de la economía del sistema
         System.out.println("******************* Paso 15 **********************");
        economiaSistema economiaSistema = new economiaSistema();
        double economiaDelSistema = economiaSistema.algoritmoEconomiaSistema(capacidadDelSistema,flujosVapor.get(0));
        
        System.out.println("economiaDelSistema = " + economiaDelSistema);
        
       
        
        //********************************ENVIAR DATOS A LA INTERFAZ **********************************
        
        vistaEvaporadores.fieldEconomiaSistema.setText(""  + economiaDelSistema);
        vistaEvaporadores.fieldCapacidadSistema.setText("" + capacidadDelSistema);
        vistaEvaporadores.fieldV0.setText(""+flujosVapor.get(0)); 
        
        //-------------------------------------CREACION DEL MODELO DE LA TABLA------------------------------------
        
        for (int i = 1; i <= variablesConocidas.getNumeroEfectos(); i++) {
            datosEvaporadores.add(new modeloTablaResultados  (
                                                        i,
                                                        entalpias.get(i),
                                                        flujosVapor.get(i),
                                                        presionesEvap.get(i),
                                                        flujosLiqConcent.get(i),
                                                        concentracionesLiqConcent.get(i),
                                                        areas.get(i),
                                                        nuevasTemperaturas.get(i)
                                                        
            ));
        } 
        
        vistaEvaporadores.modeloTabla.setRowCount(0);
        
        for (int i = 1; i <= variablesConocidas.getNumeroEfectos(); i++) {
            vistaEvaporadores.modeloTabla.addRow(new Object[]{
                datosEvaporadores.get(posicion_evaporador.get(i)-1).getEvaporador(),
                datosEvaporadores.get(posicion_evaporador.get(i)-1).getEntalpia(),
                datosEvaporadores.get(posicion_evaporador.get(i)-1).getFlujoVapor(),
                datosEvaporadores.get(posicion_evaporador.get(i)-1).getPresion(),
                datosEvaporadores.get(posicion_evaporador.get(i)-1).getFlujoLiquido(),
                datosEvaporadores.get(posicion_evaporador.get(i)-1).getConcentracionLiquido(),
                datosEvaporadores.get(posicion_evaporador.get(i)-1).getSuperficieCalefaccion(),
                datosEvaporadores.get(posicion_evaporador.get(i)-1).getTemperaturasFinales(),
            });
        }
    }
}