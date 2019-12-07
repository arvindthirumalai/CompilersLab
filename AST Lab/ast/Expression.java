package ast;
import Environment.Environment;

/**
 * Abstract class for an expression
 * 
 * @author Arvind Thirumalai 
 * @version 3/22/18
 */
public abstract class Expression
{   
    /**
     * Evaluates an expression in the given environment
     * @return an integer representing the value of the expression after it has been evaluated
     * @param env is the environment 
     */
    public abstract int eval(Environment env);
}
