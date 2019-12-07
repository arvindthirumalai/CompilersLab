package ast;
import Environment.Environment;

/**
 * Represents an If statement
 * 
 * @author Arvind Thirumalai
 * @version 3/25/18
 */
public class If extends Statement
{
    // instance variables - replace the example below with your own
    private Condition cond;
    private Statement st;

    /**
     * Constructor for objects of class If
     * @param con is the conditional statement in the if statement
     * @param s is the statement that is executed if the conditional is true
     */
    public If(Condition con, Statement s)
    {
        // initialise instance variables
        cond = con;
        st = s;
    }

    /**
     * Executes an if statement
     * @param env is the environment in which this statement is executed in
     */
    public void exec(Environment env)
    {
        if(cond.eval(env) == 0)
        {
            st.exec(env);
        }
    }

}
