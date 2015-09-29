/**
 *
 * @author Guillermo Grande
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal 
{
    //**************************************************************************
    //                              VARIABLES
    //**************************************************************************
    
    static final int MAX = 999;  //maximo numero de vértices
    static int V , E;      //numero de vertices y aristas
    static Edge arista[] = new Edge[ MAX ];//Arreglo de aristas para el uso en kruskal
    static Edge MST[] = new Edge[ MAX ];//Arreglo de aristas del MST encontrado
    
    //**************************************************************************
    //                               MAIN
    //**************************************************************************
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
		
	//Realizamos el ingreso del grafo, almacenando las aristas en un arreglo con los datos respectivos
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
    
    //**************************************************************************
    //                      METODOS DEL UNION-FIND
    //**************************************************************************
    
    static int padre[] = new int[ MAX ];  //Este arreglo contiene el padre del i-esimo nodo

    //Método de inicialización
    static void MakeSet( int n )
    {    
        for( int i = 1 ; i <= n ; ++i ) padre[ i ] = i;
    }
	
    //Método para encontrar la raiz del vértice actual X
    static int Find( int x )
    {    
        return ( x == padre[ x ] ) ? x : ( padre[ x ] = Find( padre[ x ] ) );
    }
	
    //Método para unir 2 componentes conexas
    static void Union( int x , int y )
    {    
        padre[ Find( x ) ] = Find( y );
    }
	
    //Método que me determina si 2 vértices estan o no en la misma componente conexa	
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
    
    //**************************************************************************
    //                         ORDENAR LADOS
    //**************************************************************************
	
    static class Edge implements Comparator<Edge>
    {    
        int origen;     //Vértice origen    
        int destino;    //Vértice destino    
        int peso;       //Peso entre el vértice origen y destino    
        Edge(){}
	    
        //Comparador por peso, me servira al momento de ordenar y lo realizara en orden ascendente	    	
        @Override	
        public int compare(Edge e1 , Edge e2 ) 
        {		
            return e1.peso - e2.peso;
        }
    };
    
    //**************************************************************************
    //                     IMPLEMENTACION KRUSKAL
    //**************************************************************************

    static void calcularKruskal()
    {    
        int origen , destino , peso;    
        int total = 0;          //Peso total del MST    
        int numAristas = 0;     //Numero de Aristas del MST
	        
        MakeSet( V );           //Inicializamos cada componente    
        Arrays.sort( arista , 0 , E , new Edge() );//Ordenamos las aristas por su comparador
	    
        for( int i = 0 ; i < E ; ++i )
        {   
            //Recorremos las aristas ya ordenadas por peso    
            origen = arista[ i ].origen;    //Vértice origen de la arista actual   
            destino = arista[ i ].destino;  //Vértice destino de la arista actual    
            peso = arista[ i ].peso;        //Peso de la arista actual
    
            //VERIFICAMOS SI PERTENECE AL MISMO ARBOL      
            if( !sameComponent( origen , destino ) )
            {  
                //Evito ciclos    
                total += peso;//Incremento el peso total 
                MST[ numAristas++ ] = arista[ i ];//Agrego a la arista actual     
                Union( origen , destino );  //Union de ambas componentes en una sola     
            }   
        }
    
        //VALIDACION DE ENTRADA DE GRAFO   
        if( V - 1 != numAristas )
        {       
            System.out.println("El grafo ingrsado es invalido. El grafo debe ser conexo.");      
            return;    
        }
        
        //IMPRESION DE RESULTADO
        System.out.println("**********************************************************************");
        System.out.println("El arbol de minima expansion encontrado contiene las siguientes lados."); 
        System.out.println("**********************************************************************");   
        for( int i = 0 ; i < numAristas ; ++i )
        {    
            System.out.printf("( %d , %d ) : %d\n" , MST[ i ].origen , MST[ i ].destino , MST[ i ].peso ); //( vertice u , vertice v ) : peso
        }
        
        System.out.println("**********************************************************************");
        System.out.printf( "El costo minimo total correspondiente al arbol encontrado es de: %d" , total );
        System.out.println("\n**********************************************************************");
    }
}
