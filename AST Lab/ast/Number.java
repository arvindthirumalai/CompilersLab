package ast;
import Environment.Environment;

/**
 * Represents a number
 * 
 * @author Arvind Thirumalai
 * @version 3/22/18
 */
public class Number extends Expression
{
    // instance variables - replace the example below with your own
    private int val;
    /**
     * Constructs a number
     * @param v is the value of the number
     */
    public Number(int v)
    {
        val = v;
    }
    
    /**
     * Gets and returns the value of the number
     * @return the value
     */
    public int getValue()
    {
        return val;
    }

    /**
     * Evaluates a number
     * @return the value of the evaluated expression
     * @param env is the environment
     */
    public int eval(Environment env)
    {
        return val;
    }
}
