package ast;
import java.util.*;
import java.io.*;
import Environment.Environment;
/**
 * Represents a block of statements
 * 
 * @author Arvind Thirumalai 
 * @version 3/22/18
 */
public class Block extends Statement
{
    private List<Statement> stmts;
    /**
     * Constructs a Block of statements
     * @param st is a list of statements
     */
    public Block(List<Statement> st)
    {
        stmts = st;
    }

    /**
     * Executes a block of statements
     * @param env is the environment in which this statement is executed in
     */
    public void exec(Environment env)
    {
        for(int k = 0; k < stmts.size(); k++)
        {
            stmts.get(k).exec(env);
        }
    }
}
