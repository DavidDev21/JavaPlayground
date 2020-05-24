
/*
    Doing basic file io stuff
    Note: the relative path for this is from where IntellJ is running the java programs.
    Users/David/Itellj/JavaPlayground (something like that)
 */


import java.util.Scanner;
import java.io.*;

public class BasicIO {

    public static void main(String[] args) throws IOException{
        String srcPathname = "src/test.txt";
        Scanner fileScanner = null;
        BufferedWriter outWriter = null;

        try{
            fileScanner = new Scanner(new BufferedReader(new FileReader(srcPathname)));

            while(fileScanner.hasNextLine())
            {
                System.out.println(fileScanner.nextLine());
            }

            // Write back out to the file
            String outputMessage = "THIS IS GOING OUT FROM THE PROGRAM\n";
            outWriter = new BufferedWriter(new FileWriter(srcPathname));

            outWriter.write(outputMessage);
            System.out.println("I AM DONE");
        }
        finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
//
//            if (outWriter != null) {
//                outWriter.close();
//            }
        }

    }
}
