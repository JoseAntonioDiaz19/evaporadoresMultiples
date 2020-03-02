/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
/**
 * @author Dizan
 */
public class modeloTablaResultados {
    
    private int evaporador;
    private double entalpia;
    private double flujoVapor;
    private double presion;
    private double flujoLiquido;
    private double concentracionLiquido;
    private double superficieCalefaccion;
    private double temperaturasFinales;

    public modeloTablaResultados(int evaporador, double entalpia, double flujoVapor, double presion, double flujoLiquido, double concentracionLiquido, double superficieCalefaccion, double temperaturasFinales) {
        this.evaporador = evaporador;
        this.entalpia = entalpia;
        this.flujoVapor = flujoVapor;
        this.presion = presion;
        this.flujoLiquido = flujoLiquido;
        this.concentracionLiquido = concentracionLiquido;
        this.superficieCalefaccion = superficieCalefaccion;
        this.temperaturasFinales = temperaturasFinales;
    }

    
    /**
     * @return the evaporador
     */
    public int getEvaporador() {
        return evaporador;
    }

    /**
     * @param evaporador the evaporador to set
     */
    public void setEvaporador(int evaporador) {
        this.evaporador = evaporador;
    }

    /**
     * @return the entalpia
     */
    public double getEntalpia() {
        return entalpia;
    }

    /**
     * @param entalpia the entalpia to set
     */
    public void setEntalpia(double entalpia) {
        this.entalpia = entalpia;
    }

    /**
     * @return the flujoVapor
     */
    public double getFlujoVapor() {
        return flujoVapor;
    }

    /**
     * @param flujoVapor the flujoVapor to set
     */
    public void setFlujoVapor(double flujoVapor) {
        this.flujoVapor = flujoVapor;
    }

    /**
     * @return the presion
     */
    public double getPresion() {
        return presion;
    }

    /**
     * @param presion the presion to set
     */
    public void setPresion(double presion) {
        this.presion = presion;
    }

    /**
     * @return the flujoLiquido
     */
    public double getFlujoLiquido() {
        return flujoLiquido;
    }

    /**
     * @param flujoLiquido the flujoLiquido to set
     */
    public void setFlujoLiquido(double flujoLiquido) {
        this.flujoLiquido = flujoLiquido;
    }

    /**
     * @return the concentracionLiquido
     */
    public double getConcentracionLiquido() {
        return concentracionLiquido;
    }

    /**
     * @param concentracionLiquido the concentracionLiquido to set
     */
    public void setConcentracionLiquido(double concentracionLiquido) {
        this.concentracionLiquido = concentracionLiquido;
    }

    /**
     * @return the superficieCalefaccion
     */
    public double getSuperficieCalefaccion() {
        return superficieCalefaccion;
    }

    /**
     * @param superficieCalefaccion the superficieCalefaccion to set
     */
    public void setSuperficieCalefaccion(double superficieCalefaccion) {
        this.superficieCalefaccion = superficieCalefaccion;
    }

    /**
     * @return the temperaturasFinales
     */
    public double getTemperaturasFinales() {
        return temperaturasFinales;
    }

    /**
     * @param temperaturasFinales the temperaturasFinales to set
     */
    public void setTemperaturasFinales(double temperaturasFinales) {
        this.temperaturasFinales = temperaturasFinales;
    }
    
}
