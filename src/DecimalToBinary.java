import java.util.Scanner;

/**
 *
 * @author Guillermo Grande & Vincent Perez (10/sep/2015)
 */

public class DecimalToBinary 
{
    public static void main(String[] args) 
    {   
        
        DecimalToBinary dtb = new DecimalToBinary();
        CDecimal cDec = new CDecimal();
        CBinary cBin = new CBinary();
        
        int[] vectorNumerico = solicitarVector();
        cDec.convertVecToInt(vectorNumerico);
        cBin.convertToBinary(cDec.getDecimal());
        dtb.mostrarResultado(cDec.getDecimal(), cBin.getBinary());
    }
    
    public static int[] solicitarVector()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Introduzaca el tamaño del vector numerico: ");
        int vecSize = input.nextInt();
        
        int[] V = new int[vecSize];
        
        for (int i = 0; i < V.length; i++)
        {
            System.out.println("Introduzca valor en la posicion " + i);
            int numVec = input.nextInt();
            V[i] = numVec;
        }
         
        return V;
    }
    
    public void mostrarResultado(int numeroDecimal, String numeroBinario)
    {
        System.out.println("La cifra es " + numeroDecimal + " y su valor binario es " + numeroBinario);
    }
}
