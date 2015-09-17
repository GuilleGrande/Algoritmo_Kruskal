/**
 *
 * @author Guillermo Grande y Vincent Perez.
 */

public class CDecimal
{
    //Variable
    public int decimal;

    //Constructor
    public CDecimal() {}
    public CDecimal(int decimal)
    {
        this.decimal = decimal;
    }

    //Getter
    public int getDecimal() 
    {
        return decimal;
    }

    //Setter
    public void setDecimal(int decimal)
    {
        this.decimal = decimal;
    }
    
    public void convertVecToInt(int[] V)
    {
        String str_num = "";
        
        for (int i = 0; i < V.length; i++) 
        {
            str_num = str_num + Integer.toString(V[i]);
        }
  
        setDecimal(Integer.parseInt(str_num));
    }
}
