package ast;
import Environment.Environment;

/**
 * Represents a conditional statement
 * 
 * @author Arvind Thirumalai
 * @version 3/25/18
 */
public class Condition extends Expression
{
    // instance variables - replace the example below with your own
    private Expression exp1;
    private String relop;
    private Expression exp2;

    /**
     * Constructor for objects of class Condition
     * @param e1 is the first part of the conditional statement
     * @param e2 is the second part of the conditional statement
     * @param r is the comparative operator
     */
    public Condition(Expression e1, Expression e2, String r)
    {
        // initialise instance variables
        exp1 = e1;
        exp2 = e2;
        relop = r;
    }

    /**
     * Evaluates a condition
     * @param env is the environment
     * @return 0 if true, 1 if false.
     */
    public int eval(Environment env)
    {
        int v1 = exp1.eval(env);
        int v2 = exp2.eval(env);
        boolean st = false;
        if(relop.equals("<="))
        {
            if(v1 <= v2)
            {
                return 0; //cond = true;
            }
            return 1;
        }
        else if(relop.equals("="))
        {
            if(v1 == v2)
            {
                return 0; //cond = true;
            }
            return 1;
        }
        else if(relop.equals(">"))
        {
            if(v1 > v2)
            {
                return 0; //cond = true;
            }
            return 1;
        }
        else if(relop.equals(">="))
        {
            if(v1 >= v2)
            {
                return 0; //cond = true;
            }
            return 1;
        }
        else if(relop.equals("<"))
        {
            if(v1 < v2)
            {
                return 0; //cond = true;
            }
            return 1;
        }
        else if(relop.equals("<>"))
        {
            if(v1 != v2)
            {
                return 0; //cond = true;
            }
            return 1;       
        }
        return 1;
    }
}
