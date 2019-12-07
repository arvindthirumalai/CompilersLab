package Environment;
import ast.ProcedureDeclaration;
import java.util.HashMap;
/**
 * Represents an Environment
 * 
 * @author Arvind Thirumalai 
 * @version March 25, 2018
 */
public class Environment
{
    // instance variables - replace the example below with your own
    private HashMap<String, Integer> hm; 
    private HashMap<String,ProcedureDeclaration> procedures;
    /**
     * Constructor for objects of class Environment
     */
    public Environment()
    {
        // initialise instance variables
        hm = new HashMap<String, Integer>();
        procedures = new HashMap<String, ProcedureDeclaration>();
    }
    /**
     * Gets the variable
     * @param v is the string representing the variable
     * @return the variable
     */
    public int getVariable(String v)
    {
        return hm.get(v);
    }
    /**
     * Sets the variable
     * @param var is the string representing the variable
     * @param v is the integer the variable is set to
     */
    public void setVariable(String var, int v)
    {
        // put your code here
        hm.put(var, v);
    }
    public void setProcedure(String var, ProcedureDeclaration pd)
    {
        procedures.put(var, pd);
    }
    public ProcedureDeclaration getProcedure(String v)
    {
        return procedures.get(v);
    }
}
