package ast;
import Environment.Environment;

/**
 * Represents a while loop
 * 
 * @author Arvind Thirumalai
 * @version 3/25/18
 */
public class While extends Statement
{ 
    // instance variables - replace the example below with your own
    private Condition c;
    private Statement s;

    /**
     * Constructor for objects of class While
     * @param cond is the conditional statement
     * @param st is the statement that is executed if the condition is true
     */
    public While(Condition cond, Statement st)
    {
        // initialise instance variables
        c = cond;
        s = st;
    }

    /**
     * Executes a while loop
     * @param env is the environment in which this statement is executed in
     */
    public void exec(Environment env)
    {
        while(c.eval(env) == 0)
        {
            s.exec(env);
        }
    }

}
