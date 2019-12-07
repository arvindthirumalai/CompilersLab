package ast;
import Environment.Environment;
/**
 * Represents a Binary Operation
 * 
 * @author Arvind Thirumalai
 * @version 3/22/18
 */
public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;
    /**
     * Represents a Binary operation
     * @param e1 is the first expression
     * @param e2 is the second expression
     * @param o is the string that holds the operator
     * 
     */
    public BinOp(String o, Expression e1, Expression e2)
    {
        op=o;
        exp1 = e1;
        exp2 = e2;
    }

    /**
     * Evaluates a Binary Operation
     * @return an integer representing what the Expression evaluates to
     * @param env is the environment in which this statement is evaluated in
     */
    public int eval(Environment env)
    {
        if(op.equals("-"))
        {
            return exp1.eval(env) - exp2.eval(env);
        }
        else if(op.equals("+"))
        {
            return exp1.eval(env) + exp2.eval(env);
        }
        else if(op.equals("*"))
        {
            return (exp1.eval(env) * exp2.eval(env));
        }
        else if(op.equals("/"))
        {
            return (exp1.eval(env) / exp2.eval(env));
        }
        return -1;
    }
}
