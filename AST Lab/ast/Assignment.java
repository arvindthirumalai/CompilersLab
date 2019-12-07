package ast;
import Environment.Environment;
/** 
 * Represents an assignment statement
 * @author Arvind Thirumalai
 * @version 3/22/18
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;
    /**
     * Constructor
     * @param v is the string to represent the expression
     * @param e is the expression 
     */
    public Assignment(String v, Expression e)
    {
        var = v;
        exp = e;
    }

    /**
     * Executes an assignment statement
     * @param env is the environment
     */
    public void exec(Environment env)
    {
        env.setVariable(var, exp.eval(env)); 
    }
}
