package scanner;

import java.io.*;
/**
 * Scanner is a simple scanner for Compilers and Interpreters (2014-2015) lab exercise 1
 * @author Arvind Thirumalai
 *  
 * Usage: Scanner lex = new Scanner(input_string); lex.hasNext(), lex.nextToken();
 * @version February 22, 2018
 * 
 *
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    /**
     * Scanner constructor for construction of a scanner that 
     * uses an InputStream object for input.  
     * Usage: 
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }

    /**
     * Scanner constructor for constructing a scanner that 
     * scans a given input string.  It sets the end-of-file flag an then reads
     * the first character of the input string into the instance field currentChar.
     * Usage: Scanner lex = new Scanner(input_string);
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
    }

    /**
     * Method: getNextChar
     * Sets currentChar to the next character of the input string. 
     * If the end of the input string has been reached, eof is set 
     * to true. Furthermore, if an error is caught, the program is aborted.
     * 
     */
    private void getNextChar()
    {
        if(hasNext())
        {
            int read = 0;
            try
            {
                read = in.read();                
            }
            catch (IOException e)
            {
                System.exit(-1);
            }
            finally
            {
                if(read == -1)
                    eof = true;
                else
                {
                    currentChar = (char) read; 
                }
            }
        }
    }

    /**
     * Method: isDigit
     * Checks to see if the parameter is a number between 0 and 9.
     * @param ch is the character that is checked. 
     * @return true if the character is a digit; otherwise,
     *         false.
     */
    public static boolean isDigit(char ch)
    {
        int ascii = (int)ch;
        if(ascii >= 48 && ascii <= 57)
        {
            return true;
        }
        return false;
    }

    /**
     * Method: isLetter
     * Checks to see if the parameter is a valid letter. Valid letters 
     * include lowercase and uppercase.
     * @param ch is the character that is checked
     * @return true if the letter is valid; otherwise,
     *         false.
     */
    public static boolean isLetter(char ch)
    {
        int ascii = (int)ch;
        if((ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122))
        {
            return true;
        }
        return false;
    }

    /**
     * Method: isWhiteSpace
     * Checks to see if the character is a space or an escape sequence
     * @param ch the character to be checked
     * @return true if the character is a space or escape sequence; otherwise,
     *         false.
     */
    public static boolean isWhiteSpace(char ch)
    {
        if(ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n')
        {
            return true;
        }
        return false;
    }

    /**
     * Method: eat
     * If the parameter is equal to the currentCharacter, the next character 
     * is retrieved from the input stream. Otherwise, a ScanErrorException will be thrown.
     * @param expected the character to be checked if it's equal to the current character
     * @throws ScanErrorException if an illegal character is found
     */

    private void eat(char expected) throws ScanErrorException
    {
        if(expected == currentChar)
        {
            getNextChar();
        }
        else
        {
            String error = "Illegal character - expected " + currentChar + " and found " + expected;
            throw new ScanErrorException(error);
        }
    }

    /**
     * Method: hasNext
     * Checks to see if we have reached the end of the input stream. 
     * @return true if the end of the file has not been reached; otherwise,
     *         false.
     */
    public boolean hasNext()
    {
        return !eof;
    }

    /**
     * Method: scanIdentifier
     * Scans characters from the input stream to make sure they follow the 
     * regular expressions that define an identifier.
     * @return a string representing the lexeme; otherwise an error is thrown. 
     * @throws ScanErrorException if an illegal character is found
     */
    private String scanIdentifier() throws ScanErrorException
    {
        String s = String.valueOf(currentChar);
        if(isLetter(currentChar))
        {
            eat(currentChar);
            while(isLetter(currentChar) || isDigit(currentChar))
            {
                s += String.valueOf(currentChar);
                eat(currentChar);
            }
            return s;
        }
        else
        {
            String err = "Illegal character - expected a letter and found" + " " + s;
            throw new ScanErrorException(err);
        }
    }

    /**
     * Method: scanNumber
     * Scans characters from the input stream to check if it constitutes a number
     * @return a string representing the number.
     * @throws ScanErrorException if an illegal character is found in the eat method.
     */
    private String scanNumber() throws ScanErrorException
    {
        String s = "";
        while(hasNext() && isDigit(currentChar))
        {
            s += String.valueOf(currentChar);
            eat(currentChar);
        }
        return s;
    }

    /**
     * Method: scanOperand
     * Scans characters from the input stream and checks if 
     * it constitutes an operand.
     * @return the operand; otherwise if an operand is not recognized, 
     * an error is thrown.
     * @throws ScanErrorException if an illegal character is found.
     */
    private String scanOperand() throws ScanErrorException
    {
        String s = String.valueOf(currentChar);
        if(isOperand(currentChar))
        {
            eat(currentChar);
            return s;
        }
        else if(currentChar == ':')
        {
            eat(currentChar);
            if(currentChar == '=')
            {
                eat(currentChar);
                return ":=";
            }
        }
        else if(currentChar == '<')
        {
            eat(currentChar);
            if(currentChar == '=')
            {
                eat(currentChar);
                return "<=";
            }
        }
        else if(currentChar == '>')
        {
            eat(currentChar);
            if(currentChar == '=')
            {
                eat(currentChar);
                return ">=";
            }
        }
        String err = "Illegal character - expected an operand and found" + " " + s;
        throw new ScanErrorException(err);
    }

    /**
     * Method: isOperand
     * Checks to see if the character is an operand
     * @param ch is the character to check
     * @return true if ch is an operand; otherwise,
     *         false.
     */
    public static boolean isOperand(char ch)
    {
        if(ch == '%' || ch == '+' || ch == '-' || ch == '*' || 
            ch == '=' || ch == '/' || ch == '(' || ch == ')' || 
            ch == ';' || ch == '.'|| ch == '>' || ch == '<')
        {
            return true;
        }
        return false;
    }

    /**
     * Method: nextToken
     * Scans through every character in the input stream to determine whether the 
     * characters constitute an identifier, digit, or operand. 
     * @return a string that is either the number, letter or operand. "END" is returned 
     * if the end of the input stream has been reached.
     */
    public String nextToken() throws ScanErrorException
    {
        if(eof)
        {
            return ".";
        }
        while(hasNext() && isWhiteSpace(currentChar))
        {  
            eat(currentChar);
        } 
        if(isDigit(currentChar))
        {
            return scanNumber();
        }
        else if(isLetter(currentChar))
        {
            return scanIdentifier();
        }
        else
        {
            return scanOperand();
        }

    } 

}
