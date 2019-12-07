package scanner;
import java.io.FileNotFoundException;
import java.io.*;
/**
 * Tests the scanner class using an input file.
 * 
 * @author Arvind Thirumalai
 * @version 1/31/18
 */
public class ScannerTester
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for Objects of Class ScannerTester
     */
    public ScannerTester()
    {
        // initialise instance variables
    }

    /**
     * Uses an input file to test the scanner. 
     * @param args is the argument
     */
    public static void main(String[] args) throws ScanErrorException, FileNotFoundException
    {
        String a = "/Users/arvindthirumalai/Desktop/Programming Projects/";
        String b = "ParserLabFinal/scanner/scannerTestAdvanced.txt";
        File tester = new File(a + b);
        FileInputStream stream = new FileInputStream(tester);
        Scanner sc = new Scanner(stream);
        String res = sc.nextToken();
        while(sc.hasNext())
        {
            System.out.println(res);
            res = sc.nextToken();
        }
    }
}
