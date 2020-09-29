package com.test;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Question1 {

    /*
     * Complete the pageCount function below.
     */
    static int pageCount(int n, int p) {
        
    	if (n%2 == 0)
    		n++;
    	int pageL = p/2;
    	int pageR = (n-p)/2;
    	
    	return Math.min(pageL, pageR);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    	System.out.print("Total Pages: ");
        int n = scanner.nextInt();
        
        System.out.print("Page: ");
        int p = scanner.nextInt();

        int result = pageCount(n, p);
        
        System.out.println(result +" turns.");

       // bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}
