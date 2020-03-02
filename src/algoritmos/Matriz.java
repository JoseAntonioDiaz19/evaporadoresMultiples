package algoritmos;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class Matriz 
{
          
private static final double EPSILON = 1e-8;

private  int N;      

private double[][] a;     

public Matriz()
{
    
}
public Matriz(double[][] A, double[] b) 

{
    
    N = b.length;

    
    
    a = new double[N][N+N+1];
    
    for (int i = 0; i < N; i++)
        
        for (int j = 0; j < N; j++)
            
            a[i][j] = A[i][j];

    
    
    for (int i = 0; i < N; i++)
        
        a[i][N+i] = 1.0;

    for (int i = 0; i < N; i++) 
        
        a[i][N+N] = b[i];

    resolver();

    assert check(A, b);

}

private void resolver() 

{
    
    
    
    for (int p = 0; p < N; p++) 
    
    {
        
        int max = p;
        
        for (int i = p+1; i < N; i++) 
        
        {
            
            if (Math.abs(a[i][p]) > Math.abs(a[max][p])) 
            
            {
                
                max = i;
            
            }
        
        }

      
        
        intercambio(p, max);

        
        
        if (Math.abs(a[p][p]) <= EPSILON) 
        
        {
            
            continue;
            
          
        
        }
        pivot(p, p);
    }
}

// swap row1 and row2

private void intercambio(int row1, int row2) 

{
    
    double[] temp = a[row1];
    
    a[row1] = a[row2];
    
    a[row2] = temp;

}


// pivot on entry (p, q) usando Gauss-Jordan 

private void pivot(int p, int q) 

{   // everything but row p and column q
    
    for (int i = 0; i < N; i++) {
        
        double alpha = a[i][q] / a[p][q];
        
        for (int j = 0; j <= N+N; j++) 
        
        {
            
            if (i != p && j != q) a[i][j] -= alpha * a[p][j];
        
        }
    
    }

    // cero fuera de la columna q
    
    for (int i = 0; i < N; i++)
        
        if (i != p) a[i][q] = 0.0;

    // scale row p (ok to go from q+1 to N, but do this for consistency with simplex pivot)
    
    for (int j = 0; j <= N+N; j++)
        
        if (j != q) a[p][j] /= a[p][q];
    
    a[p][q] = 1.0;

}

// extract solution to Ax = b

public double[] primal() 

{
    
    double[] x = new double[N];
    
    for (int i = 0; i < N; i++) 
    
    {
        
        if (Math.abs(a[i][i]) > EPSILON)
            
            x[i] = a[i][N+N] / a[i][i];
        
        else if (Math.abs(a[i][N+N]) > EPSILON)
            
            return null;
    }
    
    return x;

}

// extract solution to yA = 0, yb != 0

public double[] dual() 

{
    
    double[] y = new double[N];
    
    for (int i = 0; i < N; i++) 
    
    {
        
        if ( (Math.abs(a[i][i]) <= EPSILON) && (Math.abs(a[i][N+N]) > EPSILON) ) 
        
        {
            
            for (int j = 0; j < N; j++)
                
                y[j] = a[i][N+j];
            
            return y;
        
        }
    
    }
    
    return null;

}


// does the system have a solution?

public boolean factible() 

{
    
    return primal() != null;

}

// print the tableaux

private void mostrar() 

{
    
    for (int i = 0; i < N; i++) 
    
    {
        
        for (int j = 0; j < N; j++) 
        
        {
            
            System.out.print(" "+a[i][j]);
        
        }
        
        System.out.print("| ");
        
        for (int j = N; j < N+N; j++) 
        
        {
        	
          System.out.print(" "+a[i][j]);
        
        }
        
        
        System.out.print("| \n"+a[i][N+N]);
    
    }
    
    System.out.println();

}


// verifica que  Ax = b or yA = 0, yb != 0

private boolean check(double[][] A, double[] b) 

{

    
    
    if (factible()) 
    
    {
        
        double[] x = primal();
        
        for (int i = 0; i < N; i++) 
        
        {
            
            double sum = 0.0;
            
            for (int j = 0; j < N; j++) 
            
            {
                 
                 sum += A[i][j] * x[j];
            
            }
            
            if (Math.abs(sum - b[i]) > EPSILON) 
            
            {
            	
              System.out.println("no factible");
            	
              System.out.println(i+" = "+b[i]+", sum = "+sum+"\n");
               
               return false;
            
            }
        
        }
        
        return true;
    
    }

    
    
    else 
    
    {
        
        double[] y = dual();
        
        for (int j = 0; j < N; j++) 
        
        {
            
            double sum = 0.0;
            
            for (int i = 0; i < N; i++) 
            
            {
                 
                 sum += A[i][j] * y[i];
            
            }
            
            if (Math.abs(sum) > EPSILON) 
            
            {
                
                System.out.println("certificado de inviabilidad no válido");
                
                System.out.println("sum = "+sum+"\n");
                
                return false;
            
            }
        
        }
        
        double sum = 0.0;
        
        for (int i = 0; i < N; i++) 
        
        {
            
            sum += y[i] * b[i];
        
        }
        
        if (Math.abs(sum) < EPSILON) 
        
        {
        	
          System.out.println("Certificado de inviabilidad no valido");
        	
          System.out.println("yb  = "+sum+"\n");

            
            return false;
        
        }
        
        return true;
    
    }

}


public  HashMap <Integer, Double> test(double[][] A, double[] b) 

{
    HashMap < Integer, Double > Res= new HashMap();
    
    Matriz gaussian = new Matriz(A, b);
    
    if (gaussian.factible()) 
    {
    	
      System.out.println("Solucion a Ax = b");
        
        double[] x = gaussian.primal();
        
        for (int i = 0; i < x.length; i++) 
        
        {
        	
          System.out.println(" El valor de la  incognita ["+i+"] = "+x[i]+"\n");
        Res.put(i, x[i]);
        }
    return Res;
    }
    
    else 
    
    {
    	
      System.out.println("Certificado de inviabilidad");
        
        double[] y = gaussian.dual();
        
        for (int j = 0; j < y.length; j++) 
        
        {
        	
          System.out.println(" "+y[j]+"\n");
        
        }
    }
    
    System.out.println();
    return null;

}

public static void main(String[] args) 

{

	Scanner input = new Scanner(System.in);
	
  System.out.println("Ingrese la cantidad de variables en las ecuaciones: ");
	
  int n = input.nextInt();
    
    System.out.println("Ingrese los coeficientes de cada variable para cada ecuación");
    
    System.out.println("ax + by + cz + ... = d");
    
    double [][]mat = new double[n][n];
    
    double []constants = new double[n];
    
    
    
    for(int i=0; i<n; i++)
    
    {
   
        for(int j=0; j<n; j++)
        
        {
            
            mat[i][j] = input.nextDouble();
        
        }
        
        constants[i] = input.nextDouble();
    
    }
    Matriz r= new Matriz();
    r.test(mat, constants);          

}
    
}
