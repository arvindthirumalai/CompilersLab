package parser;
import scanner.Scanner;
import scanner.ScanErrorException;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;
import ast.Writeln;
import ast.BinOp;
import ast.Number;
import ast.Variable;
import ast.Assignment;
import ast.Block;
import ast.Statement;
import ast.Expression;
import ast.Condition;
import ast.While;
import ast.If;

import Environment.Environment;
/**
 * Parses pascal statements
 * 
 * @author Arvind Thirumalai
 * @version 3/11/18
 */
public class Parser
{
    // instance variables - replace the example below with your own
    private Scanner sc;
    private String currentTok;
    private HashMap<String, Integer> hm = new HashMap();
    /**
     * Constructor for objects of class Parser
     * @param s is the scanner 
     */
    public Parser(Scanner s) throws ScanErrorException
    {
        // initialise instance variables
        sc = s;
        currentTok = sc.nextToken();
    }

    /**
     * Eats a string by calling the scanner class's eat method.
     * @param expected is the string to be eaten
     * @throws ScanErrorException if the scanner's eat method throws it.
     */
    private void eat(String expected) throws ScanErrorException
    {
        if(expected.equals(currentTok))
        {   
            currentTok = sc.nextToken();
        }
        else
        {
            throw new IllegalArgumentException("Expected" + expected + " but found " + currentTok);
        }
    }

    /**
     * precondition: current tokens begin an IF statement
     * postcondition: all tokens in statement have been eaten; 
     *                current token is first one after the IF statement
     * @throws ScanErrorException if the eat method throws it.  
     */
    private void parseIF() throws ScanErrorException
    {
        eat("IF");
        while(!currentTok.equals(";"))
        {
            eat(currentTok);
        }
        eat(";");
    }

    /**
     * precondition: current tokens is an integer
     * postcondition: number token has been eaten
     * @return the value of the parsed integer
     * @throws ScanErrorException if the eat method throws it.  
     */
    private Number parseNumber() throws ScanErrorException
    {
        int num = Integer.parseInt(currentTok);
        eat(currentTok);
        return new Number(num);
    }

    /**
     * method: parseStatement
     * parses a WRITELN, BEGIN, and assignment statement using the method parseFactor
     * @return the parsed statement
     * @throws ScanErrorException if the eat method throws it.  
     */
    public Statement parseStatement() throws ScanErrorException
    {
        if(currentTok.equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            Expression exp = parseExpression();
            eat(")");
            eat(";");
            return new Writeln(exp);            
        }
        else if(currentTok.equals("BEGIN"))
        {
            return parseStatements();
        }

        else if(currentTok.equals("IF"))
        {
            eat("IF");
            Expression exp1 = parseExpression();
            String r = currentTok;
            eat(currentTok);
            Expression exp2 = parseExpression();
            Condition c = new Condition(exp1, exp2, r);
            eat("THEN");
            Statement s = parseStatement();
            If i = new If(c, s);
            return i;
        }
        else if(currentTok.equals("WHILE"))
        {
            eat("WHILE");
            Expression exp1 = parseExpression();
            String r = currentTok;
            eat(currentTok);
            Expression exp2 = parseExpression();
            Condition c = new Condition(exp1, exp2, r);
            eat("DO");
            Statement s = parseStatements();
            While w = new While(c, s);
            return w;
        }
        else if(Scanner.isLetter(currentTok.charAt(0)))
        {
            String c = currentTok;
            eat(currentTok);
            eat(":=");
            Expression exp = parseExpression();
            eat(";");
            return new Assignment(c, exp);
        }
        return null;
    }
    
    public void parseProgram() throws ScanErrorException
    {
        while(currentTok.equals("PROCEDURE"))
        {
            eat("PROCEDURE");
            String id = currentTok;
            eat(id);
            eat("(");
            ArrayList<String> params = new ArrayList<String>();
            while(!currentTok.equals(")"))
            {
                String argument = currentTok;
                eat(argument);
                params.add(argument);
                if(currentTok.equals(","))
                {
                    eat(",");
                }
            }
            eat(")");
            eat(";");
            Statement s = parseStatement();
            parseProgram();
            return;
        }
        Statement s = parseStatement();
    }

    /**
     * Parses a block of statements
     * @return a statement to be stored in a block
     * @throws ScanErrorException if the eat method throws it.]
     */
    public Statement parseStatements() throws ScanErrorException
    {
        List<Statement> st = new ArrayList<Statement>();
        eat("BEGIN");
        while(!currentTok.equals("END"))
        {
            st.add(parseStatement());
        }
        eat("END");
        eat(";");
        return new Block(st);
    }

    /**
     * Tests the parser class
     * @param args is the argument
     */
    public static void main(String []args) throws ScanErrorException, FileNotFoundException
    {
        String a = "/Users/arvindthirumalai/Desktop/Programming Projects";
        String b1 = "/AST Lab/parser/parserTest4.txt";
        FileInputStream stream1 = new FileInputStream(new File(a + b1));
        Environment env = new Environment();
        Scanner sc = new Scanner(stream1);
        Parser p = new Parser(sc);
        while(sc.hasNext())
        {
            Block b = (Block) p.parseStatements();
            b.exec(env);
        }
    }

    /**
     * Method: parseFactor
     * Recursively parses what is inside the opening and ending parenthesis of a WRITELN statement
     * @return the value of the factor that is parsed
     * @throws ScanErrorException if the eat method throws it.  
     */
    public Expression parseFactor() throws ScanErrorException
    {
        if(currentTok.equals("-"))
        {
            eat("-");
            Number num = (Number) parseFactor();
            return new BinOp("-", new Number(0), num);
        }
        else if(currentTok.equals("("))
        {
            eat("(");
            Expression exp = parseExpression();
            eat(")");
            return exp;
        }
        else if(Scanner.isDigit(currentTok.charAt(0)))
        {
            Number num = parseNumber();
            return num;
        }
        else 
        {
            //int num = hm.get(currentTok);
            String c = currentTok;
            eat(currentTok);
            return new Variable(c);
        }

    }

    /**
     * Method: parseTerm
     * Uses a while loop to parses a term as long as a factor is being multiplied or 
     * divided with an another factor.
     * @return an integer representing the value of the parsed term. 
     * @throws ScanErrorException if the eat method throws it.  
     */
    public Expression parseTerm() throws ScanErrorException
    {
        Expression res = parseFactor();       
        while(currentTok.equals("*") || currentTok.equals("/"))
        {
            if(currentTok.equals("*"))
            {
                eat("*");
                Expression oth = parseTerm();
                res = new BinOp("*", res, oth);
            }
            else if(currentTok.equals("/"))
            {
                eat("/");
                Expression oth = parseTerm();
                res = new BinOp("*", res, oth);
            }
        }
        return res;
    }

    /**
     * Method: parseExpression
     * Uses a while loop to parse an expression as long as a term is 
     * added or subtracted with another term
     * @return an integer representing the value of the parsed expression
     * @throws ScanErrorException if the eat method throws it.  
     */
    public Expression parseExpression() throws ScanErrorException
    {
        Expression res = parseTerm();
        while(currentTok.equals("+") || currentTok.equals("-"))
        {
            if(currentTok.equals("+"))
            {
                eat("+");
                Expression oth = parseExpression();
                res = new BinOp("+", res, oth);
            }
            else if(currentTok.equals("-"))
            {
                eat("-");
                Expression oth = parseExpression();
                res = new BinOp("-", res, oth);
            }
        }
        return res;
    }

}
