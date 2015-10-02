/**
 * UNIVERSIDAD METROPOLITANA
 * DEPARTAMENTO DE GESTION DE LA TECNOLOGIA
 * MODELACION DE SISTEMAS EN REDES
 * PROFESOR: MSc. JOSE G. HERNANDEZ R.
 * 
 *------------------------------------------------------------------------------
 *                   IMPLEMENTACION DE ALGORITMO KRUSKAL
 *------------------------------------------------------------------------------
 * 
 * @author Guillermo Grande
 * Caracas, 28 de septiembre de 2015.
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal 
{
    //--------------------------------------------------------------------------
    //                              VARIABLES
    //--------------------------------------------------------------------------
    
    static final int MAX = 999;  //maximo numero de nodos
    static int V , E;      //numero de nodos y lados
    static Edge arista[] = new Edge[ MAX ];//Arreglo de aristas para el uso en kruskal
    static Edge MST[] = new Edge[ MAX ];//Arreglo de lados del MST encontrado
    
    //--------------------------------------------------------------------------
    //                               MAIN
    //--------------------------------------------------------------------------
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner( System.in );
        System.out.println("****************************************************");
        System.out.println("           Inicio de algoritmo Kruskal");
        System.out.println("****************************************************");
        System.out.println("a. Introduzca el numero de nodos en el grafo: ");
        V = sc.nextInt(); 
        System.out.println("b. Introduzca el numero de lados del grafo: ");
        E = sc.nextInt();
		
	//Realizamos el ingreso del grafo, almacenando los lados en un arreglo con los datos respectivos
        System.out.println("\n****************************************************");
        System.out.println("              Arme lista de lados");
        for( int i = 0 ; i < E ; ++i )
        {
	    arista[ i ] = new Edge();

            System.out.println("****************************************************");
            System.out.println("a. Introduzca nodo origen: ");
	    arista[ i ].origen = sc.nextInt();
            System.out.println("b. Introduzca nodo destino: ");
	    arista[ i ].destino = sc.nextInt();
            System.out.println("c. Introduzca el peso correspondiente: ");
	    arista[ i ].peso = sc.nextInt();
            System.out.println("****************************************************");
	}
        
        calcularKruskal();
    }
    
    //--------------------------------------------------------------------------
    //                      METODOS UNION-FIND
    //--------------------------------------------------------------------------
    
    static int padre[] = new int[ MAX ];  //Este arreglo contiene el padre del i-esimo nodo

    //Método de inicialización
    static void MakeSet( int n )
    {    
        for( int i = 1 ; i <= n ; ++i ) padre[ i ] = i;
    }
	
    //Método para encontrar la raiz del nodo actual X
    static int Find( int x )
    {    
        return ( x == padre[ x ] ) ? x : ( padre[ x ] = Find( padre[ x ] ) );
    }
	
    //Método para unir 2 componentes conexas
    static void Union( int x , int y )
    {    
        padre[ Find( x ) ] = Find( y );
    }
	
    //Método que me determina si 2 nodos estan o no en la misma componente conexa	
    static boolean sameComponent( int x , int y )
    {    
        if( Find( x ) == Find( y ) ) 
        {
            return true;
        }
        else
        {
            return false;
        }    
    }
    
    //--------------------------------------------------------------------------
    //                         ORDENAR LADOS
    //--------------------------------------------------------------------------
	
    static class Edge implements Comparator<Edge>
    {    
        int origen;     //Nodo origen    
        int destino;    //Nodo destino    
        int peso;       //Peso entre el nodo origen y destino    
        Edge(){}
	    
        //Comparador por peso, me servira al momento de ordenar y lo realizara en orden ascendente	    	
        @Override	
        public int compare(Edge e1 , Edge e2 ) 
        {		
            return e1.peso - e2.peso;
        }
    };
    
    //--------------------------------------------------------------------------
    //                     IMPLEMENTACION KRUSKAL
    //--------------------------------------------------------------------------

    static void calcularKruskal()
    {    
        int origen , destino , peso;    
        int total = 0;          //Peso total del MST    
        int numLados = 0;     //Numero de lados del MST
	        
        MakeSet( V );           //Inicializamos cada componente    
        Arrays.sort( arista , 0 , E , new Edge() );//Ordenamos los lados por su comparador
	    
        for( int i = 0 ; i < E ; ++i )
        {   
            //Recorremos los lados ya ordenadas por peso    
            origen = arista[ i ].origen;    //Nodo origen del lado actual   
            destino = arista[ i ].destino;  //Nodo destino del lado actual    
            peso = arista[ i ].peso;        //Peso del lado actual
    
            //VERIFICAMOS SI PERTENECE AL MISMO ARBOL      
            if( !sameComponent( origen , destino ) )
            {  
                //Evito ciclos    
                total += peso;//Incremento el peso total 
                MST[ numLados++ ] = arista[ i ];//Agrego a la arista actual     
                Union( origen , destino );  //Union de ambas componentes en una sola     
            }   
        }
    
        //VALIDACION DE ENTRADA DE GRAFO   
        if( V - 1 != numLados )
        {       
            System.out.println("El grafo ingresado es invalido. El grafo debe ser conexo.");      
            return;    
        }
                
        //IMPRESION DE RESULTADO
        System.out.println("**********************************************************************");
        System.out.println("El arbol de minima expansion encontrado contiene las siguientes lados: "); 
        System.out.println("**********************************************************************");   
        for( int i = 0 ; i < numLados ; ++i )
        {    
            System.out.printf("( %d , %d ) : %d\n" , MST[ i ].origen , MST[ i ].destino , MST[ i ].peso ); //( vertice u , vertice v ) : peso
        }
        
        System.out.println("**********************************************************************");
        System.out.printf( "El costo minimo total correspondiente al arbol encontrado es de: %d" , total );
        System.out.println("\n**********************************************************************");
    }
    
    //--------------------------------------------------------------------------
}
