package modelo;

import java.util.HashMap;
/**
 *
 * @author Dizan
 */
public class variablesConocidas {

    private int numeroEfectos; //N
         //Evaporador | Posicion
    private HashMap < Integer, Integer > arreglo;
    private double F; //Flujo de alimentacion
    private double XF; //Concentracion de la alimentacion
    private double P0; //Presion del vapor de alimentacion
    private double T0; //Temperatura del vapor de alimentacion
    private double XAN; //Concentracion final de la solucion concentrada
    private double PN; //Presion del vapor proveniente del ultimo efecto
    private double TN; //Temperatura del vapor proveniente del ultimo efecto
            //NumeroCoe | coeficiente
    private HashMap < Integer, Double > Un;//Coeficientes globales de transferencia de calor
    private double CP; //Capacidad calorifica
    private double TF; //Temperatura de la alimentacion
    private double PF; //Presion de la alimentacion

    /**
     * @return the numeroEfectos
     */
    public int getNumeroEfectos() {
        return numeroEfectos;
    }

    /**
     * @param numeroEfectos the numeroEfectos to set
     */
    public void setNumeroEfectos(int numeroEfectos) {
        this.numeroEfectos = numeroEfectos;
    }

    /**
     * @return the arreglo
     */
    public HashMap < Integer, Integer > getArreglo() {
        return arreglo;
    }

    /**
     * @param arreglo the arreglo to set
     */
    public void setArreglo(HashMap < Integer, Integer > arreglo) {
        this.arreglo = arreglo;
    }

    /**
     * @return the F
     */
    public double getF() {
        return F;
    }

    /**
     * @param F the F to set
     */
    public void setF(double F) {
        this.F = F;
    }

    /**
     * @return the XF
     */
    public double getXF() {
        return XF;
    }

    /**
     * @param XF the XF to set
     */
    public void setXF(double XF) {
        this.XF = XF;
    }

    /**
     * @return the P0
     */
    public double getP0() {
        return P0;
    }

    /**
     * @param P0 the P0 to set
     */
    public void setP0(double P0) {
        this.P0 = P0;
    }

    /**
     * @return the T0
     */
    public double getT0() {
        return T0;
    }

    /**
     * @param T0 the T0 to set
     */
    public void setT0(double T0) {
        this.T0 = T0;
    }

    /**
     * @return the XAN
     */
    public double getXAN() {
        return XAN;
    }

    /**
     * @param XAN the XAN to set
     */
    public void setXAN(double XAN) {
        this.XAN = XAN;
    }

    /**
     * @return the PN
     */
    public double getPN() {
        return PN;
    }

    /**
     * @param PN the PN to set
     */
    public void setPN(double PN) {
        this.PN = PN;
    }

    /**
     * @return the TN
     */
    public double getTN() {
        return TN;
    }

    /**
     * @param TN the TN to set
     */
    public void setTN(double TN) {
        this.TN = TN;
    }

    /**
     * @return the Un
     */
    public HashMap < Integer, Double > getUn() {
        return Un;
    }

    /**
     * @param Un the Un to set
     */
    public void setUn(HashMap < Integer, Double > Un) {
        this.Un = Un;
    }

    /**
     * @return the CP
     */
    public double getCP() {
        return CP;
    }

    /**
     * @param CP the CP to set
     */
    public void setCP(double CP) {
        this.CP = CP;
    }

    /**
     * @return the TF
     */
    public double getTF() {
        return TF;
    }

    /**
     * @param TF the TF to set
     */
    public void setTF(double TF) {
        this.TF = TF;
    }

    /**
     * @return the PF
     */
    public double getPF() {
        return PF;
    }

    /**
     * @param PF the PF to set
     */
    public void setPF(double PF) {
        this.PF = PF;
    }

    
}
