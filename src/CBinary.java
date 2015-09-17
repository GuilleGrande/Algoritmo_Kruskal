/**
 *
 * @author Guillermo Grande y Vincent Perez.
 */

public class CBinary 
{
    //Variable
    public String binary;

    //Constructor
    public CBinary(){}
    public CBinary(String binary)
    {
        this.binary = binary;
    }

    //Getter
    public String getBinary()
    {
        return binary;
    }
    
    //Setter
    public void setBinary(String binary)
    {
        this.binary = binary;
    }
    
    public void convertToBinary(int decimal)
    {
        binary = Integer.toBinaryString(decimal);
        setBinary(binary);
    }
}
