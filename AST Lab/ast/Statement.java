package ast;
import Environment.Environment;

/**
 * Abstract class for a statement
 * 
 * @author Arvind Thirumalai
 * @version 3/22/18
 */
public abstract class Statement
{
    /**
     * Evaluates an statement in the given environment
     * @param env is the environment
     */
    public abstract void exec(Environment env);
}
