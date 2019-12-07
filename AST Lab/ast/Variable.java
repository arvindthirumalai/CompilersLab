package ast;
import Environment.Environment;
/**
 * Represents a variable
 * 
 * @author Arvind Thirumalai 
 * @version 3/22/18
 */
public class Variable extends Expression
{
    // instance variables - replace the example below with your own
    private String name;
    /**
     * Constructs a variable
     * @param n is the string name of the variable
     */
    public Variable(String n)
    {
        name = n;
    }

    /**
     * Evaluates a variable
     * @return the value of the evaluated expression
     * @param env is the environment
     */
    public int eval(Environment env)
    {
        return env.getVariable(name);
    }
}
