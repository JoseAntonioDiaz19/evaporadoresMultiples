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
public class crearMatrizCaracteristica 
{
    
    Matriz r= new Matriz();
    HashMap < Integer, Double > lambda = new HashMap();
    HashMap < Integer, Double > Resultados = new HashMap();
    double CP;
              //Clave  //Valor
    HashMap < Integer, Double > temperaturas= new HashMap();
    
              //temp //Posicion
    HashMap < Integer, Integer > temperatura_Posicion = new HashMap();
              //posicion //temp
    HashMap < Integer, Integer > Posicion_Temp = new HashMap();
    
    /*Pasos para encontrar el sustraendo de los 
      Terminos independientes:
    
      Con el valor de la temperatura(Minuendo) buscar la clave de la misma
      con la clave obtenida 
  
      Con la posicion formo la clave para obtener el valor 
      de la temperatura.
    */
   
    double XF;
    double Xn;
    double F;
    HashMap < String, Integer > arreglo;
    double matriz[][];
    int filas;
    int columnas;
    int arregloT[];
    double TF;
   
    public crearMatrizCaracteristica() {
        }
    
    public crearMatrizCaracteristica(int filas, 
                                     int columnas,
                                     HashMap < Integer, Double > lambda ,
                                     HashMap < Integer, Double > temperaturas, 
                                     HashMap < Integer, Integer > temperatura_Posicion,
                                     HashMap < Integer, Integer > Posicion_Temp, 
                                     double CP, 
                                     double F,
                                     double XF,
                                     double Xn,
                                     double TF){
        this.CP =CP;
        this.F=F;
        this.XF=XF;
        this.Xn=Xn;
        this.filas = filas;
        this.columnas = columnas;
        this.lambda = lambda;
        this.temperaturas=temperaturas;
        this.temperatura_Posicion = temperatura_Posicion;
        this.Posicion_Temp = Posicion_Temp;
        matriz = new double[filas][columnas];
        this.TF = TF;
    }
    
    public void llenarCeros()
    {

        for (int i = 0; i < (columnas-1); i++) 
        {
            if (i == 0) 
            {
                 matriz[i][0] = lambda.get(i);
            }
            else
            {
                 matriz[i-1][i] = (lambda.get(i)*(-1));
                     matriz[i][i] = (lambda.get(i));
            }
        
        }
        
        for (int i = 1; i < columnas; i++) 
        {
            matriz[filas-1][i]=1;
        }
    }
    
    public void Coef()
    {
        for (int i = 0;i < filas-1; i++) 
        {
              matriz[i][columnas-1]=F*CP*(temperaturas.get(i+1) - ubicarTemp(i+1) ) ; 
        }
        //F(1-(XF/XN))

        matriz[filas-1][columnas-1]=F*(1-(XF/Xn));
        
    }
    
    public  double ubicarTemp(int i)
    {
        int p=(temperatura_Posicion.get(i));
        if(p>1)
        {
            return temperaturas.get(Posicion_Temp.get(p-1));
        }else
        {
          
            return TF;
        }
        
    }
    
    public int ubicarTemp2(int i)
    {
            int p = (temperatura_Posicion.get(i));
        
        if(p>1)
        {
         return p;
        }
        
        
     return 0;  
     
    }
    
    public void tempPosAn(int i,int fila)
    {
        
        for (int j = i-1; j >0  ; j--) 
        {
            int p = Posicion_Temp.get(j);
            System.out.println("posicion a colocar operacion: " +p);
            matriz[fila-1][Posicion_Temp.get(j)]=matriz[fila-1][Posicion_Temp.get(j)]+(matriz[fila-1][columnas-1]/F);
            
            System.out.println("se intoduj: "+matriz[fila-1][Posicion_Temp.get(j)]+(matriz[fila-1][columnas-1]/F));
            System.out.println("en posicion fila:"+(fila-1)+"   Columna:"+Posicion_Temp.get(j));
        }
        

    }
    public void llenadoFilas()
    {
        int t=0;
    for (int i = 1; i <= temperaturas.size(); i++) 
        {
            
        System.out.println("temperatura: "+i);
        
       tempPosAn(ubicarTemp2(i),i);
        
        }
    
    }
    
    public HashMap <Integer,Double> Resultado()
    {
         llenarCeros();
         Coef();
         llenadoFilas();
    int n= matriz.length;
    double [][]mat = new double[n][n];
    double []constants = new double[n];
    
        for (int i = 0; i < n; i++) 
        {
            constants[i]=matriz[i][matriz[i].length-1];
            System.out.println("constantes: "+i+" = "+matriz[i][matriz[i].length-1] );
        }
    
        for (int x=0; x < matriz.length; x++) 
        {
            System.out.print("|");
            for (int y=0; y < matriz[x].length-1; y++) 
            {
              System.out.print (matriz[x][y]);
                mat[x][y]=matriz[x][y];
              if (y!=matriz[x].length-1) System.out.print("\t");
              
            }
            System.out.println("|");
        }
        
        return r.test(mat, constants);
    }
       
    public static void main(String[] args) {
        
         HashMap < Integer, Double > lambda = new HashMap();
         lambda.put(0, 12.0);
         lambda.put(1, 13.0);
         lambda.put(2, 14.0);
         lambda.put(3, 15.0);
         lambda.put(4, 16.0);
         lambda.put(5, 17.0);
         
         HashMap < Integer, Double > temperaturas = new HashMap();
         //clave = clave temperatura valor = temperatura en grados C
         temperaturas.put(1,2.0);
         temperaturas.put(2,3.0);
         temperaturas.put(3,4.0);
         temperaturas.put(4,5.0);
         temperaturas.put(5,6.0);

         HashMap < Integer, Integer > temperatura_Posicion= new HashMap();
         //clave = clave de la temperatura y el valor = Posicion
         temperatura_Posicion.put(2,1);
         temperatura_Posicion.put(3,2);
         temperatura_Posicion.put(5,3);
         temperatura_Posicion.put(1,4);     
         temperatura_Posicion.put(4,5);
         
         HashMap < Integer, Integer > Posicion_Temp= new HashMap();
         //clave = posicion valor = clave de la temperatura
         Posicion_Temp.put(1,2);
         Posicion_Temp.put(2,3);
         Posicion_Temp.put(3,5);
         Posicion_Temp.put(4,1);
         Posicion_Temp.put(5,4);

    //crearMatrizCaracteristica matriz = new crearMatrizCaracteristica(6,7, lambda,temperaturas,temperatura_Posicion,Posicion_Temp);
    // matriz.Resultado()
    //System.out.println(matriz.ubicarTemp2(5));
        
     
    } 
}
