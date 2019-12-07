package ast;

import java.util.ArrayList;
import Environment.Environment;
/**
 * Keeps track of list of procedures
 * 
 * @author Arvind Thirumalai 
 * @version 4/29/18
 */
public class Program extends Statement
{
    private ArrayList<ProcedureDeclaration> procedures;
    private Statement stmt;

    public Program()
    {
        procedures = new ArrayList<ProcedureDeclaration>();
    }

    public void addStatement(Statement statement)
    {
        stmt = statement;
    }

    public void addProcedure(ProcedureDeclaration procedure)
    {
        procedures.add(procedure);
    }

    public void exec(Environment env)
    {
        for(ProcedureDeclaration proc: procedures)
        {
            proc.exec(env);
        }
        stmt.exec(env);
    }
}
