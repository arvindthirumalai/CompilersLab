package ast;
import java.util.ArrayList;
import Environment.Environment;

/**
 * Represents a procedure declaration
 * 
 * @author Arvind Thirumalai 
 * @version 4/29/18
 */
public class ProcedureDeclaration
{
    // instance variables - replace the example below with your own
    private String id;
	private Statement stmt;
	private ArrayList<String> vari;
	
	/**
	 * Constructor
	 */
	public ProcedureDeclaration(String id, ArrayList<String> arr, Statement stmt)
	{
		vari = arr;		
		this.id = id;
		this.stmt = stmt;
	}
	
	public ArrayList<String> getVariables()
	{
		return vari;
	}
	
	public Statement getStatement()
	{
		return stmt;
	}
	
	/**
	 * Executes the declaration
	 */
	public void exec(Environment env)
	{
		env.setProcedure(id,this);
	}
}
