package ast;

import Environment.Environment;
/**
 * Represents WRITELN
 * 
 * @author Arvind Thirumalai
 * @version 3/22/18
 */
public class Writeln extends Statement
{
    // instance variables - replace the example below with your own
    private Expression exp;
    /**
     * Constructs a writeln statement
     * @param exp is the expression
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }

    /**
     * Executes the writeln statement
     * @param env is the environment
     */
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }
}
